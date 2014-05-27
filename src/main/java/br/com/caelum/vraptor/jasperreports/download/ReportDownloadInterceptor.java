package br.com.caelum.vraptor.jasperreports.download;

import java.io.IOException;
import java.io.OutputStream;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.caelum.vraptor.Accepts;
import br.com.caelum.vraptor.AfterCall;
import br.com.caelum.vraptor.InterceptionException;
import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.controller.ControllerMethod;
import br.com.caelum.vraptor.core.MethodInfo;
import br.com.caelum.vraptor.jasperreports.Report;
import br.com.caelum.vraptor.jasperreports.ReportFormatResolver;
import br.com.caelum.vraptor.jasperreports.exporter.ReportExporter;

@Intercepts
@RequestScoped
public class ReportDownloadInterceptor {

	@Inject private ReportExporter exporter;
	@Inject private HttpServletResponse response;
	@Inject private ReportFormatResolver resolver;
	@Inject private MethodInfo methodInfo;
	@Inject private Result result;
	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Accepts
    public boolean accepts(ControllerMethod method) {
        return Report.class.isAssignableFrom(method.getMethod().getReturnType());
    }
	
	@AfterCall
	public void intercept() {

		Report report = (Report) methodInfo.getResult();

		if (report == null) {
			if (result.used()) {
				return;
			} else
				throw new NullPointerException("You've just returned a Null Report. Consider redirecting to another page/logic");
		}

		ReportDownload download = new ReportDownload(report, resolver.getExportFormat(), resolver.doDownload());
		download.setExporter(exporter);
		logger.debug("Injecting {} in {}", exporter.getClass().getName(), download.getClass().getName());

		try {

			OutputStream output = response.getOutputStream();
			download.write(response);

			output.flush();
			output.close();

		} catch (IOException e) {
			throw new InterceptionException(e);
		}

	}

}
