package com.jaehoSpringBoot;

import org.junit.jupiter.api.Test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@UnitTest
@interface FastUnitTest{}

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.ANNOTATION_TYPE, ElementType.METHOD})
@Test
@interface UnitTest{}

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
