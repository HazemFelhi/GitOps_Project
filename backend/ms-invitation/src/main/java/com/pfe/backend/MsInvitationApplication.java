package com.pfe.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
@EntityScan(basePackages = {"com.pfe.backend"}) // Ajoutez le package de votre classe Entreprise ici
@SpringBootApplication
//@EnableFeignClients
public class MsInvitationApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsInvitationApplication.class, args);
	}

}
