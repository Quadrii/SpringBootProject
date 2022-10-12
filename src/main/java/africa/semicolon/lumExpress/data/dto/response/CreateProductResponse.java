package africa.semicolon.lumExpress.data.dto.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CreateProductResponse {
    private Long productId;
    private String message;
    private int code;
}
