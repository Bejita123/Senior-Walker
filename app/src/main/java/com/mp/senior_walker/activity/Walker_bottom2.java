package com.mp.senior_walker.activity;

import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.mp.senior_walker.R;
import com.mp.senior_walker.info.diaryinfo;

import static com.mp.senior_walker.Utill.showToast;

public class Walker_bottom2 extends AppCompatActivity {
    RadioButton option1;
    RadioButton option2;
    RadioButton option3;
    RadioButton option4;
    RadioButton option5;
    RadioButton option6;
    RadioButton option7;
    RadioButton option8;
    RadioButton option9;
    RadioButton option10;
    RadioButton option11;
    RadioButton option12;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_walker_bottom2);
        findViewById(R.id.Walker_main_btn1).setOnClickListener(onClickListener);
        findViewById(R.id.Walker_main_btn2).setOnClickListener(onClickListener);
        findViewById(R.id.Walker_main_btn3).setOnClickListener(onClickListener);
        findViewById(R.id.Walker_main_btn4).setOnClickListener(onClickListener);

        ScrollView scroll = (ScrollView)findViewById(R.id.scroll);

        findViewById(R.id.reportBtn).setOnClickListener(onClickListener);
         option1 = (RadioButton) findViewById(R.id.danger_yes);
         option2 = (RadioButton) findViewById(R.id.danger_no);

         option3 = (RadioButton) findViewById(R.id.water_once);
         option4 = (RadioButton) findViewById(R.id.water_twice_or_more);
         option5 = (RadioButton) findViewById(R.id.water_no);

         option6 = (RadioButton) findViewById(R.id.defecation_yes);
         option7 = (RadioButton) findViewById(R.id.defecation_no);

         option8 = (RadioButton) findViewById(R.id.contact_yes);
         option9 = (RadioButton) findViewById(R.id.contact_no);

         option10 = (RadioButton) findViewById(R.id.liveliness_high);
         option11 = (RadioButton) findViewById(R.id.liveliness_mid);
         option12 = (RadioButton) findViewById(R.id.liveliness_low);






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
                case R.id.reportBtn:
                    report();
                    break;
            }
        }
    };



    private void report() {

        diaryinfo diaryinfo = new  diaryinfo(option1.isChecked(),option2.isChecked(), option3.isChecked(),
                option4.isChecked(),option5.isChecked(),option6.isChecked(),option7.isChecked(),option8.isChecked(),option9.isChecked(),
                option10.isChecked(),option11.isChecked(),option12.isChecked());


        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        if(user != null) {


            db.collection("diary").document(user.getUid())
                    .set(diaryinfo)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            showToast(Walker_bottom2.this, "산책일지 전송 성공");

                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                        }
                    });


        }
        }

    private void myStartActivity(Class c) {
        Intent intent = new Intent(this, c);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

}
