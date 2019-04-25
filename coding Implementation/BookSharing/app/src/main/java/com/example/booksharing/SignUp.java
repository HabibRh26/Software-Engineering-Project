package com.example.booksharing;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class SignUp extends AppCompatActivity implements View.OnClickListener{

    private FirebaseAuth mAuth;
    EditText edEmail,edPass,edConfirmPass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        edEmail =(EditText)findViewById(R.id.editTextEmail) ;
        edPass =(EditText)findViewById(R.id.editTextPassword);

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
                startActivity(new Intent(this,MainActivity.class));
                break;
        }

    }

    private void registerUser() {
        String userEmail=edEmail.getText().toString().trim();
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
            edPass.setError("try again");
            edPass.requestFocus();
            return;
        }
       if(!pass.equals(confirmPassword))
       {
           edConfirmPass.setError("password doesn't match");
           edConfirmPass.requestFocus();
           return;
       }

        mAuth.createUserWithEmailAndPassword(userEmail,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>()
        {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful())
                {
                    Toast.makeText(getApplicationContext(), "user registered", Toast.LENGTH_LONG).show();
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
