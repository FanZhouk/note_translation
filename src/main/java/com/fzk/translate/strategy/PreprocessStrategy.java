package com.fzk.translate.strategy;

import java.util.List;

/**
 * 文本预处理策略接口
 */
public interface PreprocessStrategy {
    /**
     * 对传入的文本进行预处理
     */
    public List<String> translate(String content);
}
