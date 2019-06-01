package com.example.senior_walker.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.senior_walker.R;

public class Walker_bottom2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_walker_bottom2);
        findViewById(R.id.Walker_main_btn1).setOnClickListener(onClickListener);
        findViewById(R.id.Walker_main_btn2).setOnClickListener(onClickListener);
        findViewById(R.id.Walker_main_btn3).setOnClickListener(onClickListener);
        findViewById(R.id.Walker_main_btn4).setOnClickListener(onClickListener);

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
