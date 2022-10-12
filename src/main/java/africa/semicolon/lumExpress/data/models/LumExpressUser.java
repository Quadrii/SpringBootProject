package africa.semicolon.lumExpress.data.models;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LumExpressUser {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phoneNumber;
    private String imgUrl;
    private boolean isEnabled;
    private List<Notification>messages = new ArrayList<>();
}
