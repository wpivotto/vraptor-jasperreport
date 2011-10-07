package br.com.caelum.vraptor.jasperreports.exporter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.jasperreports.Report;
import br.com.caelum.vraptor.jasperreports.ReportLoader;
import br.com.caelum.vraptor.jasperreports.decorator.ReportDecorator;
import br.com.caelum.vraptor.jasperreports.formats.ExportFormat;

/**
 * Export a report into a specific format
 *
 * @author William Pivotto
 *
 */

@Component
public class DefaultExporter implements ReportExporter {

	private List<Report<?>> reports = new ArrayList<Report<?>>();
	private final ReportLoader loader; 
	private final List<ReportDecorator> decorators;
	
	public DefaultExporter(ReportLoader loader, List<ReportDecorator> decorators){
		this.loader = loader;
		this.decorators = decorators;
	}
	
	public ReportExporter export(Report<?> report) {
		this.reports.add(report);
		return this;
	}
	
	public ReportExporter export(List<Report<?>> reports) {
		this.reports = reports;
		return this;
	}

	public byte[] to(ExportFormat format) {
		
		ByteArrayOutputStream buffer = new ByteArrayOutputStream();
	
		try {
			
			JRExporter exporter = format.getExporter();
		
			exporter.setParameter(JRExporterParameter.JASPER_PRINT_LIST, fillAll());
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


	private List<JasperPrint> fillAll() throws JRException {
		
		List<JasperPrint> printList = new ArrayList<JasperPrint>();
		
		for(Report<?> report : reports){
			printList.add(fill(report));
		}
		
		return printList;
		
	}
	
	private JasperPrint fill(Report<?> report) throws JRException {
		
		for(ReportDecorator decorator : decorators){
			decorator.decorate(report);
		}
		
		JasperReport jr = loader.load(report);
		JRBeanCollectionDataSource data = new JRBeanCollectionDataSource(report.getData(), false);
		JasperPrint print = JasperFillManager.fillReport(jr, report.getParameters(), data);
		
		return print;
		
	}

	

}
