package br.com.caelum.vraptor.jasperreports.formats;

import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRPdfExporterParameter;
import br.com.caelum.vraptor.ioc.Component;

/**
 * @author William Pivotto
 */

@Component
public class Pdf extends AbstractExporter {
	
	private Integer permissions = 0;

	public String getContentType() {
		return "application/pdf";
	}
	
	public String getExtension() {
		return "pdf";
	}

	public JRExporter setup() {
		configure(JRPdfExporterParameter.IS_COMPRESSED, Boolean.TRUE );
		return new JRPdfExporter();
	}

	public void encrypt(String password){
		configure(JRPdfExporterParameter.IS_ENCRYPTED, Boolean.TRUE);
		configure(JRPdfExporterParameter.IS_128_BIT_KEY, Boolean.TRUE);
		configure(JRPdfExporterParameter.USER_PASSWORD, password);
		configure(JRPdfExporterParameter.OWNER_PASSWORD, password);
	}
	
	public Pdf addPermission(Integer permission){
		this.permissions |= permission; 
		configure(JRPdfExporterParameter.PERMISSIONS, permissions);
		return this;
	}
	
	public void setAuthor(String author){
		configure(JRPdfExporterParameter.METADATA_AUTHOR, author);
	}
	
	public void setTitle(String title){
		configure(JRPdfExporterParameter.METADATA_TITLE, title);
	}
	
	public void setCreator(String creator){
		configure(JRPdfExporterParameter.METADATA_CREATOR, creator);
	}
	
	public void setSubject(String subject){
		configure(JRPdfExporterParameter.METADATA_SUBJECT, subject);
	}
	
	/**
     * The Keywords of the PDF document, as comma-separated String. 
     */
	public void setKeywords(String keywords){
		configure(JRPdfExporterParameter.METADATA_KEYWORDS, keywords);
	}

}
