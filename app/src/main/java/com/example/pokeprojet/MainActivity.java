package com.example.pokeprojet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupMenu;

public class MainActivity extends AppCompatActivity {

    String url = "https://pokeapi.co/api/v2/pokemon";

    public static int start;
    public static int end;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.accueil);

        Button menuButton = findViewById(R.id.menu);
        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(MainActivity.this, v);
                popupMenu.getMenuInflater().inflate(R.menu.menu, popupMenu.getMenu());

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        int menuItemId = item.getItemId();

                        if (menuItemId == R.id.menu_item1) {
                            setContentView(R.layout.page1);
                            return true;

                        } else if (menuItemId == R.id.menu_item2) {
                            setContentView(R.layout.page2);
                            return true;

                        } else if (menuItemId == R.id.menu_item3) {
                            toggleTheme();
                            return true;

                        } else {
                            return false;
                        }
                    }

                });

                popupMenu.show();
            }
        });

    }

    public void genOne(View v) {
        Intent intent = new Intent(this, PokeListActivityGenOne.class);
        startActivity(intent);
    }

    public void genTwo(View v) {
        Intent intent = new Intent(this, PokeListActivityGenTwo.class);
        startActivity(intent);
    }

    public void genThree(View v) {
        Intent intent = new Intent(this, PokeListActivityGenThree.class);
        startActivity(intent);
    }
    public void genFour(View v) {
        Intent intent = new Intent(this, PokeListActivityGenFour.class);
        startActivity(intent);
    }
    public void genFive(View v) {
        Intent intent = new Intent(this, PokeListActivityGenFive.class);
        startActivity(intent);
    }
    public void genSix(View v) {
        Intent intent = new Intent(this, PokeListActivityGenSix.class);
        startActivity(intent);
    }
    public void genSeven(View v) {
        Intent intent = new Intent(this, PokeListActivityGenSeven.class);
        startActivity(intent);
    }
    public void genEight(View v) {
        Intent intent = new Intent(this, PokeListActivityGenEight.class);
        startActivity(intent);
    }


    private void toggleTheme() {
        int nightModeFlags = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
        if (nightModeFlags == Configuration.UI_MODE_NIGHT_YES) {
            // Changement vers le thème clair
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        } else {
            // Changement vers le thème sombre
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
        recreate(); // Recréez l'activité pour appliquer le changement de thème
    }
    public void hideSystemUI() {
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN);
    }
}