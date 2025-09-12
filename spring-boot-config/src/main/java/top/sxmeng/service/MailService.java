package top.sxmeng.service;

import top.sxmeng.enums.ResultStatus;
import top.sxmeng.model.Mail;

import java.io.File;

public interface MailService {
    ResultStatus sendSimpleMail(Mail mail);

    ResultStatus sendHTMLMail(Mail mail);

    ResultStatus sendAttachmentsMail(Mail mail, File file);
}