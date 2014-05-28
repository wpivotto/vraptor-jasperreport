package br.com.caelum.vraptor.jasperreports.formats;

import java.io.OutputStream;
import java.util.List;

import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.export.ExporterConfiguration;
import net.sf.jasperreports.export.ExporterOutput;
import net.sf.jasperreports.export.ReportExportConfiguration;

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
     * Specifies the report configurations
     */
	ReportExportConfiguration getReportConfiguration();
	
	/**
     * Specifies the exporter configurations
     */
	ExporterConfiguration getExporterConfiguration();
	
	
	ExporterOutput getExporterOutput(OutputStream output);
	
	/**
	 * Can work in batch mode
	 */
	boolean supportsBatchMode();
	
	/**
     * Generates output content
     */
	byte[] toByteArray(List<JasperPrint> print);

}
