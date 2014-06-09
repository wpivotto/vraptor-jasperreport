package br.com.caelum.vraptor.jasperreports.formats;

import javax.enterprise.context.ApplicationScoped;

import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.export.Exporter;
import net.sf.jasperreports.export.ExporterConfiguration;
import net.sf.jasperreports.export.ReportExportConfiguration;
import net.sf.jasperreports.export.SimpleXlsExporterConfiguration;
import net.sf.jasperreports.export.SimpleXlsReportConfiguration;

/**
 * @author William Pivotto
 */

@ApplicationScoped
public class Xls extends AbstractExporter {
	
	protected SimpleXlsReportConfiguration reportConfiguration = new SimpleXlsReportConfiguration();
	protected SimpleXlsExporterConfiguration exportConfiguration = new SimpleXlsExporterConfiguration();
	
	public String getContentType() {
		return "application/vnd.ms-excel";
	}

	public String getExtension() {
		return "xls";
	}

	@SuppressWarnings("rawtypes")
	public Exporter setup() {
		return new JRXlsExporter();  
	}

	public ReportExportConfiguration getReportConfiguration() {
		reportConfiguration.setIgnoreGraphics(Boolean.FALSE);
		reportConfiguration.setCollapseRowSpan(Boolean.FALSE);
		reportConfiguration.setDetectCellType(Boolean.TRUE);
		reportConfiguration.setFontSizeFixEnabled(Boolean.TRUE);
		reportConfiguration.setRemoveEmptySpaceBetweenColumns(Boolean.FALSE);
		reportConfiguration.setRemoveEmptySpaceBetweenRows(Boolean.FALSE);
		reportConfiguration.setIgnoreCellBorder(Boolean.FALSE);
		reportConfiguration.setImageBorderFixEnabled(Boolean.FALSE);
		reportConfiguration.setShowGridLines(Boolean.FALSE);
		reportConfiguration.setIgnoreCellBackground(Boolean.FALSE);
		reportConfiguration.setWrapText(Boolean.FALSE);
		reportConfiguration.setOnePagePerSheet(Boolean.TRUE);
		reportConfiguration.setColumnWidthRatio(1.25f);
		return reportConfiguration;
	}

	public ExporterConfiguration getExporterConfiguration() {
		return exportConfiguration;
	}

}
