package com.fzk.translate.respnode.java;

import com.fzk.translate.respnode.ProcessContext;
import com.fzk.translate.respnode.RespNode;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * 多行拼接为整段节点
 */
public class ConnectRowsNode extends RespNode {
    /**
     * 多行拼接为整段
     */
    @Override
    public List<String> process(ProcessContext context) {
        // 结果集，一个元素表示一个段落
        List<String> result = new ArrayList<>();

        StringBuilder sb = new StringBuilder();
        Pattern pattern = Pattern.compile("[a-zA-Z0-9]");
        for (String raw : context.result) {
            if (raw.trim().startsWith("@") && sb.toString().length() > 0) { // 若以@开头，强制换行（如@param, @return等）
                result.add(sb.toString());
                sb = new StringBuilder();
                result.add(raw);
            } else if (!pattern.matcher(raw).find() && sb.toString().length() > 0) { // 不包含有效字符，视为换行；否则将多行拼接
                result.add(sb.toString());
                sb = new StringBuilder();
            } else {
                sb.append(raw).append(" ");
            }
        }
        // 添加最后一段
        if (sb.toString().length() > 0)
            result.add(sb.toString());
        context.result = result;
        return go(context);
    }

    public static void main(String[] args) {
        Pattern pattern = Pattern.compile("[a-zA-Z0-9]");
        System.out.println(pattern.matcher("abc123").find());
    }
}
