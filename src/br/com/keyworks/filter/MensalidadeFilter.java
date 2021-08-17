package br.com.keyworks.filter;

import java.util.List;
import br.com.keyworks.enumeracoes.SimNaoEnum;

public class MensalidadeFilter {

	private String pagamento;

	private SimNaoEnum comprovante;

	private List<String> meses;

	private List<String> ano;

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

	public List<String> getMeses() {
		return meses;
	}

	public void setMeses(List<String> meses) {
		this.meses = meses;
	}

	public List<String> getAno() {
		return ano;
	}

	public void setAno(List<String> ano) {
		this.ano = ano;
	}

	public String getAssociado() {
		return associado;
	}

	public void setAssociado(String associado) {
		this.associado = associado;
	}

	@Override
	public String toString() {
		return "MensalidadeFilter [pagamento=" + pagamento + ", comprovante=" + comprovante + ", meses=" + meses + ", ano=" + ano + ", associado="
						+ associado + "]";
	}

}
