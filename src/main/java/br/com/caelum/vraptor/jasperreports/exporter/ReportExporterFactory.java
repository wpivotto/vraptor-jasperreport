package br.com.caelum.vraptor.jasperreports.exporter;

import java.util.List;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.ComponentFactory;
import br.com.caelum.vraptor.jasperreports.ReportLoader;
import br.com.caelum.vraptor.jasperreports.decorator.ReportDecorator;

@Component
public class ReportExporterFactory implements ComponentFactory<ReportExporter> {

	private final ReportExporter exporter;

	public ReportExporterFactory(ReportLoader loader, List<ReportDecorator> decorators) {
		this.exporter = new DefaultExporter(loader, decorators);
	}

	public ReportExporter getInstance() {
		return exporter;
	}

}