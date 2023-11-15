package backend.siptis.service.bot;

import backend.siptis.client.chat_gpt.api.CompletionRequest;
import backend.siptis.client.chat_gpt.service.BotService;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.entity.bot.ChatHistory;
import backend.siptis.model.repository.bot.ChatHistoryRespository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Scope("prototype")
public class ChatBotServiceImpl implements ChatBotService {

    private final BotService botService;

    private final ChatHistoryRespository chatHistoryRespository;

    @Override
    public ServiceAnswer chat(CompletionRequest request) {
        Optional<ChatHistory> optionalChatHistory = chatHistoryRespository.findByPromptLikeIgnoreCase(request.getPrompt());
        if (optionalChatHistory.isPresent()) {
            String commonCompletion = optionalChatHistory.get().getCompletion();
            return ServiceAnswer.builder()
                    .data(commonCompletion)
                    .serviceMessage(ServiceMessage.BOT_COMPLETION_FOUND)
                    .build();
        }
        String completion = botService.getCompletion(request);
        if (completion == null) {
            return ServiceAnswer.builder()
                    .data(null)
                    .serviceMessage(ServiceMessage.BOT_REQUEST_ERROR)
                    .build();
        }
        ChatHistory chatHistory = new ChatHistory();
        chatHistory.setPrompt(processPrompt(request.getPrompt()));
        chatHistory.setCompletion(completion);
        chatHistoryRespository.save(chatHistory);
        return ServiceAnswer.builder()
                .data(completion)
                .serviceMessage(ServiceMessage.BOT_COMPLETION_OBTAINED)
                .build();
    }


    private String processPrompt(String prompt) {
        String upperCasePrompt = prompt.toUpperCase();
        String trimmedPrompt = upperCasePrompt.trim();
        trimmedPrompt = trimmedPrompt.replaceAll("^\\?+", "");
        trimmedPrompt = trimmedPrompt.replaceAll("\\?+$", "");
        return trimmedPrompt;
    }


}
