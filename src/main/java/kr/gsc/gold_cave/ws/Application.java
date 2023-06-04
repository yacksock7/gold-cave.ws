package kr.gsc.gold_cave.ws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "kr.gsc.gold_cave.ws")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
