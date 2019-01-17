package com.fzk.translate.respnode.java;


import com.fzk.translate.respnode.ProcessContext;
import com.fzk.translate.respnode.RespNode;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 清除HTML标签和链接节点
 */
public class ClearHtmlLabelNode extends RespNode {
    /**
     * 清除HTML标签、链接
     *
     * 将"<p>abc</p>"转为"abc"
     * 将"{@code null}"转为"null"
     * @param context
     * @return
     */
    @Override
    public List<String> process(ProcessContext context) throws Exception {
        List<String> result = new ArrayList<>(context.result.size());
        for (String row : context.result) {
            // 去除标签，非贪婪匹配
            row = row.replaceAll("<.*?>", "");
            // 去除链接
            row = clearLink(row);
            result.add(row);
        }
        context.result = result;
        return go(context);
    }

    // 利用正则去除链接格式的文本
    private static String clearLink(String row) {
        Pattern pattern = Pattern.compile("\\{@.*?\\s(.*?)\\}");
        Matcher m = pattern.matcher(row);
        StringBuffer sb = new StringBuffer();
        while (m.find()) {
            m.appendReplacement(sb, m.group(1));
        }
        m.appendTail(sb);
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println("<p>abc</p>".replaceAll("<.*?>", ""));
        System.out.println(clearLink("returns {@code null} or returns {@code String}."));
    }
}
