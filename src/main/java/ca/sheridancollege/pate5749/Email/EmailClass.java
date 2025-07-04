package ca.sheridancollege.pate5749.Email;

import java.util.Properties;

import org.springframework.core.env.Environment;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class EmailClass {
    private final Environment env;
    public EmailClass(Environment env) {
        this.env = env;
    }
@Bean
public JavaMailSender getJavaMailSender() {
JavaMailSenderImpl mailSender =
new JavaMailSenderImpl();
mailSender.setHost("smtp.gmail.com");
mailSender.setPort(587);
mailSender.setUsername("cleanandsweepmaster2024@gmail.com");
mailSender.setPassword(env.getProperty("app.api.key"));

Properties props = mailSender.getJavaMailProperties();
props.put("mail.transport.protocol", "smtp");
props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
props.put("mail.smtp.auth", "true");
props.put("mail.smtp.starttls.enable", "true");
props.put("mail.debug", "true");
return mailSender;
}
}
