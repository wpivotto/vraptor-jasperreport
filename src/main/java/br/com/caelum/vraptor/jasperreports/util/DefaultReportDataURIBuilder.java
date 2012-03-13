package br.com.caelum.vraptor.jasperreports.util;

import com.lowagie.text.pdf.codec.Base64;

import net.sf.jasperreports.engine.JRExporterParameter;
import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.jasperreports.Report;
import br.com.caelum.vraptor.jasperreports.ReportFormatResolver;
import br.com.caelum.vraptor.jasperreports.exporter.ReportExporter;
import br.com.caelum.vraptor.jasperreports.formats.ExportFormat;

@Component
public class DefaultReportDataURIBuilder implements ReportDataURIBuilder {

	private final ReportExporter exporter;
	private final ReportFormatResolver resolver;

	public DefaultReportDataURIBuilder(ReportExporter exporter,
			ReportFormatResolver resolver) {
		this.exporter = exporter;
		this.resolver = resolver;
	}

	/**
	 * The data URIs have the following syntax:
	 * data:[<mimetype>][;charset][;base64],<data>
	 */
	public String build(Report report, ExportFormat format) {
		String charset = extractCharset(format).toLowerCase();
		byte[] content = exporter.export(report).to(format);
		StringBuilder URI = new StringBuilder("data:");
		URI.append(format.getContentType());
		URI.append(";charset=").append(charset);
		URI.append(";base64,").append(Base64.encodeBytes(content));
		return URI.toString();
	}

	public String build(Report report) {
		return build(report, resolver.getExportFormat());
	}

	private String extractCharset(ExportFormat format) {
		return (String) format.getParameters().get(JRExporterParameter.CHARACTER_ENCODING);
	}

}
