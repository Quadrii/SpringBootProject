package africa.semicolon.lumExpress.data.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerRegisterResponse {
    private String message;
    private int userId;
    private int code;
}
