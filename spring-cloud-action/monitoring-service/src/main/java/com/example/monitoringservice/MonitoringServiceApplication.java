package com.example.monitoringservice;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import org.springframework.cloud.netflix.turbine.EnableTurbine;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableDiscoveryClient
@EnableTurbine
@SpringBootApplication
@RestController
public class MonitoringServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MonitoringServiceApplication.class, args);
	}

	@Value("${turbine.aggregator.cluster-config}")
    String tac;

	@RequestMapping("/test")
    public String hi(){
	    return tac;
    }
}
