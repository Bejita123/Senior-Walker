package com.example.senior_walker.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.senior_walker.Memberinfo;
import com.example.senior_walker.Petinfo;
import com.example.senior_walker.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import static com.example.senior_walker.Utill.showToast;

public class DogMainActivity extends AppCompatActivity {
    String dog;
    String height;
    String weight;
    String path;
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dog_main);

        image = findViewById(R.id.dogImageView);
        image.setOnClickListener(onClickListener);
        findViewById(R.id.Dog_main_btn1).setOnClickListener(onClickListener);
        findViewById(R.id.Dog_main_btn2).setOnClickListener(onClickListener);
        findViewById(R.id.Dog_main_btn3).setOnClickListener(onClickListener);
        findViewById(R.id.Dog_main_btn4).setOnClickListener(onClickListener);
        findViewById(R.id.checkButton).setOnClickListener(onClickListener);

        UIupdate();

    }


    private void UIupdate() {

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference docRef = db.collection("pet").document(user.getUid());

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

        storageRef.child("images/"+ user.getUid()+ "/dog.jpg").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                // Got the download URL for 'users/me/profile.png'
                Log.d("성공", uri.toString());
                URLUpload(uri.toString());
                Log.d("URL  uploader call", " ");

                Glide.with(DogMainActivity.this).load(uri.toString()).centerCrop().override(500).into(image);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle any errors
                Log.d("URL 파일 없음", "  ");

            }
        });

    }
    private void setData(){

        EditText dogEdit = (EditText) findViewById(R.id.dogEditText);
        EditText weightEdit = (EditText) findViewById(R.id.weightEditText);
        EditText heightEdit = (EditText) findViewById(R.id.hightEditText);


        dogEdit.setText(dog);
        weightEdit.setText(weight);
        heightEdit.setText(height);
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
                case R.id.checkButton:
                    updateProfile();
                    break;
                case R.id.dogImageView:
                    Intent intent = new Intent();
                    intent.setType("image/*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(intent, 1);
                    break;
            }
        }
    };
    private void URLUpload(String path){
        Log.d("Before URL path upload"," path = " + path);

        if(path != null){
            Log.d("URL path upload","");
            dog = ((EditText) findViewById(R.id.dogEditText)).getText().toString();
            weight = ((EditText) findViewById(R.id.weightEditText)).getText().toString();
            height = ((EditText) findViewById(R.id.hightEditText)).getText().toString();
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            FirebaseFirestore db = FirebaseFirestore.getInstance();

            Petinfo petinfo = new Petinfo(dog, height, weight, path);

            if(user != null) {
                db.collection("pet").document(user.getUid())
                        .set(petinfo)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {

                                Log.d(" URL", "path 정보 등록 성공");
                               // myStartActivity(WalkerMainActivity.class);
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.d(" URL", "path 정보 등록 실패");
                                // myStartActivity(WalkerMainActivity.class);

                            }
                        });
            }
        }
    }
    private void updateProfile() {
        dog = ((EditText) findViewById(R.id.dogEditText)).getText().toString();
        weight = ((EditText) findViewById(R.id.weightEditText)).getText().toString();
        height = ((EditText) findViewById(R.id.hightEditText)).getText().toString();



        if (dog.length() > 0 && weight.length() > 0 &&  height.length() > 0) {
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            FirebaseFirestore db = FirebaseFirestore.getInstance();

            Petinfo petinfo = new Petinfo(dog, height, weight);

            if(user != null){
                db.collection("pet").document(user.getUid())
                        .set(petinfo)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {

                                showToast(DogMainActivity.this, "펫 정보 등록 성공");
                             //   myStartActivity(WalkerMainActivity.class);
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                showToast(DogMainActivity.this, "펫 정보 등록 실패");
                                // myStartActivity(WalkerMainActivity.class);

                            }
                        });

            }

        } else {

            showToast(DogMainActivity.this, "펫 정보를 모두 입력해주세요");
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == 1) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                try {
                    // 선택한 이미지에서 비트맵 생성
               //     ImageView image = findViewById(R.id.dogImageView);
                    InputStream in = getContentResolver().openInputStream(data.getData());
                    Bitmap img = BitmapFactory.decodeStream(in);
                    in.close();
                    image.setImageBitmap(img);
                    uploadImg();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public void uploadImg(){
        FirebaseStorage storage = FirebaseStorage.getInstance();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        StorageReference storageRef = storage.getReference();
        StorageReference profileRef = storageRef.child("images/"+ user.getUid()+ "/dog.jpg");


        //ImageView image = findViewById(R.id.dogImageView);
        image.setDrawingCacheEnabled(true);
        image.buildDrawingCache();
        Bitmap bitmap = ((BitmapDrawable) image.getDrawable()).getBitmap();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] data = baos.toByteArray();

        UploadTask uploadTask = profileRef.putBytes(data);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle unsuccessful uploads
                Log.d("테스트", "실패");

            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                // taskSnapshot.getMetadata() contains file metadata such as size, content-type, etc.
                // ...
                Log.d("테스트", "성공");

            }
        });



    }
    private void myStartActivity(Class c) {
        Intent intent = new Intent(this, c);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
