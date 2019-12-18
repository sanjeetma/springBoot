package com.bridgelabz.fundoo.configure;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.Message;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

import com.bridgelabz.fundoo.model.RabbitMessageProvider;

@Component
public class JmsProvider {
	

	
	@Autowired
	public static RabbitTemplate tempRabbit;
	private static JavaMailSender javaMailSender = new JavaMailSenderImpl();
	public static final String EXCHANGE_NAME = "tips_tx";
	   public static final String ROUTING_KEY = "tips";

	public static void sendEmail(String toEmail, String subject, String body) {

	String fromEmail = "mailfromsanjeet@gmail.com";
	String password = "Mailfrom@sanjeet";

	Properties prop = new Properties();
	prop.put("mail.smtp.auth", "true");
	prop.put("mail.smtp.starttls.enable", "true");
	prop.put("mail.smtp.host", "smtp.gmail.com");
	prop.put("mail.smtp.port", "587");

	Authenticator auth = new Authenticator() {
	@Override
	protected PasswordAuthentication getPasswordAuthentication() {
	return new PasswordAuthentication(fromEmail, password);
	}
	};
	Session session = Session.getInstance(prop, auth);
	send(session, fromEmail, toEmail, subject, body);
	}

	private static void send(Session session, String fromEmail, String toEmail, String subject, String body) {
	try {
	MimeMessage message = new MimeMessage(session);
	message.setFrom(new InternetAddress(fromEmail,"sanjeet"));
	message.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
	message.setSubject(subject);
	message.setText(body);
	Transport.send(message);
	
	} catch (Exception e) {
	e.printStackTrace();
	System.out.println("can not send mail  ");

	}
	}
//	public static void sendRabbit(RabbitMessageProvider message) {
//		tempRabbit.convertAndSend("tips_tx","tips",message);
//	}
//
//	@RabbitListener(queues = "default_parser_q")
//	public void sendToRabitMq(RabbitMessageProvider message) throws Exception{
//		sendEmail(message.getEmail(), message.getLink(), message.getToken());
//	}

	
	}

