package backend.siptis.model.repository.bot;

import backend.siptis.model.entity.bot.ChatHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ChatHistoryRespository extends JpaRepository<ChatHistory, Long> {
    Optional<ChatHistory> findByPromptLikeIgnoreCase(String prompt);

}
