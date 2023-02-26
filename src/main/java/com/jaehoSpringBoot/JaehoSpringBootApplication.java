package com.jaehoSpringBoot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.context.AnnotationConfigServletWebApplicationContext;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;

//@SpringBootApplication
@MySpringBootAnnotation
public class JaehoSpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(JaehoSpringBootApplication.class, args);
    }


}
