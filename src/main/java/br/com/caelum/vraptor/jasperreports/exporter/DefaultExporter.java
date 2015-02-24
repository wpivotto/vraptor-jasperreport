package br.com.caelum.vraptor.jasperreports.exporter;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.j2ee.servlets.ImageServlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.caelum.vraptor.jasperreports.Report;
import br.com.caelum.vraptor.jasperreports.ReportLoader;
import br.com.caelum.vraptor.jasperreports.decorator.ReportDecorator;
import br.com.caelum.vraptor.jasperreports.formats.ExportFormat;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * Export a report into a specific format
 * 
 * @author William Pivotto
 * 
 */
@RequestScoped
public class DefaultExporter implements ReportExporter {

	@Inject private ReportLoader loader;
	@Inject @Any private Instance<ReportDecorator> decorators;
	@Inject private HttpSession session;
	private Collection<Report> reports = Lists.newArrayList();
	private final Logger logger = LoggerFactory.getLogger(DefaultExporter.class);

	public ReportExporter export(Report report) {
		this.reports.add(report);
		return this;
	}

	public ReportExporter export(Collection<Report> reports) {
		this.reports = reports;
		return this;
	}

	public byte[] to(ExportFormat format) {
		try {
			List<JasperPrint> print = fillAll(format);
			return format.toByteArray(print);
		} catch (JRException e) {
			throw new RuntimeException(e);
		}
	}

	private List<JasperPrint> fillAll(ExportFormat format) throws JRException {
		List<JasperPrint> printList = new ArrayList<JasperPrint>();
		for (Report report : reports) {
			printList.add(fill(report));
		}
		configImageServlet(format, printList);
		return printList;
	}

	private JasperPrint fill(Report report) throws JRException {
		JasperReport jr = loader.load(report);
		Map<String, Object> parameters = getParameters(report);
		decorate(report);
		logParameters(report);
		return printReport(jr, parameters, getDataSource(report));
	}

	private JasperPrint printReport(JasperReport jr, Map<String, Object> parameters, JRDataSource datasource) throws JRException {
		JasperPrint print = null;
		final Object dsConn = parameters.get(Connection.class.getName());
		if (dsConn != null && dsConn instanceof Connection) {
			print = JasperFillManager.fillReport(jr, parameters, (Connection) dsConn);
		} else {
			print = JasperFillManager.fillReport(jr, parameters, datasource);
		}
		return print;
	}

	private JRDataSource getDataSource(Report report) {
		if (report.getData() != null)
			return new JRBeanCollectionDataSource(report.getData(), false);
		else if (!report.getParameters().containsKey(Connection.class.getName())) {
			logger.warn("You are willing to generate a report, but there is no valid datasource, using empty one");
			return new JREmptyDataSource();
		}
		return null;
	}

	private Map<String, Object> getParameters(Report report) {
		Map<String, Object> parameters = report.getParameters();
		if (parameters == null) {
			parameters = Maps.newHashMap();
			logger.warn("You are willing to generate a report, but there is no valid parameters");
		}
		
		return parameters;
	}

	private void decorate(Report report) {
		for (ReportDecorator decorator : decorators) {
			decorator.decorate(report);
		}
	}
	
	private void logParameters(Report report) {
		if (logger.isDebugEnabled()) {
			try {
				Map<String, Object> parameters = report.getParameters();
				if (!parameters.isEmpty()) {
					logger.debug("Parameters passed to the template {} ", report.getTemplate());
					for (Map.Entry<String, Object> param : parameters.entrySet()) {
					    logger.debug("$P{" + param.getKey() + "} = " + param.getValue().toString());
					}
				}
			} catch (Exception e) {}
		}
	}

	private void configImageServlet(ExportFormat format, List<JasperPrint> printList) {
		session.setAttribute(ImageServlet.DEFAULT_JASPER_PRINT_LIST_SESSION_ATTRIBUTE, printList);
	}
	
}
