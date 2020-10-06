package bidding.system.auction.service.impl;

import bidding.system.auction.converter.DataConverter;
import bidding.system.auction.dao.AuctionDao;
import bidding.system.auction.data.AuctionData;
import bidding.system.auction.data.AuctionStatus;
import bidding.system.auction.model.Auction;
import bidding.system.auction.service.AuctionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class AuctionServiceImpl implements AuctionService {

    @Autowired
    private AuctionDao auctionDao;

    private DataConverter dataConverter;

    @Override
    public List<Auction> getAuctionByStatus(String status) {

        AuctionStatus auctionStatus = AuctionStatus.valueOf(status);
        List<AuctionData> allByAuctionStatus = auctionDao.findAllByAuctionStatus(auctionStatus);
        List<Auction> auctions = new ArrayList<>();
        for (AuctionData auctionData : allByAuctionStatus) {
            auctions.add(DataConverter.convert(auctionData));
        }

        return auctions;
    }
}

