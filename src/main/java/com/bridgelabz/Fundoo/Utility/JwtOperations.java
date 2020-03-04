package com.bridgelabz.Fundoo.Utility;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

public class JwtOperations {
	private static final String SECRET="63000887";
	public String jwtToken(Long userId)
	{
		String token=null;
		token=JWT.create().withClaim("id",userId).sign(Algorithm.HMAC256(SECRET));
		return token;
	}
	public Long parseJWT(String jwt)
	{
		Long id=(long)0;
		if(jwt!=null)
		{
			
			id=JWT.require(Algorithm.HMAC256(SECRET)).build().verify(jwt).getClaim("id").asLong();
		}
		return id;
	}
	public static void sendEmail(String toEmail,String subject,String body)
	{
		String email=System.getenv("email");
		String password=System.getenv("password");
		Properties prop=new Properties();
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.starttls.enable", "true");
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", "587");
		Authenticator auth=new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication()
			{
				return new PasswordAuthentication(email, password);
				
			}
		};
		Session session=Session.getInstance(prop, auth);
		send(session,email,toEmail,subject,body);
	}

	private static void send(Session session, String email, String toEmail, String subject, String body) {
		MimeMessage message=new MimeMessage(session);
		try {
			message.setFrom(new InternetAddress(email, "koti"));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
			message.setSubject(subject);
			message.setText(body);
			Transport.send(message);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("exception occured while sending mail");
		}
		
	}


}
