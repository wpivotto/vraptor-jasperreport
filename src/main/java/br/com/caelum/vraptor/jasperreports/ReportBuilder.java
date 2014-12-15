package br.com.caelum.vraptor.jasperreports;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Builder class for the Report interface.
 * 
 * @author Marcio Lima
 */
public class ReportBuilder {

	/** Report configuration for this builder */
	private ReportConfig report = new ReportConfig();

	/**
	 * Adds a parameter to the report's parameters map
	 * @param param
	 * @param value
	 * @return
	 */
	public ReportBuilder addParam(String param, Object value) {
		report.addParameter(param, value);
		return this;
	}

	/**
	 * Specifies the template to be used by the report
	 * @param template
	 * @return
	 */
	public ReportBuilder withTemplate(String template) {
		report.template = template;
		return this;
	}

	/**
	 * Determines whether this report should be cacheable or not
	 * @param cacheable
	 * @return
	 */
	public ReportBuilder withCacheable(boolean cacheable) {
		report.cacheable = cacheable;
		return this;
	}

	/**
	 * Determines the report file name when downloaded
	 * @param fileName
	 * @return
	 */
	public ReportBuilder withFileName(String fileName) {
		report.fileName = fileName;
		return this;
	}

	/**
	 * Specifies the data to be used to populate the report
	 * @param data
	 * @return
	 */
	public ReportBuilder withData(Collection<?> data) {
		report.data = data;
		return this;
	}

	/**
	 * Builds the Report object using the configuration set.
	 * @return
	 */
	public Report build() {
		return report;
	}

	/**
	 * Concrete class for the Report interface which will be returned by this builder.
	 * 
	 * @author Marcio Lima
	 */
	private class ReportConfig implements Report {

		/** Holds the report file name */
		private String fileName = "report" + System.currentTimeMillis();

		/** Report template */
		private String template;

		/** Cache control */
		private boolean cacheable;

		/** Report parameters */
		private Map<String, Object> params = new HashMap<String, Object>();

		/** Report data */
		private Collection<?> data;

		@Override
		public Report addParameter(String param, Object value) {
			params.put(param, value);
			return this;
		}

		@Override
		public Collection<?> getData() {
			return data;
		}

		@Override
		public String getFileName() {
			return fileName;
		}

		@Override
		public Map<String, Object> getParameters() {
			return params;
		}

		@Override
		public String getTemplate() {
			return template;
		}

		@Override
		public boolean isCacheable() {
			return cacheable;
		}
	}

}
