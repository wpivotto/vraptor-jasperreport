package br.com.caelum.vraptor.jasperreports.serializer;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.jasperreports.Report;
import br.com.caelum.vraptor.jasperreports.ReportFormatResolver;
import br.com.caelum.vraptor.jasperreports.exporter.ReportExporter;
import br.com.caelum.vraptor.jasperreports.formats.ExportFormat;
import br.com.caelum.vraptor.view.Results;

import com.lowagie.text.pdf.codec.Base64;

/**
 * Builds URIs, defined by RFC 2397, that allows you to embed reports in browsers
 * @author William Pivotto
 */

@RequestScoped
public class DataURISerializer implements ReportSerializer {

	@Inject private ReportExporter exporter;
	@Inject private ReportFormatResolver resolver;
	@Inject private Result result;
	
	/**
	 * Builds the URI based on the export format
	 * The data URIs have the following syntax:
	 * data:[<mimetype>][;charset][;base64],<data>
	 * @return URI defined by RFC 2397
	 */
	private String buildURI(Report report, ExportFormat format) {
		byte[] content = exporter.export(report).to(format);
		StringBuilder URI = new StringBuilder("data:");
		URI.append(format.getContentType());
		URI.append(";charset=utf-8;base64,");
		URI.append(Base64.encodeBytes(content));
		return URI.toString();
	}
	
	public void serialize(Report report, ExportFormat format) {
		SerializedReport content = content(report, format);
		result.use(Results.json()).withoutRoot().from(content).serialize();
	}  
	
	public void serialize(Report report) {
		serialize(report, resolver.getExportFormat());
	}
	
	public SerializedReport content(Report report, ExportFormat format) {
		String src = buildURI(report, format);
		String type = format.getContentType();
		return new SerializedReport(src, type);
	}
	
	public SerializedReport content(Report report) {
		return content(report, resolver.getExportFormat());
	}

}
