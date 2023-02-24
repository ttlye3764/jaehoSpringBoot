package com.jaehoSpringBoot;

import org.apache.catalina.startup.Tomcat;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;

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
             servletContext.addServlet("frontController", new HttpServlet() {

                 @Override
                 protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

                     // 각종 공통 기능 처리
                     //  - 인증, 보안, 다국어 등.
                     if ("/hello".equals(req.getRequestURI())
                             && req.getMethod().equals(HttpMethod.GET.name())) {
                         String name = req.getParameter("name");

                         resp.setStatus(HttpStatus.OK.value());
                         resp.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN_VALUE);
                         resp.getWriter().println("Hello Servlet" + name);
                     } else if ("/user".equals(req.getRequestURI())) {

                     } else {
                         resp.setStatus(HttpStatus.NOT_FOUND.value());
                     }

                 }
             }).addMapping("/*");
        });
        webServer.start();
    }
}
