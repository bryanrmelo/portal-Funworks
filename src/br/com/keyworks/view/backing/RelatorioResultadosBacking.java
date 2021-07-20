package br.com.keyworks.view.backing;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.PageSize;
import com.lowagie.text.html.simpleparser.HTMLWorker;
import com.lowagie.text.pdf.PdfWriter;
import br.com.keyworks.business.ContaBean;
import br.com.keyworks.business.EmpresaBean;
import br.com.keyworks.business.RelatoriosBean;
import br.com.keyworks.dto.FilterRelatorioResultadosDTO;
import br.com.keyworks.dto.RelatorioDTO;
import br.com.keyworks.filter.FilterRelatorioResultados;
import br.com.keyworks.framework.faces.backing.AbstractBacking;
import br.com.keyworks.model.entities.administracao.Conta;
import br.com.keyworks.model.entities.administracao.ContaPagar;
import br.com.keyworks.model.entities.administracao.ContaReceber;
import br.com.keyworks.model.entities.administracao.Empresa;
import br.com.keyworks.util.FacesMessageUtils;
import br.com.keyworks.util.UteisStream;
import br.com.keyworks.view.componentes.GridLazyLoaderDTO;


/**
 * 
 * Foi utilizado tags de Html para construir a table do relatório.
 * Pois não foi encontrado componente para visualizar as informações contidas em um HashMap. 
 * HashMap contendo (Conta, HashMap(Mês, Valor)).
 * Utilizando o componente p:dataTable, a table se perdia ao formar colunas dinâmicas dos meses.
 *
 */

@Named("relatorioResultados")
@ViewScoped
public class RelatorioResultadosBacking extends AbstractBacking {

	private static final long serialVersionUID = 1L;
	private static final String CAMINHO_FILE_PDF = "/opt/wildfly-8.0.0.Final/file_tmp_pdf/";
	private String NOME_FILE_PDF = "pdf";

	@Inject
	private EmpresaBean empresaBusiness;

	@Inject
	private ContaBean contaBusiness;
	
	@Inject
	private RelatoriosBean relatoriosBusiness;
	
	@Inject
	private FilterRelatorioResultados filter;
	
	private List<SelectItem> listaContas;
	private List<Empresa> empresaList;
	private List<Empresa> empresasSelecionadas = new ArrayList<Empresa>();
	private List<BigDecimal> totaisReceitas = new ArrayList<BigDecimal>();
	private List<BigDecimal> totaisDespesas = new ArrayList<BigDecimal>();
	private List<BigDecimal> totaisResultados = new ArrayList<BigDecimal>();
	private List<String> listaMeses = new ArrayList<String>();
    private String[] mesesNomes = {"JAN", "FEV", "MAR", "ABR", "MAI", "JUN", "JUL", "AGO", "SET", "OUT", "NOV", "DEZ"};
    private SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
    private DecimalFormat df = new DecimalFormat ("#,##0.00", new DecimalFormatSymbols (new Locale ("pt", "BR")));  
    private StringBuilder html = new StringBuilder();
    private int sizeListMeses = 0;
    private boolean diminuirFonte;
    private boolean fileOpen;
	private Date dtInicial;
	private Date dtFinal;
	private String aviso;
    
    @PostConstruct
	private void init() {
		
    	empresaList = empresaBusiness.buscaTodasEmpresas();
		carregaContas();
		
		FacesContext fc = FacesContext.getCurrentInstance();
		String state = (String) fc.getExternalContext().getRequestParameterMap().get("state");
		if (state == null) {
			filter.setFilter(new FilterRelatorioResultadosDTO());
		}
		
		fileOpen = true;
		diminuirFonte = false;
		
	}
	
	private void carregaContas() {
		List<Conta> contaList = contaBusiness.buscaTodasContas();
		List<Conta> listPai = new ArrayList<Conta>();
		List<Conta> listFilho = new ArrayList<Conta>();
		for (Conta co : contaList) {
			if (co.getPai() == null) {
				listPai.add(co);
			} else {
				listFilho.add(co);
			}
		}
		
		listaContas = new ArrayList<SelectItem>();
		listaContas.add(addPaiDespesas());
		
		for (Conta pai : listPai) {
			List<SelectItem> item = new ArrayList<SelectItem>();
			
			SelectItemGroup group = new SelectItemGroup(pai.getDescricao());
			for (Conta filho : listFilho) {
				if (pai.getId() != null && pai.getId() == filho.getPai().getId()) {
					item.add(new SelectItem(filho, filho.getDescricao()));
				}
			}
			group.setSelectItems(item.toArray(new SelectItem[item.size()]));
			listaContas.add(group);
		}
		
	}

	private SelectItemGroup addPaiDespesas() {
		SelectItemGroup group = new SelectItemGroup("DESPESAS");
		List<SelectItem> item = new ArrayList<SelectItem>();
		group.setSelectItems(item.toArray(new SelectItem[item.size()]));
		return group;
	}

