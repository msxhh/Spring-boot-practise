package top.sxmeng.service;

import org.springframework.web.multipart.MultipartFile;

public interface OssService {
    /**
     * 文件上传
     * @param file
     */
    String upload(MultipartFile file);
}
