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
import java.util.Map;

public class Walker_bottom1 extends AppCompatActivity {

    private ListView listView;
    private ArrayList<MyItem> mItems = new ArrayList<>();
    Memberinfo memberinfo;
    Walkinfo walkinfo;
    MyItem item;
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
       // getdata();
        item = new MyItem();

        getdata();

    }

    public  void getdata(){
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("walk")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                count++;
                                Map<String, Object> data = document.getData();
                                Log.d("wb1", String.valueOf(data));

                                item = new MyItem();
                                item.setUid(document.getId());
                                item.setName((String) data.get("date"));//시간
                                item.setContents((String) data.get("location"));//장소
                                mItems.add(item);
                        }
                            CustomAdapter customAdapter = new CustomAdapter(mItems);
                            listView.setAdapter(customAdapter);

                        } else {
                            Log.d("request", "Error getting documents: ", task.getException());
                        }
                    }
                });
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
        });



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
