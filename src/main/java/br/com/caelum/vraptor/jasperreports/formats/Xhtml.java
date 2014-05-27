package br.com.caelum.vraptor.jasperreports.formats;

import javax.enterprise.context.RequestScoped;

import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.export.JRXhtmlExporter;

/**
 * @author William Pivotto
 */

@RequestScoped
public class Xhtml extends AbstractExporter {

	public String getContentType() {
		return "text/html";
	}

	public String getExtension() {
		return "xhtml";
	}

	public JRExporter setup() {
		return new JRXhtmlExporter();
	}
	
}
