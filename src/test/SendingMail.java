package test;



import java.util.*;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SendingMail{
	
	
	public static void main(String args[]){
		String[] to={"surendrap@smartek21.com"};
		String[] cc={};
		String[] bcc={};
		
		SendingMail.sendMail(
				"seleniumwebdriverapi@gmail.com", 
				"selenium2", 
				"smtp.gmail.com", 
				"465", 
				"true", 
				"true", 
				true, 
				"javax.net.ssl.SSLSocketFactory", 
				"false", 
				to, 
				cc, 
				bcc, 
				"Automation test reports", 
				"Please find the attached",
				"D:\\Selinium\\Java_Workspace\\Core_DataDriven_Framework_TestNG\\XSLT_Reports\\output\\Report.zip");
		
	}

	

public static boolean sendMail(String userName,String passWord,String host,String port,
		String starttls,String auth,boolean debug,String socketFactoryClass,String fallback,
		String[] to,String[] cc,String[] bcc,String subject,String text, String attachment){

		Properties props = new Properties();
		//Properties props=System.getProperties();
		props.put("mail.smtp.user", userName);
		props.put("mail.smtp.host", host);
		if(!"".equals(port))
			props.put("mail.smtp.port", port);
		if(!"".equals(starttls))
			props.put("mail.smtp.starttls.enable",starttls);
		props.put("mail.smtp.auth", auth);
		if(debug){
			props.put("mail.smtp.debug", "true");
		}else{
			props.put("mail.smtp.debug", "false");
		}
		if(!"".equals(port))
			props.put("mail.smtp.socketFactory.port", port);
		if(!"".equals(socketFactoryClass))
			props.put("mail.smtp.socketFactory.class",socketFactoryClass);
		if(!"".equals(fallback))
			props.put("mail.smtp.socketFactory.fallback", fallback);
		try
		{
			Session session = Session.getDefaultInstance(props, null);
			session.setDebug(debug);
			MimeMessage msg = new MimeMessage(session);
			msg.setText(text);
			msg.setSubject(subject);
			msg.setFrom(new InternetAddress("seleniumwebdriverapi@gmail.com"));
			for(int i=0;i<to.length;i++){
				msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to[i]));
			}

			for(int i=0;i<cc.length;i++){
				msg.addRecipient(Message.RecipientType.CC, new InternetAddress(cc[i]));
			}

			for(int i=0;i<bcc.length;i++){
				msg.addRecipient(Message.RecipientType.BCC, new InternetAddress(bcc[i]));
			}
			msg.saveChanges();
			
			BodyPart textPart = new MimeBodyPart();
			textPart.setText("Test 111");
			//textPart.setContent("<h1>Check attachment</h1>", "html/text");
			
			
			Multipart mp = new MimeMultipart();
			mp.addBodyPart(textPart);
			
			
			BodyPart attachFilePart = new MimeBodyPart();
			DataSource fds = new FileDataSource(attachment);
			attachFilePart.setDataHandler(new DataHandler(fds));		
			attachFilePart.setFileName(fds.getName());

			
			mp.addBodyPart(attachFilePart);

			msg.setContent(mp);
						
			Transport transport = session.getTransport("smtp");
			transport.connect(host, userName, passWord);
			transport.sendMessage(msg, msg.getAllRecipients());			
			transport.close();
			
			return true;
		}
		catch (Exception mex)
		{
			mex.printStackTrace();
			return false;
		}


		}

	}
