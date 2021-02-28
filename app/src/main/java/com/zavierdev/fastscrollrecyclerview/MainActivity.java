package com.zavierdev.fastscrollrecyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.l4digital.fastscroll.FastScrollRecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private FastScrollRecyclerView rvGenres;
    private RvGenreAdapter rvGenreAdapter;
    private String sJSON = "";
    private ArrayList<GenresModel> listGenre = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize View and Adapter
        rvGenres = findViewById(R.id.rv_items);
        rvGenreAdapter = new RvGenreAdapter();

        // Set Layout Manager and Set Adapter FastScroll RecyclerView
        rvGenres.setLayoutManager(new LinearLayoutManager(this));
        rvGenres.setAdapter(rvGenreAdapter);

        sJSON = loadJSON(R.raw.genre_list); // Load JSON FILE
        jsonGenreArrayList(); // Convert JSON String to ArrayList

        // Set data into Adapter
        rvGenreAdapter.setData(this.listGenre);
        rvGenreAdapter.notifyDataSetChanged();
    }

    private String loadJSON(int source) {
        String jsonString = "";
        InputStream is = getResources().openRawResource(source);
        Writer writer = new StringWriter();
        char[] buffer = new char[1024];

        try {
            Reader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            int n;
            while ((n = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, n);
            }

            jsonString = writer.toString();
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return jsonString;
    }

    private void jsonGenreArrayList() {
        if (this.sJSON.isEmpty())
            return;

        try {
            JSONObject jsonObject = new JSONObject(this.sJSON); // Read JSON String
            JSONArray listGenre = jsonObject.getJSONArray("list_genre"); // Read list_genre JSON Array

            for(int i = 0;i < listGenre.length();i++) {
                GenresModel model = new GenresModel();
                JSONObject genreObject = listGenre.getJSONObject(i); // Read Genre Object

                model.setTitle(genreObject.getString("title"));
                model.setEndpoint(genreObject.getString("endpoint"));
                this.listGenre.add(model);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}