package com.example.senior_walker;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

public class DogMenu3 extends AppCompatActivity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_bottom3);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation_view);

        // bottomNavigationView의 아이템이 선택될 때 호출될 리스너 등록
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_menu1: {
                        Intent intent = new Intent(getApplicationContext(), WalkerMain.class);
                        startActivity(intent);
                        break;
                    }
                    case R.id.navigation_menu2: {
                        break;
                    }
                    case R.id.navigation_menu3: {
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