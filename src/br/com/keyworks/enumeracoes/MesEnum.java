package br.com.keyworks.enumeracoes;

public enum MesEnum {

	JANEIRO("1", "Janeiro"),
	FEVEREIRO("2", "Fevereiro"),
	MARÇO("3", "Março"),
	ABRIL("4", "Abril"),
	MAIO("5", "Maio"),
	JUNHO("6", "Junho"),
	JULHO("7", "Julho"),
	AGOSTO("8", "Agosto"),
	SETEMBRO("9", "Setembro"),
	OUTUBRO("10", "Outubro"),
	NOVEMBRO("11", "Novembro"),
	DEZEMBRO("12", "Dezembro");

	private String id;
	private String descricao;

	private MesEnum(String id, String descricao) {
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
