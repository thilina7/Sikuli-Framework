package com.cucumber.common;

import com.github.mkolisnyk.cucumber.runner.ExtendedCucumber;

import java.io.File;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.log4j.Logger;
import org.junit.runner.notification.RunNotifier;


public class ExtraExtendedCucumber extends ExtendedCucumber {
	
	Logger log = Logger.getLogger("LOG");
	ConfigFileReader configFileReader;

    public ExtraExtendedCucumber(Class clazz) throws Exception {
        super(clazz);
    }

    @Override
    public void run(RunNotifier notifier) {
        super.run(notifier);
        sendMail();
    }
    
    public void sendMail() {
    	configFileReader = new ConfigFileReader();
		String fromEmailUserName = configFileReader.getLoginEmailUserName();
		String password = configFileReader.getLoginEmailPassword();

		Properties props = new Properties();
		props.put("mail.smtp.auth", true);
		props.put("mail.smtp.starttls.enable", true);
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		log.debug("Starting to send mail");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(fromEmailUserName, password);
			}
		});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(fromEmailUserName));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(configFileReader.getToEmailUserName()));
			message.setSubject("iPay Cucumber Test Report");

			Multipart multipart = new MimeMultipart();

			MimeBodyPart textBodyPart = new MimeBodyPart();
			textBodyPart.setText("Hi,\n\nPlease find the attached Cucumber Test Report");

			int i = 0;

			Thread.sleep(2000);

			System.out.println(configFileReader.getPDFAttachments());
			while (i < 6) {
				boolean filePath = false;
				for (String attachment : configFileReader.getPDFAttachments()) {
					File f = new File(attachment);
					if (f.exists()) {
						filePath = true;
					} else {
						filePath = false;
					}
				}
				if (filePath) {
					i = i + 6;
				} else {
					Thread.sleep(2000);
					i = i + 1;
				}
			}

			for (String attachment : configFileReader.getPDFAttachments()) {
				MimeBodyPart attachmentBodyPart = new MimeBodyPart();
				DataSource source = new FileDataSource(attachment);
				attachmentBodyPart.setDataHandler(new DataHandler(source));
				attachmentBodyPart.setFileName(attachment);
				multipart.addBodyPart(attachmentBodyPart);
			}

			multipart.addBodyPart(textBodyPart);
			message.setContent(multipart);
			Transport.send(message);
			log.debug("Finished send mail");

		} catch (MessagingException e) {
			log.error(e.getMessage(), e);
		} catch (Exception e1) {
			log.error(e1.getMessage(), e1);
		}
	}
}