	/**
	 * Cria o relatório de resultados em html com as receitas e as despesas conforme filtro selecionado
	 * 
	 */
	public void carregaRelatorio(Boolean pdf) {
		
		html = new StringBuilder();
		totaisReceitas = new ArrayList<BigDecimal>();
		totaisDespesas = new ArrayList<BigDecimal>();
		totaisResultados = new ArrayList<BigDecimal>();
		df.setParseBigDecimal(true);  
		
		if (filterDataInicial()==null || filterDataFinal()==null) {
			FacesMessageUtils.addErrorMessage("Selecione um período");
			aviso = "Selecione um período!";
			fileOpen = true;
			return;
		}
		
		if (!pdf) {
			dtInicial = filterDataInicial();
			dtFinal = filterDataFinal();
		}
		
		//Limitar no máximo 1 ano para listar o pdf
		if (maiorQueUmAno(dtInicial, dtFinal)) {
			aviso = "Período não pode ser maior que um ano!";
			fileOpen = true;
		} else {
			aviso = "";
			fileOpen = false;
		}
				
		
		/* Cria lista com todos os meses do período selecionado */
		listaMeses = new ArrayList<String>();
		listaMeses = buscarMesesPeriodo();
		sizeListMeses = listaMeses.size();
		
		empresasSelecionadas = filterEmpresasSelecionadas();
		
		/* Parâmetros */
		GridLazyLoaderDTO gridLazyLoaderDTO = new GridLazyLoaderDTO();
		gridLazyLoaderDTO.setFilters(new HashMap<String, Object>());
		gridLazyLoaderDTO.getFilters().put("dataInicial", dtInicial);
		gridLazyLoaderDTO.getFilters().put("dataFinal", dtFinal);
		gridLazyLoaderDTO.getFilters().put("empresasSelecionadas", empresasSelecionadas);
		if (filter.getFilter().getContaSelecionada() != null) {
			gridLazyLoaderDTO.getFilters().put("conta", filter.getFilter().getContaSelecionada());
		}
		
		/*Cabeçalho*/
		html.append(cabecalho(diminuirFonte));
		
		/* Se marcada opção "Exibir Valores Consolidados", cria uma única table somando os valores*/
		if (filter.getFilter().isValoresConsolidados()) {
		
			/*Lista com Todas as Receitas conforme filtro*/
			List<ContaReceber> listaReceitas = relatoriosBusiness.buscaReceitasSemPaginacao(gridLazyLoaderDTO);
			
			/*Parâmetro incluso novamente pois é removido no método de busca acima*/
			gridLazyLoaderDTO.getFilters().put("empresasSelecionadas", empresasSelecionadas);
			
			/*Lista com Todas as Despesas conforme filtro*/
			List<RelatorioDTO> listaDespesas = relatoriosBusiness.buscaDespesasSemPaginacao(gridLazyLoaderDTO, ContaPagar.RESULT_MAP_CUSTOMER_RELATORIO);
			
			/* Cria lista única das Empresas que tem despesas e receitas, para ao consolidar os valores de todas, mostrar somente o nome daquelas que tem valor*/
			List<Empresa> listaEmpresasUnica = criaListaUnicaEmpresas(listaReceitas, listaDespesas);
			
			if (listaReceitas.size() > 0 || listaDespesas.size() > 0) {
				html.append(cabecalhoConsolidado(listaEmpresasUnica));
			}
			
			if (listaReceitas.size() > 0) {
				carregaReceitasConsolidado(listaReceitas);
			}
			
			if (listaDespesas.size() > 0) {
				carregaDespesasConsolidado(listaDespesas);
			}

			if (listaReceitas.size() > 0 || listaDespesas.size() > 0) {
				html.append("</div>");
			}
			
		} else {
			
			/* Carrega lista de empresas que devem sair no relatório */
			List<Empresa> listaEmpresas = new ArrayList<Empresa>(empresaList);
			
			if (empresasSelecionadas.size() > 0) {
				listaEmpresas = new ArrayList<Empresa>(empresasSelecionadas);
			}
				 
			
			/* Cria uma table por empresa*/
			for (Empresa empresa : listaEmpresas) {
	
				totaisReceitas = new ArrayList<BigDecimal>();
				totaisDespesas = new ArrayList<BigDecimal>();
				totaisResultados = new ArrayList<BigDecimal>();
				
				gridLazyLoaderDTO.getFilters().put("empresa", empresa);
				
				/*Lista com Todas as Receitas da Empresa*/
				List<ContaReceber> listaReceitas = relatoriosBusiness.buscaReceitasSemPaginacao(gridLazyLoaderDTO);
				
				/*Parâmetro incluso novamente pois é removido no método de busca acima*/
				gridLazyLoaderDTO.getFilters().put("empresasSelecionadas", empresasSelecionadas);
				
				/*Lista com Todas as Despesas da Empresa*/
				List<RelatorioDTO> listaDespesas = relatoriosBusiness.buscaDespesasSemPaginacao(gridLazyLoaderDTO, ContaPagar.RESULT_MAP_CUSTOMER_RELATORIO);
	
				if (listaReceitas.size() > 0 || listaDespesas.size() > 0) {
					html.append(cabecalhoPorEmpresa(empresa));
				}
				
				if (listaReceitas.size() > 0) {
					carregaReceitasPorEmpresa(empresa, listaReceitas);
				}
	
				if (listaDespesas.size() > 0) {
					carregaDespesasPorEmpresa(empresa, listaDespesas);
				}
				
				if (listaReceitas.size() > 0 || listaDespesas.size() > 0) {
					html.append("</div>");
				}
			}
			
		}
		
		html.append(fechaTagsCabecalho());
		
		if (pdf) {
			gerarPDF(html);
		}
		
	}
	
