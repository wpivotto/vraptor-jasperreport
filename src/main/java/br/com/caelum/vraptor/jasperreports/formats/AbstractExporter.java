package br.com.caelum.vraptor.jasperreports.formats;

import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;

public abstract class AbstractExporter implements ExportFormat {
	
	protected Map<JRExporterParameter, Object> parameters = new HashMap<JRExporterParameter, Object>();
	protected JRExporter exporter;
	
	public AbstractExporter(){
		defaultParameters();
	}
	
	public ExportFormat configure(JRExporterParameter parameter, Object value) {
		parameters.put(parameter, value);
		return this;
	}
	
	protected void defaultParameters(){
		configure(JRExporterParameter.CHARACTER_ENCODING, "UTF-8");
	}
	
	public JRExporter getExporter() {
		setup();
		exporter.setParameters(parameters);
		return exporter;
	}
	
	public boolean supportsBatchMode() {
		return true;
	}
	
	protected abstract void setup();
	
}
