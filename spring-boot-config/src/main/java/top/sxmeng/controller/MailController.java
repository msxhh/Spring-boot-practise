package top.sxmeng.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.sxmeng.common.ApiResponse;
import top.sxmeng.enums.ResultStatus;
import top.sxmeng.model.Mail;
import top.sxmeng.service.MailService;

import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/mail")
public class MailController {

    private final MailService mailService;

    @Autowired
    public MailController(MailService mailService) {
        this.mailService = mailService;
    }

    @PostMapping("/simple")
    public ResponseEntity<ApiResponse<String>> sendSimpleMail(@Valid @RequestBody Mail mail) {
        ResultStatus resultStatus = mailService.sendSimpleMail(mail);
        if (resultStatus == ResultStatus.SUCCESS) {
            return ResponseEntity.ok(ApiResponse.success(resultStatus.getInfo(), "SUCCESS"));
        }
        return ResponseEntity.badRequest().body(ApiResponse.error(resultStatus.getInfo()));
    }

    @PostMapping("/html")
    public ResponseEntity<ApiResponse<String>> sendHTMLMail(@Valid @RequestBody Mail mail) {
        ResultStatus resultStatus = mailService.sendHTMLMail(mail);
        if (resultStatus == ResultStatus.SUCCESS) {
            return ResponseEntity.ok(ApiResponse.success(resultStatus.getInfo(), "SUCCESS"));
        }
        return ResponseEntity.badRequest().body(ApiResponse.error(resultStatus.getInfo()));
    }

    @PostMapping("/attachments")
    public ResponseEntity<ApiResponse<String>> sendAttachmentsMail(@Valid @RequestBody Mail mail) {
        // 这里假设测试文件路径，实际使用时需替换为真实文件路径
        java.io.File file = new java.io.File("/path/to/your/file.txt");
        ResultStatus resultStatus = mailService.sendAttachmentsMail(mail, file);
        if (resultStatus == ResultStatus.SUCCESS) {
            return ResponseEntity.ok(ApiResponse.success(resultStatus.getInfo(), "SUCCESS"));
        }
        return ResponseEntity.badRequest().body(ApiResponse.error(resultStatus.getInfo()));
    }
}