package br.com.caelum.vraptor.jasperreports.formats;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import net.sf.jasperreports.engine.DefaultJasperReportsContext;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.export.Exporter;
import net.sf.jasperreports.export.ExporterConfiguration;
import net.sf.jasperreports.export.ReportExportConfiguration;

/**
 * @author William Pivotto
 */

public abstract class ImageFormat extends AbstractExporter {
	
	protected Float zoom = 1.6f;
	protected final String format;
	
	public ImageFormat(String format) {
		this.format = format;
	}

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

	public void setZoom(Float zoom) {
		this.zoom = zoom;
	}
	
	public boolean supportsBatchMode() {
		return false;
	}
	
	public ReportExportConfiguration getReportConfiguration() {
		return null;
	}

	public ExporterConfiguration getExporterConfiguration() {
		return null;
	}
	
	public byte[] toByteArray(List<JasperPrint> printList) {
			byte[] output = null;
		    try {
		        ByteArrayOutputStream buffer = new ByteArrayOutputStream(30000);
		        List<BufferedImage> images = renderImages(printList);
		        BufferedImage combinedImage = mergeImages(images);
		        ImageIO.write(combinedImage, format.toUpperCase(), buffer);
		        buffer.flush();
		        output = buffer.toByteArray();
		        buffer.close();
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		    return output;
		}
	
	private BufferedImage mergeImages(List<BufferedImage> images) {
		int width = 0;
		int height = 0;

		for (BufferedImage image : images) {
			width = Math.max(width, image.getWidth());
			height += image.getHeight();
		}

		BufferedImage combined = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

		int currentHeight = 0;
		// paint all pages
		Graphics g = combined.getGraphics();
		for (BufferedImage image : images) {
			g.drawImage(image, 0, currentHeight, null);
			currentHeight += image.getHeight();
		}
		return combined;
	}

	private List<BufferedImage> renderImages(List<JasperPrint> print) {
		List<BufferedImage> images = new ArrayList<BufferedImage>();
		JasperPrintManager printManager = JasperPrintManager.getInstance(DefaultJasperReportsContext.getInstance());  
		for (JasperPrint jasperPrint : print) {
	        for(int page = 0; page < jasperPrint.getPages().size(); page++) {
				try {
					BufferedImage renderedImage = (BufferedImage)printManager.printToImage(jasperPrint, page, zoom);
					images.add(renderedImage);
				} catch (JRException e) {
					throw new RuntimeException(e);
				} 
	        }
		}
		return images;
	}

}
