package africa.semicolon.lumExpress.services;

import africa.semicolon.lumExpress.data.dto.request.NotificationRequest;

public interface NotificationService {
    String send(NotificationRequest notificationRequest);
}
