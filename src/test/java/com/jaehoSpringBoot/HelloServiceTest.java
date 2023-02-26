package com.jaehoSpringBoot;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class HelloServiceTest {

    @Test
    void simpleHelloService() {
        HelloService helloService = new SimpleHelloService();
        String result = helloService.sayHello("Spring");
        assertThat(result).isEqualTo("Hello Spring");
    }

    @Test
    void decoratorHelloService() {
        HelloService helloService = new HelloDecorator(name -> name);
        String result = helloService.sayHello("Spring");
        assertThat(result).isEqualTo("*Spring*");
    }
}
