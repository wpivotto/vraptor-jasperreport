package br.com.caelum.vraptor.jasperreports;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

@ApplicationScoped
public class ReportLoader {
	
	@Inject private ReportCache cache;
	@Inject private ReportPathResolver resolver;

	public JasperReport load(Report report) throws JRException {
		
		if(report.isCacheable())
			return loadFromCache(report);
		else
			return loadFromDisk(report);

	}
	
	private JasperReport loadFromCache(Report report) throws JRException {
		
		if(!cache.contains(report.getTemplate()))
			cache.put(report.getTemplate(), loadFromDisk(report));
		
		return cache.get(report.getTemplate());
		
	}
	
	private JasperReport loadFromDisk(Report report) throws JRException {

		String template = resolver.getPathFor(report);
		
		if(template.endsWith(".jrxml"))
			return JasperCompileManager.compileReport(template);
		else
			return (JasperReport) JRLoader.loadObjectFromFile(template);
		
	}
}
