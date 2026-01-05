package com.example.freelancerflow;

public class ChatMessageModel {

    public String messageId;
    public String chatId;
    public String senderId;
    public String receiverId;
    public String messageText;
    public long timestamp;
    public boolean edited;

    public ChatMessageModel() {
    }

    public ChatMessageModel(String messageId, String chatId,
                            String senderId, String receiverId,
                            String messageText, long timestamp,
                            boolean edited) {
        this.messageId = messageId;
        this.chatId = chatId;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.messageText = messageText;
        this.timestamp = timestamp;
        this.edited = edited;
    }
}
