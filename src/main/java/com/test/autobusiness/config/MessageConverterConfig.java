package com.test.autobusiness.config;

import org.springframework.context.annotation.Bean;

//@Configuration
public class MessageConverterConfig {

    @Bean
    public JavaScriptMessageConverter abstractJackson2HttpMessageConverter() {
        return new JavaScriptMessageConverter();
    }
}
