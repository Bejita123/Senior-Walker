package com.mp.senior_walker.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.ScrollView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.mp.senior_walker.R;
import com.mp.senior_walker.info.Petinfo;
import com.mp.senior_walker.info.Walkinfo;
import com.mp.senior_walker.info.diaryinfo;

public class Dog_bottom2 extends AppCompatActivity {
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
    ScrollView scroll;
    String target;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dog_bottom2);
        findViewById(R.id.Dog_main_btn1).setOnClickListener(onClickListener);
        findViewById(R.id.Dog_main_btn2).setOnClickListener(onClickListener);
        findViewById(R.id.Dog_main_btn3).setOnClickListener(onClickListener);
        findViewById(R.id.Dog_main_btn4).setOnClickListener(onClickListener);

        scroll = (ScrollView)findViewById(R.id.scroll_own);

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

        WaitEndWalk();

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
            }
        }
    };
    public void WaitEndWalk(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference docRef = db.collection("walk").document(user.getUid());

        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {

                Walkinfo walkinfo = documentSnapshot.toObject(Walkinfo.class);
                if(walkinfo.getWalker() != null){
                    target = walkinfo.getWalker();
                    Log.d("Walk is aligned", "ok");
                    updateUI();



                }
            }
        });
    }

    private void updateUI() {

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference docRef = db.collection("diary").document(target);

        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                diaryinfo End = documentSnapshot.toObject(diaryinfo.class);
                if(End != null){
                    scroll.setVisibility(View.VISIBLE);
                    option1.setChecked(End.getState1());
                    option2.setChecked(End.getState2());
                    option3.setChecked(End.getState3());
                    option4.setChecked(End.getState4());
                    option5.setChecked(End.getState5());
                    option6.setChecked(End.getState6());
                    option7.setChecked(End.getState7());
                    option8.setChecked(End.getState8());
                    option9.setChecked(End.getState9());
                    option10.setChecked(End.getState10());
                    option11.setChecked(End.getState11());
                    option12.setChecked(End.getState12());

                    Log.d("Walk is end", "ok");
                }
            }
        });
    }




    private void myStartActivity(Class c) {
        Intent intent = new Intent(this, c);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
