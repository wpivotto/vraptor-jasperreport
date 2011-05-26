package br.com.caelum.vraptor.jasperreports;

/**
 * Generic report exporter
 *
 * @author William Pivotto
 *
 */

public interface ReportExporter {
	
	ReportExporter export(Report<?> report);
	byte[] to(ExportFormat format);

}
