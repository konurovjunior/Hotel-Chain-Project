package dreamteam.hotelchainproject.servicesImpl;

import dreamteam.hotelchainproject.dto.SimpleDto;
import dreamteam.hotelchainproject.services.SimpleService;
import org.springframework.stereotype.Service;

@Service
public class SimpleServiceImpl implements SimpleService {

    @Override
    public SimpleDto getTheBossInfo() {
        SimpleDto simpleDto = new SimpleDto("Dulat is the boss.", 21);
        return simpleDto;
    }
}
