package br.com.caelum.vraptor.jasperreports.formats;

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

	public void setup() {
		exporter = new JRPdfExporter();
		configure(JRPdfExporterParameter.IS_COMPRESSED, Boolean.TRUE );
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

}
