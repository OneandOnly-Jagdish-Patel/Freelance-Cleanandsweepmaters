package ca.sheridancollege.pate5749.Email;

import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Component
public class EmailServiceImpl {
@Autowired
public JavaMailSender emailSender;
//public void sendSimpleMessage(
//String to, String subject, String text) {
//SimpleMailMessage message = new SimpleMailMessage();
//message.setTo(to);
//message.setSubject(subject);
//message.setText(text);
//emailSender.send(message);
//}
@Autowired
private TemplateEngine templateEngine;
public void sendMailWithInline(String name,String to)
throws MessagingException {

final Context ctx = new Context();
ctx.setVariable("name", name);

final MimeMessage mimeMessage =
this.emailSender.createMimeMessage();
final MimeMessageHelper message =
new MimeMessageHelper(mimeMessage, true, "UTF-8");
message.setSubject("Thank you for reaching out");
message.setFrom("cleanandsweepmaster2024@gmail.com");
message.setTo(to);
//Create the HTML body using Thymeleaf
final String htmlContent = this.templateEngine
.process("emailTemplate.html", ctx);
message.setText(htmlContent, true);
//Send mail
this.emailSender.send(mimeMessage);
}

public void sendMailtoOwner(String name, String address,String to, BigInteger subject, String message, long id )
throws MessagingException {

final Context ctx = new Context();
ctx.setVariable("name", name);
ctx.setVariable("address",address);
ctx.setVariable("to",to);
ctx.setVariable("number",subject);
ctx.setVariable("message",message);
ctx.setVariable("id",id);

final MimeMessage mimeMessage =
this.emailSender.createMimeMessage();
final MimeMessageHelper message1 =
new MimeMessageHelper(mimeMessage, true, "UTF-8");
message1.setSubject("Request for Cleaning");
message1.setFrom("cleanandsweepmaster2024@gmail.com");
message1.setTo("cleanandsweepmaster2024@gmail.com");

final String htmlContent = this.templateEngine
.process("emailowner.html", ctx);
message1.setText(htmlContent, true);
//Send mail
this.emailSender.send(mimeMessage);
}

}