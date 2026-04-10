package com.hallak.SentinelAI;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.hallak.SentinelAI.services.ClientOllamaService;
import com.hallak.SentinelAI.services.xss.XssScanService;


	@SpringBootApplication public class Application implements CommandLineRunner {

		private final XssScanService xssScanService;
		private final ClientOllamaService clientOllamaService;

		public Application(XssScanService xssScanService, ClientOllamaService clientOllamaService) {
			this.xssScanService = xssScanService;
			this.clientOllamaService = clientOllamaService;
		}


		public static void main(String[] args) {
			SpringApplication.run(Application.class, args);}

		@Override
		public void run(String... args) throws Exception {
			xssScanService.scanAndBasicFilter("https://www.saojoaodelrei.mg.gov.br/ws_consulta/Buscar.php?STR_BSC=INJECT")
			.subscribe();
			System.out.println(clientOllamaService.sendRequest("What is the capital of France?"));

		






		}


	}
