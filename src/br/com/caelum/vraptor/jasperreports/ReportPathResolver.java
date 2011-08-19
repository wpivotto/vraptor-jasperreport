package br.com.caelum.vraptor.jasperreports;

import java.io.File;

import javax.servlet.ServletContext;

import br.com.caelum.vraptor.ioc.ApplicationScoped;
import br.com.caelum.vraptor.ioc.Component;

@Component
@ApplicationScoped
public class ReportPathResolver {

	private final ServletContext context;
	private static final String DEFAULT_REPORTS_PATH = "/WEB-INF/reports";
	private static final String DEFAULT_SUBREPORTS_PATH = "/WEB-INF/reports/subreports";
	private static final String DEFAULT_IMAGES_PATH = "/WEB-INF/reports/images";
	private static final String SEPARATOR = File.separator;
	
	public ReportPathResolver(ServletContext context) {
		this.context = context;
	}
	
	public String getPathFor(Report<?> report){
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
}