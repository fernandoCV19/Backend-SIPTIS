package backend.siptis.client.chat_gpt.service;

import backend.siptis.client.chat_gpt.api.CompletionRequest;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;

@Service
@RequiredArgsConstructor
public class BotService {

    private final ChatGPTClient client;

    public String getCompletion(CompletionRequest request){
        try {
            ResponseEntity<?> response = client.getCompletion(request);
            //LinkedHashMap userResponse = (LinkedHashMap) ((LinkedHashMap<?, ?>) response.getBody()).get("content");

            return null;
        } catch (FeignException feignException) {
            return null;
        }
    }
}
