package com.example.myapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class FragmentA extends Fragment {
    EditText mEdexto;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_a, container, false);

        mEdexto = view.findViewById(R.id.edMsg);
        Button b = view.findViewById(R.id.buttonSend);

        b.setOnClickListener(v -> {
            String msg = mEdexto.getText().toString();

            FragmentB fragmentB = new FragmentB();
            Bundle bundle = new Bundle();
            bundle.putString("msg", msg);
            fragmentB.setArguments(bundle);

            FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frame, fragmentB);
            fragmentTransaction.commit();

        });
        return view;
    }
}