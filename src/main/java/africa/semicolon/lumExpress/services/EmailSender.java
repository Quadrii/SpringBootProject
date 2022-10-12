package africa.semicolon.lumExpress.services;

import africa.semicolon.lumExpress.services.notification.EmailDetails;

public interface EmailSender {
    String sendHtmlEmail(EmailDetails emailDetails);
}
