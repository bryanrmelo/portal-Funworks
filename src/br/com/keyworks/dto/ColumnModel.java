package br.com.keyworks.dto;

import java.io.Serializable;

public class ColumnModel implements Serializable {

	private static final long serialVersionUID = 1L;

	static public class ColumnModelStatic implements Serializable {

		private static final long serialVersionUID = 1L;

		private String header;
		private String property;

		public ColumnModelStatic(String header, String value) {
			this.header = header;
			this.property = value;
		}

		public ColumnModelStatic(String header, String value, String extra) {
			this.header = header;
			this.property = value;
		}

		public String getHeader() {
			return header;
		}

		public String getProperty() {
			return property;
		}

	}

}