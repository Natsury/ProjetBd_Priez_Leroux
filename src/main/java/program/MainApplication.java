package program;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication                                                      // Déclaration app Spring
//@ComponentScan(basePackages = {"program", "controllers", "entities"})     // Scan custom (import requis)
public class MainApplication {

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }
}
