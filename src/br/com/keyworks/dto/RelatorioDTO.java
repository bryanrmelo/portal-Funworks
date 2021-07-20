package br.com.keyworks.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class RelatorioDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String data;
	private BigDecimal valor;
	private Integer contaId;
	private String contaDescricao;
	private Date dataPrevista;
	private Integer idPai;
	private String descricaoPai;
	private Integer empresaId;

	public RelatorioDTO() {
		super();
	}

	public RelatorioDTO(String data, BigDecimal valor, Integer contaId, String contaDescricao, Date dataPrevista, Integer idPai, String descricaoPai, Integer empresaId) {

		super();
		this.data = data;
		this.valor = valor;
		this.contaId = contaId;
		this.contaDescricao = contaDescricao;
		this.dataPrevista = dataPrevista;
		this.idPai = idPai;
		this.descricaoPai = descricaoPai;
		this.empresaId = empresaId;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Integer getContaId() {
		return contaId;
	}

	public void setContaId(Integer contaId) {
		this.contaId = contaId;
	}

	public String getContaDescricao() {
		return contaDescricao;
	}

	public void setContaDescricao(String contaDescricao) {
		this.contaDescricao = contaDescricao;
	}

	public Date getDataPrevista() {
		return dataPrevista;
	}

	public void setDataPrevista(Date dataPrevista) {
		this.dataPrevista = dataPrevista;
	}

	public Integer getIdPai() {
		return idPai;
	}

	public void setIdPai(Integer idPai) {
		this.idPai = idPai;
	}

	public String getDescricaoPai() {
		return descricaoPai;
	}

	public void setDescricaoPai(String descricaoPai) {
		this.descricaoPai = descricaoPai;
	}

	public Integer getEmpresaId() {
		return empresaId;
	}

	public void setEmpresaId(Integer empresaId) {
		this.empresaId = empresaId;
	}

}
