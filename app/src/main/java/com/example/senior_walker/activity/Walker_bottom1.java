package com.example.senior_walker.activity;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.senior_walker.CustomAdapter;
import com.example.senior_walker.R;

public class Walker_bottom1 extends AppCompatActivity {

    private ListView listView;

    int[] IMAGES = {R.drawable.marker,R.drawable.marker,R.drawable.marker,R.drawable.marker,R.drawable.marker,R.drawable.marker};
    String[] NAMES = {"SEONGBEEN", "NAEUN", "SEONGJAE", "HYUNSEOK", "CHAEBEEN", "DONGSEOK"};
    String[] RESIDENCE = {"중랑구", "광명", "성남", "금천구", "일산","울산"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_walker_bottom1);

        findViewById(R.id.Walker_main_btn1).setOnClickListener(onClickListener);
        findViewById(R.id.Walker_main_btn2).setOnClickListener(onClickListener);
        findViewById(R.id.Walker_main_btn3).setOnClickListener(onClickListener);
        findViewById(R.id.Walker_main_btn4).setOnClickListener(onClickListener);

        listView = findViewById(R.id.listView);


        dataSetting();
    }
    private void dataSetting(){

        CustomAdapter customAdapter = new CustomAdapter();


        for (int i=0; i<6; i++) {
            customAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.marker), NAMES[i], RESIDENCE[i]);
        }

        /* 리스트뷰에 어댑터 등록 */
        listView.setAdapter(customAdapter);
    }


    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.Walker_main_btn1:
                    myStartActivity(DogMainActivity.class);
                    break;
                case R.id.Walker_main_btn2:
                    myStartActivity(WalkerMainActivity.class);
                    break;
                case R.id.Walker_main_btn3:
                    myStartActivity(Walker_bottom1.class);
                    break;
                case R.id.Walker_main_btn4:
                    myStartActivity(Walker_bottom2.class);
                    break;
            }
        }
    };
    private void myStartActivity(Class c) {
        Intent intent = new Intent(this, c);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

}
