package org.sparta.hh_lv1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class HhLv1Application {

    public static void main(String[] args) {
        SpringApplication.run(HhLv1Application.class, args);
    }

}
