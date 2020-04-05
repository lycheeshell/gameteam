package edu.nju.util;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.sun.mail.util.MailSSLSocketFactory;
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

    /**
     * ssl加密发送邮件
     *
     * @param strMail   收件人
     * @param strTitle  邮件标题
     * @param strText   邮件内容
     * @return
     */
    public static void sendEmail(String strMail, String strTitle, String strText){
        try {
            final Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.host", "smtp.163.com");
            //使用SSL加密SMTP通过465端口进行邮件发送
            MailSSLSocketFactory sf = new MailSSLSocketFactory();
            sf.setTrustAllHosts(true);
            props.put("mail.smtp.ssl.enable","true");
            props.put("mail.smtp.ssl.socketFactory",sf);
            //你自己的邮箱
            props.put("mail.user", USERNAME);
            //你开启pop3/smtp时的验证码
            props.put("mail.password", PASSWORD);
            //此时将端口设置为465
            props.put("mail.smtp.port", "465");
            props.put("mail.smtp.starttls.enable", "true");
            Authenticator authenticator = new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    String userName = props.getProperty("mail.user");
                    String password = props.getProperty("mail.password");
                    return new PasswordAuthentication(userName, password);
                }
            };
            // 使用环境属性和授权信息，创建邮件会话
            Session mailSession = Session.getInstance(props, authenticator);
            // 创建邮件消息
            MimeMessage message = new MimeMessage(mailSession);
            // 设置发件人
            String username = props.getProperty("mail.user");
            InternetAddress form = new InternetAddress(username);
            message.setFrom(form);
            InternetAddress to = new InternetAddress(strMail);
            message.setRecipient(Message.RecipientType.TO, to);
            // 设置邮件标题
            message.setSubject(strTitle);
            // 设置邮件的内容体
            message.setContent(strText, "text/html;charset=UTF-8");
            // 发送邮件
            Transport.send(message);
        } catch (MessagingException e) {
            System.out.println("发送邮件失败:"+e.getMessage());
        } catch (GeneralSecurityException e) {
            System.out.println("发送邮件失败:"+e.getMessage());
        }
    }

}
