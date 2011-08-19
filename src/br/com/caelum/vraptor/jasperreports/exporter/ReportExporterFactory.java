package br.com.caelum.vraptor.jasperreports.exporter;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.ComponentFactory;
import br.com.caelum.vraptor.jasperreports.ReportLoader;


@Component
public class ReportExporterFactory implements ComponentFactory<ReportExporter> {

	private final ReportExporter exporter;

	public ReportExporterFactory(ReportLoader loader) {
		this.exporter = new JasperExporter(loader);
	}
	
	@Override
	public ReportExporter getInstance() {
		return exporter;
	}

}
