package com.cainiao.training;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * Pandora Boot应用的入口类
 *
 * @author chengxu
 */
@SpringBootApplication(scanBasePackages = {"com.cainiao.training"})
public class Application {

    public static void main(String[] args) {
//        PandoraBootstrap.run(args);
        SpringApplication.run(Application.class, args);
//        PandoraBootstrap.markStartupAndWait();
    }
}
