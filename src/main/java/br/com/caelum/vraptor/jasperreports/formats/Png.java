package br.com.caelum.vraptor.jasperreports.formats;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.imageio.ImageIO;

import net.sf.jasperreports.engine.DefaultJasperReportsContext;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRPropertiesUtil;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.export.JRGraphics2DExporter;
import net.sf.jasperreports.engine.util.JRProperties;
import net.sf.jasperreports.export.Exporter;
import net.sf.jasperreports.export.ExporterConfiguration;
import net.sf.jasperreports.export.ReportExportConfiguration;
import net.sf.jasperreports.export.SimpleGraphics2DExporterConfiguration;
import net.sf.jasperreports.export.SimpleGraphics2DReportConfiguration;

/**
 * @author William Pivotto
 */

@ApplicationScoped
public class Png extends AbstractExporter {
	
	protected SimpleGraphics2DReportConfiguration reportConfiguration = new SimpleGraphics2DReportConfiguration();
	protected SimpleGraphics2DExporterConfiguration exportConfiguration = new SimpleGraphics2DExporterConfiguration();
	
	public Png() {
		setPage(0);
		setZoom(1.6f);
	}
	
	public String getContentType() {
		return "image/png";
	}

	public String getExtension() {
		return "png";
	}

	@SuppressWarnings("rawtypes")
	public Exporter setup() {
		try {
			return new JRGraphics2DExporter();
		} catch (JRException e) {
			throw new RuntimeException(e);
		}
	}

	public Png setZoom(Float zoom) {
		reportConfiguration.setZoomRatio(zoom);
		return this;
	}
	
	public Png setPage(Integer page) {
		reportConfiguration.setPageIndex(page);
		return this;
	}
	
	public Integer getPage() {
		return reportConfiguration.getPageIndex();
	}

	public boolean supportsBatchMode() {
		return false;
	}
	
	public ReportExportConfiguration getReportConfiguration() {
		return reportConfiguration;
	}

	public ExporterConfiguration getExporterConfiguration() {
		return exportConfiguration;
	}
	
	public byte[] toByteArray(List<JasperPrint> printList) {
		
		try {
			
			ByteArrayOutputStream content = new ByteArrayOutputStream();
			JasperPrintManager printManager = JasperPrintManager.getInstance(DefaultJasperReportsContext.getInstance());  
			Integer page = reportConfiguration.getPageIndex();
			Float zoom = reportConfiguration.getZoomRatio();
			
			if (printList != null) {
				JasperPrint print = printList.get(0);
				if (print != null) {
					BufferedImage image = (BufferedImage)printManager.printToImage(print, page, zoom);
					ImageIO.write(image, "png", content);
				}
			}	
			return content.toByteArray();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
