package bidding.system.auction.service.impl;

import bidding.system.auction.dao.ItemBidDao;
import bidding.system.auction.data.BidStatus;
import bidding.system.auction.data.ItemBid;
import bidding.system.auction.service.ItemBidService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ItemBidServiceImpl implements ItemBidService {

    @Autowired
    private ItemBidDao itemBidDao;

    @Override
    public ItemBid getHighestBid(Integer auctionId){
        return itemBidDao.findByAuction_AuctionIdAndBidStatus(auctionId, BidStatus.SUCCESSFUL);
    }

    @Override
    public ItemBid saveBid(ItemBid itemBid) {
        return itemBidDao.save(itemBid);
    }
}
