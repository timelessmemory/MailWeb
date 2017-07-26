package personal.timeless.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class MailService {
	
	@Autowired
	private JavaMailSender mailSender;

	@Value("${spring.mail.username}")
	private String from;

	public void send(String title, String content, String to) {
	  MimeMessage mailMessage = mailSender.createMimeMessage();
	  
	  try {
	    MimeMessageHelper helper = new MimeMessageHelper(mailMessage, true, "utf-8");
	    helper.setFrom(from);
	    helper.setTo(to);
	    helper.setSubject(title);
	    helper.setText(content, true);
	    mailSender.send(mailMessage);
	  } catch (MessagingException e) {
	    System.out.println("failed");
	  }
	}
}
