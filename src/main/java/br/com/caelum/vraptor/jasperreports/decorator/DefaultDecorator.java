package br.com.caelum.vraptor.jasperreports.decorator;

import java.util.Map;

import net.sf.jasperreports.engine.JRParameter;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.jasperreports.Report;
import br.com.caelum.vraptor.jasperreports.ReportPathResolver;

@Component
public class DefaultDecorator implements ReportDecorator {

	private final ReportPathResolver resolver;
	private final ReportsResourceBundle bundle;
	private final Result result;

	public DefaultDecorator(ReportPathResolver resolver, ReportsResourceBundle bundle, Result result) {
		this.resolver = resolver;
		this.bundle = bundle;
		this.result = result;
	}

	public void decorate(Report<?> report) {
		if(report.getParameters() != null) {
			report.addParameter("REPORT_DIR", resolver.getReportsPath());
			report.addParameter("SUBREPORT_DIR", resolver.getSubReportsPath());
			report.addParameter("IMAGES_DIR", resolver.getImagesPath());
			report.addParameter(JRParameter.REPORT_LOCALE, bundle.getLocale());
			report.addParameter(JRParameter.REPORT_RESOURCE_BUNDLE, bundle);
			includeRequestParameters(report);
		}
	}
	
	private void includeRequestParameters(Report<?> report){
		for(Map.Entry<String, Object> entry : result.included().entrySet()){
			report.addParameter(entry.getKey(), entry.getValue());
		}
	}

	
}