	public void gerarPDF(StringBuilder html) {
		Calendar dtAtual = Calendar.getInstance();
		NOME_FILE_PDF = "pdf" + dtAtual.getTimeInMillis() + ".pdf";
		
		Document document = new Document();
		try {
			PdfWriter.getInstance(document, new FileOutputStream(CAMINHO_FILE_PDF + NOME_FILE_PDF));
			document.setPageSize(PageSize.A4.rotate());
			document.open();
			
			HTMLWorker hw = new HTMLWorker(document);
            hw.parse(new StringReader(html.toString())); 

		} catch (DocumentException de) {
			System.err.println(de.getMessage());
			FacesMessageUtils.addErrorMessage(de.getMessage());
		} catch (IOException ioe) {
			System.err.println(ioe.getMessage());
			FacesMessageUtils.addErrorMessage(ioe.getMessage());
		}
		
		document.close();
	} 
	
	public StreamedContent getFilePdf() throws IOException {

		diminuirFonte = true;
		carregaRelatorio(true);
		diminuirFonte = false;
		
		try {
			InputStream stream = new FileInputStream(CAMINHO_FILE_PDF + NOME_FILE_PDF); 
			StreamedContent file = new DefaultStreamedContent(stream, "application/pdf", "relatorio.pdf");
			
			apagaFilePdf(CAMINHO_FILE_PDF + NOME_FILE_PDF); 
			
			return file;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return null;
    } 
	
	public void apagaFilePdf(String fileName) {
		File f = new File(fileName);
		if (f.exists()) {
			f.delete();
			f = null;
			System.gc();
		}
	} 
	
	/**
	 * Cria uma lista única das Empresas que tem despesas e receitas, para ao consolidar os valores de todas, mostrar somente o nome daquelas que tem valor
	 * @return List<Empresa>
	 */
	private List<Empresa> criaListaUnicaEmpresas(List<ContaReceber> listaReceitas, List<RelatorioDTO> listaDespesas) {
		
		/*Lista Distinct Empresas das Receitas*/
		List<ContaReceber> listaEmpresasReceitas = listaReceitas.stream().filter(UteisStream.distinctByKey(p -> p.getEmpresa().getId()))
				.collect(Collectors.toList());
		
		/*Lista Distinct Empresas das Despesas*/
		List<RelatorioDTO> listaEmpresasDespesas = listaDespesas.stream().filter(UteisStream.distinctByKey(p -> p.getEmpresaId()))
				.collect(Collectors.toList());
		
		/* Lista única das Empresas que tem despesas e receitas, para ao consolidar os valores de todas, mostrar somente o nome daquelas que tem valor*/
		List<Empresa> listaEmpresasUnica = new ArrayList<Empresa>();
		for (RelatorioDTO r : listaEmpresasDespesas) {
			Empresa emp = new Empresa();
			emp = empresaBusiness.buscaPorId(r.getEmpresaId());
			listaEmpresasUnica.add(emp);
		}
		for (ContaReceber c : listaEmpresasReceitas) {
			if (!listaEmpresasUnica.contains(c.getEmpresa())) {
				listaEmpresasUnica.add(c.getEmpresa());
			}
		}
		
		return listaEmpresasUnica;
	}

	/**
	 * Cria uma única table geral, com todas as empresas, para somar os valores de todas as despesas
	 */
	private void carregaDespesasConsolidado(List<RelatorioDTO> listaDespesas) {
		BigDecimal sumConta = new BigDecimal(0);
		BigDecimal totalLinha = new BigDecimal(0);
		String mesAno = "";
		
		totaisResultados = new ArrayList<BigDecimal>();
		totaisDespesas = new ArrayList<BigDecimal>();
		
		/*Título Despesas*/
		html.append(tagTitulo("DESPESAS"));
		
		/*Lista Contas Pai*/
		List<RelatorioDTO> listaContasPai = listaDespesas.stream().filter(UteisStream.distinctByKey(p -> p.getIdPai()))
				.collect(Collectors.toList());
		
		for (RelatorioDTO contaPai : listaContasPai) {
			totaisDespesas = new ArrayList<BigDecimal>();
			
			html.append(tagSubTitulo(contaPai.getDescricaoPai()));
			
			/*Table*/
			html.append(tagDivTable());
			/*Body*/
			html.append("<tbody>");
			
			html.append("<tr>");
			html.append("<th>");
			html.append("");
			html.append("</th>");
			
			/* Gera as colunas com todos os meses */
			html.append(gerarTodosMeses(listaMeses));
			
			html.append("<td style=\"font-weight: bold\";>");
			html.append("TOTAL");
			html.append("</td>");
			html.append("</tr>");
			
			/*Lista Contas da conta pai*/
			List<RelatorioDTO> listaContas = listaDespesas.stream()
					.filter((ctaPai)->ctaPai.getIdPai()==contaPai.getIdPai())
					.filter(UteisStream.distinctByKey(p -> p.getContaId()))
					.collect(Collectors.toList());
			
			for (RelatorioDTO conta : listaContas) {
					totalLinha = new BigDecimal(0);
					
					html.append("<tr>");
					html.append(abreTagColunaTamanhoFixo());
					html.append(conta.getContaDescricao());
					html.append("</td>");
					
					/*Lista das Despesas da conta pai e conta*/
					List<RelatorioDTO> listaDespesasCtaPai = listaDespesas.stream()
							.filter((ctaPai)->ctaPai.getIdPai()==contaPai.getIdPai())
							.filter((cta)->cta.getContaId()==conta.getContaId())
							.collect(Collectors.toList());
					
					for (String mes : listaMeses) {
						sumConta = new BigDecimal(0);
						html.append("<td>");
						
						for (RelatorioDTO despesa : listaDespesasCtaPai) {
						
							mesAno = meseAno(despesa.getDataPrevista());
							
							if (mesAno.equals(mes)){
								sumConta = sumConta.add(despesa.getValor());
								totalLinha = totalLinha.add(despesa.getValor());
							}
						}
						
						html.append(df.format(sumConta));
						html.append("</td>");
						
					}
					/*Total por Linha e Conta*/
					html.append("<td>");
					html.append(df.format(totalLinha));
					html.append("</td>");
					html.append("</tr>");	 
				
			}
			
			/*Lista das Despesas por conta pai */
			List<RelatorioDTO> listaDespesasTot = listaDespesas.stream()
					.filter((ctaPai)->ctaPai.getIdPai()==contaPai.getIdPai())
					.collect(Collectors.toList());
			
			/*Total por Coluna, Mês e Conta Pai*/
			html.append(totalPorColunaMesDespesas(listaDespesasTot));	
			
			/*Resultados por Coluna, Mês e Conta Pai*/
			html.append(resultadoGeralPorColunaMesDespesas());	
			
			html.append("</tbody>");
			html.append(fechaTags());
		}
		
		html.append("</div>");
	}


	/**
	 * Cria uma table de despesas por empresa
	 */
	public void carregaDespesasPorEmpresa(Empresa empresa, List<RelatorioDTO> listaDespesas) {
		BigDecimal sumConta = new BigDecimal(0);
		BigDecimal totalLinha = new BigDecimal(0);
		String mesAno = "";

		totaisResultados = new ArrayList<BigDecimal>();
		totaisDespesas = new ArrayList<BigDecimal>();
			
		/*Título Despesas*/
		html.append(tagTitulo("DESPESAS"));
		
		/*Lista Contas Pai da empresa*/
		List<RelatorioDTO> listaContasPai = listaDespesas.stream()
				.filter((emp)->emp.getEmpresaId()==empresa.getId())
				.filter(UteisStream.distinctByKey(p -> p.getIdPai()))
				.collect(Collectors.toList());
		
		for (RelatorioDTO contaPai : listaContasPai) {
			totaisDespesas = new ArrayList<BigDecimal>();
			
			html.append(tagSubTitulo(contaPai.getDescricaoPai()));
			
			/*Table*/
			html.append(tagDivTable());
			/*Body*/
			html.append("<tbody>");
			
			html.append("<tr>");
			html.append("<th>");
			html.append("");
			html.append("</th>");
			
			/* Gera as colunas com todos os meses */
			html.append(gerarTodosMeses(listaMeses));
			
			html.append("<td style=\"font-weight: bold\";>");
			html.append("TOTAL");
			html.append("</td>");
			html.append("</tr>");
			
			
			/*Lista Contas da empresa e conta pai*/
			List<RelatorioDTO> listaContas = listaDespesas.stream()
					.filter((emp)->emp.getEmpresaId()==empresa.getId())
					.filter((ctaPai)->ctaPai.getIdPai()==contaPai.getIdPai())
					.filter(UteisStream.distinctByKey(p -> p.getContaId()))
					.collect(Collectors.toList());
			
			for (RelatorioDTO conta : listaContas) {
				totalLinha = new BigDecimal(0);
				
				html.append("<tr>");
				html.append(abreTagColunaTamanhoFixo());
				html.append(conta.getContaDescricao());
				html.append("</td>");
				
				/*Lista das Despesas da empresa, conta pai e conta*/
				List<RelatorioDTO> listaDespesasEmpresa = listaDespesas.stream()
						.filter((emp)->emp.getEmpresaId()==empresa.getId())
						.filter((ctaPai)->ctaPai.getIdPai()==contaPai.getIdPai())
						.filter((cta)->cta.getContaId()==conta.getContaId())
						.collect(Collectors.toList());
				
				for (String mes : listaMeses) {
					sumConta = new BigDecimal(0);
					html.append("<td>");
					
					for (RelatorioDTO despesa : listaDespesasEmpresa) {
						
						mesAno = meseAno(despesa.getDataPrevista());
						if (mesAno.equals(mes)){
							sumConta = sumConta.add(despesa.getValor());
							totalLinha = totalLinha.add(despesa.getValor());
						}
					}
					html.append(df.format(sumConta));
					html.append("</td>");
					
				}
				/*Total por Linha e Conta*/
				html.append("<td>");
				html.append(df.format(totalLinha));
				html.append("</td>");
				html.append("</tr>");	 
				
			}
			
			/*Lista das Despesas da empresa e conta pai */
			List<RelatorioDTO> listaDespesasTot = listaDespesas.stream()
					.filter((emp)->emp.getEmpresaId()==empresa.getId())
					.filter((ctaPai)->ctaPai.getIdPai()==contaPai.getIdPai())
					.collect(Collectors.toList());
			
			/*Total por Coluna, Mês, Empresa e Conta Pai*/
			html.append(totalPorColunaMesDespesas(listaDespesasTot));	
			
			/*Resultados por Coluna, Mês, Empresa e Conta Pai*/
			html.append(resultadoGeralPorColunaMesDespesas());	
			
			html.append("</tbody>");
			html.append(fechaTags());
			
		}
		
		html.append("</div>");
		
	}
	
	
	/**
	 * Cria uma única table geral, com todas as empresas, somando os valores de todas as receitas
	 */
	private void carregaReceitasConsolidado(List<ContaReceber> listaReceitas) {
		BigDecimal sumConta = new BigDecimal(0);
		BigDecimal totalLinha = new BigDecimal(0); 
		String mesAno = "";
		
		/*Título Receitas*/
		html.append(tagTitulo("RECEITAS"));
		
		/*Table*/
		html.append(tagDivTable()); 
		
		/*Body*/
		html.append("<tbody>");
		
		html.append("<tr>");
		html.append("<th>");
		html.append("");
		html.append("</th>");
		
		/* Gera as colunas com todos os meses */
		html.append(gerarTodosMeses(listaMeses));
		
		html.append("<td style=\"font-weight: bold\";>");
		html.append("TOTAL");
		html.append("</td>");
		html.append("</tr>");
		
		
		/*Lista Contas da conta pai*/
		List<ContaReceber> listaContas = listaReceitas.stream()
				.filter(UteisStream.distinctByKey(p -> p.getConta().getId()))
				.collect(Collectors.toList());
		
		for (ContaReceber conta : listaContas) {
				totalLinha = new BigDecimal(0);
				
				html.append("<tr>");
				html.append(abreTagColunaTamanhoFixo());
				html.append(conta.getConta().getDescricao());
				html.append("</td>");
				
				/*Lista das Despesas da conta pai e conta*/
				List<ContaReceber> listaReceitasCta = listaReceitas.stream()
						.filter((cta)->cta.getConta().getId()==conta.getConta().getId())
						.collect(Collectors.toList());
				
				for (String mes : listaMeses) {
					sumConta = new BigDecimal(0);
					html.append("<td>");
					
					for (ContaReceber receita : listaReceitasCta) {
						
						mesAno = meseAno(receita.getDataRecebimento());
						if (mesAno.equals(mes)){
							sumConta = sumConta.add(receita.getValor());
							totalLinha = totalLinha.add(receita.getValor());
						}
					}
					
					html.append(df.format(sumConta));
					html.append("</td>");
					
				}
				/*Total por Linha e Conta*/
				html.append("<td>");
				html.append(df.format(totalLinha));
				html.append("</td>");
				html.append("</tr>");	 
			
		}
		
		/*Lista Geral das Receitas */
		List<ContaReceber> listaReceitasTot = listaReceitas.stream().collect(Collectors.toList());
		
		/*Total Geral por Coluna e Mês de Receitas*/
		html.append(totalGeralPorColunaMesReceitas(listaReceitasTot));	
		
		html.append("</tbody>");
		
		html.append(fechaTags());
		
		html.append("</div>");
	}


	/**
	 * Cria uma table de receitas por empresa
	 */
	public void carregaReceitasPorEmpresa(Empresa empresa, List<ContaReceber> listaReceitas) {
		BigDecimal sumConta = new BigDecimal(0);
		BigDecimal totalLinha = new BigDecimal(0);
		String mesAno = "";
		
		/*Título Receitas*/
		html.append(tagTitulo("RECEITAS"));
		
		/*Table*/
		html.append(tagDivTable()); 

		
		/*Body*/
		html.append("<tbody>");
		
		html.append("<tr>");
		html.append("<th>");
		html.append("");
		html.append("</th>");
		
		/* Gera as colunas com todos os meses */
		html.append(gerarTodosMeses(listaMeses));
		
		html.append("<td style=\"font-weight: bold\";>");
		html.append("TOTAL");
		html.append("</td>");
		html.append("</tr>");
		
		/*Lista Contas da empresa e conta pai*/
		List<ContaReceber> listaContas = listaReceitas.stream()
				.filter((emp)->emp.getEmpresa().getId()==empresa.getId())
				.filter(UteisStream.distinctByKey(p -> p.getConta().getId()))
				.collect(Collectors.toList());
		
		for (ContaReceber conta : listaContas) {
			totalLinha = new BigDecimal(0);
			
			html.append("<tr>");
			html.append(abreTagColunaTamanhoFixo());
			html.append(conta.getConta().getDescricao());
			html.append("</td>");
			
			/*Lista das Despesas da empresa, conta pai e conta*/
			List<ContaReceber> listaReceitasEmpresa = listaReceitas.stream()
					.filter((emp)->emp.getEmpresa().getId()==empresa.getId())
					.filter((cta)->cta.getConta().getId()==conta.getConta().getId())
					.collect(Collectors.toList());
			
			for (String mes : listaMeses) {
				sumConta = new BigDecimal(0);
				html.append("<td>");
				
				for (ContaReceber receita : listaReceitasEmpresa) {
					
					mesAno = meseAno(receita.getDataRecebimento());
					
					if (mesAno.equals(mes)){
						sumConta = sumConta.add(receita.getValor());
						totalLinha = totalLinha.add(receita.getValor());
					}
				}
				html.append(df.format(sumConta));
				html.append("</td>");
				
			}
			/*Total por Linha e Conta*/
			html.append("<td>");
			html.append(df.format(totalLinha));
			html.append("</td>");
			html.append("</tr>");	 
			
		}
		
		/*Lista das Receitas da empresa */
		List<ContaReceber> listaReceitasTot = listaReceitas.stream()
				.filter((emp)->emp.getEmpresa().getId()==empresa.getId())
				.collect(Collectors.toList());
		
		/*Total Geral por Coluna, Mês e Empresa */
		html.append(totalGeralPorColunaMesReceitas(listaReceitasTot));		
		
		html.append("</tbody>");
		
		html.append(fechaTags());
		
		html.append("</div>");
	}

	
	/**
	 * Coluna Total Geral da table por Coluna e Mês de Receitas
	 * @return String
	 */
	private String totalGeralPorColunaMesReceitas(List<ContaReceber> listaReceitasTot) {
		BigDecimal totalColuna = new BigDecimal(0);
		BigDecimal totalColunaeLinha = new BigDecimal(0);
		String mesAno = "";
		
		totaisReceitas = new ArrayList<BigDecimal>();

		StringBuilder html = new StringBuilder();
		html.append(abreTagTotalGeral());
		
		for (String mes : listaMeses) {
			totalColuna = new BigDecimal(0);
			for (ContaReceber receita : listaReceitasTot) {
				
				mesAno = meseAno(receita.getDataRecebimento());
				if (mesAno.equals(mes)){
					totalColuna = totalColuna.add(receita.getValor());
				}
			}
			html.append(tagColuna(totalColuna));
			totalColunaeLinha = totalColunaeLinha.add(totalColuna);
			totaisReceitas.add(totalColuna);
		}
		
		/*Total Geral da Coluna e Linha*/
		html.append(tagColuna(totalColunaeLinha));
		
		html.append(fechaTagLinha());
		
		return html.toString();
	}

	/**
	 * Coluna Total da table por Coluna e Mês de Despesas
	 * @return String
	 */
	private String totalPorColunaMesDespesas(List<RelatorioDTO> listaDespesasTot) {
		BigDecimal totalColuna = new BigDecimal(0);
		BigDecimal totalColunaeLinha = new BigDecimal(0);
		String mesAno = "";
		
		StringBuilder html = new StringBuilder();
		html.append(abreTagTotal());
		
		for (String mes : listaMeses) {
			totalColuna = new BigDecimal(0);
			for (RelatorioDTO despesa : listaDespesasTot) {
				
				mesAno = meseAno(despesa.getDataPrevista());
				if (mesAno.equals(mes)){
					totalColuna = totalColuna.add(despesa.getValor());
				}
			}
			totaisDespesas.add(totalColuna);
			html.append(tagColuna(totalColuna));
			totalColunaeLinha = totalColunaeLinha.add(totalColuna);
		}
		
		/*Total Geral da Coluna e Linha*/
		html.append(tagColuna(totalColunaeLinha));
		
		html.append(fechaTagLinha());
		
		return html.toString();
	}
	
	/**
	 * Coluna Resultado da table por Coluna, Mês e Conta Pai de Despesas
	 * @return String
	 */
	private String resultadoGeralPorColunaMesDespesas() {
		List<BigDecimal> listaNovosResultados = new ArrayList<BigDecimal>();
		BigDecimal totalColuna = new BigDecimal(0);
		BigDecimal totalColunaeLinha = new BigDecimal(0);
		
		StringBuilder html = new StringBuilder();
		html.append(abreTagResultadoGeral());
		
		/* Se não tiver receitas recebe uma lista zerada*/
		if (totaisReceitas == null || totaisReceitas.isEmpty()) {
			totaisReceitas = criaListaZerada();
		}
		
		/* Se for a primeira conta pai, os resultados são os totais das despesas*/
		if (totaisResultados == null || totaisResultados.isEmpty()){
			totaisResultados = new ArrayList<BigDecimal>(totaisReceitas);
		}
		
		int i=0;
		for (BigDecimal resultado : totaisResultados) {
			totalColuna = new BigDecimal(0);
			
			totalColuna = resultado.subtract(totaisDespesas.get(i));
			listaNovosResultados.add(totalColuna);
			totalColunaeLinha = totalColunaeLinha.add(totalColuna);
			i++;
			
			html.append(tagColuna(totalColuna));
		}
		
		totaisResultados = new ArrayList<BigDecimal>(listaNovosResultados);
				
		/*Total Geral da Coluna e Linha*/
		html.append(tagColuna(totalColunaeLinha));
		
		
		html.append(fechaTagLinha());
		
		return html.toString();
	}
	
	
	/**
	 * Busca todos os meses do período selecionado
	 * @return List<String>
	 */
	private List<String> buscarMesesPeriodo() {
		List<String> listaMeses = new ArrayList<String>();
		GregorianCalendar data = new GregorianCalendar();
		GregorianCalendar dataF = new GregorianCalendar();
		
		String mesAno = "";
		data.setTime(dtInicial);
		dataF.setTime(dtFinal);
		
		while (data.compareTo(dataF)<=0) {
			mesAno = obtemMeseAnoData(data);
			listaMeses.add(mesAno);
			data.add(GregorianCalendar.MONTH, 1);
		}
		return listaMeses;
	}

	private String meseAno(Date data) {
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(data);
		return obtemMeseAnoData(calendar);
	}
	
	private String obtemMeseAnoData(GregorianCalendar data) {
		return mesesNomes[data.get(GregorianCalendar.MONTH)] + "/" + data.get(GregorianCalendar.YEAR);
	}
	
	/**
	 * Cria as colunas de todos os meses na table
	 * @return String
	 */
	private String gerarTodosMeses(List<String> listaMeses) {
		StringBuilder html = new StringBuilder();
		for (String mes : listaMeses) {
			html.append("<td style=\"font-weight: bold\";>");
			html.append(mes);
			html.append("</td>");
		}
		return html.toString();
	}
	
	/**
	 * Cria uma lista zerada de receitas
	 * @return List<BigDecimal
	 */
	private List<BigDecimal> criaListaZerada() {
		List<BigDecimal> totaisReceitasZeradas = new ArrayList<BigDecimal>();
		for (int i=0; i < sizeListMeses; i++) {
			totaisReceitasZeradas.add(new BigDecimal(0));
		}
		return totaisReceitasZeradas;
	}
	
	/**
	 * Cria o cabeçalho da table por empresa, com o nome da empresa e o período selecionado
	 * @return String
	 */
	private String cabecalhoPorEmpresa(Empresa empresa) {
		StringBuilder html = new StringBuilder();

		html.append(styleCabecalho());
		html.append("EMPRESA: ");
		html.append(empresa.getDescricao());
		html.append("</div>");	
		html.append(tagColunaPeriodo());
		html.append("</div>");
		
		return html.toString();
	}
	
	/**
	 * Cria o cabeçalho da table consolidada, com o nome de todas as empresas e o período selecionado
	 * @return String
	 */
	private String cabecalhoConsolidado(List<Empresa> listaEmpresas) {
		StringBuilder html = new StringBuilder();
		
		html.append(styleCabecalho());
		html.append("EMPRESA(S): ");
		for (Empresa empresa : listaEmpresas) {
			html.append(empresa.getDescricao());
			html.append("; ");
		}
		html.append("</div>") ;
		html.append(tagColunaPeriodo());
		html.append("</div>");
		
		return html.toString();
	}

	/**
	 * Cria o cabeçalho inicial do relatório
	 * @return String
	 */
	private String cabecalho(Boolean diminuirFonte) {
		/*Cabeçalho*/
		StringBuilder html = new StringBuilder();
		html.append("<div class=\"portlet light\">");
		if (diminuirFonte) {
			html.append("<div class=\"portlet-body\" style=\"font-size: 9px;\">");
		} else {
			html.append("<div class=\"portlet-body\">");
		}
		return html.toString();
	}
	
	private String styleCabecalho() {
		StringBuilder html = new StringBuilder();
		html.append("<div style='border: 1px solid #dddddd; margin-top: 10px;'>");
		html.append("<div class=\"row\" style=\"padding-left: 10px;\">");
		html.append("<div class=\"col-md-8\" style=\"font-weight: bold; min-height: 29px; margin-top: 7px;\">");
		return html.toString();
	}
	
	private String tagDivTable() {
		StringBuilder html = new StringBuilder();
		html.append("<div class=\"table-scrollable\" style=\"border-width: 0px; margin: 0 !important;\">");
		html.append("<table class=\"table table-bordered table-hover\">");
		return html.toString();
	}

	private String tagColunaPeriodo() {
		StringBuilder html = new StringBuilder();
		html.append("<div class=\"col-md-4\" style=\"font-weight: bold; min-height: 29px; margin-top: 7px;\">");
		html.append(" | PERÍODO: ");
		html.append(format.format(filterDataInicial()));
		html.append(" à ");
		html.append(format.format(filterDataFinal()));
		html.append("</div>") ;
		return html.toString();
	}
	
	private String tagTitulo(String nome) {
		StringBuilder html = new StringBuilder();
		
		html.append("<div style='border: 0px !important; border-top: 1px solid #dddddd !important;'>");
		html.append("<div style='width: 100%; height: auto; text-align: center; font-weight: bold; padding: 10px 0 !important;'>");
		html.append(nome);
		html.append("</div>");
		
		return html.toString();
	}
	
	private String tagSubTitulo(String nome) {
		StringBuilder html = new StringBuilder();
		
		html.append("<div style='text-align: center; font-weight: bold; border-top: 1px solid #dddddd; padding: 10px 0 !important;'>");
		html.append(nome);
		html.append("</div>");
		
		return html.toString();
	}
	
	private String fechaTagsCabecalho() {
		StringBuilder html = new StringBuilder();
		html.append("</div>");
		html.append("</div>");
		return html.toString();
	}
	
	private String fechaTags() {
		StringBuilder html = new StringBuilder();
		html.append("</table>");
		html.append("</div>");
		return html.toString();
	}
	
	private String abreTagTotal() {
		StringBuilder html = new StringBuilder();
		html.append("<tr style=\"background-color: #E6E6E6;\">");
		html.append("<td style=\"font-weight: bold\";>");
		html.append("TOTAL");
		html.append("</td>");
		return html.toString();
	}
	
	private String abreTagTotalGeral() {
		StringBuilder html = new StringBuilder();
		html.append("<tr style=\"background-color: #BDBDBD;\">");
		html.append("<td style=\"font-weight: bold\";>");
		html.append("TOTAL");
		html.append("</td>");
		return html.toString();
	}
	
	private String abreTagResultadoGeral() {
		StringBuilder html = new StringBuilder();
		html.append("<tr style=\"background-color: #BDBDBD;\">");
		html.append("<td style=\"font-weight: bold\";>");
		html.append("RESULTADO");
		html.append("</td>");
		return html.toString();
	}
	
	private String fechaTagLinha() {
		StringBuilder html = new StringBuilder();
		html.append("</tr>");
		return html.toString();
	}
	
	private String abreTagColunaTamanhoFixo() {
		StringBuilder html = new StringBuilder();
		html.append("<td style=\"width: 220px;\">");
		return html.toString();
	}
	
	private String tagColuna(BigDecimal totalColuna) {
		StringBuilder html = new StringBuilder();
		html.append("<td>");
		html.append(df.format(totalColuna));
		html.append("</td>");
		return html.toString();
	}

	public void limparFiltro() {
		empresaList = empresaBusiness.buscaTodasEmpresas();
		filter.limpaFiltro();
	}
	
	public List<SelectItem> getListaContas() {
		return listaContas;
	}

	public void setListaContas(List<SelectItem> listaContas) {
		this.listaContas = listaContas;
	}
	
	public List<Empresa> getEmpresaList() {
		return empresaList;
	}

	public void setEmpresaList(List<Empresa> empresaList) {
		this.empresaList = empresaList;
	}

	public StringBuilder getHtml() {
		return html;
	}

	public void setHtml(StringBuilder html) {
		this.html = html;
	}

	public FilterRelatorioResultados getFilter() {
		return filter;
	}

	public void setFilter(FilterRelatorioResultados filter) {
		this.filter = filter;
	}
	
	private Date filterDataFinal() {
		return filter.getFilter().getDataFinal();
	}

	private Date filterDataInicial() {
		return filter.getFilter().getDataInicial();
	}

	private List<Empresa> filterEmpresasSelecionadas() {
		return empresaList.stream().filter((ck)-> ck.isCheckBoxRelValue()).collect(Collectors.toList());
	}
	
	private Boolean maiorQueUmAno(Date ini, Date fim) {
		Calendar dtini = Calendar.getInstance();
		dtini.setTime(ini);
		Calendar dtfim = Calendar.getInstance();
		dtfim.setTime(fim);
		
		long d1 = dtini.getTimeInMillis();  
        long d2 = dtfim.getTimeInMillis();
        
        if (((d2 - d1)/ (24*60*60*1000)) > 364) {
        	return true;
        }
        
		return false;
	}
	
	public boolean isFileOpen() {
		return fileOpen;
	}
	
	public String getAviso(){
		return aviso;
	}
}
