package br.com.caelum.vraptor.jasperreports.exporter;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.caelum.vraptor.Accepts;
import br.com.caelum.vraptor.AfterCall;
import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.controller.ControllerMethod;
import br.com.caelum.vraptor.core.MethodInfo;
import br.com.caelum.vraptor.jasperreports.download.BatchReportsDownload;
import br.com.caelum.vraptor.jasperreports.download.ReportDownload;
import br.com.caelum.vraptor.jasperreports.download.ReportsDownload;
import br.com.caelum.vraptor.observer.download.Download;

@Intercepts
public class ExporterInjector {

	@Inject private ReportExporter exporter;
	@Inject private MethodInfo methodInfo;
	@Inject private Result result;
	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Accepts
	public boolean accepts(ControllerMethod method) {
		Class<?> type = method.getMethod().getReturnType();
		return Download.class.isAssignableFrom(type); 
	}

	@AfterCall
	public void intercept() {
		
		Object reportDownload =  methodInfo.getResult();
		if (reportDownload == null) {
			if (result.used()) {
				return;
			} else
				throw new NullPointerException("You've just returned a Null ReportDownload. Consider redirecting to another page/logic");
		}
		
		if(reportDownload instanceof ReportDownload){
			ReportDownload download = (ReportDownload) reportDownload;
			download.setExporter(exporter);
		}
		
		if(reportDownload instanceof ReportsDownload){
			ReportsDownload download = (ReportsDownload) reportDownload;
			download.setExporter(exporter);
		}
		
		if(reportDownload instanceof BatchReportsDownload){
			BatchReportsDownload download = (BatchReportsDownload) reportDownload;
			download.setExporter(exporter);
		}
		
		logger.debug("Injecting {} in {}", exporter.getClass().getName(), reportDownload.getClass().getName());
		
	}

}