package com.example.manosyollas.fragmentos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.manosyollas.R;

public class TransferenciaFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_transferencia, container, false);

        ImageView btnBackToDonaciones = view.findViewById(R.id.ic_flotante);
        ImageButton btnYape = view.findViewById(R.id.btn_yape);
        ImageButton btnPlin = view.findViewById(R.id.btn_plin);
        ImageButton btnVisa = view.findViewById(R.id.btn_visa);

        btnYape.setOnClickListener(v -> navigateToFragment(new YapeFragment()));
        btnPlin.setOnClickListener(v -> navigateToFragment(new PlinFragment()));
        btnVisa.setOnClickListener(v -> navigateToFragment(new VisaFragment()));

        btnBackToDonaciones.setOnClickListener(v -> {
            if (getParentFragmentManager().getBackStackEntryCount() > 0) {
                getParentFragmentManager().popBackStack();
            }
        });

        return view;
    }

    private void navigateToFragment(Fragment fragment) {
        getParentFragmentManager()
                .beginTransaction()
                .replace(R.id.menRelArea, fragment)
                .addToBackStack(null)
                .commit();
    }
}

