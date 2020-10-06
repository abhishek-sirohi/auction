package bidding.system.auction.model;

import bidding.system.auction.data.AuctionStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class Auction {

    private Integer auctionId;

    private String itemName;

    private String itemDescription;

    private AuctionStatus auctionStatus;

    private BigDecimal stepRate;

    private BigDecimal minBidPrice;

    private Date startDate;

    private Date endDate;

    private BigDecimal commission;

    private BigDecimal highestBid;
}
