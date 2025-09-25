package top.sxmeng.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import top.sxmeng.enums.ErrorCode;

import java.time.Instant;

/**
 * 统一API响应结果封装
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> {
    /**
     * 响应状态码：200成功，其他为错误
     */
    private int code = 200;

    /**
     * 响应消息
     */
    private String msg = "success";

    /**
     * 响应数据
     */
    private T data;

    /**
     * 响应时间戳
     */
    private long timestamp = Instant.now().toEpochMilli();

    // 成功响应（带数据）
    public static <T> Result<T> success(T data) {
        return new Result<>(200, "success", data, Instant.now().toEpochMilli());
    }

    // 成功响应（无数据）
    public static Result<Void> success() {
        return new Result<>(200, "success", null, Instant.now().toEpochMilli());
    }

    // 错误响应（使用ErrorCode枚举）
    public static <T> Result<T> error(ErrorCode errorCode) {
        return new Result<>(errorCode.getCode(), errorCode.getMsg(), null, Instant.now().toEpochMilli());
    }

    // 错误响应（自定义消息）
    public static <T> Result<T> error(int code, String msg) {
        return new Result<>(code, msg, null, Instant.now().toEpochMilli());
    }
}
