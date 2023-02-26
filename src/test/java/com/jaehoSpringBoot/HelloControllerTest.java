package com.jaehoSpringBoot;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class HelloControllerTest {

    @Test
    public void hello() {
        HelloController helloController = new HelloController(name -> "hello " + name);
        String result = helloController.hello("Spring");
        assertThat(result).isEqualTo("hello Spring");
    }

    @Test
    void fail() {
        HelloController helloController = new HelloController(name -> "hello " + name);

        assertThatThrownBy(() -> helloController.hello(null))
                .isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> helloController.hello(""))
                .isInstanceOf(IllegalArgumentException.class);

    }
}
