package com.mp.senior_walker.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.firestore.DocumentReference;
import com.mp.senior_walker.R;
import com.mp.senior_walker.info.Walkinfo;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.security.acl.Owner;

import static com.mp.senior_walker.Utill.showToast;

public class Dog_bottom1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dog_bottom1);
        findViewById(R.id.Dog_main_btn1).setOnClickListener(onClickListener);
        findViewById(R.id.Dog_main_btn2).setOnClickListener(onClickListener);
        findViewById(R.id.Dog_main_btn3).setOnClickListener(onClickListener);
        findViewById(R.id.Dog_main_btn4).setOnClickListener(onClickListener);
        findViewById(R.id.Dog_main_btn4).setOnClickListener(onClickListener);
        findViewById(R.id.regWalkBtn).setOnClickListener(onClickListener);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.Dog_main_btn1:
                    myStartActivity(WalkerMainActivity.class);
                    break;
                case R.id.Dog_main_btn2:
                    myStartActivity(DogMainActivity.class);
                    break;
                case R.id.Dog_main_btn3:
                    myStartActivity(Dog_bottom1.class);
                    break;
                case R.id.Dog_main_btn4:
                    myStartActivity(Dog_bottom2.class);
                    break;
                case R.id.regWalkBtn:
                    updateWalk();
                    break;
            }
        }
    };
        private void updateWalk() {

            String date = ((EditText) findViewById(R.id.dateEditText)).getText().toString();
            String time = ((EditText) findViewById(R.id.walkTimeText)).getText().toString();
            String location = ((EditText) findViewById(R.id.locatoinEditText)).getText().toString();


            if (date.length() > 0 && time.length() > 0 && location.length() > 0) {

                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                FirebaseFirestore db = FirebaseFirestore.getInstance();


                if(user != null) {

                    DocumentReference washingtonRef = db.collection("walk").document(user.getUid());
                    washingtonRef
                            .update("date", date, "time", time, "location", location, "Owner", user.getUid())
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    showToast(Dog_bottom1.this, "산책 등록 성공");
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                }
                            });



                }

            } else {

                showToast(Dog_bottom1.this, "산책 정보를 모두 입력해주세요");
            }
        }


        private void myStartActivity(Class c) {
            Intent intent = new Intent(this, c);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }

    }

