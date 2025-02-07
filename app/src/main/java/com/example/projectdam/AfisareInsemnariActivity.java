package com.example.projectdam;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class AfisareInsemnariActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afisare_insemnari);

        TextView observatiiTextView = findViewById(R.id.observatii_text_view);
        String observatii = readData();
        observatiiTextView.setText(observatii);
    }

    private String readData() {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            FileInputStream fileInputStream = openFileInput("observatii.txt");
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }
            fileInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    public void goBackToMain(View view) {
        finish();
    }

    public void deleteObservatii(View view) {
        try {
            FileOutputStream fileOutputStream = openFileOutput("observatii.txt", MODE_PRIVATE);
            fileOutputStream.close();
            TextView observatiiTextView = findViewById(R.id.observatii_text_view);
            observatiiTextView.setText("");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
