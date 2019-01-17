package com.fzk.translate.respnode.java;

import com.fzk.translate.respnode.ProcessContext;
import com.fzk.translate.respnode.RespNode;
import com.fzk.translate.util.HttpUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 翻译节点
 *
 * 使用有道云API，文档地址：https://ai.youdao.com/docs/doc-trans-api.s#p01
 */
public class TranslateNode extends RespNode {
    @Override
    public List<String> process(ProcessContext context) {
        /*List<String> result = new ArrayList<>(context.result.size());

        for (String section : context.result) {

            HttpUtils.doPost("http://openapi.youdao.com/api", "");

        }

        context.result = result;*/
        return go(context);
    }
}
