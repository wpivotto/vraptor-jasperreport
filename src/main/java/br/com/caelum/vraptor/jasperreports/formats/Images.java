package br.com.caelum.vraptor.jasperreports.formats;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.enterprise.context.ApplicationScoped;
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

@ApplicationScoped
public class Images extends AbstractExporter {

	protected Float zoom = 1.6f;

	public String getContentType() {
		return "application/zip";
	}

	public String getExtension() {
		return "zip";
	}

	@SuppressWarnings("rawtypes")
	public Exporter setup() {
		return null;
	}

	public Images zoom(Float zoom) {
		this.zoom = zoom;
		return this;
	}

	public boolean supportsBatchMode() {
		return false;
	}

	public byte[] toByteArray(List<JasperPrint> print) {
		
		try {
			List<BufferedImage> images = renderImages(print);
			ByteArrayOutputStream output = new ByteArrayOutputStream();
			int page = 1;
			ZipOutputStream zip = new ZipOutputStream(output);
			for (BufferedImage image : images) {
				ByteArrayOutputStream content = new ByteArrayOutputStream();
				ImageIO.write(image, "png", content);
				ZipEntry entry = new ZipEntry("Page" + page + ".png");
				entry.setSize(content.size());
				entry.setMethod(ZipEntry.DEFLATED);
				zip.putNextEntry(entry);
				zip.write(content.toByteArray());
				zip.closeEntry();
				content.close();
				page++;
			}
			zip.flush();
			zip.close();
			return output.toByteArray();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
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
	
	public ReportExportConfiguration getReportConfiguration() {
		return null;
	}

	public ExporterConfiguration getExporterConfiguration() {
		return null;
	}

}
