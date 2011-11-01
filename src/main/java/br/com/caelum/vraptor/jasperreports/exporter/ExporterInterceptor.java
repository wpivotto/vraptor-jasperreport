package br.com.caelum.vraptor.jasperreports.exporter;

import br.com.caelum.vraptor.InterceptionException;
import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.Lazy;
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
public class ExporterInterceptor implements Interceptor {

	private final ReportExporter exporter;
	private final MethodInfo methodInfo;
	
	public ExporterInterceptor(ReportExporter exporter, MethodInfo methodInfo) {
		this.exporter = exporter;
		this.methodInfo = methodInfo;
	}

	public boolean accepts(ResourceMethod method) {
		Class<?> type = method.getMethod().getReturnType();
		return Download.class.isAssignableFrom(type) || 
		       type == ReportDownload.class || 
		       type == ReportsDownload.class ||
		       type == BatchReportsDownload.class;
	}

	public void intercept(InterceptorStack stack, ResourceMethod method, Object instance) throws InterceptionException {
		
		Object result =  methodInfo.getResult();
		
		if(result instanceof ReportDownload){
			ReportDownload download = (ReportDownload)result;
			download.setExporter(exporter);
		}
		
		if(result instanceof ReportsDownload){
			ReportsDownload download = (ReportsDownload)result;
			download.setExporter(exporter);
		}
		
		if(result instanceof BatchReportsDownload){
			BatchReportsDownload download = (BatchReportsDownload)result;
			download.setExporter(exporter);
		}
		
		stack.next(method, instance);
		
	}

}