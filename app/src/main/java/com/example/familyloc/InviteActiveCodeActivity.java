package com.example.familyloc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class InviteActiveCodeActivity extends AppCompatActivity {

    String name,email,password,date,isSharing,code;
    Uri imageUri;

    ProgressDialog progressDialog;
    TextView t1;
    String userId;

    FirebaseAuth auth;
    FirebaseUser user;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invite_active_code);
        t1=findViewById(R.id.textView);

        auth = FirebaseAuth.getInstance();
        reference = FirebaseDatabase.getInstance().getReference().child("Users");

        progressDialog = new ProgressDialog(this);

        Intent myIntent = getIntent();
        if(myIntent!=null){
            name = myIntent.getStringExtra("name");
            email= myIntent.getStringExtra("email");
            password=myIntent.getStringExtra("password");
            code = myIntent.getStringExtra("code");
            isSharing = myIntent.getStringExtra("isSharing");
            imageUri = myIntent.getParcelableExtra("imageUri");

        }
        t1.setText(code);
    }

    public void registerUser(View v){
        progressDialog.setMessage("Please wait account is creating");
        progressDialog.show();

        auth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            // insert values in db
                            CreateUser createUser = new CreateUser(name,email,password,code,"false","na","na","na");

                            user = auth.getCurrentUser();
                            userId = user.getUid();

                            reference.child(userId).setValue(createUser)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if(task.isSuccessful()){
                                                progressDialog.dismiss();
                                                Toast.makeText(getApplicationContext(),"User Registered Successfully",Toast.LENGTH_SHORT).show();

                                               Intent myIntent = new Intent(InviteActiveCodeActivity.this,MyNavigationActivity.class);
                                                startActivity(myIntent);
                                                finish();

                                            }
                                            else{
                                                progressDialog.dismiss();
                                                Toast.makeText(getApplicationContext(),"Could not register",Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                        }

                    }
                });



    }
}