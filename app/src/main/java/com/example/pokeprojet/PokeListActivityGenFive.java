package com.example.pokeprojet;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PokeListActivityGenFive extends AppCompatActivity {


    ListView list;

    private String[] pokeName = new String[156];

    private String[] pokeID = new String[156];

    private String[] spriteUrls = new String[156];



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pokelistlayout);

        call();
        PokeListAdapter adapter = new PokeListAdapter(this, pokeName, pokeID, spriteUrls);
        list = findViewById(R.id.listView);

        list.setAdapter(adapter);

    }




    public String getDataFromHTTP(String param){
        StringBuilder result = new StringBuilder();
        HttpURLConnection connexion = null;

        try {
            URL url = new URL(param);
            connexion = (HttpURLConnection) url.openConnection();
            connexion.setRequestMethod("GET");
            InputStream inputStream = connexion.getInputStream();
            InputStreamReader inputStreamReader = new
                    InputStreamReader(inputStream);
            BufferedReader bf = new BufferedReader(inputStreamReader);
            String ligne = "";
            while ((ligne = bf.readLine()) != null) {
                result.append(ligne);
            }
            inputStream.close();
            bf.close();
            connexion.disconnect();
        } catch (Exception e) {
            result = new StringBuilder("Erreur ");
        }
        return result.toString();
    }

    public void call() {
        ExecutorService executor =
                Executors.newSingleThreadExecutor();
        Handler handler = new
                Handler(Looper.getMainLooper());
        executor.execute(new Runnable() {
            @Override
            public void run() {

                String name_id = getDataFromHTTP("https://pokeapi.co/api/v2/pokemon?limit=156&offset=493");

                handler.post(new Runnable() {
                    @Override
                    public void run() {

                        JSONObject jo = null;
                        try {
                            jo = new JSONObject(name_id);


                            JSONArray results = jo.getJSONArray("results");

                            for (int j = 0; j < results.length(); j++) {

//                                System.out.println("taille : "+ results.length());
//
//                                System.out.println("index + " + j);

                                JSONObject resultObject = results.getJSONObject(j);
                                String name = resultObject.getString("name");

                                pokeName[j] = name;
                                System.out.println("nom : " + pokeName[j]);

                                pokeID[j] = Integer.toString(j + +152+100+135+107);
                                int test = j+152;
                                System.out.println("numero : " + j+test);

                                String param = Integer.toString(j+152+100+135+107);
                                System.out.println("param = " + param);
                                getSprites(param, j);
                            }
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    };
                });
            }
        });
    }

    public void getSprites(String param, int index) {
        ExecutorService executor =
                Executors.newSingleThreadExecutor();
        Handler handler = new
                Handler(Looper.getMainLooper());
        executor.execute(new Runnable() {
            @Override
            public void run() {

                String spriteUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/" + param + ".png";

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        spriteUrls[index] = spriteUrl;
                        System.out.println(spriteUrl);
                    }
                });
            }
        });
    }



}