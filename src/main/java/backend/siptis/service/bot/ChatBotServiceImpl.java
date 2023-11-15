package backend.siptis.service.bot;

import backend.siptis.client.chat_gpt.api.CompletionRequest;
import backend.siptis.client.chat_gpt.service.BotService;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.model.entity.bot.ChatHistory;
import backend.siptis.model.repository.bot.ChatHistoryRespository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChatBotServiceImpl implements ChatBotService {

    private final BotService botService;

    private final ChatHistoryRespository chatHistoryRespository;

    @Override
    public ServiceAnswer chat(CompletionRequest request) {
        Optional<ChatHistory> optionalChatHistory = chatHistoryRespository.findByPromptLikeIgnoreCase(request.getPrompt());
        if (optionalChatHistory.isPresent()) {
            String commonCompletion = optionalChatHistory.get().getCompletion();

        }
        String completion = botService.getCompletion(request);
        return null;
    }


    private String processPrompt(String prompt) {
        String upperCasePrompt = prompt.toUpperCase();
        String trimmedPrompt = upperCasePrompt.trim();
        trimmedPrompt = trimmedPrompt.replaceAll("^\\?+", "");
        trimmedPrompt = trimmedPrompt.replaceAll("\\?+$", "");
        return trimmedPrompt;
    }


}
