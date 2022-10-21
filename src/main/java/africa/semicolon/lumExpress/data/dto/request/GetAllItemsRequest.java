package africa.semicolon.lumExpress.data.dto.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetAllItemsRequest {
    private int numberOfItemsPerPage;
    private int pageNumber;
}
