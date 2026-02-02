package com.devender.loan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.hateoas.mediatype.hal.Jackson2HalModule;
import org.springframework.web.filter.ForwardedHeaderFilter;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.v3.core.jackson.ModelResolver;

@SpringBootApplication
public class LoanBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoanBackendApplication.class, args);
	}

    @Bean
    ForwardedHeaderFilter forwardedHeaderFilter() {
        return new ForwardedHeaderFilter();
    }
    @Bean
    Module halModule() {
        return new Jackson2HalModule();
    }
    @Bean
    ModelResolver modelResolver(ObjectMapper objectMapper) {
        return new ModelResolver(objectMapper);
    }    
}
