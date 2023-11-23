package backend.siptis.service.bot;


import backend.siptis.client.chat_gpt.api.CompletionRequest;
import backend.siptis.commons.ServiceAnswer;

public interface ChatBotService {

    ServiceAnswer chat(CompletionRequest request);

}
