package com.example.familyloc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class PasswordActivity extends AppCompatActivity {

    String email;
    EditText e3password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);
        e3password = findViewById(R.id.editTextTextPassword2);

        Intent myIntent = getIntent();
        if(myIntent!=null){
            email = myIntent.getStringExtra("email");

        }
    }
    public void goToNameActivity(View v){
        if(e3password.getText().toString().length() > 6){
            Intent myIntent = new Intent(PasswordActivity.this,NameActivity.class);
            myIntent.putExtra("email",email);
            myIntent.putExtra("password",e3password.getText().toString());
            startActivity(myIntent);
            finish();

        }
        else{
            Toast.makeText(getApplicationContext(),"Password length should be > 6 ",Toast.LENGTH_SHORT).show();
        }

    }
}