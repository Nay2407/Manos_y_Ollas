package com.example.manosyollas.clases;

import android.os.Message;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class ChatViewModel extends ViewModel {
    private MessageRepository messageRepository; // Asumimos que tienes un repositorio
    private LiveData<List<ChatMessage>> messages; // Lista de mensajes observables

    public ChatViewModel(int forumId) {
        messageRepository = new MessageRepository(); // Asegúrate de que el repositorio esté configurado
        messages = messageRepository.getMessagesByForum(forumId);
    }

    public LiveData<List<ChatMessage>> getMessagesByForum(int forumId) {
        return messageRepository.getMessagesByForum(forumId);
    }

    public void insertMessage(ChatMessage message) {
        messageRepository.insert(message); // Inserta el mensaje en la base de datos
    }

}

