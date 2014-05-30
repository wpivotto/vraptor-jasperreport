package br.com.caelum.vraptor.jasperreports;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

import net.sf.jasperreports.engine.DefaultJasperReportsContext;
import net.sf.jasperreports.engine.JRPropertiesUtil;

/**
 * http://jasperreports.sourceforge.net/config.reference.html
 */
@ApplicationScoped
public class ReportPropertiesProducer {

	@Produces
	public JRPropertiesUtil create() {
		return JRPropertiesUtil.getInstance(DefaultJasperReportsContext.getInstance());
	}
	
}
