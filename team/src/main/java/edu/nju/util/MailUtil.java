package edu.nju.util;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

/**
 * @Author ：lycheeshell
 * @Date ：Created in 21:54 2020/3/28
 */
public class MailUtil {

    private static final String HOST = "smtp.163.com";
    private static final Integer PORT = 25;
    private static final String USERNAME = "ticketsnju@163.com";
    private static final String PASSWORD = "tickets666";
    private static final String emailFrom = "ticketsnju@163.com";
    private static final String timeout = "25000";
    private static final String personal = "南哪儿组局";

    private static JavaMailSenderImpl mailSender = createMailSender();
    /**
     * 邮件发送器
     *
     * @return 配置好的工具
     */
    private static JavaMailSenderImpl createMailSender() {
        JavaMailSenderImpl sender = new JavaMailSenderImpl();
        sender.setHost(HOST);
        sender.setPort(PORT);
        sender.setUsername(USERNAME);
        sender.setPassword(PASSWORD);
        sender.setDefaultEncoding("Utf-8");
        Properties p = new Properties();
        p.setProperty("mail.smtp.timeout", timeout);
        p.setProperty("mail.smtp.auth", "false");
        sender.setJavaMailProperties(p);
        return sender;
    }

    /**
     * 发送邮件
     *
     * @param to 接受人
     * @param subject 主题
     * @param html 发送内容
     * @throws MessagingException 异常
     * @throws UnsupportedEncodingException 异常
     */
    public static void sendMail(String to, String subject, String html) throws MessagingException,UnsupportedEncodingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        // 设置utf-8或GBK编码，否则邮件会有乱码
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
        messageHelper.setFrom(emailFrom, personal);
        messageHelper.setTo(to);
        messageHelper.setSubject(subject);
        messageHelper.setText(html, true);
        mailSender.send(mimeMessage);
        System.out.println("邮件已发送至" + to);
    }

}
