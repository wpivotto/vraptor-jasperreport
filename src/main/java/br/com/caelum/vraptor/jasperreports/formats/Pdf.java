package br.com.caelum.vraptor.jasperreports.formats;

import javax.enterprise.context.ApplicationScoped;

import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.Exporter;
import net.sf.jasperreports.export.ExporterConfiguration;
import net.sf.jasperreports.export.ReportExportConfiguration;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import net.sf.jasperreports.export.SimplePdfReportConfiguration;

/**
 * @author William Pivotto
 */

@ApplicationScoped
public class Pdf extends AbstractExporter {
	
	private SimplePdfExporterConfiguration config = new SimplePdfExporterConfiguration();

	public String getContentType() {
		return "application/pdf";
	}
	
	public String getExtension() {
		return "pdf";
	}

	@SuppressWarnings("rawtypes")
	public Exporter setup() {
		config.setCompressed(Boolean.TRUE);
		return new JRPdfExporter();
	}

	public void encrypt(String password){
		config.setEncrypted(Boolean.TRUE);
		config.set128BitKey(Boolean.TRUE);
		config.setUserPassword(password);
		config.setOwnerPassword(password);
	}
	
	public void setAuthor(String author) {
		config.setMetadataAuthor(author);
	}
	
	public void setTitle(String title){
		config.setMetadataTitle(title);
	}
	
	public void setCreator(String creator){
		config.setMetadataCreator(creator);
	}
	
	public void setSubject(String subject){
		config.setMetadataSubject(subject);
	}
	
	/**
     * The Keywords of the PDF document, as comma-separated String. 
     */
	public void setKeywords(String keywords){
		config.setMetadataKeywords(keywords);
	}

	public ReportExportConfiguration getReportConfiguration() {
		return new SimplePdfReportConfiguration();
	}

	public ExporterConfiguration getExporterConfiguration() {
		return config;
	}

}
