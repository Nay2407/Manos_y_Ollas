package com.example.manosyollas.fragmentos;
import com.example.manosyollas.actividades.InicioActivity;
import com.example.manosyollas.actividades.MenuActivity;
import com.example.manosyollas.actividades.PrincipalActivity;
import com.example.manosyollas.clases.Menu;
import com.example.manosyollas.clases.Message;
import com.example.manosyollas.controladores.MessageAdapter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;

import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.manosyollas.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ChatFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ChatFragment extends Fragment {

    private String foroId;
    private String foroNombre;
    private TextView foroTitle;
    private RecyclerView recyclerViewMessages;

    private MessageAdapter messageAdapter;
    private List<Message> messageList;
    private EditText editTextMessage;
    private ImageButton buttonSend;
    private TextView chatTitle;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ChatFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ChatFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ChatFragment newInstance(String param1, String param2) {
        ChatFragment fragment = new ChatFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chat, container, false);

        recyclerViewMessages = view.findViewById(R.id.recyclerViewMessages);
        editTextMessage = view.findViewById(R.id.editTextMessage);
        buttonSend = view.findViewById(R.id.buttonSend);
        chatTitle = view.findViewById(R.id.chatTitle);
        ImageButton backButton = view.findViewById(R.id.backButton);

        messageList = new ArrayList<>();
        messageAdapter = new MessageAdapter(messageList);
        recyclerViewMessages.setAdapter(messageAdapter);
        recyclerViewMessages.setLayoutManager(new LinearLayoutManager(getContext()));

        // Evento para enviar el mensaje
        buttonSend.setOnClickListener(v -> {
            String messageText = editTextMessage.getText().toString().trim();
            if (!messageText.isEmpty()) {
                Message message = new Message(messageText, true); // true indica que es el usuario quien envía
                messageList.add(message);
                messageAdapter.notifyDataSetChanged();
                editTextMessage.setText("");
            }
        });

        Bundle bundle = getArguments();
        if (bundle != null) {

            String title = bundle.getString("foroTitle", "Chat");

            // Set the icon and title
            chatTitle.setText(title);
        }

        // Cambiar el título en el Toolbar o ActionBar
        //volver
        // Configurar el evento click para el icono de retroceso
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent menusito = new Intent(getContext(), PrincipalActivity.class);
                startActivity(menusito);

            }
        });

        return view;
    }

    private void cargarMensajes(String foroId) {
        //Aquí proximamente irá la lógica para llamar a todos los mensajes de la BD
    }

}