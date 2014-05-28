package br.com.caelum.vraptor.jasperreports;


import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import br.com.caelum.vraptor.http.FormatResolver;
import br.com.caelum.vraptor.jasperreports.formats.ExportFormat;
import br.com.caelum.vraptor.jasperreports.formats.ExportFormats;

@RequestScoped
public class ReportFormatResolver {

	@Inject private ExportFormats formats;
	@Inject private FormatResolver formatResolver;
	
	public ExportFormat getExportFormat() {
		return formats.byExtension(formatResolver.getAcceptFormat());
	}
	
	public boolean doDownload() {
		return !"html".equals(formatResolver.getAcceptFormat());
	}
}
