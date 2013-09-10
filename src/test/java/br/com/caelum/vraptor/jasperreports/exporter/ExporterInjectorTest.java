package br.com.caelum.vraptor.jasperreports.exporter;

import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

import java.lang.reflect.Method;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.core.InterceptorStack;
import br.com.caelum.vraptor.core.MethodInfo;
import br.com.caelum.vraptor.interceptor.Interceptor;
import br.com.caelum.vraptor.interceptor.download.Download;
import br.com.caelum.vraptor.interceptor.download.FileDownload;
import br.com.caelum.vraptor.jasperreports.download.BatchReportsDownload;
import br.com.caelum.vraptor.jasperreports.download.ReportDownload;
import br.com.caelum.vraptor.jasperreports.download.ReportsDownload;
import br.com.caelum.vraptor.resource.DefaultResourceMethod;
import br.com.caelum.vraptor.resource.ResourceMethod;

public class ExporterInjectorTest {
	
	private ExporterInjector injector;
	
	@Mock private Result result;
	@Mock private ReportExporter exporter;
	@Mock private MethodInfo methodInfo;
	@Mock private ResourceMethod resourceMethod;
	@Mock private InterceptorStack stack;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		injector = new ExporterInjector(exporter, methodInfo, result);
	}
	
	@Test
	public void whenResultIsAReportDownloadShouldInjectExporter() {
		
		ReportDownload reportDownload = mock(ReportDownload.class);
		
		when(methodInfo.getResult()).thenReturn(reportDownload);
		
		injector.intercept(stack, resourceMethod, null);
		
		verify(reportDownload).setExporter(exporter);
		verify(stack).next(resourceMethod, null);
	}
	
	@Test
	public void whenResultIsABatchReportsDownloadShouldInjectExporter() {
		
		BatchReportsDownload batchReportsDownload = mock(BatchReportsDownload.class);
		
		when(methodInfo.getResult()).thenReturn(batchReportsDownload);
		
		injector.intercept(stack, resourceMethod, null);
		
		verify(batchReportsDownload).setExporter(exporter);
		verify(stack).next(resourceMethod, null);
	}
	
	@Test
	public void whenResultIsAReportsDownloadShouldInjectExporter() {
		
		ReportsDownload reportsDownload = mock(ReportsDownload.class);
		
		when(methodInfo.getResult()).thenReturn(reportsDownload);
		
		injector.intercept(stack, resourceMethod, null);
		
		verify(reportsDownload).setExporter(exporter);
		verify(stack).next(resourceMethod, null);
	}

	@Test
	public void whenResultIsNullAndResultWasUsedShouldDoNothing() {
		
		when(methodInfo.getResult()).thenReturn(null);
		when(result.used()).thenReturn(true);
		
		injector.intercept(stack, resourceMethod, null);
		
		verify(stack).next(resourceMethod, null);
		verifyZeroInteractions(exporter);
	}
	
	@Test
	public void whenResultIsNullAndResultWasNotUsedShouldThrowNPE() throws Exception {
		
		when(methodInfo.getResult()).thenReturn(null);
		when(result.used()).thenReturn(false);

		try {
			injector.intercept(stack, resourceMethod, null);
			fail("expected NullPointerException");
		} catch (NullPointerException e) {
			verifyZeroInteractions(exporter);
		}
	}
	
	@Test
	public void shouldAcceptReportDownloadReturn() throws Exception {
		Method method = FakeResource.class.getMethod("reportDownload");
		assertThat(injector, accepts(method));
	}
	
	@Test
	public void shouldAcceptReportsDownloadReturn() throws Exception {
		Method method = FakeResource.class.getMethod("reportsDownload");
		assertThat(injector, accepts(method));
	}
	
	@Test
	public void shouldAcceptBatchReportsDownloadReturn() throws Exception {
		Method method = FakeResource.class.getMethod("batchReportsDownload");
		assertThat(injector, accepts(method));
	}
	
	@Test
	public void shouldAcceptDownloadReturn() throws Exception {
		Method method = FakeResource.class.getMethod("download");
		assertThat(injector, accepts(method));
	}
	
	@Test
	public void shouldAcceptFileDownloadReturn() throws Exception {
		Method method = FakeResource.class.getMethod("fileDownload");
		assertThat(injector, accepts(method));
	}
	
	@Test
    public void shouldNotAcceptStringReturn() throws Exception {
        Method method = FakeResource.class.getMethod("string");
        assertThat(injector, not(accepts(method)));
    }
	
	private Matcher<Interceptor> accepts(final Method method) {
		return new TypeSafeMatcher<Interceptor>() {

			public void describeTo(Description description) {
				description.appendText("the method ").appendValue(method);
			}

			public boolean matchesSafely(Interceptor item) {
				ResourceMethod m = DefaultResourceMethod.instanceFor(method.getDeclaringClass(), method);
				return injector.accepts(m);
			}
		};
    }
	
	static class FakeResource {
		public String string() {
			return null;
		}
		public ReportDownload reportDownload() {
			return null;
		}
		public ReportsDownload reportsDownload() {
			return null;
		}
		public BatchReportsDownload batchReportsDownload() {
			return null;
		}
		public Download download() {
			return null;
		}
		public FileDownload fileDownload() {
		    return null;
		}
	}

}
