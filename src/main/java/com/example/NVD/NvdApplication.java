package com.example.NVD;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NvdApplication {

	public static void main(String[] args) {
		SpringApplication.run(NvdApplication.class, args);
		
	}
}
/*
Sample URLs to Test
•	http://localhost:8080/api/cves/id/CVE-2022
•	http://localhost:8080/api/cves/year/2023
•	http://localhost:8080/api/cves/score/7.5
•	http://localhost:8080/api/cves/modified-since/30
*/