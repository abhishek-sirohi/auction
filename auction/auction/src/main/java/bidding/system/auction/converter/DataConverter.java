package bidding.system.auction.converter;

import bidding.system.auction.data.AuctionData;
import bidding.system.auction.data.ItemBid;
import bidding.system.auction.model.Auction;

public class DataConverter {


    public static Auction convert(AuctionData auctionData, ItemBid itemBid) {
        Auction auction = new Auction();
        auction.setAuctionId(auctionData.getAuctionId());
        auction.setAuctionStatus(auctionData.getAuctionStatus());
        auction.setCommission(auctionData.getCommission());
        auction.setEndDate(auctionData.getEndDate());
        auction.setItemName(auctionData.getAuctionItem().getItemName());
        auction.setMinBidPrice(auctionData.getMinBidPrice());
        auction.setStartDate(auctionData.getStartDate());
        auction.setStepRate(auctionData.getStepRate());
        auction.setHighestBid(itemBid != null ? itemBid.getBidAmount() : null);
        auction.setItemDescription(auctionData.getAuctionItem().getDescription());
        return auction;
    }
}
