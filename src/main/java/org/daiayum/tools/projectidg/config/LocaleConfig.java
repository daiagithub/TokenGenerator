package org.daiayum.tools.projectidg.config;

import java.util.Date;
import java.util.TimeZone;

import org.springframework.context.annotation.Configuration;

import jakarta.annotation.PostConstruct;

@Configuration
public class LocaleConfig {

	@PostConstruct
	public void init() {

		TimeZone.setDefault(TimeZone.getTimeZone("Asia/Kolkata"));

		System.out.println("Current Date: " + new Date().toString());
	}
}