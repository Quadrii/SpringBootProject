package africa.semicolon.lumExpress.data.dto.request;

import com.sun.istack.NotNull;
import lombok.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
@Validated
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CreateProductRequest {
    private String name;
    private BigDecimal price;
    private int quantity;
    private String productCategory;
    @NotNull
    private MultipartFile image;
}
