package com.library.services;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.library.entities.User;

@Service
public class EmailService {
	
	private final Log log = LogFactory.getLog(this.getClass());
	
	@Value("${spring.mail.username}")
	private String MESSAGE_FROM;
	
	@Value("${service.url}")
	private String WEB_URL;
	
	private JavaMailSender javaMailSender;
	
	@Autowired
	public void setJavaMailSender(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}
	
	public void sendAuthMessage(User user) {
		SimpleMailMessage message = null;
		
		try {
			message = new SimpleMailMessage();
			message.setFrom(MESSAGE_FROM);
			message.setTo(user.getEmail());
			message.setSubject("Regsitráció megerősítése");
			message.setText("Kedves " + user.getFullName() + "! \n \nAz alábbi linkre kattintva megerősítheti a registrációt.\n"+WEB_URL+"/activation/"+user.getActivation());
			javaMailSender.send(message);
			
			
		} catch (Exception e) {
			log.error("hiba az email küldésekor");
		}
	}
}
