package com.increff.pos.spring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan("com.increff.pos")
@PropertySources({ @PropertySource(value = "file:./pos.properties", ignoreResourceNotFound = false)})
public class SpringConfig {

}
