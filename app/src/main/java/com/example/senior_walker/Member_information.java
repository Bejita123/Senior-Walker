package com.example.senior_walker;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import static com.example.senior_walker.Utill.showToast;

public class Member_information extends AppCompatActivity {
    private static final String TAG = "Member_infromation";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_information);
        Log.d("Error", " 111121212121212121");

        TextView reginformBtn = (TextView)findViewById(R.id.regInformBtn);
        reginformBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                updateProfile();
                //myStartActivity(MainActivity.class);

            }
        });
    }


    private void updateProfile() {
        String name = ((EditText) findViewById(R.id.nameText)).getText().toString();
        String age = ((EditText) findViewById(R.id.ageText)).getText().toString();
        String phoneNumber = ((EditText) findViewById(R.id.phoneText)).getText().toString();




        if (name.length() > 0 && age.length() > 0 &&  phoneNumber.length() > 9) {
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            FirebaseFirestore db = FirebaseFirestore.getInstance();

            Memberinfo memberinfo = new Memberinfo(name, age, phoneNumber);

            if(user != null){
                db.collection("user").document(user.getUid())
                        .set(memberinfo)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {

                                showToast(Member_information.this, "회원정보 등록 성공");
                                myStartActivity(MainActivity.class);
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                               showToast(Member_information.this, "회원정보 등록 실패");
                               // myStartActivity(MainActivity.class);

                            }
                        });

            }

        } else {

            showToast(Member_information.this, "회원정보를 모두 입력해주세요");
        }
    }
    private void myStartActivity(Class c) {
        Intent intent = new Intent(this, c);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }


}
