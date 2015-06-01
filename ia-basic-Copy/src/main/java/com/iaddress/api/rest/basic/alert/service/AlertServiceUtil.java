package com.iaddress.api.rest.basic.alert.service;


import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.lang.StringUtils;

public class AlertServiceUtil {

	
	public static void sendEMail(String... args) {
		  if(args.length == 0 || StringUtils.isBlank(args[0])){
			  throw new IllegalArgumentException();
		  }
		  // Recipient's email ID needs to be mentioned.
	      String to = args[0];//change accordingly
	      String mail_subject = "Test Mail";
	      if(args.length > 1 && StringUtils.isNotBlank(args[1])){
			  mail_subject = args[1];
		  }
	      String mail_content = "Its a test mail. Please Ignore.";
	      if(args.length > 2 && StringUtils.isNotBlank(args[2])){
	    	  mail_content = args[2];
		  }

	      // Sender's email ID needs to be mentioned
	      String from = "abc@gmail.com";//change accordingly
	      final String username = "donotreply@bworks.in";//change accordingly
	      final String password = "allowAll777";//change accordingly

	      // Assuming you are sending email through relay.jangosmtp.net
	      String host = "smtp.gmail.com";

	      Properties props = new Properties();
	      props.put("mail.smtp.auth", "true");
	      props.put("mail.smtp.starttls.enable", "true");
	      props.put("mail.smtp.host", host);
	      props.put("mail.smtp.port", "587");

	      // Get the Session object.
	      Session session = Session.getInstance(props,
	      new javax.mail.Authenticator() {
	         protected PasswordAuthentication getPasswordAuthentication() {
	            return new PasswordAuthentication(username, password);
	         }
	      });

	      try {
	         // Create a default MimeMessage object.
	         Message message = new MimeMessage(session);

	         // Set From: header field of the header.
	         message.setFrom(new InternetAddress(from));

	         // Set To: header field of the header.
	         message.setRecipients(Message.RecipientType.TO,
	         InternetAddress.parse(to));

	         // Set Subject: header field
	         message.setSubject(mail_subject);

	         // Now set the actual message
	         message.setText(mail_content);

	         // Send message
	         Transport.send(message);

	         System.out.println("Sent message successfully....");

	      } catch (MessagingException e) {
	            throw new RuntimeException(e);
	      }
	}
}
