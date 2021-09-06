package br.com.keyworks.services;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.inject.Inject;
import org.primefaces.model.UploadedFile;
import br.com.keyworks.enumeracoes.MesEnum;
import br.com.keyworks.enumeracoes.PagoPendenteEnum;
import br.com.keyworks.enumeracoes.SimNaoEnum;
import br.com.keyworks.exceptions.MensalidadeExistenteException;
import br.com.keyworks.exceptions.UsuarioNaoEncontradoException;
import br.com.keyworks.model.entities.administracao.Mensalidade;
import br.com.keyworks.model.entities.administracao.Usuario;
import br.com.keyworks.repository.MensalidadeRepository;
import br.com.keyworks.repository.UsuarioRepository;
import br.com.keyworks.util.ConverterNomeUtil;
import br.com.keyworks.view.componentes.GridLazyLoaderDTO;
import br.com.keyworks.view.componentes.PagedResult;

@Stateless
public class MensalidadeService {

	@Inject
	private UsuarioRepository usuarioRepo;

	@Inject
	private MensalidadeRepository mensalidadeRepo;

	public List<Mensalidade> getMensalidadeByUsuario(String nome) {
		try {
			Usuario usuario = usuarioRepo.getUsuario(nome);
			return mensalidadeRepo.getMensalidadesByIdUsuario(usuario.getId());
		} catch (UsuarioNaoEncontradoException e) {

		}
		return null;
	}

	public List<Mensalidade> getMensalidades() {
		return mensalidadeRepo.getMensalidades();
	}

	public String formatarData(Date data) {
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		return format.format(data);
	}

	public String formatarValor(Double valor) {
		DecimalFormat format = new DecimalFormat("#");
		return format.format(valor);
	}

	public void updateObservacao(Mensalidade mensalidade) {
		mensalidadeRepo.atualizar(mensalidade);
	}

	public Mensalidade salvarComprovante(Mensalidade mensalidade, UploadedFile comprovante) {
		try {
			mensalidade.setNomeComprovante(new String(comprovante.getFileName().getBytes(Charset.defaultCharset()), "UTF-8"));
		} catch (UnsupportedEncodingException e) {

		}
		mensalidade.setComprovante(comprovante.getContents());
		return mensalidadeRepo.atualizar(mensalidade);
	}

	public Long getQuantidadeUsuarios() {
		return usuarioRepo.getUsuarioCount();
	}

	public byte[] getComprovante(Integer id) {
		return mensalidadeRepo.getMensalidadeById(id).getComprovante();
	}

	public String getNomeComprovante(Integer id) {
		return mensalidadeRepo.getMensalidadeById(id).getNomeComprovante();
	}

	public Mensalidade getMensalidadesById(String id) {
		return mensalidadeRepo.getMensalidadeById(Integer.parseInt(id));
	}

	public Mensalidade getMensalidadesById(Integer id) {
		return mensalidadeRepo.getMensalidadeById(id);

	}

	public void updateMensalidade(Mensalidade mensalidade) {
		mensalidadeRepo.atualizar(mensalidade);

	}

	public Mensalidade getMensalidade(Mensalidade mensalidade) {
		return mensalidadeRepo.getMensalidadeById(mensalidade.getId());
	}

	public List<String> getAnos() {
		return mensalidadeRepo.getAnos();
	}

