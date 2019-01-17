package com.fzk.translate.respnode.java;

import com.fzk.translate.respnode.ProcessContext;
import com.fzk.translate.respnode.RespNode;

import java.util.List;

/**
 * 初始化节点
 *
 * @Author fanzhoukai
 * @Date 2019/1/17 9:47
 */
public class HeaderNode  extends RespNode {
    @Override
    public List<String> process(ProcessContext context) {
        return go(context);
    }
}
