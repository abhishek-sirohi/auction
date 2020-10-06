package bidding.system.auction.dao;

import bidding.system.auction.data.ItemBid;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemBidDao extends CrudRepository<ItemBid, Integer> {
}
