package africa.semicolon.lumExpress.services.notification;
//EmailNotification
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class EmailDetails {
    private String userEmail;
    private String mailContent;
}
