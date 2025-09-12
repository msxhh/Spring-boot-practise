package top.sxmeng.service;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import top.sxmeng.model.Mail;

import java.io.File;

@SpringBootTest
class MailServiceTest {
    @Resource
    private MailService mailService;

    @Test
    void sendSimpleMail() {
        Mail mail = new Mail();
        mail.setTo("449266367@qq.com");
        mail.setSubject("测试简单邮件");
        mail.setContent("这是简单邮件的测试内容");
        mailService.sendSimpleMail(mail);
    }

    @Test
    void sendHTMLMail() {
        Mail mail = new Mail();
        mail.setTo("449266367@qq.com");
        mail.setSubject("测试HTML邮件");
        mail.setContent("<html><body><h1>这是HTML邮件的测试内容</h1></body></html>");
        mailService.sendHTMLMail(mail);
    }

    @Test
    void sendAttachmentsMail() {
        Mail mail = new Mail();
        mail.setTo("449266367@qq.com");
        mail.setSubject("测试带附件邮件");
        mail.setContent("这是带附件邮件的测试内容");
        // 这里替换为实际存在的文件路径
        File file = new File("/path/to/your/file.txt");
        mailService.sendAttachmentsMail(mail, file);
    }
}