Vraptor Jasper Report Plugin
======

Report Generation Plug-in for Vraptor

Supported File Formats:

* PDF
* CSV
* XLS
* TXT
* ODT
* HTML
* DOCX
* RTF
* ODS
* PPTX
* XHTML
* XLSX
* IMAGE (PNG and JPEG)

Using it
------

1. 	In a Maven project's pom.xml file:

```xml	 
<repositories>
	<repository>
    	<id>sonatype-oss-public</id>
        <url>https://oss.sonatype.org/content/groups/public/</url>
    	<releases>
    		<enabled>true</enabled>
    	</releases>
    	<snapshots>
    		<enabled>true</enabled>
		</snapshots>
	</repository>		
</repositories>  

<dependency>
	<groupId>br.com.prixma</groupId>
  	<artifactId>vraptor-jasperreport</artifactId>
  	<version>1.0.2</version>
</dependency>
```

2.	Put vraptor-jasperreport-version.jar [Download Here](http://oss.sonatype.org/content/groups/public/br/com/prixma/vraptor-jasperreport/) and dependencies in your `WEB-INF/lib` folder.
3.	Create a class to represent your report: make it implement the `br.com.caelum.vraptor.jasperreports.Report` interface.
4.	In your controller, create an instance of your report and return it from your method

Controller
--------

```java
@Resource
public class ClientsController {

	private final Result result;
	private final Clients clients;
	private final ExportFormats formats;
	private final User user;
	
	public ClientsController(Result result, Clients clients, ExportFormats formats, User user) {
		this.result = result;
		this.clients = clients;
		this.formats = formats;
		this.user = user;
	}
		
	@Path("/clients/pdf") 
	public Download pdfReport() {
		Report report = generateReport();
		return new ReportDownload(report, pdf());
	}
		
	@Path("/clients/pdf/encrypted") 
	public Download encryptedPdfReport() {
		Report report = generateReport();
		Pdf pdf = ExportFormats.pdf();
		pdf.encrypt(user.getPassword());
		pdf.addPermission(PdfWriter.ALLOW_COPY)
		   .addPermission(PdfWriter.ALLOW_PRINTING);
		return new ReportDownload(report, pdf);
	}
		
	@Path("/clients/csv") 
	public Download csvReport() {
		Report report = generateReport();
		return new ReportDownload(report, csv());
	}
		
	@Path("/clients/xls") 
	public Download xlsReport() {
		Report report = generateReport();
		return new ReportDownload(report, xls());
	}
		
	@Path("/clients/docx") 
	public Download docxReport() {
		Report report = generateReport();
		return new ReportDownload(report, docx());
	}
		
	@Path("/clients/txt") 
	public Download txtReport() {
		Report report = generateReport();
		return new ReportDownload(report, txt());
	}
		
	@Path("/clients/odt") 
	public Download odtReport() {
		Report report = generateReport();
		return new ReportDownload(report, odt());
	}
		
	@Path("/clients/rtf") 
	public Download rtfReport() {
		Report report = generateReport();
		return new ReportDownload(report, rtf());
	}
		
	@Path("/clients/report/preview") 
	public Download previewReport() {
		Report report = generateReport();
		return new ReportDownload(report, png(), false);
	}
		
	@Path("/clients/report/{format}") 
	public Download report(String format) {
		Report report = generateReport();
		return new ReportDownload(report, formats.byExtension(format));
	}
		
	private Report generateReport(){
		List<Client> data = clients.listAll();
		return new ClientsReport(data);
	}
	
}
```

Report
--------

```java
public class ClientsReport implements Report {
	
	private final List<Client> data;
	private Map<String, Object> parameters;
		
	public ClientsReport(List<Client> data) {
		this.data = data;
		this.parameters = new HashMap<String, Object>();
	}
	
	public Report addParameter(String key, Object value) {
		this.parameters.put(key, value);
		return this;
	}
	
	public Collection<Client> getData() {
		return data;
	}
	
	public String getFileName() {
		return "report" + System.currentTimeMillis();
	}
	
	public Map<String, Object> getParameters() {
		return this.parameters;
	}
	
	public String getTemplate() {
		return "report.jasper";
	}

	public boolean isCacheable() {
		return true;
	}

}
```
	
Batch Export
------

Several reports can be exported together to form a single resulting document.

```java
public Download batchReport(){
	BatchReportsDownload download = new BatchReportsDownload(pdf());
	Report header = ...
	Report content = ...
	Report footer = ...
	download.add(header, content, footer);
	return download;
}
```
	
Note: Not all exporters can work in batch mode
	
Zip Export
------

This option allows you to export reports in different formats and agroup them into a single zip file.

```java
public Download zipReport(){
	ReportsDownload download = new ReportsDownload();
	download.add(pdfReport, pdf())
			.add(csvReport, csv())
			.add(xlsReport, xls())
			.add(docxReport, rtf())
			.add(odtReport, odt());
	return download;
}
```

Accept header
------

If your method return a report, an interceptor tries to discover the request format (through _format or Accept header) and then render the report in 
this format.

```html
<a href="<c:url value="/clients/report?_format=csv"/>">CSV Report</a>
```

```java
@Get("/clients/report") 
public Report report() {
	Report report = ...;
	return report;
}
```

Data URIs
------

The data URI is a scheme that provides a way to include data in-line in web pages as if they were external resources.
The scheme is defined in [RFC 2397](http://tools.ietf.org/html/rfc2397 "RFC 2397").

Example 1: Embedding a report in a div

```java
@Get("/clients.json") 
public void jsonReport(ReportDataURIBuilder builder) {
	String uri = builder.build(report(), png());
    result.use(json()).withoutRoot().from(uri).serialize();
}
```

In your jsp:

```javascript
$("#reportButton").click(function() {
	$.getJSON('/clients.json', function(uri) {
		$('#container').append("<img src=\"" + uri + "\" />");
	});
});
```

Example 2: Embedding a generic report in a new window

```java
@Get("/clients.json") 
public void jsonReport(ReportDataURIBuilder builder) {
	String uri = builder.build(report()); //recognizes the format via _format parameter
    result.use(json()).withoutRoot().from(uri).serialize();
}
```

In your jsp:

```javascript
$("#reportButton").click(function() {
	$.getJSON('/clients.json?_format=pdf', function(uri) {
		window.open(uri);
	});
});
```

Customizing paths
------

By default the lib will consider only reports under `WEB-INF/reports` folder, but you can also specify a different path format.
For enabling this you must put this parameters on web.xml:

```xml
<context-param>
	<param-name>vraptor.reports.path</param-name>
	<param-value>custom reports path</param-value>
</context-param>
	
<context-param>
	<param-name>vraptor.subreports.path</param-name>
	<param-value>custom subreports path</param-value>
</context-param>
	
<context-param>
	<param-name>vraptor.images.path</param-name>
	<param-value>custom images path</param-value>
</context-param>
```
	
These folders are passed as parameters to reports:

	`$P{REPORT_DIR}` directory where the reports are available
	`$P{SUBREPORT_DIR}` directory where the sub-reports are available
	`$P{IMAGES_DIR}` directory where the images are available

So, to include an image in the report just do: `$P{IMAGES_DIR} + "image.png"`

Decorating reports
------

Decorators can be used to provide default parameters for all reports, like this

```java 
@Component
@SessionScoped
public class MyDecorator implements ReportDecorator {
	
	private final User user;
	
	public MyDecorator(User user) {
		this.user = user;
	}
	
	public void decorate(Report report) {
		report.addParameter("GeneratedBy", user);
	}
}
```
	
Now all reports have a parameter called `$P{GeneratedBy}`.

Using Result
------

Another way to pass parameters to your report is through the result object.
Values ​​passed to the result are automatically converted into parameters

```java
@Get("/clients/report") 
public Report report() {
	result.include("GeneratedBy", user);
	Report report = generateReport();
	return report;
}
```

Internationalization
------

JasperReports lets you associate a `java.util.ResourceBundle` with the report template, at runtime (by providing a value for the built-in `REPORT_RESOURCE_BUNDLE` parameter). 
If your report needs to be generated in a locale that is different from the current one, the built-in `REPORT_LOCALE` parameter can be used to specify the runtime locale when filling the report. 
You just need to put a file called `i18n_en_US.properties` (or whatever) in the same folder of your reports.
Having done this, parameters such `$R{text.message}`, will be automatically converted to the expected language.

For changing this you must put this parameter on web.xml:

```xml
<context-param>
	<param-name>vraptor.reports.resourcebundle.name</param-name>
	<param-value>custom resource bundle name</param-value>
</context-param>
```

Image Servlet
------

This servlet (included in the JasperReports distribution package) is needed as part of your Web application in order to include images in your HTML reports.
To map this servlet to serve the images that are included in yours report, just do:

```xml
<servlet>
	<servlet-name>ImageServlet</servlet-name> 
    <servlet-class>net.sf.jasperreports.j2ee.servlets.ImageServlet</servlet-class> 
</servlet>  
             
<servlet-mapping> 
	<servlet-name>ImageServlet</servlet-name> 
    <url-pattern>/report.image</url-pattern> 
</servlet-mapping> 
```
   
Dependencies
------

* Commons Logging <http://jakarta.apache.org/commons/logging/>
* Commons Collections <http://jakarta.apache.org/commons/collections/>
* Commons BeanUtils <http://jakarta.apache.org/commons/beanutils/>
* Commons Digester <http://jakarta.apache.org/commons/digester/>
* JFreeChart <http://www.jfree.org/jfreechart/> - Only required when the report contains charts
* iText <http://www.lowagie.com/iText/> - Only required when exporting reports to PDF or RTF
* Jakarta POI <http://jakarta.apache.org/poi/> - Only required when exporting reports to Excel format
* JExcelApi <http://jexcelapi.sourceforge.net/> - Only required when exporting reports to Excel format


