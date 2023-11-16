package backend.siptis.controller.bot;

import backend.siptis.client.chat_gpt.api.CompletionRequest;
import backend.siptis.commons.ControllerAnswer;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.service.bot.ChatBotService;
import backend.siptis.utils.constant.controllerConstans.ControllerConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(ControllerConstants.Bot.BASE_PATH)
@CrossOrigin
@RequiredArgsConstructor
public class BotController {

    private final ChatBotService chatBotService;

    @PostMapping("/chat")
    public ResponseEntity<?> chat(@RequestBody CompletionRequest request) {
        ServiceAnswer serviceAnswer = chatBotService.chat(request);
        return createResponseEntity(serviceAnswer);
    }

    private ResponseEntity<?> createResponseEntity(ServiceAnswer serviceAnswer) {
        ServiceMessage serviceMessage = serviceAnswer.getServiceMessage();
        HttpStatus httpStatus = HttpStatus.OK;

        if (serviceMessage == ServiceMessage.BOT_REQUEST_ERROR || serviceMessage == ServiceMessage.ERROR)
            httpStatus = HttpStatus.NOT_FOUND;

        ControllerAnswer controllerAnswer = ControllerAnswer
                .builder()
                .data(serviceAnswer.getData())
                .message(serviceMessage.toString())
                .build();
        return new ResponseEntity<>(controllerAnswer, httpStatus);
    }
}
