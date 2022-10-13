package africa.semicolon.lumExpress.data.models;

import lombok.*;

import javax.persistence.MappedSuperclass;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter

@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class LumExpressUser {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phoneNumber;
    private String imgUrl;
    private boolean isEnabled;
}
