package bidding.system.auction.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
public class BidResponse {
    private Integer status;
    private String message;
}
