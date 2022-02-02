package com.mettle.featureflag;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
@EntityScan(basePackages = {"com.mettle.featureflag.model"})
public class FeatureFlagApplication {

	public static void main(String[] args) {
		SpringApplication.run(FeatureFlagApplication.class, args);
	}

}
