package br.com.caelum.vraptor.jasperreports;

@SuppressWarnings("unchecked")
public interface ReportExporter {
	
	ReportExporter export(Report report);
	byte[] to(ExportFormat format);

}
