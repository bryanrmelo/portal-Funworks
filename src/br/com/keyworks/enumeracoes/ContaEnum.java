package br.com.keyworks.enumeracoes;

public enum ContaEnum {

	ADMINISTRADOR("A", "Administrador"), PADRAO("N", "Padrão");

	private String id;
	private String descricao;

	private ContaEnum(String id, String descricao) {
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
