package backend.siptis.client.chat_gpt.service;

import backend.siptis.client.chat_gpt.api.CompletionRequest;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Scope("prototype")
public class BotService {

    private final ChatGPTClient client;

    public String getCompletion(CompletionRequest request) {
        try {
            ResponseEntity<?> response = client.getCompletion(request);
            return ((LinkedHashMap<?, ?>) Objects.requireNonNull(response.getBody())).get("result").toString();
        } catch (FeignException feignException) {
            return null;
        }
    }
}
