package africa.semicolon.lumExpress.data.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class GetAllItemsRequest {
    private int numberOfItemsPerPage;
    private int pageNumber;
}
