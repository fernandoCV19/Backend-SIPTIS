package backend.siptis.model.pjo.dto.bot;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BotRequest {
    private String model;
    private List<Message> messages;

    public BotRequest(String model, Message message){
        this.model = model;
        this.messages = new ArrayList<>();
        messages.add(new Message(message.getRole() ,message.getContent()));
    }
}