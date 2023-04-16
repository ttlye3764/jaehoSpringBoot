package com.jaehoSpringBoot;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.*;


public class HelloApiTest {
<<<<<<< HEAD
	// test
=======
	
>>>>>>> 30ae0164e5d141036d91ebc06cfb5dec62bec94b
    @Test
    void helloApi() {
        // http localhost:8080/hello?name=Spring

        TestRestTemplate testRestTemplate = new TestRestTemplate();
        ResponseEntity<String> res
                = testRestTemplate.getForEntity("http://localhost:8080/hello?name={name}", String.class, "Spring");

        // status code 200
        // header(content-type) text/plain
        // body Hello Spring
        assertThat(res.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(res.getHeaders().getContentType().toString()).startsWith("text/plain");
        assertThat(res.getBody()).isEqualTo("*Hello Spring*");
    }

    @Test
    void failsHelloApi() {
        // http localhost:8080/hello?name=Spring

        TestRestTemplate testRestTemplate = new TestRestTemplate();
        ResponseEntity<String> res
                = testRestTemplate.getForEntity("http://localhost:8080/hello?name=", String.class);

        assertThat(res.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
