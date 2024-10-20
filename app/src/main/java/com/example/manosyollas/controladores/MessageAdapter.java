package com.example.manosyollas.controladores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.manosyollas.R;
import com.example.manosyollas.clases.MessageItem;

import java.util.ArrayList;
import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MessageViewHolder> {
    private List<MessageItem> messages = new ArrayList<>();

    public static class MessageViewHolder extends RecyclerView.ViewHolder {
        private TextView sender;
        private TextView messageContent;
        private ImageView profilePicture;

        public MessageViewHolder(View itemView) {
            super(itemView);
            sender = itemView.findViewById(R.id.textUsername);
            messageContent = itemView.findViewById(R.id.textMessage);
            profilePicture = itemView.findViewById(R.id.userProfileImage);
        }

        public void bind(MessageItem message) {
            sender.setText(message.getUserName());
            messageContent.setText(message.getContent());
            // Aquí puedes usar Glide o Picasso para cargar la imagen de perfil
            Glide.with(profilePicture.getContext())
                    .load(message.getUserProfileImage())
                    .into(profilePicture);
        }
    }

    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat_message, parent, false);
        return new MessageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageViewHolder holder, int position) {
        MessageItem currentMessage = messages.get(position);
        holder.bind(currentMessage);
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    public void setMessages(List<MessageItem> messages) {
        this.messages = messages;
        notifyDataSetChanged();
    }
}
