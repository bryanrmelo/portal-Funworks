package br.com.keyworks.view.componentes;


import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * Classe que representa um reultado paginado.
 * 
 * @param <T>
 */
public class PagedResult<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	private int totalSize;
	private List<T> page;

	public PagedResult() {
		totalSize = 0;
		page = new LinkedList<T>();
	}

	/**
	 * Obtém o tamanho total do resultado.
	 * 
	 * @return
	 */
	public int getTotalSize() {
		return totalSize;
	}

	/**
	 * Define o tamanho total do resultado.
	 * 
	 * @param totalSize
	 */
	public void setTotalSize(int totalSize) {
		this.totalSize = totalSize;
	}

	/**
	 * Obtém a página de registros.
	 * 
	 * @return
	 */
	public List<T> getPage() {
		return page;
	}

	/**
	 * Define a página de registros.
	 * 
	 * @param page
	 */
	public void setPage(List<T> page) {
		this.page = page;
	}

}
