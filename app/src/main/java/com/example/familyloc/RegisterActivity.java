package com.example.familyloc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.SignInMethodQueryResult;

public class RegisterActivity extends AppCompatActivity {

    EditText e4mail;
    FirebaseAuth auth;
    ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        e4mail=findViewById(R.id.editTextTextEmailAddress3);
        auth=FirebaseAuth.getInstance();
        dialog=new ProgressDialog(this);

    }
    public void goToPasswordActivity(View v){
        dialog.setMessage("checkin email");
        dialog.show();
        // check of this mail is already reg or not
        auth.fetchSignInMethodsForEmail(e4mail.getText().toString())
                .addOnCompleteListener(new OnCompleteListener<SignInMethodQueryResult>() {
                    @Override
                    public void onComplete(@NonNull Task<SignInMethodQueryResult> task) {
                    if(task.isSuccessful()){
                        dialog.dismiss();
                        boolean check = !task.getResult().getSignInMethods().isEmpty();
                        if(!check){
                            //emial doest not exist
                            Intent myIntent = new Intent(RegisterActivity.this,PasswordActivity.class);
                            myIntent.putExtra("email",e4mail.getText().toString());
                            startActivity(myIntent);
                            finish();
                        }
                        else{
                            dialog.dismiss();
                            Toast.makeText(getApplicationContext(),"already reg",Toast.LENGTH_SHORT).show();
                        }
                    }
                   }
                });


    }
}