//package controlador;
//
//import javax.mail.*;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeMessage;
//import java.util.Properties;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//
//public class Email {
//    public static void enviarEmail(final String recipiente, String code) {
//        Properties properties = new Properties();
//        properties.put("mail.smtp.auth", "true");
//        properties.put("mail.smtp.starttls.enable", "true");
//        properties.put("mail.smtp.host", "smtp.gmail.com");
//        properties.put("mail.smtp.port", "587");
//
//        final String miCuentaEmail = "fernandez.shande@gmail.com";
//        final String password = "admin";
//
//        Session session = Session.getInstance(properties, new Authenticator() {
//            @Override
//            protected PasswordAuthentication getPasswordAuthentication() {
//                return new PasswordAuthentication(miCuentaEmail, password);
//            }
//        });
//
//        try {
//            Message message = new MimeMessage(session);
//            message.setFrom(new InternetAddress(miCuentaEmail));
//            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipiente));
//            message.setSubject("Prueba de 2AuthCode");
//            message.setText("Code es: " + code);
//
//            Transport.send(message);
//        } catch (MessagingException ex) {
//            Logger.getLogger(Email.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//}
