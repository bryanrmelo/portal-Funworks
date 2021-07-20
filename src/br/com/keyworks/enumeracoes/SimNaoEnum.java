package br.com.keyworks.enumeracoes;

public enum SimNaoEnum {

	SIM(true, "Sim"), NAO(false, "Não");

	private boolean id;
	private String descricao;

	private SimNaoEnum(boolean id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}

	public boolean getId() {
		return this.id;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public static SimNaoEnum getById(boolean id) {
		for (SimNaoEnum item : SimNaoEnum.values()) {
			if (item.getId() == id) {
				return item;
			}
		}
		return null;
	}
}
