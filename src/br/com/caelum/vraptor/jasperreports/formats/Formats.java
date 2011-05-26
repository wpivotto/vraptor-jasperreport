package br.com.caelum.vraptor.jasperreports.formats;

import java.util.HashMap;
import java.util.Map;

import br.com.caelum.vraptor.jasperreports.ExportFormat;

/**
 * @author William Pivotto
 */

public class Formats {
	
	private static final Map<String, ExportFormat> exporters = new HashMap<String, ExportFormat>();
	
	static {
		exporters.put("pdf", Pdf());
		exporters.put("csv", Csv());
		exporters.put("xls", Xls());
		exporters.put("rtf", Rtf());
		exporters.put("docx", Docx());
		exporters.put("odt", Odt());
		exporters.put("txt", Txt());
		exporters.put("html", Html());
	};
	
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

	public static ExportFormat byExtension(String extension){
		String key = extension.toLowerCase();
		if(exporters.containsKey(key))
			return exporters.get(key);
		throw new IllegalArgumentException("Format is not supported");
	}
	
}
