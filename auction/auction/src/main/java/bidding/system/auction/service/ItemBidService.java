package bidding.system.auction.service;

import bidding.system.auction.data.ItemBid;

public interface ItemBidService {
    ItemBid getHighestBid(Integer auctionId);

    ItemBid saveBid(ItemBid itemBid);
}
