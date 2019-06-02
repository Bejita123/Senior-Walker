package com.mp.senior_walker.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.mp.senior_walker.CustomAdapter;
import com.mp.senior_walker.MyItem;
import com.mp.senior_walker.R;
import com.mp.senior_walker.info.Memberinfo;
import com.mp.senior_walker.info.Walkinfo;

import java.io.InputStream;
import java.util.ArrayList;

public class Walker_bottom1 extends AppCompatActivity {

    private ListView listView;
    private ArrayList<MyItem> mItems = new ArrayList<>();
    Memberinfo memberinfo;
    Walkinfo walkinfo;
    MyItem item;
    int[] IMAGES = {R.drawable.marker,R.drawable.marker,R.drawable.marker,R.drawable.marker,R.drawable.marker,R.drawable.marker};
    String[] NAMES = {"SEONGBEEN", "NAEUN", "SEONGJAE", "HYUNSEOK", "CHAEBEEN", "DONGSEOK"};
    String[] RESIDENCE = {"중랑구", "광명", "성남", "금천구", "일산","울산"};
    int count=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_walker_bottom1);

        findViewById(R.id.Walker_main_btn1).setOnClickListener(onClickListener);
        findViewById(R.id.Walker_main_btn2).setOnClickListener(onClickListener);
        findViewById(R.id.Walker_main_btn3).setOnClickListener(onClickListener);
        findViewById(R.id.Walker_main_btn4).setOnClickListener(onClickListener);

        listView = findViewById(R.id.listView);

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("walk")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                count++;
                                Log.d("wb1", document.getId() + " => " + document.getData() );
                                Log.d("wb1", " count = " + count);

                                dataSetting(document.getId());

                            }
                        } else {
                            Log.d("request", "Error getting documents: ", task.getException());
                        }
                    }
                });

        CustomAdapter customAdapter = new CustomAdapter(mItems);


        listView.setAdapter(customAdapter);
    }

    private void dataSetting(String uid) {
        Log.d("wb1", " uid = "+ uid );
        item = new MyItem();

        item.setUid(uid);
        
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference docRef = db.collection("user").document(uid);
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Memberinfo mem = documentSnapshot.toObject(Memberinfo.class);
                if(mem!= null){
                  memberinfo = new Memberinfo(mem.getName(), mem.getAge(),mem.getPhoneNumber(),mem.getPhotoUrl());
                    Log.d("wb1", " url =" + memberinfo.getPhotoUrl());
                    item.setUrlpath(memberinfo.getPhotoUrl());
                    item.setName(memberinfo.getName());
                    mItems.add(item);
                }
                else{
                    Log.d("wb1", " Mem is null");

                }
            }
        });/*
        DocumentReference docRef2 = db.collection("walk").document(uid);
        docRef2.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Walkinfo walk = documentSnapshot.toObject(Walkinfo.class);
                if(walk != null){
                    walkinfo = new Walkinfo(walk.getDate(),walk.getTime(),walk.getLocation());
                    Log.d("wb1", " location =" + walkinfo.getLocation());
                    item.setContents(walkinfo.getLocation());

                }else{
                    Log.d("wb1", " walk is null");

                }
            }
        });*/



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
