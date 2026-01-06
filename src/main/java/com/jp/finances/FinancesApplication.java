package com.jp.finances;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;

@SpringBootApplication
@Slf4j
public class FinancesApplication {

	public static void main(String[] args) throws UnknownHostException {
		SpringApplication app = new SpringApplication(FinancesApplication.class);
		Environment env = app.run(args).getEnvironment();

		String info = """
				\n-----------------------------------------------------------------------------------------------------
				Application {} is running!
				-----------------------------------------------------------------------------------------------------
					Access URLs:
						Docs: \t\thttp://localhost:{}{}/swagger-ui/index.html#
						Local: \t\thttp://localhost:{}{}
						External: \thttp://{}:{}{}
				-----------------------------------------------------------------------------------------------------
				""";
		log.info(info, env.getProperty("spring.application.name"),
				env.getProperty("server.port"),
				env.getProperty("server.servlet.context-path"),
				env.getProperty("server.port"),
				env.getProperty("server.servlet.context-path"),
				InetAddress.getLocalHost().getHostAddress(),
				env.getProperty("server.port"),
				env.getProperty("server.servlet.context-path"));
	}

}
