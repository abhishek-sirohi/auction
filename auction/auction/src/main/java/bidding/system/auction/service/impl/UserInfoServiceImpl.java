package bidding.system.auction.service.impl;


import bidding.system.auction.dao.UserInfoDao;
import bidding.system.auction.data.UserInfo;
import bidding.system.auction.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoDao userInfoDao;


    @Override
    public UserInfo getUser(int userId) {
        return userInfoDao.findById(userId).orElse(null);
    }
}
