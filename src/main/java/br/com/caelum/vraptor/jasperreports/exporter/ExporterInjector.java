package br.com.caelum.vraptor.jasperreports.exporter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.caelum.vraptor.InterceptionException;
import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.Lazy;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.core.InterceptorStack;
import br.com.caelum.vraptor.core.MethodInfo;
import br.com.caelum.vraptor.interceptor.ExecuteMethodInterceptor;
import br.com.caelum.vraptor.interceptor.Interceptor;
import br.com.caelum.vraptor.interceptor.download.Download;
import br.com.caelum.vraptor.interceptor.download.DownloadInterceptor;
import br.com.caelum.vraptor.jasperreports.download.BatchReportsDownload;
import br.com.caelum.vraptor.jasperreports.download.ReportDownload;
import br.com.caelum.vraptor.jasperreports.download.ReportsDownload;
import br.com.caelum.vraptor.resource.ResourceMethod;

@Intercepts(after=ExecuteMethodInterceptor.class, before=DownloadInterceptor.class)
@Lazy
public class ExporterInjector implements Interceptor {

	private final ReportExporter exporter;
	private final MethodInfo methodInfo;
	private final Result result;
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	public ExporterInjector(ReportExporter exporter, MethodInfo methodInfo, Result result) {
		this.exporter = exporter;
		this.methodInfo = methodInfo;
		this.result = result;
	}

	public boolean accepts(ResourceMethod method) {
		Class<?> type = method.getMethod().getReturnType();
		return Download.class.isAssignableFrom(type); 
	}

	public void intercept(InterceptorStack stack, ResourceMethod method, Object instance) throws InterceptionException {
		
		Object reportDownload =  methodInfo.getResult();
		if (reportDownload == null) {
			if (result.used()) {
				stack.next(method, instance);
				return;
			} else
				throw new NullPointerException("You've just returned a Null ReportDownload. Consider redirecting to another page/logic");
		}
		
		if(reportDownload instanceof ReportDownload){
			ReportDownload download = (ReportDownload) reportDownload;
			download.setExporter(exporter);
		}
		
		if(reportDownload instanceof ReportsDownload){
			ReportsDownload download = (ReportsDownload) reportDownload;
			download.setExporter(exporter);
		}
		
		if(reportDownload instanceof BatchReportsDownload){
			BatchReportsDownload download = (BatchReportsDownload) reportDownload;
			download.setExporter(exporter);
		}
		
		logger.debug("Injecting {} in {}", exporter.getClass().getName(), reportDownload.getClass().getName());
		
		stack.next(method, instance);
		
	}

}