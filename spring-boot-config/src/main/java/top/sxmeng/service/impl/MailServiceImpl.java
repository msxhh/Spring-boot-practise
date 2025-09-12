package top.sxmeng.service.impl;

import jakarta.annotation.Resource;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import top.sxmeng.enums.ResultStatus;
import top.sxmeng.model.Mail;
import top.sxmeng.service.MailService;

import java.io.File;

@Service
public class MailServiceImpl implements MailService {

    @Value("${spring.mail.username}")
    private String from;

    @Resource
    private JavaMailSender javaMailSender;

    @Override
    public ResultStatus sendSimpleMail(Mail mail) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(mail.getTo());
        message.setSubject(mail.getSubject());
        message.setText(mail.getContent());
        try {
            javaMailSender.send(message);
            return ResultStatus.SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultStatus.FAIL;
        }
    }

    @Override
    public ResultStatus sendHTMLMail(Mail mail) {
        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(mail.getTo());
            helper.setSubject(mail.getSubject());
            helper.setText(mail.getContent(), true);
            javaMailSender.send(message);
            return ResultStatus.SUCCESS;
        } catch (MessagingException e) {
            e.printStackTrace();
            return ResultStatus.FAIL;
        }
    }

    @Override
    public ResultStatus sendAttachmentsMail(Mail mail, File file) {
        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(mail.getTo());
            helper.setSubject(mail.getSubject());
            helper.setText(mail.getContent());
            if (file.exists()) {
                helper.addAttachment(file.getName(), file);
            } else {
                return ResultStatus.FAIL;
            }
            javaMailSender.send(message);
            return ResultStatus.SUCCESS;
        } catch (MessagingException e) {
            e.printStackTrace();
            return ResultStatus.FAIL;
        }
    }
}