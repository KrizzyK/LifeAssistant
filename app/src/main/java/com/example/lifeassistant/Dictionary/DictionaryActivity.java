package com.example.lifeassistant.Dictionary;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lifeassistant.BaseDrawerActivity;
import com.example.lifeassistant.R;
import com.google.android.material.textfield.TextInputEditText;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class DictionaryActivity extends BaseDrawerActivity {

    private Button searchButton;
    private TextInputEditText searchBar;
    private RecyclerView dictionaryRecView;
    DictionaryRecAdapter dictionaryRecAdapter;
    List<String> defList = new ArrayList<String>();
    private Spinner languageSpinner;
    String language;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.dictionary_main, contentFrameLayout);

        searchBar = (TextInputEditText) findViewById(R.id.wordDictInput);
        dictionaryRecView = (RecyclerView) findViewById(R.id.searchResultsRecView);
        searchButton = (Button) findViewById(R.id.searchButton);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String word = searchBar.getText().toString();

                try {
                    URL url = new URL("https://od-api.oxforddictionaries.com/api/v2/" + "entries/" + language + "/" +word+"?fields=definitions");
                    defList = new RequestHandler().execute(url).get();
                    if (defList.isEmpty()) {
                        Toast.makeText(getApplicationContext(), "No Results!", Toast.LENGTH_SHORT).show();
                    } else {
                        dictionaryRecAdapter = new DictionaryRecAdapter(defList, dictionaryRecView);
                        dictionaryRecView.setLayoutManager(new LinearLayoutManager( getBaseContext() ));
                        dictionaryRecView.setAdapter(dictionaryRecAdapter);
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        initializeSpinner();


    }
    private void initializeSpinner() {
        languageSpinner = findViewById(R.id.languageSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.spinner_array, R.layout.spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        languageSpinner.setAdapter(adapter);
        languageSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String elo = adapterView.getItemAtPosition(i).toString();
                switch(elo) {
                    case "Spanish":
                        language = "es";
                        break;
                    case "Latvian":
                        language = "lv";
                        break;
                    case "Swedish":
                        language = "sw";
                        break;
                    case "English":
                        language = "en";
                        break;
                    case "Romanian":
                        language = "ro";
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

}
