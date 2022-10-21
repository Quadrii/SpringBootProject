package africa.semicolon.lumExpress.data.dto.request;

import lombok.*;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerRegisterRequest {
    @NotNull(message = "country cannot be null")
    @NotEmpty(message = "country cannot be empty")
    private String country;
    @NotNull(message = "email cannot be null")
    @NotEmpty(message = "email cannot be empty")
    @Email(message = "invalid Email")
    private String email;
    @NotNull(message = "provide a name")
    @NotEmpty(message = ("firs name cannot be empty"))
    private String firstName;
    @NotNull
    @NotEmpty
    private String password;
}
