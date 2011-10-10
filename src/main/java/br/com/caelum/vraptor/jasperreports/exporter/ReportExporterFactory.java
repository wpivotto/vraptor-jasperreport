package br.com.caelum.vraptor.jasperreports.exporter;

import java.util.List;

import javax.servlet.http.HttpSession;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.ComponentFactory;
import br.com.caelum.vraptor.jasperreports.ReportLoader;
import br.com.caelum.vraptor.jasperreports.decorator.ReportDecorator;

@Component
public class ReportExporterFactory implements ComponentFactory<ReportExporter> {
	
	/*private final HttpSession session;
	private final ReportLoader loader;
	private final List<ReportDecorator> decorators;*/
	private ReportExporter exporter;

	public ReportExporterFactory(HttpSession session, ReportLoader loader, List<ReportDecorator> decorators) {
		/*this.session = session;
		this.loader = loader;
		this.decorators = decorators;*/
		this.exporter = new DefaultExporter(loader, decorators, session);
	}

	public ReportExporter getInstance() {
		return exporter;
	}

}