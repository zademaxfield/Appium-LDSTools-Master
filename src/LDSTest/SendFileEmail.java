package LDSTest;

import java.util.*;

import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

import com.sun.mail.smtp.SMTPTransport;

public class SendFileEmail
{
   public static void main(String [] args)
   {

      // Recipient's email ID needs to be mentioned.
      String to = "zmaxfield@ldschurch.org";
      // Sender's email ID needs to be mentioned
      String from = "zademobile007@gmail.com";
      // Assuming you are sending email from localhost
      //String host = "localhost";
      // Setup mail server
      //properties.setProperty("mail.smtp.host", host);
      // Get the default Session object.
     
      final String username = "zademobile007@gmail.com";
      final String password = "ldschurch1";

      Properties props = new Properties();
      props.put("mail.smtp.starttls.enable", "true");
      props.put("mail.smtp.auth", "true");
      props.put("mail.smtp.host", "smtp.gmail.com");
      props.put("mail.smtp.port", "465");
      props.put("mail.smtp.socketFactory.port", "465");
      props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
      props.put("mail.smtp.socketFactory.fallback", "false");

      Session session = Session.getInstance(props,
        new javax.mail.Authenticator() {
          protected PasswordAuthentication getPasswordAuthentication() {
              return new PasswordAuthentication(username, password);
          }
        });
      
      
      
      

      try{
         // Create a default MimeMessage object.
         MimeMessage message = new MimeMessage(session);
         // Set From: header field of the header.
         message.setFrom(new InternetAddress(from));
         // Set To: header field of the header.
         message.addRecipient(Message.RecipientType.TO,
                                  new InternetAddress(to));

         // Set Subject: header field
         message.setSubject("Automated Test Results");

         // Create the message part 
         BodyPart messageBodyPart = new MimeBodyPart();

         // Fill the message
         messageBodyPart.setText("Attached are the results for the automated test.");

         // Create a multipart message
         Multipart multipart = new MimeMultipart();

         // Set text message part
         multipart.addBodyPart(messageBodyPart);

         // Part two is attachment
         messageBodyPart = new MimeBodyPart();
         String filename = "test-output/custom-report.html";
         DataSource source = new FileDataSource(filename);
         messageBodyPart.setDataHandler(new DataHandler(source));
         messageBodyPart.setFileName(filename);
         multipart.addBodyPart(messageBodyPart);

         // Send the complete message parts
         message.setContent(multipart );

         // Send message
         Transport.send(message);
         System.out.println("Sent message successfully....");
      } catch (MessagingException mex) {
         mex.printStackTrace();
      }
   }
}
