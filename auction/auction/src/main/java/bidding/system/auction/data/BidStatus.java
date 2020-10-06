package bidding.system.auction.data;

public enum BidStatus {
    SUCCESSFUL, FAILED, EXCEEDED, CANCELLED;

    public String getValue(){
        return this.name();
    }
}
