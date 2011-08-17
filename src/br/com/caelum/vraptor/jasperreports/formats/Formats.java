package br.com.caelum.vraptor.jasperreports.formats;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.jasperreports.ExportFormat;

/**
 * @author William Pivotto
 */

@Component
public class Formats {
	
	private Map<String, ExportFormat> exporters = new HashMap<String, ExportFormat>();
	
	public Formats(List<ExportFormat> formats){
		for(ExportFormat format : formats){
			exporters.put(format.getExtension(), format);
		}
	}
	
	public static ExportFormat Pdf(){
		return new Pdf();
	}
	
	public static ExportFormat Csv(){
		return new Csv();
	}
	
	public static ExportFormat Xls(){
		return new Xls();
	}
	
	public static ExportFormat Rtf(){
		return new Rtf();
	}
	
	public static ExportFormat Docx(){
		return new Docx();
	}
	
	public static ExportFormat Odt(){
		return new Odt();
	}
	
	public static ExportFormat Txt(){
		return new Txt();
	}
	
	public static ExportFormat Html(){
		return new Html();
	}

	public ExportFormat byExtension(String extension){
		if(supports(extension))
			return exporters.get(extension.toLowerCase());
		return Pdf(); // default
	}
	
	public boolean supports(String format){
		return exporters.containsKey(format.toLowerCase());
	}
}
