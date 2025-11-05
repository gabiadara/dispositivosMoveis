package com.example.myapp;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    SQLiteDatabase database;
    Button saveButton;
    ListView listView;
    EditText editText;
    ArrayList<String> notasList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        saveButton = findViewById(R.id.button);
        editText = findViewById(R.id.editTextText);
        //path to create or open the database
        database = openOrCreateDatabase("app_base", MODE_PRIVATE, null);
        //path
        database.execSQL("CREATE TABLE IF NOT EXISTS notas (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name VARCHAR, texto VARCHAR)");
        saveButton.setOnClickListener(v -> {
            String texto = editText.getText().toString();
            if(!texto.isEmpty()) {
                ContentValues contentValues = new ContentValues();
                contentValues.put("name", texto);
                contentValues.put("texto", texto);
                database.insert("notas", null, contentValues);
            }
        });

    }

    public void carregarNotas() {
        notasList.clear();
        Cursor cursor = database.query("SELECT * FROM notas", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String s = cursor.getString(cursor.getColumnIndex("name"));
            notasList.add(s);
        }
        adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, notasList);
        listView.setAdapter(adapter);
    }
}