package br.com.caelum.vraptor.jasperreports.formats;

import javax.enterprise.context.ApplicationScoped;

/**
 * @author William Pivotto
 */

@ApplicationScoped
public class BMP extends ImageFormat {
	
	public BMP() {
		super("bmp");
	}

}
