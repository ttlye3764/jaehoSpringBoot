package com.jaehoSpringBoot;

import org.apache.catalina.startup.Tomcat;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@SpringBootApplication
public class JaehoSpringBootApplication {

    public static void main(String[] args) {

//        SpringApplication.run(JaehoSpringBootApplication.class, args);

        // tomcat 시작.
        ServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
        WebServer webServer = serverFactory.getWebServer(servletContext -> {
             servletContext.addServlet("hello", new HttpServlet() {

                 @Override
                 protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

                     String name = req.getParameter("name");

                     // web response 3가지 요소
                     // 1. status
                     resp.setStatus(HttpStatus.OK.value());
                     // 2. header
                     resp.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN_VALUE);
                     // 3. body
                     resp.getWriter().println("Hello Servlet" + name);
                 }
             }).addMapping("/hello");
        });
        webServer.start();


    }


}
