package top.sxmeng.hutool.param;

import lombok.Data;

/**
 * 验证码生成接口请求参数
 */
@Data
public class CodeGenerateParam {
    /**
     * 验证码长度（1-10位）
     * 示例：6
     */
    private int length;
}
