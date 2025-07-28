package sky.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@EnableCaching
@SpringBootApplication
@ComponentScan({"sky.project","property","handler","intercepter","utils"})
public class SkyProjectApplication {
    public static void main(String[] args) {
        SpringApplication.run(SkyProjectApplication.class, args);
    }
}
