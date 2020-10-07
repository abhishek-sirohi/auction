package bidding.system.auction.service.impl;

import bidding.system.auction.constant.AuctionConstant;
import bidding.system.auction.converter.DataConverter;
import bidding.system.auction.dao.AuctionDao;
import bidding.system.auction.data.AuctionData;
import bidding.system.auction.data.AuctionStatus;
import bidding.system.auction.data.BidStatus;
import bidding.system.auction.data.ItemBid;
import bidding.system.auction.data.UserInfo;
import bidding.system.auction.model.Auction;
import bidding.system.auction.model.BidRequest;
import bidding.system.auction.model.BidResponse;
import bidding.system.auction.service.AuctionService;
import bidding.system.auction.service.ItemBidService;
import bidding.system.auction.service.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Service
public class AuctionServiceImpl implements AuctionService {

    @Autowired
    private AuctionDao auctionDao;

    @Autowired
    private ItemBidService itemBidService;

    @Autowired
    private UserInfoService userInfoService;

    Map<Integer, AuctionData> auctionDataMap = new ConcurrentHashMap<>();

    @PostConstruct
    public void initializeAuctionDataMap(){
        List<AuctionData> auctionDataList = auctionDao.findAllByAuctionStatus(AuctionStatus.RUNNING);
        for(AuctionData auctionData : auctionDataList){
            auctionDataMap.put(auctionData.getAuctionId(), auctionData);
        }
    }

    @Override
    public List<Auction> getAuctionByStatus(String status) {

        AuctionStatus auctionStatus = AuctionStatus.valueOf(status);
        List<AuctionData> allByAuctionStatus = auctionDao.findAllByAuctionStatus(auctionStatus);
        return returnAuctionList(allByAuctionStatus);
    }

    private List<Auction> returnAuctionList(List<AuctionData> allByAuctionStatus) {
        List<Auction> auctions = new ArrayList<>();
        for (AuctionData auctionData : allByAuctionStatus) {
            ItemBid itemBid = itemBidService.getHighestBid(auctionData.getAuctionId());
            auctions.add(DataConverter.convert(auctionData, itemBid));
        }
        return auctions;
    }

    @Override
    public List<Auction> getAllAuctions() {
        List<AuctionData> allAuctions = auctionDao.findAll();
        return returnAuctionList(allAuctions);
    }

    @Override
    public BidResponse postBid(Integer auctionId, BidRequest bidRequest) {
        if (auctionId == null || bidRequest == null) {
            return BidResponse.builder().status(502).message(AuctionConstant.REQUIRED_FIELD_CANNOT_BE_NULL).build();
        }
        AuctionData auctionData = auctionDao.findById(auctionId).orElse(null);
        if (auctionData == null) {
            return BidResponse.builder().status(400).message(AuctionConstant.AUCTION_NOT_FOUND).build();
        } else {
            if (!auctionData.getAuctionStatus().equals(AuctionStatus.RUNNING)) {
                return BidResponse.builder().status(501).message(AuctionConstant.AUCTION_NOT_RUNNING).build();
            } else {
                return processBid(auctionId, bidRequest, auctionData);


            }
        }
    }

    private BidResponse processBid(Integer auctionId, BidRequest bidRequest, AuctionData auctionData) {
        AuctionData auctionDataObject = auctionDataMap.get(auctionId);
        if(auctionDataObject == null){
            synchronized (auctionDataMap){
                if(auctionDataMap.get(auctionId) == null){
                    auctionDataMap.put(auctionData.getAuctionId(), auctionDataObject);
                }
                auctionDataObject = auctionDataMap.get(auctionId);
            }

        }
        synchronized (auctionDataObject){
            ItemBid previous = itemBidService.getHighestBid(auctionId);
            UserInfo userInfo = userInfoService.getUser(bidRequest.getUserId());
            if(userInfo.getUserId() == previous.getBiddingUser().getUserId()){
                registerBid(auctionData, BidStatus.FAILED, bidRequest, userInfo);
                return BidResponse.builder().status(406).message(AuctionConstant.CANNOT_BID_OVER_SUCCESSFUL_BID).build();
            } else if(auctionData.getAuctionItem().getOwner().getUserId() == userInfo.getUserId()){
                registerBid(auctionData, BidStatus.FAILED, bidRequest, userInfo);
                return BidResponse.builder().status(406).message(AuctionConstant.OWNER_CANNOT_BID).build();
            }
            if (previous != null) {
                if (previous.getBidAmount().add(auctionData.getStepRate())
                        .compareTo(bidRequest.getBidAmount()) > 0) {
                    // bid price is not high enough hence bid is set to failed
                    registerBid(auctionData, BidStatus.FAILED, bidRequest, userInfo);
                    return BidResponse.builder().status(406).message(AuctionConstant.BID_FAILED).build();
                } else {
                    registerBid(auctionData, BidStatus.SUCCESSFUL, bidRequest, userInfo);
                    previous.setBidStatus(BidStatus.EXCEEDED);
                    itemBidService.saveBid(previous);
                    return BidResponse.builder().status(200).message(AuctionConstant.BID_SUCCEEDED).build();
                }
            } else {
                if (bidRequest.getBidAmount().compareTo(auctionData.getMinBidPrice()) >= 0) {
                    registerBid(auctionData, BidStatus.SUCCESSFUL, bidRequest, userInfo);
                    return BidResponse.builder().status(200).message(AuctionConstant.BID_SUCCEEDED).build();
                } else {
                    registerBid(auctionData, BidStatus.FAILED, bidRequest, userInfo);
                    return BidResponse.builder().status(406).message(AuctionConstant.BID_FAILED).build();
                }

            }
        }
    }

    private ItemBid registerBid(AuctionData auctionData, BidStatus bidStatus, BidRequest bidRequest, UserInfo userInfo) {
        ItemBid itemBid = new ItemBid();

        itemBid.setBiddingUser(userInfo);
        itemBid.setCreatedOn(new Date());
        itemBid.setAuction(auctionData);
        itemBid.setBidStatus(bidStatus);
        itemBid.setBidAmount(bidRequest.getBidAmount());
        return itemBidService.saveBid(itemBid);
    }
}

