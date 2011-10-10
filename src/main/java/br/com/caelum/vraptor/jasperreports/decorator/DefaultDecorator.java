package br.com.caelum.vraptor.jasperreports.decorator;

import net.sf.jasperreports.engine.JRParameter;
import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.jasperreports.Report;
import br.com.caelum.vraptor.jasperreports.ReportPathResolver;

@Component
public class DefaultDecorator implements ReportDecorator {

	private final ReportPathResolver resolver;
	private final ReportsResourceBundle bundle;

	public DefaultDecorator(ReportPathResolver resolver, ReportsResourceBundle bundle) {
		this.resolver = resolver;
		this.bundle = bundle;
	}

	public void decorate(Report<?> report) {
		report.addParameter("REPORT_DIR", resolver.getReportsPath());
		report.addParameter("SUBREPORT_DIR", resolver.getSubReportsPath());
		report.addParameter("IMAGES_DIR", resolver.getImagesPath());
		report.addParameter(JRParameter.REPORT_LOCALE, bundle.getLocale());
		report.addParameter(JRParameter.REPORT_RESOURCE_BUNDLE, bundle);
	}

	
}
