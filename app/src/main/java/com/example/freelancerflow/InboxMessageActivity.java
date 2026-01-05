package com.example.freelancerflow;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class InboxMessageActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private EditText edtMessage;
    private ImageView btnSend;

    private ChatAdapter adapter;
    private List<ChatMessageModel> messageList = new ArrayList<>();

    private FirebaseFirestore db;

    private String currentUserId;
    private String receiverId;
    private String chatId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inbox_message);

        recyclerView = findViewById(R.id.chatRecycler);
        edtMessage = findViewById(R.id.edtMes);
        btnSend = findViewById(R.id.Send);

        db = FirebaseFirestore.getInstance();

        if (FirebaseAuth.getInstance().getCurrentUser() == null) {
            finish();
            return;
        }

        currentUserId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        receiverId = getIntent().getStringExtra("receiverId");

        if (receiverId == null) {
            finish();
            return;
        }

        chatId = generateChatId(currentUserId, receiverId);

        adapter = new ChatAdapter(messageList, currentUserId);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        readMessages();

        btnSend.setOnClickListener(v -> sendMessage());
    }


    private void sendMessage() {
        String text = edtMessage.getText().toString().trim();
        if (text.isEmpty()) return;

        String messageId = UUID.randomUUID().toString();

        ChatMessageModel message = new ChatMessageModel(
                messageId,
                chatId,
                currentUserId,
                receiverId,
                text,
                System.currentTimeMillis(),
                false
        );

        db.collection("chats")
                .document(chatId)
                .collection("messages")
                .document(messageId)
                .set(message);

        edtMessage.setText("");
    }


    private void readMessages() {
        db.collection("chats")
                .document(chatId)
                .collection("messages")
                .orderBy("timestamp", Query.Direction.ASCENDING)
                .addSnapshotListener((value, error) -> {
                    if (value == null) return;

                    messageList.clear();
                    for (DocumentSnapshot doc : value.getDocuments()) {
                        ChatMessageModel msg = doc.toObject(ChatMessageModel.class);
                        messageList.add(msg);
                    }
                    adapter.notifyDataSetChanged();

                    if (!messageList.isEmpty()) {
                        recyclerView.scrollToPosition(messageList.size() - 1);
                    }
                });
    }


    private void editMessage(String messageId, String newText) {
        db.collection("chats")
                .document(chatId)
                .collection("messages")
                .document(messageId)
                .update("messageText", newText, "edited", true);
    }


    private void deleteMessage(String messageId) {
        db.collection("chats")
                .document(chatId)
                .collection("messages")
                .document(messageId)
                .delete();
    }


    private void deleteChat() {
        db.collection("chats").document(chatId).delete();
    }

    private String generateChatId(String u1, String u2) {
        return u1.compareTo(u2) < 0 ? u1 + "_" + u2 : u2 + "_" + u1;
    }
}
