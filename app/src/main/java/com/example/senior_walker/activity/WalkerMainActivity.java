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
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.senior_walker.Memberinfo;
import com.example.senior_walker.R;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import static com.example.senior_walker.Utill.showToast;


public class WalkerMainActivity extends AppCompatActivity {
    String name;
    String age;
    String phoneNumber;
    String path;
    ImageView image;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        image = findViewById(R.id.profileImageView);
        image.setOnClickListener(onClickListener);
        findViewById(R.id.Walker_main_btn1).setOnClickListener(onClickListener);
        findViewById(R.id.Walker_main_btn2).setOnClickListener(onClickListener);
        findViewById(R.id.Walker_main_btn3).setOnClickListener(onClickListener);
        findViewById(R.id.Walker_main_btn4).setOnClickListener(onClickListener);
        findViewById(R.id.checkButton).setOnClickListener(onClickListener);

        UIupdate();
    }

    private void UIupdate() {

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference docRef = db.collection("user").document(user.getUid());
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference();



        storageRef.child("images/"+ user.getUid()+ "/profile.jpg").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                // Got the download URL for 'users/me/profile.png'
                Log.d("성공", uri.toString());
                path = uri.toString();
                Glide.with(WalkerMainActivity.this).load(uri.toString()).centerCrop().override(500).into(image);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle any errors
                Log.d("URL 파일 없음", "  ");

            }
        });
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Memberinfo memberinfo = documentSnapshot.toObject(Memberinfo.class);
                if(memberinfo!= null){
                    name = memberinfo.getName();
                    age = memberinfo.getAge();
                    phoneNumber = memberinfo.getPhoneNumber();
                    setData();
                }
            }
        });
    }
    private void setData(){

        EditText nameEdit = (EditText) findViewById(R.id.nameEditText);
        EditText ageEdit = (EditText) findViewById(R.id.ageEditText);
        EditText phoneEdit = (EditText) findViewById(R.id.phoneNumberEditText);


        nameEdit.setText(name);
        ageEdit.setText(age);
        phoneEdit.setText(phoneNumber);
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
                case R.id.checkButton:
                    updateProfile();
                    break;
                case R.id.profileImageView:
                    Intent intent = new Intent();
                    intent.setType("image/*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(intent, 1);
                    break;
            }
        }
    };

    private void updateProfile() {
        name = ((EditText) findViewById(R.id.nameEditText)).getText().toString();
        age = ((EditText) findViewById(R.id.ageEditText)).getText().toString();
        phoneNumber = ((EditText) findViewById(R.id.phoneNumberEditText)).getText().toString();

        if( path != null){
            user = FirebaseAuth.getInstance().getCurrentUser();
            FirebaseFirestore db = FirebaseFirestore.getInstance();

            Memberinfo memberinfo = new Memberinfo(name, age, phoneNumber, path);

            if (user != null) {
                db.collection("user").document(user.getUid())
                        .set(memberinfo)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {

                                showToast(WalkerMainActivity.this, "회원정보 등록 성공");
                                myStartActivity(WalkerMainActivity.class);
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                showToast(WalkerMainActivity.this, "회원정보 등록 실패");
                                // myStartActivity(WalkerMainActivity.class);

                            }
                        });
            } else {
                showToast(WalkerMainActivity.this, "회원정보를 모두 입력해주세요");
            }
        }
        else if (name.length() > 0 && age.length() > 0 && phoneNumber.length() > 9) {
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            user = FirebaseAuth.getInstance().getCurrentUser();
            FirebaseFirestore db = FirebaseFirestore.getInstance();

            Memberinfo memberinfo = new Memberinfo(name, age, phoneNumber);

            if (user != null) {
                db.collection("user").document(user.getUid())
                        .set(memberinfo)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {

                                showToast(WalkerMainActivity.this, "회원정보 등록 성공");
                                myStartActivity(WalkerMainActivity.class);
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                showToast(WalkerMainActivity.this, "회원정보 등록 실패");
                                // myStartActivity(WalkerMainActivity.class);

                            }
                        });
            } else {
                showToast(WalkerMainActivity.this, "회원정보를 모두 입력해주세요");
            }
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
                  //  ImageView image = findViewById(R.id.profileImageView);
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
        StorageReference profileRef = storageRef.child("images/"+ user.getUid()+ "/profile.jpg");


      //  ImageView image = findViewById(R.id.profileImageView);
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
