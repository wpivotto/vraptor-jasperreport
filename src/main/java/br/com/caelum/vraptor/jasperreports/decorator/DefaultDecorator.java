package br.com.caelum.vraptor.jasperreports.decorator;

import java.util.Locale;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import net.sf.jasperreports.engine.JRParameter;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.config.Configuration;
import br.com.caelum.vraptor.jasperreports.Report;
import br.com.caelum.vraptor.jasperreports.ReportPathResolver;

@RequestScoped
public class DefaultDecorator implements ReportDecorator {

	@Inject private Locale locale;
	@Inject private ReportPathResolver resolver;
	@Inject private Result result;
	@Inject private Configuration cfg;

	public void decorate(Report report) {
		if (report == null)
			return;
		if(report.getParameters() == null)
			return;
		ReportsResourceBundle bundle = new ReportsResourceBundle(locale, resolver);
		addParameter("REPORT_DIR", resolver.getReportsPath(), report);
		addParameter("SUBREPORT_DIR", resolver.getSubReportsPath(), report);
		addParameter("IMAGES_DIR", resolver.getImagesPath(), report);
		addParameter("CONTEXT_PATH", cfg.getApplicationPath(), report);
		addParameter(JRParameter.REPORT_LOCALE, locale, report);
		addParameter(JRParameter.REPORT_RESOURCE_BUNDLE, bundle, report);
		includeRequestParameters(report);
		
	}
	
	private void includeRequestParameters(Report report) {
		for(Map.Entry<String, Object> entry : result.included().entrySet()){
			addParameter(entry.getKey(), entry.getValue(), report);
		}
	}
	
	private void addParameter(String param, Object value, Report report) {
		if (!report.getParameters().containsKey(param)) {
			report.addParameter(param, value);
		}
	}
}