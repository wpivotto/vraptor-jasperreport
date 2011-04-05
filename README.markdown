Vraptor Jasper Report Plugin
======

Plugin para facilitar a geracao de relatorios com o Vraptor

Formatos suportados:

* PDF
* CSV
* TXT
* ODT
* HTML
* DOCX
* RTF

Exemplo de Controller
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
			return new JasperReportDownload(report, CSV());
		}
		
		@Path("/clients/docx") 
		public Download docxReport() {
			Report<Client> report = generateReport();
			return new JasperReportDownload(report, DOCX());
		}
		
		@Path("/clients/txt") 
		public Download txtReport() {
			Report<Client> report = generateReport();
			return new JasperReportDownload(report, TXT());
		}
		
		@Path("/clients/odt") 
		public Download odtReport() {
			Report<Client> report = generateReport();
			return new JasperReportDownload(report, ODT());
		}
		
		@Path("/clients/rtf") 
		public Download rtfReport() {
			Report<Client> report = generateReport();
			return new JasperReportDownload(report, RTF());
		}
		
		@Path("/clients/pdf") 
		public Download pdfReport() {
			Report<Client> report = generateReport();
			return new JasperReportDownload(report, PDF());
		}
		
		private Report<Client> generateReport(){
			List<Client> data = clients.listAll();
			return new ClientsReport(data);
		}
		
	}

Exemplo de Relatorio
--------

	public class ClientsReport implements Report<Client> {
	
		private final List<Client> data;
		private Map<String, Object> parameters;
		
		public ClientsReport(List<Client> data) {
			this.data = data;
			this.parameters = new HashMap<String, Object>();
		}
	
		public void addParameter(String key, Object value) {
			this.parameters.put(key, value);
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
	

Projeto Exemplo
------

<https://github.com/wpivotto/vraptor-jasperreport-example>

