package br.com.keyworks.enumeracoes;

public enum AnoEnum {

	PRIMEIRO("2019", "2019"), SEGUNDO("2020", "2020"), TERCEIRO("2021", "2021");

	private String id;
	private String descricao;

	private AnoEnum(String id, String descricao) {
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
