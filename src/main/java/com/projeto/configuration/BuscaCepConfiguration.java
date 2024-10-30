package com.projeto.configuration;

import com.projeto.interceptor.LoggingRequestInterceptor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class BuscaCepConfiguration {

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder,
                                     LoggingRequestInterceptor loggingRequestInterceptor) {
        return builder
                .additionalInterceptors(loggingRequestInterceptor)
                .build();
    }

}
