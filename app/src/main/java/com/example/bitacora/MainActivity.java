package com.example.bitacora;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    Button button;
    String[] fileList;
    String fileName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editTextTextMultiLine);
        button = findViewById(R.id.button);

        fileList = fileList();
        fileName = "bitacora.txt";

        if (fileExist(fileList, fileName)){
            try {
                InputStreamReader file = new InputStreamReader(openFileInput(fileName));
                BufferedReader bufferedReader = new BufferedReader(file);
                String line = bufferedReader.readLine();
                String bitacoraCompleta = "";

                while (line != null){
                    bitacoraCompleta += line + "\n";
                    line = bufferedReader.readLine();
                }
                bufferedReader.close();
                file.close();
                editText.setText(bitacoraCompleta);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    private boolean fileExist(String[] fileList, String fileName) {
        // Enhanced for statement
        for (String s : fileList)
            if (fileName.equals(s))
                return true;
            return false;
    }

    public void saveText(View view){
        try {
            OutputStreamWriter file = new OutputStreamWriter(openFileOutput(fileName, Activity.MODE_PRIVATE));
            file.write(editText.getText().toString());
            file.flush();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Toast.makeText(this, "Saved Text", Toast.LENGTH_SHORT).show();
        finish();
    }
}