package com.apache.camel.handson.config;

import org.apache.camel.CamelContext;
import org.apache.camel.ConsumerTemplate;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.component.servlet.CamelHttpTransportServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    private final CamelContext camelContext;
    private final String CONTEXT_PATH = "/ecom";


    public AppConfig(CamelContext camelContext) {
        this.camelContext = camelContext;
    }

    @Bean
    ServletRegistrationBean servletRegistrationBean() {
        ServletRegistrationBean<CamelHttpTransportServlet> registrationBean
                = new ServletRegistrationBean<>(new CamelHttpTransportServlet(), CONTEXT_PATH + "/*");
        registrationBean.setName("CamelServlet");
        return registrationBean;
    }

    @Bean
    ProducerTemplate producerTemplate() {
        return camelContext.createProducerTemplate();
    }

    @Bean
    ConsumerTemplate consumerTemplate() {
        return camelContext.createConsumerTemplate();
    }
}
