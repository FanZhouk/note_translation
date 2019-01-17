package com.fzk.translate.respnode;

import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * 责任链节点
 */
public abstract class RespNode {

    private RespNode next;

    /**
     * 当前责任链的处理逻辑
     */
    public abstract List<String> process(ProcessContext context) throws Exception;

    /**
     * 设置下一个责任链节点
     */
    public RespNode setNext(RespNode next) {
        return this.next = next;
    }

    /**
     * 调用下一个责任链
     */
    public List<String> go(ProcessContext context) throws Exception {
        if (next != null)
            return next.process(context);
        return context.result;
    }
}
