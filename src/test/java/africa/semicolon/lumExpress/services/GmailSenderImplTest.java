package africa.semicolon.lumExpress.services;

import africa.semicolon.lumExpress.services.notification.EmailDetails;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class GmailSenderImplTest {
    @Autowired
    private EmailSender emailSender;

    @Test
    void sendHtmlEmail() {
        EmailDetails emailDetails = new EmailDetails();
        emailDetails.setUserEmail("adisaquadri234@yahoo.com");
        emailDetails.setMailContent("Hello, Mr Queue");
        String response = emailSender.sendHtmlEmail(emailDetails);
        assertThat(response.contains("successfully")).isTrue();
    }
}