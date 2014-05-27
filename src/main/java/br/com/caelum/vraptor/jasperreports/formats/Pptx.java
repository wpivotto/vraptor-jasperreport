package br.com.caelum.vraptor.jasperreports.formats;

import javax.enterprise.context.RequestScoped;

import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.export.ooxml.JRPptxExporter;

/**
 * @author William Pivotto
 */

@RequestScoped
public class Pptx extends AbstractExporter {
	
	public String getContentType() {
		return "application/vnd.openxmlformats-officedocument.presentationml.presentation";
	}
	
	public String getExtension() {
		return "pptx";
	}

	public JRExporter setup() {
		return new JRPptxExporter();
	}

}
