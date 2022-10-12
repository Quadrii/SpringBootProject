package africa.semicolon.lumExpress.data.models;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private BigDecimal price;
    private String description;
    private int quantity;
    private String imageUrl;
    @ElementCollection(fetch = FetchType.EAGER)
    private List<Category> category = new ArrayList<>();
}
