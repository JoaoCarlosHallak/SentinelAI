package com.hallak.SentinelAI;

import com.hallak.SentinelAI.services.LoadPayloadsService;
	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.boot.CommandLineRunner;
	import org.springframework.boot.SpringApplication;
	import org.springframework.boot.autoconfigure.SpringBootApplication;

	import java.util.List;
	@SpringBootApplication public class Application implements CommandLineRunner {

		private final LoadPayloadsService loadPayloadsService;


		@Autowired
		public Application(LoadPayloadsService loadPayloadsService) {
			this.loadPayloadsService = loadPayloadsService;}

		public static void main(String[] args) {
			SpringApplication.run(Application.class, args);}

		@Override
		public void run(String... args) throws Exception {
			List<String> payloads = loadPayloadsService.loadPayloads("payloads/xss.txt");

			payloads.forEach(System.out::println);





		}


	}
