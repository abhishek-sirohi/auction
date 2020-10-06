package bidding.system.auction.data;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import static javax.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "AUCTION_HOUSE")
public class AuctionHouse {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "AUCTION_HOUSE_ID", unique = true, nullable = false)
    private Integer auctionHouseId;

    @Column(name = "AUCTION_HOUSE_NAME", nullable = false)
    private String auctionHouseName;

    @Column(name = "ADDRESS", nullable = false)
    private String address;
}
