package com.example.freelancerflow;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_LEFT = 0;
    private static final int TYPE_RIGHT = 1;

    private List<ChatMessageModel> messageList;
    private String currentUserId;

    public ChatAdapter(List<ChatMessageModel> messageList, String currentUserId) {
        this.messageList = messageList;
        this.currentUserId = currentUserId;
    }

    @Override
    public int getItemViewType(int position) {
        if (messageList.get(position).senderId.equals(currentUserId)) {
            return TYPE_RIGHT;
        } else {
            return TYPE_LEFT;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent, int viewType) {

        if (viewType == TYPE_RIGHT) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_inbox_messages_right, parent, false);
            return new MessageViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_inbox_messages_left, parent, false);
            return new MessageViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(
            @NonNull RecyclerView.ViewHolder holder, int position) {

        ChatMessageModel msg = messageList.get(position);
        ((MessageViewHolder) holder).textView.setText(msg.messageText);
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }

    static class MessageViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        MessageViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView;
        }
    }
}
