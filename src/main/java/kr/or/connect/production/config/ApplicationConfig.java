package kr.or.connect.production.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = { "kr.or.connect.production.dao",  "kr.or.connect.production.service"})
@Import({ DBConfig.class })
public class ApplicationConfig {

}