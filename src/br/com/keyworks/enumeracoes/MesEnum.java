package br.com.keyworks.enumeracoes;

public enum MesEnum {

	JANEIRO("01", "Janeiro"),
	FEVEREIRO("02", "Fevereiro"),
	MARÇO("03", "Março"),
	ABRIL("04", "Abril"),
	MAIO("05", "Maio"),
	JUNHO("06", "Junho"),
	JULHO("07", "Julho"),
	AGOSTO("08", "Agosto"),
	SETEMBRO("09", "Setembro"),
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
