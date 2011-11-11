package br.com.caelum.vraptor.jasperreports.formats;

import java.util.List;

import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperPrint;

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
     * Stores a configuration parameter
     * @param parameter a String specifying the key of the parameter
     * @param value the object to be stored
     */
	ExportFormat configure(JRExporterParameter parameter, Object value);
	
	/**
	 * Can work in batch mode
	 */
	boolean supportsBatchMode();
	
	/**
     * Generates output content
     */
	byte[] toByteArray(List<JasperPrint> print);

}
