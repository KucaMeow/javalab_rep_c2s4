package ru.itis.services;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import ru.itis.dto.RegisterDto;
import ru.itis.repositories.UsersRepository;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Component
@PropertySource("classpath:email.properties")
public class EmailService {

    @Autowired
    private Environment environment;
    @Autowired
    private UsersRepository usersRepository;

    public void sendVerify(RegisterDto user) {
        try {
            Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", 587);
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            String username = environment.getProperty("email");
            String password = environment.getProperty("password");

            Session session = Session.getInstance(props,
                    new javax.mail.Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(username, password);
                        }
                    });


            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(user.getEmail()));
            message.setSubject("Verify email");

            Configuration cfg = new Configuration();
            cfg.setClassForTemplateLoading(EmailService.class, "/ftl/");
            cfg.setDefaultEncoding("UTF-8");
            cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
            Template template = cfg.getTemplate("email.ftl");

            Map paramMap = new HashMap();
            paramMap.put("email", user.getEmail());
            paramMap.put("username", user.getUsername());
            Writer out = new StringWriter();

            template.process(paramMap, out);
            BodyPart body = new MimeBodyPart();
            body.setContent(out.toString(), "text/html");
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(body);
            message.setContent(multipart);
            Transport.send(message);
        } catch (MessagingException | IOException | TemplateException e) {
            e.printStackTrace();
        }
    }

    public void verify(String email) {
        usersRepository.verify(email);
    }

}
