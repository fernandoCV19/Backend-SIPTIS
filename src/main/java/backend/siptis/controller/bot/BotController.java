package backend.siptis.controller.bot;


import backend.siptis.model.pjo.dto.bot.BotRequest;
import backend.siptis.model.pjo.dto.bot.BotResponse;
import backend.siptis.model.pjo.dto.bot.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


@RestController
@RequestMapping("/bot")
@CrossOrigin
public class BotController {

    @Value("${openai.model}")
    private String model;

    @Value("${openai.api.url}")
    private String url;

    @Value("${openai.api.key}")
    private String apiKey;

    @Autowired
    @Qualifier("botRestTemplate")
    private RestTemplate restTemplate;

    @GetMapping()
    public String test() {
        return "Test";
    }

    @PostMapping("/chat")
    public BotResponse chat(@RequestBody Message message) {
        BotRequest request = new BotRequest(model, message);
        BotResponse response = restTemplate.postForObject(url, request, BotResponse.class);
        return response;
    }

}
