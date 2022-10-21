package africa.semicolon.lumExpress.data.dto.request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UpdateCustomerProfile {
    private Long customerId;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String imgUrl;
    private String street;
    private String city;
    private Long buildingNumber;
    private String state;
}
