package com.example.booksharing;

import android.support.test.rule.ActivityTestRule;
import android.view.View;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class ReturnBookTest {

    @Rule
    public ActivityTestRule<ReturnBook> mActivityTestRule= new ActivityTestRule<ReturnBook>(ReturnBook.class);
    private ReturnBook mActivity=null;

    @Before
    public void setUp() throws Exception {

        mActivity=mActivityTestRule.getActivity();
    }
    @Test
    public void testLaunch()
    {
        View view=mActivity.findViewById(R.id.spinnerBookCategory);
        assertNotNull(view);
    }


    @After
    public void tearDown() throws Exception {
        mActivity=null;
    }
}