package backend.siptis.model.entity.bot;

import backend.siptis.utils.constant.entityConstants.BotConstants.ChatHistoryTable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = ChatHistoryTable.NAME)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChatHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = ChatHistoryTable.Id.NAME,
            nullable = ChatHistoryTable.Id.NULLABLE,
            unique = ChatHistoryTable.Id.UNIQUE)
    private Long id;

    @Column(name = ChatHistoryTable.Prompt.NAME)
    private String prompt;

    @Column(name = ChatHistoryTable.Completion.NAME)
    private String completion;
}
