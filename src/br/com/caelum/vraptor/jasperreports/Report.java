package br.com.caelum.vraptor.jasperreports;

import java.util.Collection;
import java.util.Map;


public interface Report<T> {
	
	String getTemplate();
	
	Map<String, Object> getParameters();
	
	Collection<T> getData();
	
	String getFileName();
	
	void addParameter(String parameter, Object value);
	

}
