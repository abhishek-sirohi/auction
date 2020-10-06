package bidding.system.auction.data;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "ITEM_BID")
public class ItemBid {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ITEM_BID_ID", unique = true, nullable = false)
    private Integer itemBidId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "AUCTION_ID", nullable = false)
    private AuctionData auction;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", nullable =  false)
    private UserInfo biddingUser;

    @Column(name ="BID_AMOUNT", nullable = false)
    private BigDecimal bidAmount;

    @Column(name ="BID_STATUS", nullable = false)
    private BidStatus bidStatus;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name ="CREATED_ON",length = 19,nullable = false)
    private Date createdOn;
}
