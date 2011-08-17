package br.com.caelum.vraptor.jasperreports;


import br.com.caelum.vraptor.http.FormatResolver;
import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.jasperreports.formats.Formats;

@Component
public class ReportFormatResolver {

	private final Formats formats;
	private final FormatResolver formatResolver;
	
	public ReportFormatResolver(Formats formats, FormatResolver formatResolver) {
		this.formats = formats;
		this.formatResolver = formatResolver;
	}
	
	public ExportFormat getExportFormat(){
		return formats.byExtension(formatResolver.getAcceptFormat());
	}
	
	public boolean doDownload(){
		return !"html".equals(formatResolver.getAcceptFormat());
	}
}
