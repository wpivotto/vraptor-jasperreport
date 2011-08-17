package br.com.caelum.vraptor.jasperreports;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import br.com.caelum.vraptor.InterceptionException;
import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.Lazy;
import br.com.caelum.vraptor.core.InterceptorStack;
import br.com.caelum.vraptor.core.MethodInfo;
import br.com.caelum.vraptor.interceptor.ExecuteMethodInterceptor;
import br.com.caelum.vraptor.interceptor.ForwardToDefaultViewInterceptor;
import br.com.caelum.vraptor.interceptor.Interceptor;
import br.com.caelum.vraptor.interceptor.download.Download;
import br.com.caelum.vraptor.resource.ResourceMethod;

@Intercepts(after=ExecuteMethodInterceptor.class, before=ForwardToDefaultViewInterceptor.class)
@Lazy
public class ReportDownloadInterceptor implements Interceptor {

	private final HttpServletResponse response;
	private final ReportFormatResolver resolver;
	private final MethodInfo methodInfo;
	
	public ReportDownloadInterceptor(HttpServletResponse response, ReportFormatResolver resolver, MethodInfo methodInfo) {
		this.response = response;
		this.resolver = resolver;
		this.methodInfo = methodInfo;
	}

	public boolean accepts(ResourceMethod method) {
		return Report.class.isAssignableFrom(method.getMethod().getReturnType());
	}

	public void intercept(InterceptorStack stack, ResourceMethod method, Object instance) throws InterceptionException {
		
		Report<?> report = (Report<?>) methodInfo.getResult();
		
		Download download = new ReportDownload(report, resolver.getExportFormat(), resolver.doDownload());
		
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
