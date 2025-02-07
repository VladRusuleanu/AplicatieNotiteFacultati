package com.example.projectdam;

import android.os.Bundle;
import android.util.Xml;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import java.io.InputStream;
import androidx.appcompat.app.AppCompatActivity;
import org.xmlpull.v1.XmlPullParser;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class IntroducereInsemnariActivity extends AppCompatActivity {
    private Spinner spinnerFacultati;
    private EditText editTextObservatii;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inserare_insemnari);

        spinnerFacultati = findViewById(R.id.facultate_spinner);
        editTextObservatii = findViewById(R.id.observatii_edit_text);
        setupSpinner();
    }

    private void setupSpinner() {
        List<String> facultati = new ArrayList<>();

        try {
            InputStream is = getAssets().open("universitati.xml");
            XmlPullParser parser = Xml.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(is, null);

            int eventType = parser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                if (eventType == XmlPullParser.START_TAG && parser.getName().equals("universitate")) {
                    String numeFacultate = parser.getAttributeValue(null, "nume");
                    facultati.add(numeFacultate);
                }
                eventType = parser.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, facultati);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFacultati.setAdapter(adapter);
    }

    public void saveObservatii(View view) {
        String observatii = editTextObservatii.getText().toString();
        String facultateSelectata = spinnerFacultati.getSelectedItem().toString();

        saveData(facultateSelectata, observatii);

        editTextObservatii.setText("");
    }

    private void saveData(String facultate, String observatii) {
        String filename = "observatii.txt";
        FileOutputStream outputStream;

        try {
            outputStream = openFileOutput(filename, MODE_APPEND);
            String dataToWrite = "Facultate: " + facultate + "\nObservații: " + observatii + "\n\n";
            outputStream.write(dataToWrite.getBytes());
            outputStream.close();
            Toast.makeText(this, "Date salvate cu succes!", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Eroare la salvarea datelor!", Toast.LENGTH_SHORT).show();
        }
    }

    public void goBackToMain(View view) {
        finish();
    }
}
