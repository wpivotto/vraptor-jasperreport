package br.com.caelum.vraptor.jasperreports;

import java.io.InputStream;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

public class ReportLoader {

	public JasperReport load(Report<?> report) throws JRException {
		
		if(report.isCacheable())
			return loadFromCache(report);
		else
			return loadFromDisk(report);

	}
	
	private JasperReport loadFromCache(Report<?> report) throws JRException {
		
		ReportCache cache = ReportCache.New();
		
		if(!cache.contains(report.getTemplate()))
			cache.put(report.getTemplate(), loadFromDisk(report));
		
		return cache.get(report.getTemplate());
		
	}
	
	private JasperReport loadFromDisk(Report<?> report) throws JRException {

		String template = report.getTemplate();
		
		InputStream stream = ReportLoader.class.getResourceAsStream(template);
		
		if(stream == null)
			throw new RuntimeException("Could not find the file " + template);
		
		if(template.contains(".jrxml"))
			return JasperCompileManager.compileReport(stream);
		else
			return (JasperReport) JRLoader.loadObject(stream);
		
	}
}