	@SuppressWarnings("unchecked")
	public PagedResult<Mensalidade> getMensalidades(GridLazyLoaderDTO gridLazyLoaderDTO) {
		Map<String, Object> parametros = gridLazyLoaderDTO.getFilteredFilters();

		StringBuilder query = new StringBuilder("SELECT m FROM Mensalidade m");
		if (parametros.size() > 0) {
			query.append(" WHERE 1 = 1");

			if (parametros.get("pagamento") != null) {
				PagoPendenteEnum e = (PagoPendenteEnum) parametros.get("pagamento");
				parametros.remove("pagamento");
				parametros.put("pagamento", e.getId());
				query.append(" AND pagamento = :pagamento");
			}

			if (parametros.get("comprovante") != null) {
				if (parametros.get("comprovante").equals(SimNaoEnum.SIM)) {
					query.append(" AND comprovante IS NOT NULL");
				} else {
					query.append(" AND comprovante IS NULL");
				}
				parametros.remove("comprovante");
			}
			if (parametros.get("associado") != null) {
				// Caso o input text não seja preenchido o valor vem como "" e não como null, esse if previne que dê
				// erro nos parametros
				if (parametros.get("associado").equals("") || parametros.get("associado").equals("todos")) {
					parametros.remove("associado");
				} else {
					try {
						// Testa caso se o associado definiu um nome, caso não tenha definido será buscado pelo nome
						// de
						// usuario

						parametros.put("associado", usuarioRepo.getUsuario(ConverterNomeUtil.converterParaLogin((String) parametros.get("associado")))
										.getLogin());
						query.append(" AND UPPER(m.usuario.login) = UPPER(:associado)");

					} catch (NullPointerException | UsuarioNaoEncontradoException e) {
						query.append(" AND UPPER(m.usuario.nome) = UPPER(:associado)");
					} catch (ArrayIndexOutOfBoundsException e) {
						String valor = (String) parametros.get("associado");
						query.append(" AND UPPER(m.usuario.login) LIKE UPPER(:associado)");
						parametros.put("associado", "%" + valor + "%");

					}
				}
			}
			if (parametros.get("meses") != null) {

				ArrayList<MesEnum> meses = (ArrayList<MesEnum>) parametros.get("meses");

				if (meses.size() >= 1) {
					query.append(" AND  ( ");

					for (int i = 0; i < meses.size(); i++) {
						MesEnum mes = meses.get(i);

						if (i == 0) {
							query.append("MONTH(m.dataVencimento) = " + mes.getId());
						} else {
							query.append(" OR MONTH(m.dataVencimento) = " + mes.getId());
						}
					}
					query.append(" ) ");

				}
				parametros.remove("meses");
			}

			if (parametros.get("anos") != null) {

				ArrayList<String> anos = (ArrayList<String>) parametros.get("anos");

				if (anos.size() >= 1) {

					query.append(" AND  ( ");
					for (int i = 0; i < anos.size(); i++) {
						String ano = anos.get(i);

						if (i == 0) {
							query.append("YEAR(m.dataVencimento) = " + ano);
						} else {
							query.append(" OR YEAR(m.dataVencimento) = " + ano);
						}
					}
					query.append(" ) ");
				}
				parametros.remove("anos");
			}

		}

		query.append(" ORDER BY m.usuario.login ASC, m.dataVencimento ASC");

		return mensalidadeRepo.buscarMensalidadesLazy(gridLazyLoaderDTO, query.toString());
	}

	public void createMensalidade(Date data) throws MensalidadeExistenteException {

		LocalDate dataAtual = data.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

		List<Mensalidade> mensalidades = mensalidadeRepo.getMensalidades();

		List<Usuario> listaUsuarios = usuarioRepo.getUsuarios();

		List<Mensalidade> mensalidadesParaAdicionar = new ArrayList<Mensalidade>();

		for (Mensalidade mensalidade : mensalidades) {
			LocalDate dataSerTestada = mensalidade.getDataVencimento().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			if (dataSerTestada.getMonthValue() == dataAtual.getMonthValue() && dataSerTestada.getYear() == dataAtual.getYear()) {
				throw new MensalidadeExistenteException();
			}
		}

		HashMap<Integer, Usuario> idMap = new HashMap<Integer, Usuario>();

		for (int i = 0; i < listaUsuarios.size(); i++) {
			if (!(idMap.containsValue(listaUsuarios.get(i)))) {
				idMap.put(i, listaUsuarios.get(i));
			}

		}

		for (int i = 0; i < idMap.size(); i++) {
			Mensalidade mensalidade = new Mensalidade(listaUsuarios.get(i), data);
			mensalidadesParaAdicionar.add(mensalidade);

		}
		mensalidadeRepo.persistList(mensalidadesParaAdicionar);

	}

	public void createMensalidades(Usuario usuario) {
		List<Date> datas = mensalidadeRepo.getDatas();
		LocalDate startDate = usuario.getAdmissao().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate endDate = datas.get(datas.size() - 1).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate date = startDate;
		List<Mensalidade> mensalidades = new ArrayList<Mensalidade>();

		while (startDate.minusMonths(1).isBefore(endDate)) {

			for (Date d : datas) {
				LocalDate data = d.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				if (startDate.getMonthValue() == data.getMonthValue() && startDate.getYear() == data.getYear()) {
					Mensalidade mensalidade = new Mensalidade(usuario, Date.from(data.atStartOfDay(ZoneId.systemDefault()).toInstant()));
					mensalidades.add(mensalidade);
					break;
				}
			}
			startDate = startDate.plusMonths(1);
		}
		System.out.println(date.compareTo(endDate));
		System.out.println(startDate.minusMonths(1).compareTo(endDate));
		if (date.compareTo(endDate) < startDate.compareTo(endDate))
			mensalidadeRepo.persistList(mensalidades);
	}
}
