package br.com.caelum.vraptor.jasperreports.formats;

import javax.enterprise.context.ApplicationScoped;

/**
 * @author William Pivotto
 */

@ApplicationScoped
public class Png extends ImageFormat {
	
	public Png() {
		super("png");
	}

}
