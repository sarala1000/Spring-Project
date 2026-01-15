package com.handson.basic.util;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
public class EmailService {

    protected final Log logger = LogFactory.getLog(getClass());

    @Value("${mail.host}")
    private String host;

    @Value("${mail.port}")
    private int port;

    @Value("${mail.user}")
    private String user;

    @Value("${mail.password}")
    private String password;

    public boolean send(String subject, String text, String toEmail) {
        if (toEmail == null || toEmail.isBlank()) return false;

        try {
            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.port", String.valueOf(port));

            Session session = Session.getInstance(props, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(user, password);
                }
            });

            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(toEmail)
            );
            message.setSubject(subject, "UTF-8");
            message.setText(text, "UTF-8");

            Transport.send(message);
            return true;

        } catch (Exception e) {
            logger.error("Failed to send email", e);
            return false;
        }
    }
}

