package bidding.system.auction.service;


import bidding.system.auction.model.Auction;
import bidding.system.auction.model.BidRequest;
import bidding.system.auction.model.BidResponse;

import java.util.List;

public interface AuctionService {
    List<Auction> getAuctionByStatus(String status);

    List<Auction> getAllAuctions();

    BidResponse postBid(Integer auctionId, BidRequest itemBid);
}
