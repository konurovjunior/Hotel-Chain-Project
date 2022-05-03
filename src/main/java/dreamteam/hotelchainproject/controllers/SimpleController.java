package dreamteam.hotelchainproject.controllers;

import dreamteam.hotelchainproject.dto.SimpleDto;
import dreamteam.hotelchainproject.services.SimpleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class SimpleController {

    @Autowired
    private SimpleService simpleService;

    @GetMapping("/whoisboss")
    public SimpleDto getBoss(){
        return simpleService.getTheBossInfo();
    }
}
