package com.fzk.translate.strategy;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 预处理策略工厂
 *
 * @Author fanzhoukai
 * @Date 2019/1/17 9:30
 */
@Component
public class PreprocessStrategyFactory implements ApplicationContextAware {

    public static Map<String, PreprocessStrategy> strategyMap;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        strategyMap = applicationContext.getBeansOfType(PreprocessStrategy.class);
    }
}
