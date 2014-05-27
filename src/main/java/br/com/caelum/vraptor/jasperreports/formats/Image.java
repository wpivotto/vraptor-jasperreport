package br.com.caelum.vraptor.jasperreports.formats;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.imageio.ImageIO;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRGraphics2DExporter;
import net.sf.jasperreports.engine.export.JRGraphics2DExporterParameter;

import com.google.common.io.Closeables;

/**
 * @author William Pivotto
 */

@RequestScoped
public class Image extends AbstractExporter {

	private Float zoom = 1f;
	private Integer page = 0;
	private String format = "png";

	public String getContentType() {
		return "image/" + format;
	}

	public String getExtension() {
		return format;
	}

	public JRExporter setup() {
		try {
			return new JRGraphics2DExporter();
		} catch (JRException e) {
			throw new RuntimeException(e);
		}
	}

	public Image zoom(Float zoom) {
		this.zoom = zoom;
		return this;
	}

	public Image page(Integer page) {
		this.page = page;
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

		ByteArrayOutputStream output = new ByteArrayOutputStream();

		try {

			JRExporter exporter = setup();
			exporter.setParameters(parameters);
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, print.get(0));

			BufferedImage image = getPageImage(print.get(0));
			exporter.setParameter(JRGraphics2DExporterParameter.GRAPHICS_2D, image.getGraphics());
			exporter.setParameter(JRGraphics2DExporterParameter.ZOOM_RATIO, zoom);
			exporter.setParameter(JRExporterParameter.PAGE_INDEX, page);
			exporter.exportReport();

			ImageIO.write(image, format, output);
			return output.toByteArray();

		} catch (JRException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException("Unable to convert image to stream", e);
		} finally {
			Closeables.closeQuietly(output);
			parameters.clear();
		}
	}

	private BufferedImage getPageImage(JasperPrint print) {
		int width = (int) (print.getPageWidth() * zoom);
		int height = (int) (print.getPageHeight() * zoom);
		return new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	}

}
