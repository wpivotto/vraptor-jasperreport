package br.com.caelum.vraptor.jasperreports.decorator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.jasperreports.engine.JRParameter;
import br.com.caelum.vraptor.core.Localization;
import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.jasperreports.Report;

@Component
public class ReportLocaleDecorator implements ReportDecorator {

	private final Localization localization;
	private final Logger logger = LoggerFactory.getLogger(ReportLocaleDecorator.class);

	public ReportLocaleDecorator(Localization localization) {
		this.localization = localization;
	}

	public void decorate(Report<?> report) {
		report.addParameter(JRParameter.REPORT_LOCALE, localization.getLocale());
		report.addParameter(JRParameter.REPORT_RESOURCE_BUNDLE, localization.getBundle());
		logger.debug("REPORT_LOCALE --> " + localization.getLocale());
	}
	
}
