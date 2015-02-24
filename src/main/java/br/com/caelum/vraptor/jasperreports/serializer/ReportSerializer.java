package br.com.caelum.vraptor.jasperreports.serializer;

import br.com.caelum.vraptor.jasperreports.Report;
import br.com.caelum.vraptor.jasperreports.formats.ExportFormat;


public interface ReportSerializer {
	
	SerializedReport content(Report report, ExportFormat format);
	SerializedReport content(Report report);
	void serialize(Report report, ExportFormat format);
	void serialize(Report report);

}
