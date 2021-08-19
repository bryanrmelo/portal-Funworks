package br.com.keyworks.enumeracoes;

public enum PagoPendenteEnum {

	PAGO("pa", "Pago"), PENDENTE("pe", "Pendente");

	private String id;
	private String descricao;

	private PagoPendenteEnum(String id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public String getId() {
		return id;
	}
}
