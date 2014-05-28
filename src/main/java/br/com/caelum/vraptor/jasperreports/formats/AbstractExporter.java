package br.com.caelum.vraptor.jasperreports.formats;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.export.Exporter;
import net.sf.jasperreports.export.ExporterConfiguration;
import net.sf.jasperreports.export.ExporterInput;
import net.sf.jasperreports.export.ExporterOutput;
import net.sf.jasperreports.export.ReportExportConfiguration;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

import com.google.common.io.Flushables;

@SuppressWarnings({"unchecked", "rawtypes"})
public abstract class AbstractExporter implements ExportFormat {
	
	protected abstract Exporter setup();
	
	public boolean supportsBatchMode() {
		return true;
	}
	
	public ExporterOutput getExporterOutput(OutputStream output) {
		return new SimpleOutputStreamExporterOutput(output);
	}
	
	public byte[] toByteArray(List<JasperPrint> print) {
		
		ByteArrayOutputStream output = new ByteArrayOutputStream();
	
		try {
			
			Exporter exporter = setup();
			
			ExporterInput exporterInput = SimpleExporterInput.getInstance(print);
			exporter.setExporterInput(exporterInput);
			
			ExporterOutput exporterOutput = getExporterOutput(output);
			exporter.setExporterOutput(exporterOutput);

			ExporterConfiguration exporterConfiguration = getExporterConfiguration();
			if (exporterConfiguration != null)
				exporter.setConfiguration(exporterConfiguration);
			
			ReportExportConfiguration reportConfiguration = getReportConfiguration();
			if (reportConfiguration != null)
				exporter.setConfiguration(reportConfiguration);
			
			exporter.exportReport();
			Flushables.flushQuietly(output);
			return output.toByteArray();  
			
		} catch (JRException e) {
			throw new RuntimeException(e);
		}
		finally {
			try {
				output.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
	}
	
}
