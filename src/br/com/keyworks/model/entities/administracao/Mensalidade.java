package br.com.keyworks.model.entities.administracao;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "mensalidade")
@SequenceGenerator(name = "MensalidadeSeq", sequenceName = "MENSALIDADE_SEQ", allocationSize = 1)
public class Mensalidade implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MensalidadeSeq")
	@Column(name = "id", length = 19, nullable = false)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "idUsuario", referencedColumnName = "id")
	private Usuario usuario;

	@Column(name = "dataVencimento")
	private Date dataVencimento;

	@Column(name = "valor")
	private Double valor;

	@Column(name = "pagamento")
	private boolean pagamento;

	public Mensalidade() {

	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public boolean isPagamento() {
		return pagamento;
	}

	public void setPagamento(boolean pagamento) {
		this.pagamento = pagamento;
	}

	@Override
	public String toString() {
		return "Mensalidade [id=" + id + ", usuario=" + usuario + ", dataVencimento=" + dataVencimento + ", valor=" + valor + ", pagamento="
						+ pagamento + "]";
	}

}
