package br.com.caelum.vraptor.jasperreports.formats;

import javax.enterprise.context.ApplicationScoped;

/**
 * @author William Pivotto
 */

@ApplicationScoped
public class Jpeg extends ImageFormat {
	
	public Jpeg() {
		super("jpeg");
	}

}
