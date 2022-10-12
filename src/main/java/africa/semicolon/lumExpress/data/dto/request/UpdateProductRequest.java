package africa.semicolon.lumExpress.data.dto.request;

import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UpdateProductRequest {
    private BigDecimal price;
    private int quantity;
    private String description;
    private Long productId;
}
