package br.com.caelum.vraptor.jasperreports.exporter;

import java.util.List;

import br.com.caelum.vraptor.jasperreports.Report;
import br.com.caelum.vraptor.jasperreports.formats.ExportFormat;

/**
 * Generic report exporter
 *
 * @author William Pivotto
 *
 */

public interface ReportExporter {
	
	ReportExporter export(Report<?> report);
	ReportExporter export(List<Report<?>> reports);
	byte[] to(ExportFormat format);

}
