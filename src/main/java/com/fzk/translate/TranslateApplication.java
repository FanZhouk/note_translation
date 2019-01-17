package com.fzk.translate;

import com.fzk.translate.strategy.JavaPreprocessStrategy;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@SpringBootApplication
// 扫描translate包下的所有组件
@ComponentScan(value = {"com.fzk.translate"})
public class TranslateApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(TranslateApplication.class, args);
    }

    @Override//为了打包springboot项目
    protected SpringApplicationBuilder configure(
            SpringApplicationBuilder builder) {
        return builder.sources(this.getClass());
    }
}

