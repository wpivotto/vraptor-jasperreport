package br.com.caelum.vraptor.jasperreports.exporter;

import java.io.IOException;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Specializes;
import javax.inject.Inject;

import br.com.caelum.vraptor.jasperreports.Report;
import br.com.caelum.vraptor.jasperreports.ReportFormatResolver;
import br.com.caelum.vraptor.jasperreports.download.BatchReportsDownload;
import br.com.caelum.vraptor.jasperreports.download.ReportDownload;
import br.com.caelum.vraptor.jasperreports.download.ReportsDownload;
import br.com.caelum.vraptor.observer.download.Download;
import br.com.caelum.vraptor.observer.download.DownloadObserver;

@Specializes
@RequestScoped
public class CustomDownloadObserver extends DownloadObserver {

	@Inject private ReportExporter exporter;
	@Inject private ReportFormatResolver resolver;
	
	@Override
	public Download resolveDownload(Object result) throws IOException {
		
		if(result instanceof Report) {
			Report report = (Report) result;
			ReportDownload download = new ReportDownload(report, resolver.getExportFormat(), resolver.doDownload());
			download.setExporter(exporter);
			return download;
		}
		
		if(result instanceof ReportDownload) {
			ReportDownload download = (ReportDownload) result;
			download.setExporter(exporter);
			return download;
		}
		
		if(result instanceof ReportsDownload) {
			ReportsDownload download = (ReportsDownload) result;
			download.setExporter(exporter);
			return download;
		}
		
		if(result instanceof BatchReportsDownload) {
			BatchReportsDownload download = (BatchReportsDownload) result;
			download.setExporter(exporter);
			return download;
		}

		return super.resolveDownload(result);
	}

}