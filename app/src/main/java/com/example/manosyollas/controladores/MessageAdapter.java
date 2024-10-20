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

    private List<MessageItem> messageList;

    public MessageAdapter(List<MessageItem> messageList) {
        this.messageList = messageList;
    }

    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat_message, parent, false);
        return new MessageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageViewHolder holder, int position) {
        MessageItem messageItem = messageList.get(position);
        holder.txtMessageContent.setText(messageItem.getContent());
        holder.txtMessageTimestamp.setText(messageItem.getTimestamp());
        holder.txtUserName.setText(messageItem.getUserName());  // Mostrar el nombre de usuario

        // Cargar la imagen de perfil usando Glide
        Glide.with(holder.imgUserProfile.getContext())
                .load(messageItem.getUserProfileImage())
                .into(holder.imgUserProfile);
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }

    public class MessageViewHolder extends RecyclerView.ViewHolder {
        public TextView txtMessageContent;
        public TextView txtMessageTimestamp;
        public TextView txtUserName;  // Nuevo TextView para el nombre del usuario
        public ImageView imgUserProfile;

        public MessageViewHolder(@NonNull View itemView) {
            super(itemView);
            txtMessageContent = itemView.findViewById(R.id.txtMessageContent);
            txtMessageTimestamp = itemView.findViewById(R.id.txtMessageTimestamp);
            txtUserName = itemView.findViewById(R.id.txtUserName);  // Asignar el TextView del nombre del usuario
            imgUserProfile = itemView.findViewById(R.id.imgUserProfile);
        }
    }
}
