package bidding.system.auction.dao;

import bidding.system.auction.data.BidStatus;
import bidding.system.auction.data.ItemBid;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemBidDao extends CrudRepository<ItemBid, Integer> {
    ItemBid findByAuction_AuctionIdAndBidStatus(int auctionId, BidStatus bidStatus);
}
