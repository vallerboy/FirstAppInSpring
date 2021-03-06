package pl.oskarpolak;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by OskarPraca on 2017-02-18.
 */

@ComponentScan
@EnableAutoConfiguration //@SpringBootAplication lub @ComponentScan
public class AppConfig {

    public static void main(String[] args) {
         SpringApplication.run(AppConfig.class, args);
    }

}
