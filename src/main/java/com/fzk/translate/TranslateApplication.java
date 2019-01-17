package com.fzk.translate;

import com.fzk.translate.strategy.JavaPreprocessStrategy;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@SpringBootApplication
// 扫描translate包下的所有组件
@ComponentScan(value = {"com.fzk.translate"})
public class TranslateApplication {

    public static void main(String[] args) {
        SpringApplication.run(TranslateApplication.class, args);
    }

}

