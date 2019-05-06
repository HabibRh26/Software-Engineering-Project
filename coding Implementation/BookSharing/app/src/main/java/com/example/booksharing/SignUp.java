package com.example.booksharing;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.booksharing.model.DbHelperClassUserPointTable;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;




public class SignUp extends AppCompatActivity implements View.OnClickListener{



    private FirebaseAuth mAuth;
    EditText edEmail,edPass,edConfirmPass;
    DbHelperClassUserPointTable mDbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        edEmail =(EditText)findViewById(R.id.editTextEmail) ;
        edPass =(EditText)findViewById(R.id.editTextPassword);
        edConfirmPass= (EditText)findViewById(R.id.editTextConfirmPassword);
        mAuth = FirebaseAuth.getInstance();

        findViewById(R.id.buttonSignUp).setOnClickListener(this);


        findViewById(R.id.textViewLogIn).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonSignUp :
                registerUser();
                break;

            case R.id.textViewLogIn :
                startActivity(new Intent(this,LogIn.class));
                break;
        }

    }
    public void addData(String email,int bpoint,int ppoint,int tpoint)
    {
        mDbHelper=new DbHelperClassUserPointTable(this);
      boolean insertData= (boolean) mDbHelper.addData(email,bpoint,ppoint,tpoint);
      if(insertData)
      {
          Toast.makeText(getApplicationContext(),"Point added successfully",Toast.LENGTH_SHORT).show();
      }
      else
      {
          Toast.makeText(getApplicationContext(),"Something went wrong",Toast.LENGTH_SHORT).show();
      }
    }

    private void registerUser() {
        final String userEmail=edEmail.getText().toString().trim();
        String pass = edPass.getText().toString().trim();
        String confirmPassword =edConfirmPass.getText().toString().trim();
        if(userEmail.isEmpty())
        {
            edEmail.setError("email is required");
            edEmail.requestFocus();
            return;
        }
        if(pass.isEmpty())
        {
            edPass.setError("password is required");
            edPass.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(userEmail).matches())
        {
            edEmail.setError("email is invalid");
            edEmail.requestFocus();
            return;
        }
        if(pass.length()<6)
        {
            edPass.setError("password length should not be less than 6");
            edPass.requestFocus();
            return;
        }
        if(!pass.equals(confirmPassword))
        {
            edConfirmPass.setError("password didn't match");
            edConfirmPass.requestFocus();
            return;
        }

        mAuth.createUserWithEmailAndPassword(userEmail,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>()
        {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful())
                {
                    addData(userEmail,0,100,100);
                    Toast.makeText(getApplicationContext(), "user registered successfully", Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(SignUp.this,ViewProfile.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
                else
                {
                    if(task.getException() instanceof FirebaseAuthUserCollisionException)
                    {
                        Toast.makeText(getApplicationContext(), "already registered", Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "user registration failed\ntry again!", Toast.LENGTH_LONG).show();
                    }
                }


            }
        });
    }
}
