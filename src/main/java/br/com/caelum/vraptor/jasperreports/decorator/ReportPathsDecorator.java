package br.com.caelum.vraptor.jasperreports.decorator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.caelum.vraptor.ioc.ApplicationScoped;
import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.jasperreports.Report;
import br.com.caelum.vraptor.jasperreports.ReportPathResolver;

@Component
@ApplicationScoped
public class ReportPathsDecorator implements ReportDecorator {

	private final ReportPathResolver resolver;
	private final Logger logger = LoggerFactory.getLogger(ReportPathsDecorator.class);

	public ReportPathsDecorator(ReportPathResolver resolver) {
		this.resolver = resolver;
	}

	public void decorate(Report<?> report) {
		report.addParameter("REPORT_DIR", resolver.getReportsPath());
		report.addParameter("SUBREPORT_DIR", resolver.getSubReportsPath());
		report.addParameter("IMAGES_DIR", resolver.getImagesPath());
		log();
	}
	
	private void log(){
		logger.debug("REPORT_DIR --> " + resolver.getReportsPath());
		logger.debug("SUBREPORT_DIR --> " + resolver.getSubReportsPath());
		logger.debug("IMAGES_DIR --> " + resolver.getImagesPath());
	}

}
