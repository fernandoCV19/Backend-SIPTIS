package backend.siptis.utils.constant.entityConstants;

public final class BotConstants {
    public static class ChatHistoryTable {
        public static final String NAME = "chat_history_";

        public static class Id {
            public static final String NAME = "id_";
            public static final Boolean NULLABLE = false;
            public static final Boolean UNIQUE = true;
        }
        public static class Prompt {
            public static final String NAME = "prompt_";
        }
        public static class Completion {
            public static final String NAME = "completion_";
        }
    }
}
