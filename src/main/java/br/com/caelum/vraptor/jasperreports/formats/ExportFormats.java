package br.com.caelum.vraptor.jasperreports.formats;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.caelum.vraptor.ioc.Component;


/**
 * Collection of factory methods to objects that represent export formats.
 * @author William Pivotto
 */

@Component
public class ExportFormats {
	
	private Map<String, ExportFormat> exporters = new HashMap<String, ExportFormat>();

	public ExportFormats(List<ExportFormat> formats){
		for(ExportFormat format : formats){
			exporters.put(format.getExtension(), format);
		}
	}
	
	public static Pdf pdf(){
		return new Pdf();
	}
	
	public static Csv csv(){
		return new Csv();
	}
	
	public static Xls xls(){
		return new Xls();
	}
	
	public static Rtf rtf(){
		return new Rtf();
	}
	
	public static Docx docx(){
		return new Docx();
	}
	
	public static Odt odt(){
		return new Odt();
	}
	
	public static Txt txt(){
		return new Txt();
	}
	
	public Html html(){
		return (Html) byExtension("html");
	}

	public static Ods ods(){
		return new Ods();
	}
	
	public static Pptx pptx(){
		return new Pptx();
	}
	
	public static Xhtml xhtml(){
		return new Xhtml();
	}
	
	public static Xlsx xlsx(){
		return new Xlsx();
	}
	
	public static Image png(){
		return new Image();
	}
	
	public static Image png(Integer page, Float zoom){
		Image image = new Image();
		image.page(page);
		image.zoom(zoom);
		return image;
	}
	
	public static Image jpeg(){
		Image image = new Image();
		image.jpeg();
		return image;
	}
	
	public static Image jpeg(Integer page, Float zoom){
		Image image = new Image();
		image.jpeg();
		image.page(page);
		image.zoom(zoom);
		return image;
	}
	
	public ExportFormat byExtension(String extension){
		if(supports(extension))
			return exporters.get(extension.toLowerCase());
		return pdf(); // default
	}
	
	public boolean supports(String format){
		return exporters.containsKey(format.toLowerCase());
	}
}
