/*
package Utils;

import java.util.*;
import javax.mail.*;
import javax.mail.Message;
import javax.mail.internet.*;
import javax.activation.*;

    public class SendMailAttachment {

        public SendMailAttachment() {

        }

        public void inviaMail(int x, String mitt, String dest, String object, String text, String filename){
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



                BodyPart messageBodyPart = new MimeBodyPart();
                messageBodyPart.setText(text);
                Multipart multipart = new MimeMultipart();
                multipart.addBodyPart(messageBodyPart);
                // Part two is attachment
                messageBodyPart = new MimeBodyPart();
                DataSource source = new FileDataSource(filename);
                messageBodyPart.setDataHandler(new DataHandler(source));
                messageBodyPart.setFileName(filename);
                multipart.addBodyPart(messageBodyPart);

                // Send the complete message parts
                message.setContent(multipart);

                Transport.send(message);

                System.out.println("Done");

            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
        }
        public static void main(String[]args) {
            SendMailAttachment sendmail = new SendMailAttachment();

            sendmail.inviaMail(2, "mattiapica94@gmail.com", "mattia_94p@hotmail.it", "oggetto",  "testo", "/home/mattia/Scrivania/vimrc");
        }


    }

*/

