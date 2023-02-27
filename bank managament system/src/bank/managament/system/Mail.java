
package bank.managament.system;


import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Message;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Mail {
    
    public static boolean sendPin(String gmail,String password,String AccountNo) {
        boolean flag = false;
        final String SMTP_HOST = "smtp.gmail.com";
        final String SMTP_PORT = "587";
        final String GMAIL_USERNAME = "vikrampratapsingh628@gmail.com";
        final String GMAIL_PASSWORD = "glqwzpijniaakoum";

        System.out.println("Process Started");

        Properties prop = System.getProperties();
        prop.setProperty("mail.smtp.starttls.enable", "true");
        prop.setProperty("mail.smtp.host", SMTP_HOST);
        prop.setProperty("mail.smtp.user", GMAIL_USERNAME);
        prop.setProperty("mail.smtp.password", GMAIL_PASSWORD);
        prop.setProperty("mail.smtp.port", SMTP_PORT);
        prop.setProperty("mail.smtp.auth", "true");
        prop.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");
        prop.setProperty("mail.smtp.starttls.enable", "true");

       // System.out.println("Props : " + prop);

        Session session = Session.getInstance(prop, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(GMAIL_USERNAME,
                        GMAIL_PASSWORD);
            }
        });

        //System.out.println("Got Session : " + session);

        MimeMessage message = new MimeMessage(session);
        try {
           // System.out.println("before sending");
            message.setFrom(new InternetAddress(GMAIL_USERNAME));
            message.addRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(gmail));
            message.setSubject("Account  information");
            message.setText("your Accout was ceated succesful "+"\n"+"Your AccountNo "+AccountNo+"\n"+ "Your password is "+password);
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(gmail));
            Transport transport = session.getTransport("smtp");
            //System.out.println("Got Transport" + transport);
            transport.connect(SMTP_HOST, GMAIL_USERNAME, GMAIL_PASSWORD);
            transport.sendMessage(message, message.getAllRecipients());
           // System.out.println("message Object : " + message);
            System.out.println("Email Sent Successfully");
            flag = true;
        }catch (Exception e) {
             System.out.println("G mail not found");
             System.out.println("Note :- Check your internet");
             flag = false;
        }

        return flag;
    }
    
}
