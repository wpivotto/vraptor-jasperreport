Vraptor Jasper Report Plugin
======

Report Generation Plug-in for Vraptor

Supported File Formats:

* PDF
* CSV
* TXT
* ODT
* HTML
* DOCX
* RTF

Example of a controller
--------

	@Resource
	public class ClientsController {

		private final Result result;
		private final Clients clients;
	
		public ClientsController(Result result, Clients clients) {
			this.result = result;
			this.clients = clients;
		}
	
		@Path("/")
		public void index() {
			result.include("clients", clients.listAll());
		}
	
		@Post("/clients") 
		public void add(Client client) {
			clients.add(client);
			result.redirectTo(this).index();
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
		
		private Report<Client> generateReport(){
			List<Client> data = clients.listAll();
			return new ClientsReport(data);
		}
	
	}

Example of a report
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

	}
	

Example
------

<https://github.com/wpivotto/vraptor-jasperreport-example>

