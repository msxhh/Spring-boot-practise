package top.sxmeng.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import top.sxmeng.enums.ErrorCode;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T> {
    private int code = 0;

    private String msg = "success";

    private T data;

    public static <T> ApiResponse<T> ok() {
        return ok(null);
    }

    public static <T> ApiResponse<T> ok(T data) {
        ApiResponse<T> result = new ApiResponse<>();
        result.setData(data);
        return result;
    }

    public static <T> ApiResponse<T> error() {
        return error(ErrorCode.SERVER_ERROR);
    }

    public static <T> ApiResponse<T> error(String msg) {
        return error(ErrorCode.SERVER_ERROR.getCode(), msg);
    }

    public static <T> ApiResponse<T> error(ErrorCode errorCode) {
        return error(errorCode.getCode(), errorCode.getMsg());
    }

    public static <T> ApiResponse<T> error(int code, String msg) {
        ApiResponse<T> result = new ApiResponse<>();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(200, "操作成功", data);
    }

    public static ApiResponse<Void> success() {
        return new ApiResponse<>(200, "操作成功", null);
    }

    public static ApiResponse<Void> fail(Integer code, String message) {
        return new ApiResponse<>(code, message, null);
    }
}
