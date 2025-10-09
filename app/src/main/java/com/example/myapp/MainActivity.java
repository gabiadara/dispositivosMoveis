package com.example.myapp;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<String> nomes;
    EditText editText;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listView);
        editText = findViewById(R.id.editText);
        button = findViewById(R.id.button);

        nomes = new ArrayList<>() { {
            add("Apple");
            add("Banana");
            add("Cherry");
        }};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                nomes
        );

        button.setOnClickListener( v -> {
            String text = editText.getText().toString();
            nomes.add(text);
            adapter.notifyDataSetChanged();
        });

        listView.setOnItemClickListener(
                (parent, view, position, id) -> {
                    Toast.makeText(
                            getApplicationContext(),
                            "Elemento clicado: " + nomes.get(position),
                            Toast.LENGTH_SHORT).show();
                });

        listView.setOnItemLongClickListener(
                ((parent, view, position, id) -> {
                    nomes.remove(nomes.get(position));
                    adapter.notifyDataSetChanged();
                    return true;
                })
        );

        listView.setAdapter(adapter);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}