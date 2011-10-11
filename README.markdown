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

Using it
------

1. 	In a Maven project's pom.xml file:
	 
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
  			<version>1.0.0</version>
		</dependency>

2.	Put vraptor-jasperreport-version.jar and dependencies in your `WEB-INF/lib` folder.
3.	Create a class to represent your report: make it implement the `br.com.caelum.vraptor.jasperreports.Report` interface.
4.	In your controller, create an instance of your report and return it from your method

Controller
--------

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
			Report<Client> report = generateReport();
			return new ReportDownload(report, pdf());
		}
		
		@Path("/clients/pdf/encrypted") 
		public Download encryptedPdfReport() {
			Report<Client> report = generateReport();
			Pdf pdf = ExportFormats.pdf();
			pdf.encrypt(user.getPassword());
			pdf.addPermission(PdfWriter.ALLOW_COPY)
			   .addPermission(PdfWriter.ALLOW_PRINTING);
			return new ReportDownload(report, pdf);
		}
		
		@Path("/clients/csv") 
		public Download csvReport() {
			Report<Client> report = generateReport();
			return new ReportDownload(report, csv());
		}
		
		@Path("/clients/xls") 
		public Download xlsReport() {
			Report<Client> report = generateReport();
			return new ReportDownload(report, xls());
		}
		
		@Path("/clients/docx") 
		public Download docxReport() {
			Report<Client> report = generateReport();
			return new ReportDownload(report, docx());
		}
		
		@Path("/clients/txt") 
		public Download txtReport() {
			Report<Client> report = generateReport();
			return new ReportDownload(report, txt());
		}
		
		@Path("/clients/odt") 
		public Download odtReport() {
			Report<Client> report = generateReport();
			return new ReportDownload(report, odt());
		}
		
		@Path("/clients/rtf") 
		public Download rtfReport() {
			Report<Client> report = generateReport();
			return new ReportDownload(report, rtf());
		}
		
		@Path("/clients/report/{format}") 
		public Download report(String format) {
			Report<Client> report = generateReport();
			return new ReportDownload(report, formats.byExtension(format));
		}
		
		private Report<Client> generateReport(){
			List<Client> data = clients.listAll();
			return new ClientsReport(data);
		}
	
	}

Report
--------

	public class ClientsReport implements Report<Client> {
	
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
	
Batch Export
------

Several reports can be exported together to form a single resulting document.

	public Download batchReport(){
		BatchReportsDownload download = new BatchReportsDownload(pdf());
		Report header = ...
		Report another = ...
		Report footer = ...
		download.add(header, another, footer);
		return download;
	}
	
Note: Not all exporters can work in batch mode
	
Zip Export
------

This option allows you to export reports in different formats and agroup them into a single zip file.

	public Download zipReport(){
		ReportsDownload download = new ReportsDownload();
		download.add(pdfReport, pdf())
				.add(csvReport, csv())
				.add(xlsReport, xls())
				.add(docxReport, rtf())
				.add(odtReport, odt());
		return download;
	}
	
Accept header
------

If your method return a report, an interceptor tries to discover the request format (through _format or Accept header) and then render the report in 
this format.

	@Get("/clients/report") 
	public Report report() {
		Report<Client> report = generateReport();
		return report;
	}

Customizing paths
------

By default the lib will consider only reports under `WEB-INF/reports` folder, but you can also specify a different path format.
For enabling this you must put this parameters on web.xml:

	<context-param>
	    <param-name>vraptor.reports.path</param-name>
	    <param-value>...</param-value>
	    <param-name>vraptor.subreports.path</param-name>
	    <param-value>...</param-value>
	    <param-name>vraptor.images.path</param-name>
	    <param-value>...</param-value>
	</context-param>

Decorating reports
------

Decorators can be used to provide default values for all reports, like this
 
	@Component
	@SessionScoped
	public class MyDecorator implements ReportDecorator {
	
		private final User user;
		
		public ReportLocaleDecorator(User user) {
			this.user = user;
		}
	
		public void decorate(Report<?> report) {
			report.addParameter("GeneratedBy", user);
		}
	}
	
Now all reports have a parameter called `$P{GeneratedBy}`.

Internationalization
------

JasperReports lets you associate a `java.util.ResourceBundle` with the report template, at runtime (by providing a value for the built-in `REPORT_RESOURCE_BUNDLE` parameter). 
If your report needs to be generated in a locale that is different from the current one, the built-in `REPORT_LOCALE` parameter can be used to specify the runtime locale when filling the report. 
You just need to put a file called `i18n_en_US.properties` (or whatever) in the same folder of your reports.
Having done this, parameters such `$R{text.message}`, will be automatically converted to the expected language.

For changing this you must put this parameter on web.xml:

	<context-param>
	    <param-name>vraptor.reports.resourcebundle.name</param-name>
	    <param-value>...</param-value>
	</context-param>

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


