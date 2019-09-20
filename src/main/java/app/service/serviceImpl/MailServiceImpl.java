package app.service.serviceImpl;

import org.springframework.stereotype.Service;

import javax.activation.*;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import app.model.Magazine;
import app.model.Subscriber;
import app.service.MailService;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Properties;


@Service
public class MailServiceImpl implements MailService {

   // @Autowired
 //   private JavaMailSenderImpl mailSender = new JavaMailSenderImpl();;


    public void sendEmail(Magazine magazine, List<Subscriber> subscribers) {
        byteToFile(magazine);
        System.out.println(subscribers);
        subscribers.forEach(subscriber -> {
            try {
                Properties props = new Properties();
                props.put("mail.smtp.host", "smtp.gmail.com");
                props.put("mail.smtp.port", "587");
                props.put("mail.smtp.auth", "true");
                props.put("mail.smtp.starttls.enable", "true");

                String fromEmail = "zemtsova18anna@gmail.com";
                String toEmail = subscriber.getEmail();
                String password = "A1n610n610A1F13k144N610";
                Authenticator auth = new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(fromEmail, password);
                    }
                };
                Session session = Session.getInstance(props, auth);

                MimeMessage message = new MimeMessage(session);
                message.setFrom(new InternetAddress(fromEmail));
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
                message.setSubject("Журнал \"Математика\"");
                BodyPart messageBodyPart;
                Multipart multipart = new MimeMultipart();
                BodyPart messageBodyPartText = new MimeBodyPart();
                messageBodyPartText.setText(subscriber.getName()+ ",\nВышел новый журнал \"Математика\"! Приятного изучения!\n\n");
                multipart.addBodyPart(messageBodyPartText);

                messageBodyPart = new MimeBodyPart();
                String filename = "tmp.pdf";
                DataSource source = new FileDataSource(filename);
                messageBodyPart.setDataHandler(new DataHandler(source));
                messageBodyPart.setFileName(magazine.getName());
                multipart.addBodyPart(messageBodyPart);

                message.setContent(multipart);
                Transport.send(message);
            } catch (Exception ex) {
                System.out.println(ex);
            }
        });
    }


    private void byteToFile(Magazine magazine) {
        OutputStream out = null;
        try {
            out = new FileOutputStream("tmp.pdf");
            out.write(magazine.getBody());
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
