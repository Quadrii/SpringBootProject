package africa.semicolon.lumExpress.data.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerRegisterRequest {
    private String country;
    private String email;
    private String password;
}
