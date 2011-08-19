package br.com.caelum.vraptor.jasperreports.exporter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import br.com.caelum.vraptor.jasperreports.Report;
import br.com.caelum.vraptor.jasperreports.ReportLoader;
import br.com.caelum.vraptor.jasperreports.formats.ExportFormat;

/**
 * Export a report into a specific format
 *
 * @author William Pivotto
 *
 */
public class JasperExporter implements ReportExporter {

	private Report<?> report;
	private final ReportLoader loader; 

	public JasperExporter(ReportLoader loader){
		this.loader = loader;
	}
	
	public ReportExporter export(Report<?> report) {
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


	private JasperPrint fill(Report<?> report) throws JRException {
		
		JasperReport jr = loader.load(report);
		JRBeanCollectionDataSource data = new JRBeanCollectionDataSource(report.getData(), false);
		JasperPrint print = JasperFillManager.fillReport(jr, report.getParameters(), data);
		
		return print;
		
	}

}
