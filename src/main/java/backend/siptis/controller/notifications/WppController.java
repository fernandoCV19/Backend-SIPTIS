package backend.siptis.controller.notifications;

import backend.siptis.service.notifications.WppService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wpp")
@CrossOrigin
@AllArgsConstructor
public class WppController {
    private final WppService wppService;


    @GetMapping("")
    public void sendWppNotification(){
        wppService.sendPersonalActivitiesWpp();
    }
}
