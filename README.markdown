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

Using it
------

1.	Put Vraptor-JasperReports.jar and dependencies in your `WEB-INF/lib` folder. You can get a copy here
2.	Add packages on `web.xml`

		<context-param>
        	<param-name>br.com.caelum.vraptor.packages</param-name>
	        <param-value>br.com.caelum.vraptor.jasperreports</param-value>
    	</context-param>
    	
3.	Create a class to represent your report: make it implement the `br.com.caelum.vraptor.jasperreports.Report` interface.
4.	In your controller, create an instance of your report and return it from your method

Controller
--------

	@Resource
	public class ClientsController {

		private final Result result;
		private final Clients clients;
	
		public ClientsController(Result result, Clients clients) {
			this.result = result;
			this.clients = clients;
		}
		
		@Path("/clients/csv") 
		public Download csvReport() {
			Report<Client> report = generateReport();
			return new ReportDownload(report, Csv());
		}
		
		@Path("/clients/xls") 
		public Download xlsReport() {
			Report<Client> report = generateReport();
			return new ReportDownload(report, Xls());
		}
		
		@Path("/clients/docx") 
		public Download docxReport() {
			Report<Client> report = generateReport();
			return new ReportDownload(report, Docx());
		}
		
		@Path("/clients/txt") 
		public Download txtReport() {
			Report<Client> report = generateReport();
			ExportFormat txt = new Txt();
			txt.configure(JRTextExporterParameter.OFFSET_X, 0)
			   .configure(JRTextExporterParameter.OFFSET_Y, 0);
			return new ReportDownload(report, txt, false);
		}
		
		@Path("/clients/odt") 
		public Download odtReport() {
			Report<Client> report = generateReport();
			return new ReportDownload(report, Odt());
		}
		
		@Path("/clients/rtf") 
		public Download rtfReport() {
			Report<Client> report = generateReport();
			return new ReportDownload(report, Rtf());
		}
		
		@Path("/clients/pdf") 
		public Download pdfReport() {
			Report<Client> report = generateReport();
			return new ReportDownload(report, Pdf());
		}
		
		@Path("/clients/zip") 
		public Download zipReport() throws IOException {
			ReportsDownload download = new ReportsDownload();
			Report<Client> report = generateReport();
			download.add(report, Pdf())
					.add(report, Csv())
					.add(report, Xls())
					.add(report, Rtf())
					.add(report, Docx())
					.add(report, Txt())
					.add(report, Odt());
			return download;
		}
		
		@Path("/clients/report/{format}") 
		public Download report(String format) {
			Report<Client> report = generateReport();
			return new ReportDownload(report, Formats.byExtension(format));
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
			return "/templates/report.jasper";
		}

		public boolean isCacheable() {
			return true;
		}
	
	}

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


Example
------

<https://github.com/wpivotto/vraptor-jasperreport-example>

