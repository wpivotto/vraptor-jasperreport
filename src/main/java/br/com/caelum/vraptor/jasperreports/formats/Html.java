package br.com.caelum.vraptor.jasperreports.formats;

import javax.enterprise.context.RequestScoped;

import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporterParameter;
import br.com.caelum.vraptor.jasperreports.ReportPathResolver;

/**
 * @author William Pivotto
 */

@RequestScoped
public class Html extends AbstractExporter {

	private final ReportPathResolver resolver;
	
	public Html(ReportPathResolver resolver){
		this.resolver = resolver;
	}

	public String getContentType() {
		return "text/html";
	}

	public String getExtension() {
		return "html";
	}

	public JRExporter setup() {
		configure(JRHtmlExporterParameter.IS_USING_IMAGES_TO_ALIGN, Boolean.FALSE);
		configure(JRHtmlExporterParameter.IMAGES_DIR_NAME, resolver.getImagesPath());
		configure(JRHtmlExporterParameter.IMAGES_URI, resolver.getImagesURI());
		return new JRHtmlExporter();
	}
	
}
