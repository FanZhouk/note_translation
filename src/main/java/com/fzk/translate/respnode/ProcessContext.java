package com.fzk.translate.respnode;

import java.util.List;

/**
 * 处理数据上下文
 * 存储处理过程中需要用到的临时数据
 */
public class ProcessContext {
    /**
     * 原始数据
     */
    public String rawContent;

    /**
     * 最近一次的处理结果
     */
    public List<String> result;
}
