package com.jaehoSpringBoot;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;


@Service
public class HelloDecorator implements HelloService {

    // Decorator Pattern
    // 기존 Service 에 기능을 추가하고 싶을 때 사용
    // 기존 Service 에 책임을 추가하는 개념.

    private final HelloService helloService;

    public HelloDecorator(HelloService helloService) {
        this.helloService = helloService;
    }

    @Override
    public String sayHello(String name) {
        return '*' + helloService.sayHello(name) + '*';
    }
}
