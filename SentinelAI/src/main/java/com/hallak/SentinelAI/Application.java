package com.hallak.SentinelAI;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.hallak.SentinelAI.services.ClientOllamaService;
import com.hallak.SentinelAI.services.sqli.SqlInjectionScanService;
import com.hallak.SentinelAI.services.xss.XssScanService;


	@SpringBootApplication public class Application implements CommandLineRunner {

		private final XssScanService xssScanService;
		private final ClientOllamaService clientOllamaService;
		private final SqlInjectionScanService sqlInjectionScanService;

		public Application(XssScanService xssScanService, ClientOllamaService clientOllamaService, SqlInjectionScanService sqlInjectionScanService) {
			this.xssScanService = xssScanService;
			this.clientOllamaService = clientOllamaService;
			this.sqlInjectionScanService = sqlInjectionScanService;
		}


		public static void main(String[] args) {
			SpringApplication.run(Application.class, args);}

		@Override
		public void run(String... args) throws Exception {
			/*xssScanService.scanAndBasicFilter("https://www.saojoaodelrei.mg.gov.br/ws_consulta/Buscar.php?STR_BSC=INJECT")
			.subscribe();*/
			sqlInjectionScanService.scanAndBasicFilter("https://saojoaodelrei.mg.gov.br/ws_consulta/Cadastro_Generico.php?INT_CAD_GEN=INJECT")
			.subscribe(); // Reativo e preguicoso
			System.out.println(clientOllamaService.sendRequest("What is the capital of France?"));

		






		}


	}
