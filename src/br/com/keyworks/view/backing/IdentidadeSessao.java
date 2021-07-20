package br.com.keyworks.view.backing;

import java.io.Serializable;
import java.util.Date;
import javax.enterprise.context.SessionScoped;

@SessionScoped
public class IdentidadeSessao implements Serializable {

	private static final long serialVersionUID = 1L;

	private String nome;

	private Date dataCriacao;

	public IdentidadeSessao() {

	}

	public IdentidadeSessao(String nome, Date dataCriacao) {
		this.nome = nome;
		this.dataCriacao = dataCriacao;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

}
