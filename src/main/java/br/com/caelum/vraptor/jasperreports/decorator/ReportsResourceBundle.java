package br.com.caelum.vraptor.jasperreports.decorator;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.caelum.vraptor.jasperreports.ReportPathResolver;
import br.com.caelum.vraptor.util.EmptyBundle;

@RequestScoped
public class ReportsResourceBundle extends ResourceBundle {

	@Inject private Locale locale;
	@Inject private ResourceBundle delegate;
	@Inject private ReportPathResolver resolver;
	private static final Logger logger = LoggerFactory.getLogger(ReportsResourceBundle.class);

	public Locale getLocale(){
		return locale;
	}
	
	@PostConstruct
	private void buildBundle(){
		
		String path = resolver.getResourceBundleFor(locale);
		File bundle = new File(path);
		
		try {	
			this.delegate = new PropertyResourceBundle(new FileInputStream(bundle));
			logger.debug("REPORT_LOCALE --> " + locale);
			logger.debug("REPORT_RESOURCE_BUNDLE --> " + bundle.getAbsolutePath());
        } catch (FileNotFoundException e) {
        	logger.debug("Couldn't find report bundle at " + bundle.getAbsolutePath() + ", creating an empty one");
            this.delegate = new EmptyBundle();
        } catch (IOException e) {
        	logger.debug("Couldn't use report bundle at " + bundle.getAbsolutePath() + ", creating an empty one");
            this.delegate = new EmptyBundle();
		}
	}

	public Enumeration<String> getKeys() {
		return delegate.getKeys();
	}

	protected Object handleGetObject(String key) {
		try {
			return delegate.getString(key);
		} catch (MissingResourceException e) {
			return "???" + key + "???";
		}
	}

}
