package br.com.keyworks.model.entities.administracao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "sistema_versao")
@SequenceGenerator(name = "sistemaVersaoseq", sequenceName = "SISTEMA_VERSAO_SEQ", allocationSize = 1)
@NamedQueries({ @NamedQuery(name = SistemaVersao.VERSAO, query = "SELECT e FROM SistemaVersao as e ") })
public class SistemaVersao {

	public static final String VERSAO = "sistema.versao.versao";

	@Id
	@SequenceGenerator(sequenceName = "sis_versao_id_seq", name = "sis_versao_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sistemaVersaoseq")
	@Column(name = "id", nullable = false)
	private Integer id;

	@Column(name = "versao", length = 2000, nullable = false)
	private String versao;

	public SistemaVersao() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getVersao() {
		return versao;
	}

	public void setVersao(String versao) {
		this.versao = versao;
	}

	@Override
	public int hashCode() {
		int hash = 1;
		hash = hash * 31 + (this.getId() == null ? 0 : this.getId().hashCode());
		hash = hash * 31 + (this.getVersao() == null ? 0 : this.getVersao().hashCode());
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		} else
			if (obj instanceof SistemaVersao) {
				return this.getId().equals(((SistemaVersao) obj).getId());
			}
		return false;
	}

	@Override
	public String toString() {
		return versao;
	}
}
