package africa.semicolon.lumExpress.services;

import africa.semicolon.lumExpress.services.notification.EmailDetails;
import lombok.AllArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@AllArgsConstructor
public class GmailSenderImpl implements EmailSender {

    private final JavaMailSender javaMailSender;

    @Override
    public String sendHtmlEmail(EmailDetails emailDetails) {

        MimeMessage mineMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mineMessageHelper = new MimeMessageHelper(mineMessage);
        try{
            mineMessageHelper.setFrom("no-reply@email.lumexpress.com.ng");
            mineMessageHelper.setTo(emailDetails.getUserEmail());
            mineMessageHelper.setText(emailDetails.getMailContent(), true);
            javaMailSender.send(mineMessage);
            return String.format("email sent to %s successfully", emailDetails.getUserEmail());
        }catch(MessagingException e){
            e.printStackTrace();
        }
        return String.format("email not sent to %s", emailDetails.getUserEmail());
    }
}
