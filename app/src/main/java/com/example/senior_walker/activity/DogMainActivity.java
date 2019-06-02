package com.example.senior_walker.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.senior_walker.R;
import com.example.senior_walker.SpecificProfile;

public class DogMainActivity extends AppCompatActivity {

    int[] IMAGES = {R.drawable.marker,R.drawable.marker,R.drawable.marker,R.drawable.marker,R.drawable.marker,R.drawable.marker};
    String[] NAMES = {"SEONGBEEN", "NAEUN", "SEONGJAE", "HYUNSEOK", "CHAEBEEN", "DONGSEOK"};
    String[] RESIDENCE = {"중랑구", "광명", "성남", "금천구", "일산","울산"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dog_main);

        ListView listView = findViewById(R.id.listView);
        CustomAdapter customAdapter = new CustomAdapter();
        listView.setAdapter(customAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(DogMainActivity.this, SpecificProfile.class);
                startActivity(intent);
            }
        });

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_menu1:
                        Intent a = new Intent(DogMainActivity.this,WalkerMainActivity.class);
                        startActivity(a);
                        overridePendingTransition(0, 0);
                        break;
                    case R.id.navigation_menu2:
                        Intent b = new Intent(DogMainActivity.this,Dog_bottom1.class);
                        startActivity(b);
                        overridePendingTransition(0, 0);
                        break;
                    case R.id.navigation_menu3:
                        Intent c = new Intent(DogMainActivity.this,Dog_bottom2.class);
                        startActivity(c);
                        overridePendingTransition(0, 0);
                        break;
                    case R.id.navigation_menu4:
                        Intent d = new Intent(DogMainActivity.this,Dog_bottom3.class);
                        startActivity(d);
                        overridePendingTransition(0, 0);
                        break;
                }
                return false;
            }
        });
    }

    class CustomAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return IMAGES.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = getLayoutInflater().inflate(R.layout.customlayout,null);

            ImageView imageView = convertView.findViewById(R.id.imageView);
            TextView textView_name = convertView.findViewById(R.id.textView_name);
            TextView textView_residence = convertView.findViewById(R.id.textView_residence);
            Button button = convertView.findViewById(R.id.btnMore);

            imageView.setImageResource(IMAGES[position]);
            textView_name.setText(NAMES[position]);
            textView_residence.setText(RESIDENCE[position]);

            return convertView;
        }
    }

    private void myStartActivity(Class c) {
        Intent intent = new Intent(this, c);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
