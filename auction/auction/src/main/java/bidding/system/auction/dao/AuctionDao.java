package bidding.system.auction.dao;

import bidding.system.auction.data.AuctionData;
import bidding.system.auction.data.AuctionStatus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuctionDao extends CrudRepository<AuctionData, Integer> {

    List<AuctionData> findAllByAuctionStatus(AuctionStatus status);

    @Override
    List<AuctionData> findAll();
}
