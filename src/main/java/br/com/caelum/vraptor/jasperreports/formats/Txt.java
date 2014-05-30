package br.com.caelum.vraptor.jasperreports.formats;

import java.io.OutputStream;

import javax.enterprise.context.ApplicationScoped;

import net.sf.jasperreports.engine.export.JRTextExporter;
import net.sf.jasperreports.export.Exporter;
import net.sf.jasperreports.export.ExporterConfiguration;
import net.sf.jasperreports.export.ExporterOutput;
import net.sf.jasperreports.export.ReportExportConfiguration;
import net.sf.jasperreports.export.SimpleTextExporterConfiguration;
import net.sf.jasperreports.export.SimpleTextReportConfiguration;
import net.sf.jasperreports.export.SimpleWriterExporterOutput;

/**
 * @author William Pivotto
 */

@ApplicationScoped
public class Txt extends AbstractExporter {
	
	protected SimpleTextReportConfiguration reportConfiguration = new SimpleTextReportConfiguration();
	protected SimpleTextExporterConfiguration exportConfiguration = new SimpleTextExporterConfiguration();
	protected Integer pageWidth = 798; 
	protected Integer pageHeigth = 1000;
	protected Integer charactersPerLine = 110; 
	protected Integer linesPerPage = 71;

	public String getContentType() {
		return "text/plain";
	}

	public String getExtension() {
		return "txt";
	}
	
	@SuppressWarnings("rawtypes")
	public Exporter setup() {
		return new JRTextExporter();
	}
	
	public boolean supportsBatchMode() {
		return false;
	}

	public Integer getPageWidth() {
		return pageWidth;
	}

	public void setPageWidth(Integer pageWidth) {
		this.pageWidth = pageWidth;
	}

	public Integer getPageHeigth() {
		return pageHeigth;
	}

	public void setPageHeigth(Integer pageHeigth) {
		this.pageHeigth = pageHeigth;
	}

	public Integer getCharactersPerLine() {
		return charactersPerLine;
	}

	public void setCharactersPerLine(Integer charactersPerLine) {
		this.charactersPerLine = charactersPerLine;
	}

	public Integer getLinesPerPage() {
		return linesPerPage;
	}

	public void setLinesPerPage(Integer linesPerPage) {
		this.linesPerPage = linesPerPage;
	}

	public ReportExportConfiguration getReportConfiguration() {
		float charHeight = getLinesPerPage() > 0 ? getPageHeigth() / getLinesPerPage() : 13.948f;
		float charWidth = getCharactersPerLine() > 0 ? getPageWidth() / getCharactersPerLine() : 7.238f;
		reportConfiguration.setCharHeight(charHeight);
		reportConfiguration.setCharWidth(charWidth);
		return reportConfiguration;
	}

	public ExporterConfiguration getExporterConfiguration() {
		return exportConfiguration;
	}
	
	public ExporterOutput getExporterOutput(OutputStream output) {
		return new SimpleWriterExporterOutput(output);
	}

}
