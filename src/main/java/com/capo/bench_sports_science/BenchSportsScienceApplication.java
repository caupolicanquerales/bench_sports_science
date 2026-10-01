package com.capo.bench_sports_science;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class BenchSportsScienceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BenchSportsScienceApplication.class, args);
	}

}
