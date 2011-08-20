package br.com.caelum.vraptor.jasperreports.decorator;

import net.sf.jasperreports.engine.JRParameter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.jasperreports.Report;

@Component
public class I18nDecorator implements ReportDecorator {

	private final ReportsResourceBundle bundle;
	private final Logger logger = LoggerFactory.getLogger(I18nDecorator.class);

	public I18nDecorator(ReportsResourceBundle bundle) {
		this.bundle = bundle;
	}

	public void decorate(Report<?> report) {
		report.addParameter(JRParameter.REPORT_LOCALE, bundle.getLocale());
		report.addParameter(JRParameter.REPORT_RESOURCE_BUNDLE, bundle);
		logger.debug("REPORT_LOCALE --> " + bundle.getLocale());
		logger.debug("REPORT_RESOURCE_BUNDLE --> " + bundle);
	}
	
}
