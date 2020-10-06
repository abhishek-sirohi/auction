package bidding.system.auction.service;


import bidding.system.auction.data.AuctionStatus;
import bidding.system.auction.model.Auction;

import java.util.List;

public interface AuctionService {
    List<Auction> getAuctionByStatus(String status);
}
