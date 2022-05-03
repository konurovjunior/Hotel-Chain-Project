package dreamteam.hotelchainproject.services;

import dreamteam.hotelchainproject.dto.UserInfoDto;

public interface UserService {
    UserInfoDto getUserInfo(String username);
}
