package br.com.caelum.vraptor.jasperreports;

import java.io.File;
import java.util.Locale;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.caelum.vraptor.ioc.ApplicationScoped;
import br.com.caelum.vraptor.ioc.Component;

@Component
@ApplicationScoped
public class ReportPathResolver {

	private final ServletContext context;
	public static final String DEFAULT_REPORTS_PATH = "/WEB-INF/reports";
	public static final String DEFAULT_SUBREPORTS_PATH = "/WEB-INF/reports/subreports";
	public static final String DEFAULT_IMAGES_PATH = "/WEB-INF/reports/images";
	private static final String DEFAULT_BUNDLE_NAME = "i18n_";
	private static final String SEPARATOR = File.separator;
	private final Logger logger = LoggerFactory.getLogger(ReportPathResolver.class);
	
	public ReportPathResolver(ServletContext context) {
		this.context = context;
		logger.debug("REPORT_DIR --> " + getReportsPath());
		logger.debug("SUBREPORT_DIR --> " + getSubReportsPath());
		logger.debug("IMAGES_DIR --> " + getImagesPath());
	}
	
	public String getPathFor(Report report){
		return getReportsPath() + report.getTemplate();
	}
	
	public String getReportsPath(){
		return context.getRealPath(getRelativeReportsPath()) + SEPARATOR;
	}
	
	public String getSubReportsPath(){
		return context.getRealPath(getRelativeSubReportsPath()) + SEPARATOR;
	}
	
	public String getImagesPath(){
		return context.getRealPath(getRelativeImagesPath()) + SEPARATOR;
	}
	
	public String getResourceBundleFor(Locale locale){
		return getReportsPath() + getBundleName() + locale.toString() + ".properties";
	}
	
	private String getRelativeImagesPath(){
		String param = context.getInitParameter("vraptor.images.path");
		return param != null ? param.trim() : DEFAULT_IMAGES_PATH;
	}

	private String getRelativeSubReportsPath(){
		String param = context.getInitParameter("vraptor.subreports.path");
		return param != null ? param.trim() : DEFAULT_SUBREPORTS_PATH;
	}
	
	private String getRelativeReportsPath(){
		String param = context.getInitParameter("vraptor.reports.path");
		return param != null ? param.trim() : DEFAULT_REPORTS_PATH;
	}
	
	private String getBundleName(){
		String param = context.getInitParameter("vraptor.reports.resourcebundle.name");
		return param != null ? param.trim() : DEFAULT_BUNDLE_NAME;
	}

	public String getImagesURI() {
		return context.getContextPath() + "/report.image?image=";
	}

}