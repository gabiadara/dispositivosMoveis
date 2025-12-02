package com.example.myapp;

import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.skydoves.colorpickerview.ColorEnvelope;
import com.skydoves.colorpickerview.ColorPickerDialog;
import com.skydoves.colorpickerview.listeners.ColorEnvelopeListener;

public class MainActivity extends AppCompatActivity {

    SimplePaint simplePaint;
    Button btnCirculo, btnRetangulo, btnCamadas, btnColor, btnLimpar, btnLivre;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        simplePaint = findViewById(R.id.simplePaint);
        btnCirculo = findViewById(R.id.buttonCirculo);
        btnRetangulo = findViewById(R.id.buttonRetangulo);
        btnCamadas = findViewById(R.id.buttonCamadas);
        btnColor = findViewById(R.id.buttonColor);
        btnLimpar = findViewById(R.id.buttonLimpar);
        btnLivre = findViewById(R.id.buttonLivre);

        btnLivre.setOnClickListener(v -> {
            simplePaint.setShapeType(SimplePaint.ShapeType.TRACO_LIVRE);
        });

        btnRetangulo.setOnClickListener(v -> {
            simplePaint.setShapeType(SimplePaint.ShapeType.RETANGULO);
        });

        btnCirculo.setOnClickListener(v -> {
            simplePaint.setShapeType(SimplePaint.ShapeType.CIRCULO);
        });

        btnCamadas.setOnClickListener(v -> {
            simplePaint.undo();
        });

        btnColor.setOnClickListener(v-> {
            new ColorPickerDialog.Builder(this)
                    .setTitle("ColorPicker Dialog")
                    .setPreferenceName("MyColorPickerDialog")
                    .setPositiveButton("Confirma",
                            new ColorEnvelopeListener() {
                                @Override
                                public void onColorSelected(ColorEnvelope envelope, boolean fromUser) {
                                    setColor(envelope);
                                }
                            }
                    )
                    .setNegativeButton("Cancelar",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                }
                            })
                    .attachAlphaSlideBar(true) // the default value is true.
                    .attachBrightnessSlideBar(true)  // the default value is true.
                    .setBottomSpace(12) // set a bottom space between the last slidebar and buttons.
                    .show();
        });
        btnLimpar.setOnClickListener( v -> {
            simplePaint.clean();
        });

    }
    public void setColor(ColorEnvelope envelope) {
        int color = envelope.getColor();
        simplePaint.setColor(color);

    }
}