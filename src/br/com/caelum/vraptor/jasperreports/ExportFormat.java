package br.com.caelum.vraptor.jasperreports;

import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;

/**
 * Generic Exporter
 *
 * @author William Pivotto
 *
 */

public interface ExportFormat {
	
	/**
     * Specifies the Content-Type Header
     */
	String getContentType();
	
	/**
     * Specifies the output file extension
     */
	String getExtension();
	
	/**
     * Specifies the export implementation
     */
	JRExporter getExporter();
	
	/**
     * Stores a configuration parameter
     * @param parameter a String specifying the key of the parameter
     * @param value the object to be stored
     */
	ExportFormat configure(JRExporterParameter parameter, Object value);

}
