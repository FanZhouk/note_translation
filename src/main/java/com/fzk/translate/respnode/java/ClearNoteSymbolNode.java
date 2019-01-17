package com.fzk.translate.respnode.java;


import com.fzk.translate.respnode.ProcessContext;
import com.fzk.translate.respnode.RespNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 去注释符责任节点
 */
public class ClearNoteSymbolNode extends RespNode {

    /**
     * 每行需去除的注释前缀正则
     */
    private static String[] prefixes = {"//+\\s*", "/\\*+\\s*", "\\*+/*\\s*"};

    /**
     * 去掉注释符号（单行/多行）
     */
    @Override
    public List<String> process(ProcessContext context) {
        if (context.rawContent == null)
            return new ArrayList<>();
        String[] rows = context.rawContent.split("\n");

        List<String> result = new ArrayList<>(rows.length);

        StringBuilder sb = new StringBuilder();
        for (String row : rows) {
            // 去除首尾空字符
            row = row.trim();
            // 去除注释前缀
            String newRow = null;
            for (String prefix : prefixes) {
                newRow = row.replaceFirst(prefix, "");
                if (newRow.length() != row.length())
                    break;
            }
            result.add(newRow);
        }
        context.result = result;
        return go(context);
    }


    public static void main(String[] args) {
        System.out.println("*a".replaceFirst("\\*+",""));
    }
}
