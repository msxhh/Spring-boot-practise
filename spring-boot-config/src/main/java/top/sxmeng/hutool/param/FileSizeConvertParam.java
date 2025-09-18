package top.sxmeng.hutool.param;

import lombok.Data;

/**
 * 文件大小转换接口请求参数
 */
@Data
public class FileSizeConvertParam {
    /**
     * 文件大小（字节数）
     * 示例：1048576（1MB）
     */
    private long byteSize;
    
    /**
     * 目标单位（B/KB/MB/GB）
     * 示例：MB
     */
    private String targetUnit;
}
