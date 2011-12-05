package br.com.caelum.vraptor.jasperreports.util;

import br.com.caelum.vraptor.jasperreports.Report;
import br.com.caelum.vraptor.jasperreports.formats.ExportFormat;

/**
 * Builds URIs, defined by RFC 2397, that allows you to embed reports in browsers
 * @author William Pivotto
 */

public interface ReportDataURIBuilder {
	
	/**
	 * Builds the URI based on the export format
	 * @return URI defined by RFC 2397
	 */
	String build(Report report, ExportFormat format);
	
	/**
	 * Builds the URI through _format or Accept header
	 * @return URI defined by RFC 2397
	 */
	String build(Report report);

}
