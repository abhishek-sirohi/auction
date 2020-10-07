package bidding.system.auction.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class BidRequest {
    private Integer userId;
    private BigDecimal bidAmount;
}
