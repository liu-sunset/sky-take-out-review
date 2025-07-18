package sky.project;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"sky.project","property"})
public class SkyProjectApplication {
    public static void main(String[] args) {
        SpringApplication.run(SkyProjectApplication.class, args);
    }
}
