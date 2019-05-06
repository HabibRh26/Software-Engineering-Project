package com.example.booksharing;

import android.support.test.rule.ActivityTestRule;
import android.view.View;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class LogInTest {

    @Rule
    public ActivityTestRule<LogIn> logActivityTestRule= new ActivityTestRule<LogIn>(LogIn.class);

     private LogIn logActivity=null;


    @Before
    public void setUp() throws Exception {
        logActivity=logActivityTestRule.getActivity();
    }

    @Test
    public void testLaunch()
    {
       View view= logActivity.findViewById(R.id.buttonLogIn);
        assertNotNull(view);
    }

    @After
    public void tearDown() throws Exception {
        logActivity=null;
    }
}