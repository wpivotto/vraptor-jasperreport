package br.com.caelum.vraptor.jasperreports.formats;

import br.com.caelum.vraptor.jasperreports.ExportFormat;

/**
 * @author William Pivotto
 */

public class Formats {
	
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

}
