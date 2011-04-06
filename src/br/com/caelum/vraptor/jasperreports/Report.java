package br.com.caelum.vraptor.jasperreports;

import java.util.Collection;
import java.util.Map;

/**
 * Generic report
 *
 * @author William Pivotto
 *
 */

public interface Report<T> {
	
	/**
     * Specifies the path to locate template file (.jrxml or .jasper)
     */
	String getTemplate();
	
	/**
     * Return all included parameters via Report.addParameter();
     */
	Map<String, Object> getParameters();
	
	/**
     * Specifies the content to fill the report
     */
	Collection<T> getData();
	
	/**
     * Specifies the output file name
     */
	String getFileName();
	
	/**
     * Stores a parameter in the report
     * @param parameter a String specifying the key of the parameter
     * @param value the object to be stored
     */
	void addParameter(String parameter, Object value);
	

}
