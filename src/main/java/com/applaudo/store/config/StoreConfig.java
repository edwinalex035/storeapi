package com.applaudo.store.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = { "com.applaudo.store", "com.applaudo.store.web"})
public class StoreConfig {
}
