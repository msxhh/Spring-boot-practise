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
        mail.setContent("<!DOCTYPE html>\n" +
                "<html>\n" +
                "  <head>\n" +
                "    <meta charset=\"UTF-8\" />\n" +
                "    <style>\n" +
                "      body {\n" +
                "        font-family: \"Microsoft YaHei\", Arial, sans-serif;\n" +
                "        background-color: #f6f8fa;\n" +
                "        margin: 0;\n" +
                "        padding: 0;\n" +
                "      }\n" +
                "      .container {\n" +
                "        max-width: 600px;\n" +
                "        margin: 30px auto;\n" +
                "        background: #ffffff;\n" +
                "        border-radius: 8px;\n" +
                "        box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);\n" +
                "      }\n" +
                "      .header {\n" +
                "        background: #2d8cf0;\n" +
                "        color: #fff;\n" +
                "        text-align: center;\n" +
                "        padding: 20px;\n" +
                "        font-size: 24px;\n" +
                "        font-weight: bold;\n" +
                "      }\n" +
                "      .content {\n" +
                "        padding: 30px;\n" +
                "        color: #333;\n" +
                "        line-height: 1.6;\n" +
                "      }\n" +
                "      .content h2 {\n" +
                "        color: #2d8cf0;\n" +
                "      }\n" +
                "      .button {\n" +
                "        display: inline-block;\n" +
                "        margin: 20px 0;\n" +
                "        padding: 12px 28px;\n" +
                "        background: #2d8cf0;\n" +
                "        color: #fff;\n" +
                "        text-decoration: none;\n" +
                "        border-radius: 4px;\n" +
                "        font-size: 16px;\n" +
                "      }\n" +
                "      .note {\n" +
                "        font-size: 13px;\n" +
                "        color: #888;\n" +
                "        margin-top: 15px;\n" +
                "      }\n" +
                "      .footer {\n" +
                "        background: #f1f1f1;\n" +
                "        text-align: center;\n" +
                "        font-size: 12px;\n" +
                "        color: #666;\n" +
                "        padding: 15px;\n" +
                "      }\n" +
                "    </style>\n" +
                "  </head>\n" +
                "  <body>\n" +
                "    <div class=\"container\">\n" +
                "      <div class=\"header\">账号注册成功确认</div>\n" +
                "      <div class=\"content\">\n" +
                "        <h2>亲爱的用户，欢迎加入！</h2>\n" +
                "        <p>\n" +
                "          您的账号已经注册成功，请点击下方按钮完成邮箱确认，开启完整功能体验：\n" +
                "        </p>\n" +
                "        <a href=\"https://example.com/verify?token=xxxxxx\" class=\"button\"\n" +
                "          >确认我的邮箱</a\n" +
                "        >\n" +
                "        <p class=\"note\">如果按钮无法点击，请复制以下链接到浏览器打开：</p>\n" +
                "        <p class=\"note\">https://example.com/verify?token=xxxxxx</p>\n" +
                "        <hr style=\"margin: 30px 0\" />\n" +
                "        <p>\n" +
                "          为了保障您的账号安全，本链接将在\n" +
                "          <b>24小时内失效</b>。如果您没有注册该账号，请忽略此邮件。\n" +
                "        </p>\n" +
                "      </div>\n" +
                "      <div class=\"footer\">\n" +
                "        © 2025 示例平台 · 本邮件由系统自动发送，请勿直接回复\n" +
                "      </div>\n" +
                "    </div>\n" +
                "  </body>\n" +
                "</html>\n");
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