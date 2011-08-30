package br.com.caelum.vraptor.jasperreports.decorator;

import net.sf.jasperreports.engine.JRParameter;
import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.jasperreports.Report;

@Component
public class I18nDecorator implements ReportDecorator {

	private final ReportsResourceBundle bundle;

	public I18nDecorator(ReportsResourceBundle bundle) {
		this.bundle = bundle;
	}

	public void decorate(Report<?> report) {
		report.addParameter(JRParameter.REPORT_LOCALE, bundle.getLocale());
		report.addParameter(JRParameter.REPORT_RESOURCE_BUNDLE, bundle);
	}
	
}
