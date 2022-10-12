package africa.semicolon.lumExpress.data.repositories;

import africa.semicolon.lumExpress.data.models.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
}
