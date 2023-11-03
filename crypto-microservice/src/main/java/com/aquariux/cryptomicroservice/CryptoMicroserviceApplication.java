package com.aquariux.cryptomicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class CryptoMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CryptoMicroserviceApplication.class, args);
	}

}
