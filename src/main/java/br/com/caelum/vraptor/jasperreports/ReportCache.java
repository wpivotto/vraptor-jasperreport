package br.com.caelum.vraptor.jasperreports;

import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;

import net.sf.jasperreports.engine.JasperReport;

@ApplicationScoped
public class ReportCache {

	private Map<String, JasperReport> cache = new HashMap<String, JasperReport>();
	
	public JasperReport get(String template) {
		return cache.get(template);
	}
	
	public void put(String template, JasperReport report) {
		cache.put(template, report);
	}
	
	public boolean contains(String template){
		return cache.containsKey(template);
	}
	
	
}
