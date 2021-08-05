package br.com.keyworks.model.entities.administracao;

import java.io.Serializable;
import java.util.Arrays;
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
import javax.persistence.Transient;
import org.primefaces.model.UploadedFile;

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

	@Column(name = "comprovante")
	private byte[] comprovante;

	@Column(name = "observacao")
	private String observacao;

	@Column(name = "pagamento")
	private String pagamento;

	@Transient
	private UploadedFile comprovanteFile;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public byte[] getComprovante() {
		return comprovante;
	}

	public void setComprovante(byte[] comprovante) {
		this.comprovante = comprovante;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public String getPagamento() {
		return pagamento;
	}

	public void setPagamento(String pagamento) {
		this.pagamento = pagamento;
	}

	public UploadedFile getComprovanteFile() {
		return comprovanteFile;
	}

	public void setComprovanteFile(UploadedFile comprovanteFile) {
		this.comprovanteFile = comprovanteFile;
	}

	@Override
	public String toString() {
		return "Mensalidade [id=" + id + ", usuario=" + usuario + ", dataVencimento=" + dataVencimento + ", valor=" + valor + ", comprovante="
						+ Arrays.toString(comprovante) + ", observacao=" + observacao + ", pagamento=" + pagamento + ", comprovanteFile="
						+ comprovanteFile + "]";
	}
}
