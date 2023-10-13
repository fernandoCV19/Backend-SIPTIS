package backend.siptis.controller.notifications;

import backend.siptis.service.notifications.WppService;
import lombok.AllArgsConstructor;
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
    public void sendWppNotification() {
        wppService.sendPersonalActivitiesWpp();
    }
}
