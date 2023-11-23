package backend.siptis.model.repository.bot;

import backend.siptis.model.entity.bot.ChatHistory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
class ChatHistoryRepositoryTest {
    @Autowired
    private ChatHistoryRespository chatHistoryRespository;
    private ChatHistory chatHistory;

    @BeforeEach
    void createChatHistory() {
        chatHistory = new ChatHistory();
        chatHistory.setPrompt("CHAT_HISTORY_TEST");
    }

    @Test
    @DisplayName("Test for find Chat History by prompt")
    void givenChatHistoryPromp_whenFindByPromptLikeIgnoreCase_thenChatHistoryPrompObject() {
        chatHistoryRespository.save(chatHistory);
        assertNotNull(chatHistoryRespository.findByPromptLikeIgnoreCase("CHAT_HISTORY_TEST").get());
    }

    @Test
    @DisplayName("Test for find Chat History by non existing prompt")
    void givenChatHistoryPromp_whenFindByPromptLikeIgnoreCase_thenNull() {
        assertTrue(chatHistoryRespository.findByPromptLikeIgnoreCase("CHAT_HISTORY_TEST_2").isEmpty());
    }

    @Test
    @DisplayName("Test for find Chat History by non existing prompt")
    void test() {
        chatHistoryRespository.save(chatHistory);
        assertNotNull(chatHistoryRespository.findByPromptLikeIgnoreCase("chat_history_test").get());
    }
}
