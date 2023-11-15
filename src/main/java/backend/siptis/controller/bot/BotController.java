package backend.siptis.controller.bot;


import backend.siptis.model.pjo.dto.bot.BotResponse;
import backend.siptis.model.pjo.dto.bot.Message;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/bot")
@CrossOrigin
public class BotController {

    @PostMapping("/chat")
    public BotResponse chat(@RequestBody Message message) {
        return null;
    }

}
