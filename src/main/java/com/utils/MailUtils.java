package com.utils;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * 邮件发送工具类
 * Created by Administrator on 2017/7/5.
 */
public class MailUtils {

    /**
     * 发送邮件的方法
     *
     * @param to   :给谁发送邮件
     * @param code :邮件的激活码
     */
    public static void sendMail(String to, String code) throws Exception {
        //1.创建连接对象，连接到邮箱服务器
        Properties properties = new Properties();
//        properties.setProperty("host",value);
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("service@store.com", "123");
            }
        });

        //2.创建邮件对象
        Message message = new MimeMessage(session);
        //2.1设置发件人
        message.setFrom(new InternetAddress("service@store.com"));
        //2.2设置收件人
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
        //2.3设置邮件的主题
        message.setSubject("来自xx网站的激活邮件");
        //2.4设置邮件的正文
        message.setContent("<h1>来自xx网站的激活邮件,激活请点击以下链接:</h1>" +
                        "<h3><a href='http://localhost:8080/MailDemo/user/active?code=" + code + "'>http://localhost:8080/MailDemo/user/active?code=" + code + "</a></h3>"
                , "text/html;charset=UTF-8");
//        System.out.println(message.getContent());
        //3.发送一封激活邮件
        Transport.send(message);
    }
}
