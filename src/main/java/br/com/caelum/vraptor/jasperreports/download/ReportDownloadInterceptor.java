package br.com.caelum.vraptor.jasperreports.download;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.caelum.vraptor.InterceptionException;
import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.Lazy;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.core.InterceptorStack;
import br.com.caelum.vraptor.core.MethodInfo;
import br.com.caelum.vraptor.interceptor.ExecuteMethodInterceptor;
import br.com.caelum.vraptor.interceptor.ForwardToDefaultViewInterceptor;
import br.com.caelum.vraptor.interceptor.Interceptor;
import br.com.caelum.vraptor.jasperreports.Report;
import br.com.caelum.vraptor.jasperreports.ReportFormatResolver;
import br.com.caelum.vraptor.jasperreports.exporter.ReportExporter;
import br.com.caelum.vraptor.resource.ResourceMethod;

@Intercepts(after = ExecuteMethodInterceptor.class, before = ForwardToDefaultViewInterceptor.class)
@Lazy
public class ReportDownloadInterceptor implements Interceptor {

	private final ReportExporter exporter;
	private final HttpServletResponse response;
	private final ReportFormatResolver resolver;
	private final MethodInfo methodInfo;
	private final Result result;
	private final Logger logger = LoggerFactory.getLogger(getClass());

	public ReportDownloadInterceptor(ReportExporter exporter,
			HttpServletResponse response, ReportFormatResolver resolver,
			MethodInfo methodInfo, Result result) {
		this.exporter = exporter;
		this.response = response;
		this.resolver = resolver;
		this.methodInfo = methodInfo;
		this.result = result;
	}

	public boolean accepts(ResourceMethod method) {
		return Report.class.isAssignableFrom(method.getMethod().getReturnType());
	}

	public void intercept(InterceptorStack stack, ResourceMethod method, Object instance) throws InterceptionException {

		Report report = (Report) methodInfo.getResult();

		if (report == null) {
			if (result.used()) {
				stack.next(method, instance);
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
