package top.sxmeng.hutool.param;

import lombok.Data;

/**
 * 日期格式化接口请求参数
 */
@Data
public class DateFormatParam {
    /**
     * 原始日期字符串，支持多种格式
     * 示例：20240520、2024-05-20、2024/05/20 14:30:00
     */
    private String dateStr;
    
    /**
     * 目标日期格式
     * 示例：yyyy年MM月dd日、yyyy-MM-dd HH:mm:ss
     */
    private String targetPattern;
}
