package dreamteam.hotelchainproject.servicesImpl;

import dreamteam.hotelchainproject.dto.UserInfoDto;
import dreamteam.hotelchainproject.models.User;
import dreamteam.hotelchainproject.repositories.UserRepository;
import dreamteam.hotelchainproject.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserInfoDto getUserInfo(String username) {
        User user = userRepository.findByUsername(username).get();
        return mapUserToDto(user);
    }

    private UserInfoDto mapUserToDto(User user){
        UserInfoDto userInfoDto = new UserInfoDto();
        userInfoDto.setFirstName(user.getFirstName());
        userInfoDto.setAddress(user.getAddress());
        userInfoDto.setHomePhone(user.getHomePhone());
        userInfoDto.setIdentificationNumber(user.getIdentificationNumber());
        userInfoDto.setIdentificationType(user.getIdentificationType());
        userInfoDto.setLastName(user.getLastName());
        userInfoDto.setMobilePhone(user.getMobilePhone());
        userInfoDto.setUsername(user.getUsername());
        return userInfoDto;
    }
}
