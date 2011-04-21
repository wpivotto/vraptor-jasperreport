package br.com.caelum.vraptor.jasperreports.formats;

import br.com.caelum.vraptor.jasperreports.ExportFormat;

/**
 * @author William Pivotto
 */

public class Formats {
	
	public static ExportFormat PDF(){
		return new PDF();
	}
	
	public static ExportFormat CSV(){
		return new CSV();
	}
	
	public static ExportFormat XLS(){
		return new XLS();
	}
	
	public static ExportFormat RTF(){
		return new RTF();
	}
	
	public static ExportFormat DOCX(){
		return new DOCX();
	}
	
	public static ExportFormat ODT(){
		return new ODT();
	}
	
	public static ExportFormat TXT(){
		return new TXT();
	}
	
	public static ExportFormat HTML(){
		return new HTML();
	}

}
