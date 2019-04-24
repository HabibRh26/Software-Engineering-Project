package com.example.booksharing;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goToLogIn(View view) {
        Intent intent= new Intent(this,LogIn.class);
        startActivity(intent);
    }

    public void goToSignUp(View view) {
        Intent intent= new Intent(this,SignUp.class);
        startActivity(intent);
    }

    public void goToViewProfile(View view) {
        Intent intent= new Intent(this,ViewProfile.class);
        startActivity(intent);
    }

    public void goToSearchBook(View view) {
        Intent intent= new Intent(this,SearchBook.class);
        startActivity(intent);
    }
}
