package bidding.system.auction.dao;

import bidding.system.auction.data.UserInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoDao extends CrudRepository<UserInfo, Integer> {
}
