package Utils;

import javax.mail.Message;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMailHTML{

    public boolean inviaMail(String mitt, String dest, String object, String text/*, String filename*/){
        final String username = "mattiapica94@gmail.com";
        final String password = "trottola123";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(mitt));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(dest));
            message.setSubject(object);
            //String s = "La sua prenotazione è stata eliminata/html";
            //String s = "La sua prenotazione è stata eliminata";

            //message.setContent(String.valueOf(s), s);

            message.setText(text);

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        return true;
    }
    /*public static void main(String[]args) {
        SendMailHTML sendmail = new SendMailHTML();
        sendmail.inviaMail("mattiapica94@gmail.com", "lorenzo.zara96@gmail.com ", "oggetto", "text");
    }*/
}