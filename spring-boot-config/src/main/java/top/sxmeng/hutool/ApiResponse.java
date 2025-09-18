package top.sxmeng.hutool;

import lombok.Data;

/**
 * 统一API响应结果封装类
 */
@Data
public class ApiResponse<T> {
    // 状态码：200成功，400参数错误，500服务器错误
    private int code;

    // 响应消息
    private String message;

    // 响应数据
    private T data;

    /**
     * 成功响应
     */
    public static <T> ApiResponse<T> success(String message, T data) {
        ApiResponse<T> response = new ApiResponse<>();
        response.setCode(200);
        response.setMessage(message);
        response.setData(data);
        return response;
    }

    /**
     * 错误响应
     */
    public static <T> ApiResponse<T> error(int code, String message) {
        ApiResponse<T> response = new ApiResponse<>();
        response.setCode(code);
        response.setMessage(message);
        response.setData(null);
        return response;
    }

    /**
     * 参数错误响应（默认400）
     */
    public static <T> ApiResponse<T> badRequest(String message) {
        return error(400, message);
    }

    /**
     * 服务器错误响应（默认500）
     */
    public static <T> ApiResponse<T> serverError(String message) {
        return error(500, message);
    }
}

