package bidding.system.auction.controller;



import bidding.system.auction.data.AuctionStatus;
import bidding.system.auction.model.Auction;
import bidding.system.auction.service.AuctionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "auction")
public class AuctionController {

    @Autowired
    private AuctionService auctionService;

    @GetMapping("")
    public List<Auction> getAuctionList(@RequestParam(value = "status") String status){

        return auctionService.getAuctionByStatus(status);
    }
}
