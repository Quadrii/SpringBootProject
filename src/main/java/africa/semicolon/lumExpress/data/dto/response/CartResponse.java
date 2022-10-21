package africa.semicolon.lumExpress.data.dto.response;

import africa.semicolon.lumExpress.data.models.Cart;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartResponse {
    private String message;
    private Cart cart;
}
