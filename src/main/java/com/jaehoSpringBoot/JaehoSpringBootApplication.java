package com.jaehoSpringBoot;

import org.apache.catalina.startup.Tomcat;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletPath;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcProperties;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

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

        // SpringContainer 를 대표하는 인터페이스: applicationContext
        // 1. Bean이 뭐가 들어갈지
        // 2. resource에 접근하는 방법
        // 3. 이벤트에 뭐 어쩌고 하는 방법 등등.

        GenericApplicationContext applicationContext = new GenericApplicationContext();
        applicationContext.registerBean(HelloController.class);
        applicationContext.refresh();

        // tomcat 시작.
        ServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
        WebServer webServer = serverFactory.getWebServer(servletContext -> {
             servletContext.addServlet("frontController", new HttpServlet() {
                 @Override
                 protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                     // 각종 공통 기능 처리
                     //  - 인증, 보안, 다국어 등.

                     if ("/hello".equals(req.getRequestURI()) // 매핑
                             && req.getMethod().equals(HttpMethod.GET.name())) {

                         // 바인딩
                         String name = req.getParameter("name");
                         HelloController helloController = applicationContext.getBean(HelloController.class);
                         String ret = helloController.hello(name);

                         resp.setContentType(MediaType.TEXT_PLAIN_VALUE);
                         resp.getWriter().println(ret);

                     } else if ("/file".equals(req.getRequestURI())) {
                         // 파일 가져올때.
                         MultipartResolver resolver = new CommonsMultipartResolver(req.getServletContext());
                         MultipartHttpServletRequest multipartHttpServletRequest = resolver.resolveMultipart(req);
                         MultipartFile file = multipartHttpServletRequest.getFile("file");
                     } else {
                         resp.setStatus(HttpStatus.NOT_FOUND.value());
                     }

                 }
             }).addMapping("/*");
        });
        webServer.start();
    }
}
