// BeanCopyUtils.java - 需要创建这个工具类
package top.sxmeng.utils;

import org.springframework.beans.BeanUtils;

// BeanCopyUtils.java - 确保这个方法正确实现
public class BeanCopyUtils {
    public static <V> V copyBean(Object source, Class<V> clazz) {
        if (source == null) {
            return null;
        }
        V result = null;
        try {
            result = clazz.getDeclaredConstructor().newInstance();
            BeanUtils.copyProperties(source, result);
        } catch (Exception e) {
            throw new RuntimeException("Bean拷贝失败", e);
        }
        return result;
    }
}