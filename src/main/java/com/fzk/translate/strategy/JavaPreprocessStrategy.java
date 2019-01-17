package com.fzk.translate.strategy;


import com.fzk.translate.respnode.ProcessContext;
import com.fzk.translate.respnode.RespNode;
import com.fzk.translate.respnode.java.*;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Java语言注释预处理策略
 */
@Component(value = "java")
public class JavaPreprocessStrategy implements PreprocessStrategy {
    /**
     * 翻译Java注释文本
     */
    @Override
    public List<String> translate(String content) {
        // 构造责任链
        RespNode respChain = new HeaderNode();
        respChain.setNext(new ClearNoteSymbolNode()) // 去除注释符号节点
                .setNext(new ClearHtmlLabelNode()) // 清除HTML标签节点
                .setNext(new ConnectRowsNode()) // 多行拼接为整段节点
                .setNext(new TranslateNode()) // 翻译节点
                .setNext(null);

        // 构造上下文对象
        ProcessContext context = new ProcessContext();
        context.rawContent = content;

        // 执行责任链
        return respChain.process(context);
    }
}
