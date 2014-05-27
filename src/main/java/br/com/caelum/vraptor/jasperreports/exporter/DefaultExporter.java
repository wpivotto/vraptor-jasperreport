package br.com.caelum.vraptor.jasperreports.exporter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

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
import br.com.caelum.vraptor.jasperreports.formats.Html;

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

	private Collection<Report> reports = Lists.newArrayList();
	@Inject private ReportLoader loader;
	@Inject @Any private Instance<ReportDecorator> decorators;
	@Inject private HttpSession session;
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

		configImageServlet(format, printList.get(0));

		return printList;

	}

	private JasperPrint fill(Report report) throws JRException {

		for (ReportDecorator decorator : decorators) {
			decorator.decorate(report);
		}

		JasperReport jr = loader.load(report);
		JRBeanCollectionDataSource data = new JRBeanCollectionDataSource(report.getData(), false);

		Map<String, Object> parameters = report.getParameters();

		if (parameters == null) {
			parameters = Maps.newHashMap();
			logger.warn("You are willing to generate a report, but there is no valid parameters");
		}

		JasperPrint print = JasperFillManager.fillReport(jr, parameters, data);

		return print;

	}

	private void configImageServlet(ExportFormat format, JasperPrint print) {
		if (format.getClass().equals(Html.class) && print != null)
			session.setAttribute(ImageServlet.DEFAULT_JASPER_PRINT_SESSION_ATTRIBUTE, print);
	}

}
