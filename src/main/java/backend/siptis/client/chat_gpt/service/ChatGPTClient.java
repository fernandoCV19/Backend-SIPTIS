package backend.siptis.client.chat_gpt.service;

import backend.siptis.client.chat_gpt.api.CompletionRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "CHAT-GPT-API", url = "${application.gateway.chatAPI}")
public interface ChatGPTClient {

    @PostMapping(value = "/gpt")
    ResponseEntity<?> getCompletion(@RequestBody CompletionRequest request);

}
