package backend.siptis.controller.bot;


import backend.siptis.model.pjo.dto.bot.BotResponse;
import backend.siptis.model.pjo.dto.bot.Message;
import backend.siptis.utils.constant.controllerConstans.ControllerConstants;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(ControllerConstants.Bot.BASE_PATH)
@CrossOrigin
public class BotController {

    @PostMapping("/chat")
    public BotResponse chat(@RequestBody Message message) {
        return null;
    }

}
