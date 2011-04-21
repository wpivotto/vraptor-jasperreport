package br.com.caelum.vraptor.jasperreports;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 * Export a report into a specific format
 *
 * @author William Pivotto
 *
 */

public class JasperExporter implements ReportExporter {

	private Report report;
	
	public ReportExporter export(Report report) {
		this.report = report;
		return this;
	}

	public byte[] to(ExportFormat format) {
		
		ByteArrayOutputStream buffer = new ByteArrayOutputStream();
	
		try {
			
			JRExporter exporter = format.getExporter();
		
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, fill(report));
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, buffer);

			exporter.exportReport();
			
			buffer.flush();
		
			return buffer.toByteArray();  
			
		} catch (JRException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		
		finally{
			if(buffer != null) { 
				try {
					buffer.close();
				} catch (IOException e) {
					throw new RuntimeException(e);
				} 
			}
		}
	}


	private JasperPrint fill(Report report) throws JRException {
		
		String template = report.getTemplate();
		
		InputStream stream = JasperExporter.class.getResourceAsStream(template);
		if(stream == null)
			throw new RuntimeException("Could not find the file " + template);
		
		JasperReport jr;
		
		if(template.contains(".jrxml"))
			jr = JasperCompileManager.compileReport(stream);
		else
			jr = (JasperReport) JRLoader.loadObject(stream);
		
		JRBeanCollectionDataSource data = new JRBeanCollectionDataSource(report.getData(), false);
		JasperPrint print = JasperFillManager.fillReport(jr, report.getParameters(), data);
		return print;
		
	}

}
