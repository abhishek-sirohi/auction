package bidding.system.auction.data;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.math.BigDecimal;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "AUCTION_DATA")
public class AuctionData {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "AUCTION_ID", unique = true, nullable = false)
    private Integer auctionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "AUCTION_HOUSE_ID", nullable = false)
    private AuctionHouse auctionHouse;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "AUCTION_ITEM_ID", nullable = false)
    private AuctionItem auctionItem;

    @Column(name = "AUCTION_STATUS", nullable = false)
    private AuctionStatus auctionStatus;

    @Column(name = "STEP_RATE", nullable = false)
    private BigDecimal stepRate;

    @Column(name = "MIN_BID_PRICE", nullable = false)
    private BigDecimal minBidPrice;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "START_DATE", length = 19, nullable = false)
    private Date startDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "END_DATE", length = 19, nullable = false)
    private Date endDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATED_ON", length = 19, nullable = false)
    private Date createdOn;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "UPDATED_ON", length = 19)
    private Date updatedOn;

    @Column(name = "COMMISSION", nullable = false)
    private BigDecimal commission;

}
