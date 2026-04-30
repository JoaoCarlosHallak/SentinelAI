package com.hallak.SentinelAI;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.hallak.SentinelAI.services.ClientOllamaService;
import com.hallak.SentinelAI.services.ci.CommandInjectionScanService;
import com.hallak.SentinelAI.services.ih.InsecureHeadersScanService;
import com.hallak.SentinelAI.services.lfi.LfiScanService;
import com.hallak.SentinelAI.services.op.OpenRedirectScanService;
import com.hallak.SentinelAI.services.sqli.SqlInjectionScanService;
import com.hallak.SentinelAI.services.xss.XssScanService;


	@SpringBootApplication public class Application implements CommandLineRunner {

		private final XssScanService xssScanService;
		private final ClientOllamaService clientOllamaService;
		private final LfiScanService lfiScanService;
		private final SqlInjectionScanService sqlInjectionScanService;
		private final CommandInjectionScanService commandInjectionScanService;
		private final OpenRedirectScanService openRedirectScanService;
		private final InsecureHeadersScanService insecureHeadersScanService;

		public Application(XssScanService xssScanService, ClientOllamaService clientOllamaService, SqlInjectionScanService sqlInjectionScanService, LfiScanService lfiScanService, CommandInjectionScanService commandInjectionScanService, OpenRedirectScanService openRedirectScanService, InsecureHeadersScanService insecureHeadersScanService) {
			this.xssScanService = xssScanService;
			this.clientOllamaService = clientOllamaService;
			this.sqlInjectionScanService = sqlInjectionScanService;
			this.lfiScanService = lfiScanService;
			this.commandInjectionScanService = commandInjectionScanService;
			this.openRedirectScanService = openRedirectScanService;
			this.insecureHeadersScanService = insecureHeadersScanService;
		}


		public static void main(String[] args) {
			SpringApplication.run(Application.class, args);}

		@Override
		public void run(String... args) throws Exception {
			/*xssScanService.scanAndBasicFilter("https://www.saojoaodelrei.mg.gov.br/ws_consulta/Buscar.php?STR_BSC=INJECT")
			.subscribe();*/
			/*sqlInjectionScanService.scanAndBasicFilter("https://saojoaodelrei.mg.gov.br/ws_consulta/Cadastro_Generico.php?INT_CAD_GEN=INJECT")
			.subscribe(); // Reativo e preguicoso*/
			//lfiScanService.scanAndBasicFilter("http://localhost/vulnerabilities/fi/?page=INJECT").subscribe();
			//openRedirectScanService.scanAndBasicFilter("http://localhost/vulnerabilities/redirect/?page=INJECT").subscribe();
			//commandInjectionScanService.handleCommandInjectionScanner("http://localhost/vulnerabilities/command_injection/?page=INJECT").subscribe();
			insecureHeadersScanService.scanAndBasicFilter("https://www.saojoaodelrei.mg.gov.br/").subscribe();
			
		}
	}
