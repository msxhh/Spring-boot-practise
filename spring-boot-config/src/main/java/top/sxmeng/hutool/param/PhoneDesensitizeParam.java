package top.sxmeng.hutool.param;

import lombok.Data;

/**
 * 手机号脱敏接口请求参数
 */
@Data
public class PhoneDesensitizeParam {
    /**
     * 11位手机号
     * 示例：13800138000
     */
    private String phone;
}
