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


public class LogIn extends AppCompatActivity implements View.OnClickListener{

    private FirebaseAuth mAuth;
    EditText edEmail,edPass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        mAuth = FirebaseAuth.getInstance();
        findViewById(R.id.textViewSignUp).setOnClickListener(this);
        findViewById(R.id.buttonLogIn).setOnClickListener(this);
        edEmail =(EditText)findViewById(R.id.editTextEmail) ;
        edPass =(EditText)findViewById(R.id.editTextPassword);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.textViewSignUp :
                startActivity(new Intent(this,SignUp.class));
                break;
            case R.id.buttonLogIn:
                userLogin();
                break;
        }
    }

    private void userLogin() {
        String userEmail=edEmail.getText().toString().trim();
        String pass = edPass.getText().toString().trim();
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
            edEmail.setError("invalid email address");
            edEmail.requestFocus();
            return;
        }
        if(pass.length()<6) {
            edPass.setError("password length error occurred.");
            edPass.requestFocus();
            return;
        }
        mAuth.signInWithEmailAndPassword(userEmail, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            Toast.makeText(getApplicationContext(), "user LOGGED IN successfully", Toast.LENGTH_LONG).show();
                            Intent intent=new Intent(LogIn.this,ViewProfile.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);


                        } else {
                            Toast.makeText(getApplicationContext(), "error occured", Toast.LENGTH_LONG).show();
                        }


                    }
                });
    }


}

