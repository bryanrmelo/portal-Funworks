package br.com.keyworks.dto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import br.com.keyworks.model.entities.administracao.ContaPagar;

public class ContaPagarVencimentoDTO {

	private String dataVencimento;
	private List<ContaPagar> contaPagarList;
	private GregorianCalendar dataOrder;

	public ContaPagarVencimentoDTO(String dataVencimento, List<ContaPagar> contaPagarList) {
		super();
		this.contaPagarList = contaPagarList;

		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		try {
			Date dataVenc = new Date(format.parse("01/" + dataVencimento).getTime());
			GregorianCalendar d = new GregorianCalendar();
			d.setTime(dataVenc);
			this.dataVencimento = "Periodo: " + getMesLiteral(d.get(GregorianCalendar.MONTH)) + "/" + d.get(GregorianCalendar.YEAR);
			dataOrder = new GregorianCalendar();
			this.dataOrder.set(d.get(GregorianCalendar.YEAR), d.get(GregorianCalendar.MONTH) + 1, 01);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public String getMesLiteral(Integer mes) {
		if (mes == 0) {
			return "Janeiro";
		}
		if (mes == 1) {
			return "Fevereiro";
		}
		if (mes == 2) {
			return "Março";
		}
		if (mes == 3) {
			return "Abril";
		}
		if (mes == 4) {
			return "Maio";
		}
		if (mes == 5) {
			return "Junho";
		}
		if (mes == 6) {
			return "Julho";
		}
		if (mes == 7) {
			return "Agosto";
		}
		if (mes == 8) {
			return "Setembro";
		}
		if (mes == 9) {
			return "Outubro";
		}
		if (mes == 10) {
			return "Novembro";
		}
		if (mes == 11) {
			return "Dezembro";
		}
		return "";
	}

	public String getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(String dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public List<ContaPagar> getContaPagarList() {
		return contaPagarList;
	}

	public void setContaPagarList(List<ContaPagar> contaPagarList) {
		this.contaPagarList = contaPagarList;
	}

	public GregorianCalendar getDataOrder() {
		return dataOrder;
	}

	public void setDataOrder(GregorianCalendar dataOrder) {
		this.dataOrder = dataOrder;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((dataOrder == null) ? 0 : dataOrder.hashCode());
		result = prime * result
				+ ((dataVencimento == null) ? 0 : dataVencimento.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ContaPagarVencimentoDTO other = (ContaPagarVencimentoDTO) obj;
		if (dataOrder == null) {
			if (other.dataOrder != null)
				return false;
		} else if (!dataOrder.equals(other.dataOrder))
			return false;
		if (dataVencimento == null) {
			if (other.dataVencimento != null)
				return false;
		} else if (!dataVencimento.equals(other.dataVencimento))
			return false;
		return true;
	}

	
}
