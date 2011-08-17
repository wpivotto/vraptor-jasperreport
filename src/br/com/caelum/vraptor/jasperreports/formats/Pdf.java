package br.com.caelum.vraptor.jasperreports.formats;

import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRPdfExporterParameter;
import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.jasperreports.ExportFormat;

/**
 * @author William Pivotto
 */

@Component
public class Pdf implements ExportFormat {
	
	private JRExporter exporter;
	private Integer permissions = 0;

	public Pdf(){
		this.exporter = new JRPdfExporter();
		configure(JRPdfExporterParameter.IS_COMPRESSED, Boolean.TRUE );
		configure(JRPdfExporterParameter.CHARACTER_ENCODING, "UTF-8" );
	}

	public String getContentType() {
		return "application/pdf";
	}

	public JRExporter getExporter() {
		return exporter;
	}

	public String getExtension() {
		return "pdf";
	}
	
	public ExportFormat configure(JRExporterParameter parameter, Object value) {
		exporter.setParameter(parameter, value);
		return this;
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
