package com.fzk.translate.web;

import com.alibaba.fastjson.JSON;
import com.fzk.translate.dto.ContentDto;
import com.fzk.translate.strategy.PreprocessStrategy;
import com.fzk.translate.strategy.PreprocessStrategyFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author fanzhoukai
 * @Date 2019/1/16 14:39
 */
@RestController
@RequestMapping("translate")
public class TranslateController {

    @RequestMapping("")
    public String translate(@RequestBody ContentDto dto) {
        try {
            // 获取策略bean
            PreprocessStrategy strategy = PreprocessStrategyFactory.strategyMap.get(dto.getStrategy());
            // 翻译文本
            List<String> result = strategy.translate(dto.getContent());
            // TODO 异步记录ES日志

            return JSON.toJSONString(result);
        } catch (Throwable e) {
            e.printStackTrace();
            // TODO 记录异常日志
            // TODO 标准化返回结果
            return "error";
        }
    }
}
