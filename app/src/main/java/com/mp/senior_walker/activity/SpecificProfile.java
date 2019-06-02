package com.mp.senior_walker.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
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

public class SpecificProfile extends AppCompatActivity {

    String dog;
    String height;
    String weight;
    String uid;
    ImageView image;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.specific_profile);

        image = findViewById(R.id.specific_img);
        image.setOnClickListener(onClickListener);

        findViewById(R.id.regWalkBtn).setOnClickListener(onClickListener);



        Intent intent = getIntent();
        uid = intent.getStringExtra("UID");
        UIupdate();


    }
    private void UIupdate() {


        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference docRef = db.collection("pet").document(uid);

        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference();

        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Petinfo petinfo = documentSnapshot.toObject(Petinfo.class);
                if(petinfo!= null){
                    dog = petinfo.getDog();
                    weight = petinfo.getWeight();
                    height = petinfo.getHeight();
                    setData();
                }
            }
        });

        storageRef.child("images/"+ uid+ "/dog.jpg").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                // Got the download URL for 'users/me/profile.png'
                Log.d("성공", uri.toString());
                Log.d("URL  uploader call", " ");

                Glide.with(SpecificProfile.this).load(uri.toString()).centerCrop().override(500).into(image);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle any errors
                Log.d("URL 파일 없음", "  ");

            }
        });

    }

    private void setData() {
        TextView nameEdit = (TextView) findViewById(R.id.specific_dog);
        TextView ageEdit = (TextView) findViewById(R.id.specific_weight);
        TextView phoneEdit = (TextView) findViewById(R.id.specific_height);


        nameEdit.setText(dog);
        ageEdit.setText(weight);
        phoneEdit.setText(height);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.regWalkBtn:
                    update();
                    myStartActivity(Walker_bottom2.class);
                    break;
            }
        }
    };

    private void update() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        FirebaseFirestore db = FirebaseFirestore.getInstance();


        if (user != null) {

            DocumentReference washingtonRef = db.collection("walk").document(uid);
            washingtonRef
                    .update("walker", user.getUid())
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
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