package com.example.projectdam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.room.Room;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Xml;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import org.xmlpull.v1.XmlPullParser;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private List<Universitate> universitati;
    private UniversitateDao universityDao;
    private static final int OPTIUNE_1_ID = R.id.optiune_1;
    private static final int OPTIUNE_2_ID = R.id.optiune_2;
    private static final int OPTIUNE_3_ID = R.id.optiune_3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        listView = findViewById(R.id.listView);
        universitati = new ArrayList<>();

        new Thread(new Runnable() {
            @Override
            public void run() {
                UniversitateDB universityDatabase = Room.databaseBuilder(getApplicationContext(), UniversitateDB.class, "universitati-db").build();
                universityDao = universityDatabase.getUniversityDao();

                try {
                    InputStream is = getAssets().open("universitati.xml");
                    XmlPullParser parser = Xml.newPullParser();
                    parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
                    parser.setInput(is, null);

                    int eventType = parser.getEventType();
                    while (eventType != XmlPullParser.END_DOCUMENT) {
                        if (eventType == XmlPullParser.START_TAG && parser.getName().equals("universitate")) {
                            Universitate universitate = new Universitate();
                            universitate.setNume(parser.getAttributeValue(null, "nume"));
                            universitate.setAdresa(parser.getAttributeValue(null, "adresa"));
                            universitate.setAnulFondarii(Integer.parseInt(parser.getAttributeValue(null, "anulFondarii")));

                            universityDao.insertUniversitate(universitate);
                            universitati.add(universitate);
                        }
                        eventType = parser.next();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                // universitati = universityDao.getUniversitati(); -> verificare continut DB
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        UniversitateAdapter adaptor = new UniversitateAdapter(MainActivity.this, universitati);
                        listView.setAdapter(adaptor);
                        adaptor.notifyDataSetChanged();
                    }
                });
            }
        }).start();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Universitate universitateSelectata = universitati.get(position);
                Intent intent = new Intent(MainActivity.this, DetaliiUniversitateActivity.class);
                intent.putExtra("universitate", universitateSelectata);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == OPTIUNE_1_ID) {
            Intent intent = new Intent(MainActivity.this, IntroducereInsemnariActivity.class);
            startActivity(intent);
            return true;
        } else if (id == OPTIUNE_2_ID) {
            Intent intent = new Intent(MainActivity.this, AfisareInsemnariActivity.class);
            startActivity(intent);
            return true;
        } else if (id == OPTIUNE_3_ID) {
            showDialogAbout();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    private void showDialogAbout() {
        FragmentDialog dialog = new FragmentDialog();
        dialog.show(getSupportFragmentManager(), "fragment_dialog");
    }
}