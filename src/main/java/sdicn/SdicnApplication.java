package sdicn;

import org.jsondoc.spring.boot.starter.EnableJSONDoc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableJSONDoc
@ComponentScan(basePackages = {"controller", "sdicn"})
public class SdicnApplication {

    public static void main(String[] args) {
        SpringApplication.run(SdicnApplication.class, args);
    }
}
