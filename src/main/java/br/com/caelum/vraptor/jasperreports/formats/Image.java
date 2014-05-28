package br.com.caelum.vraptor.jasperreports.formats;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.imageio.ImageIO;

import net.sf.jasperreports.engine.DefaultJasperReportsContext;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.export.Exporter;
import net.sf.jasperreports.export.ExporterConfiguration;
import net.sf.jasperreports.export.ReportExportConfiguration;

/**
 * @author William Pivotto
 */

@ApplicationScoped
public class Image extends AbstractExporter {

	private Float zoom = 1.6f;
	private String format = "png";

	public String getContentType() {
		return "image/" + format;
	}

	public String getExtension() {
		return format;
	}

	@SuppressWarnings("rawtypes")
	public Exporter setup() {
		return null;
	}

	public Image zoom(Float zoom) {
		this.zoom = zoom;
		return this;
	}

	public Image png() {
		this.format = "png";
		return this;
	}

	public Image jpeg() {
		this.format = "jpeg";
		return this;
	}

	public boolean supportsBatchMode() {
		return false;
	}

	public byte[] toByteArray(List<JasperPrint> print) {
		try {
			ByteArrayOutputStream output = new ByteArrayOutputStream();
			for (JasperPrint jasperPrint : print) {
		        JasperPrintManager printManager = JasperPrintManager.getInstance(DefaultJasperReportsContext.getInstance());  
		        for(int page = 0; page < jasperPrint.getPages().size(); page++) {
		        	BufferedImage rendered_image = (BufferedImage)printManager.printToImage(jasperPrint, page, zoom); 
		        	ImageIO.write(rendered_image, format, output); 
		        }
			}
	        return output.toByteArray();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public ReportExportConfiguration getReportConfiguration() {
		return null;
	}

	public ExporterConfiguration getExporterConfiguration() {
		return null;
	}

}
