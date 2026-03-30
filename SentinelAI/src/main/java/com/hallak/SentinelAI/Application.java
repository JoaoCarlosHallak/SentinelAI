package com.hallak.SentinelAI;

import com.hallak.SentinelAI.services.LoadPayloadsService;
import com.hallak.SentinelAI.services.xss.XssScanService;
import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.boot.CommandLineRunner;
	import org.springframework.boot.SpringApplication;
	import org.springframework.boot.autoconfigure.SpringBootApplication;

	import java.util.List;
	@SpringBootApplication public class Application implements CommandLineRunner {

		private final XssScanService  xssScanService;


		@Autowired
		public Application(XssScanService xssScanService) {
			this.xssScanService = xssScanService;
		}


		public static void main(String[] args) {
			SpringApplication.run(Application.class, args);}

		@Override
		public void run(String... args) throws Exception {
			xssScanService.scan("https://www.hallak.com");






		}


	}
