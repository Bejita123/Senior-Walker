package com.example.senior_walker;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class WalkerMain extends AppCompatActivity {

    private RecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.walker_main);



        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation_view);

        // bottomNavigationView의 아이템이 선택될 때 호출될 리스너 등록
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_menu1: {
                        break;
                    }
                    case R.id.navigation_menu2: {
                        break;
                    }
                    case R.id.navigation_menu3: {
                        Intent intent = new Intent(getApplicationContext(), DogMenu3.class);
                        startActivity(intent);
                        break;
                    }
                    case R.id.navigation_menu4: {
                        Intent intent = new Intent(getApplicationContext(), DogMenu4.class);
                        startActivity(intent);
                        break;
                    }
                }

                return true;
            }
        });
    }
}