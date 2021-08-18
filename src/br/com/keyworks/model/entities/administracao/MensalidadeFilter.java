package br.com.keyworks.model.entities.administracao;

import java.util.List;
import br.com.keyworks.enumeracoes.MesEnum;
import br.com.keyworks.enumeracoes.SimNaoEnum;

public class MensalidadeFilter {

	private String pagamento;

	private SimNaoEnum comprovante;

	private List<MesEnum> meses;

	private List<String> anos;

	private String associado;

	public String getPagamento() {
		return pagamento;
	}

	public void setPagamento(String pagamento) {
		this.pagamento = pagamento;
	}

	public SimNaoEnum getComprovante() {
		return comprovante;
	}

	public void setComprovante(SimNaoEnum comprovante) {
		this.comprovante = comprovante;
	}

	public List<MesEnum> getMeses() {
		return meses;
	}

	public void setMeses(List<MesEnum> meses) {
		this.meses = meses;
	}

	public List<String> getAnos() {
		return anos;
	}

	public void setAnos(List<String> anos) {
		this.anos = anos;
	}

	public String getAssociado() {
		return associado;
	}

	public void setAssociado(String associado) {
		this.associado = associado;
	}

	@Override
	public String toString() {
		return "MensalidadeFilter [pagamento=" + pagamento + ", comprovante=" + comprovante + ", meses=" + meses + ", ano=" + anos + ", associado="
						+ associado + "]";
	}

}
