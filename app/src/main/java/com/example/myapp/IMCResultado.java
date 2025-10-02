package com.example.myapp;

import static android.view.View.VISIBLE;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class IMCResultado extends AppCompatActivity {

    TextView tvIMC, tvNome, tvAcao, tvQuilos;
    ImageView imageView;
    LinearLayout linearLayout;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_imcresultado);
        tvIMC = findViewById(R.id.tvIMC);
        tvNome = findViewById(R.id.tvNome);
        linearLayout = findViewById(R.id.linearLayout);
        tvAcao = findViewById(R.id.tvAcao);
        tvQuilos = findViewById(R.id.tvQuilos);

        imageView = findViewById(R.id.imgPerfil);

        Bundle b = getIntent().getExtras();

        float peso = b.getFloat("peso");
        float altura = b.getFloat("altura");
        String nome = b.getString("nome");

        float imc = (peso)/(altura * altura);

        tvIMC.setText(Float.toString(imc));
        tvNome.setText(nome.toString());

        if(imc < 20) {
            imageView.setImageResource(R.drawable.abaixopeso);
        } else if(imc < 24.9) {
            imageView.setImageResource(R.drawable.normal);
        } else if(imc < 29.9) {
            imageView.setImageResource(R.drawable.sobrepeso);
        } else if (imc < 34.9) {
            imageView.setImageResource(R.drawable.obesidade1);
        } else if (imc < 39.9) {
            imageView.setImageResource(R.drawable.obesidade2);
        } else {
            imageView.setImageResource(R.drawable.obesidade3);
        }

        if(imc < 20) {
            linearLayout.setVisibility(VISIBLE);
            tvAcao.setText("Ganhar: ");
            float imcMin = (20 * (altura * altura)) -  peso;
            tvQuilos.setText(Float.toString(imcMin) + " kg");
        } else if(imc > 25) {
            linearLayout.setVisibility(VISIBLE);
            tvAcao.setText("Perder: ");
            float imcMax = peso - (25 * (altura * altura));
            tvQuilos.setText(Float.toString(imcMax) + " kg");
        }
    }

}