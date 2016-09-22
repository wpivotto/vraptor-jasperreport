package br.com.caelum.vraptor.jasperreports;

import java.io.File;
import java.util.Locale;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

@ApplicationScoped
public class ReportPathResolver {

	@Inject private ServletContext context;
	@Inject private HttpServletRequest request;
	
	public static final String DEFAULT_REPORTS_PATH = "/WEB-INF/reports";
	public static final String DEFAULT_SUBREPORTS_PATH = "/WEB-INF/reports/subreports";
	public static final String DEFAULT_IMAGES_PATH = "/WEB-INF/reports/images";
	public static final String DEFAULT_REPORT_GENERATION_PATH = "/WEB-INF/reports/tmp";
	private static final String DEFAULT_BUNDLE_NAME = "i18n_";
	private static final String SEPARATOR = File.separator;
	
	public String getPathFor(Report report){
		return getReportsPath() + report.getTemplate();
	}
	
	public String getReportsPath(){
		return getContext().getRealPath(getRelativeReportsPath()) + SEPARATOR;
	}
	
	public String getSubReportsPath(){
		return getContext().getRealPath(getRelativeSubReportsPath()) + SEPARATOR;
	}
	
	public String getImagesPath(){
		return getContext().getRealPath(getRelativeImagesPath()) + SEPARATOR;
	}
	
	public String getReportGenerationPath(){
		return getContext().getRealPath(getRelativeReportGenerationPath()) + SEPARATOR;
	}
	
	public String getResourceBundleFor(Locale locale){
		return getReportsPath() + getBundleName() + locale.toString() + ".properties";
	}
	
	private String getRelativeImagesPath(){
		String param = getContext().getInitParameter("vraptor.images.path");
		return param != null ? param.trim() : DEFAULT_IMAGES_PATH;
	}

	private String getRelativeSubReportsPath(){
		String param = getContext().getInitParameter("vraptor.subreports.path");
		return param != null ? param.trim() : DEFAULT_SUBREPORTS_PATH;
	}
	
	private String getRelativeReportsPath(){
		String param = getContext().getInitParameter("vraptor.reports.path");
		return param != null ? param.trim() : DEFAULT_REPORTS_PATH;
	}
	
	private String getRelativeReportGenerationPath(){
		String param = getContext().getInitParameter("vraptor.reports.generation.path");
		return param != null ? param.trim() : DEFAULT_REPORT_GENERATION_PATH;
	}
	
	private String getBundleName(){
		String param = getContext().getInitParameter("vraptor.reports.resourcebundle.name");
		return param != null ? param.trim() : DEFAULT_BUNDLE_NAME;
	}
	
	private ServletContext getContext(){
		String tryPath = request.getSession().getServletContext().getRealPath(DEFAULT_REPORTS_PATH) ;
		return tryPath != null ? request.getSession().getServletContext() : context;
	}

}