package com.example.booksharing;

import android.support.test.rule.ActivityTestRule;
import android.view.View;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class SignUpTest {
    @Rule
    public ActivityTestRule<SignUp> SignUpTestRule= new ActivityTestRule<SignUp>(SignUp.class);
    private SignUp SignUpActivity=null;

    @Before
    public void setUp() throws Exception {
        SignUpActivity=SignUpTestRule.getActivity();
    }

    @Test
    public void testLaunch()
    {
        View view= SignUpActivity.findViewById(R.id.buttonSignUp);
        assertNotNull(view);
    }

    @After
    public void tearDown() throws Exception {
        SignUpActivity=null;
    }
}