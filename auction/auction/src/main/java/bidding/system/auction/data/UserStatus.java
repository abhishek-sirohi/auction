package bidding.system.auction.data;

public enum UserStatus {

    ACTIVE, INACTIVE, BANNED;

    public String getValue(){
        return this.name();
    }
}
