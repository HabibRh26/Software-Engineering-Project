package com.example.booksharing;

import android.support.test.rule.ActivityTestRule;
import android.view.View;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class SearchBookActivityTest {
    @Rule
    public ActivityTestRule<SearchBookActivity> mSearchActivityTestRule = new ActivityTestRule<SearchBookActivity>(SearchBookActivity.class);
    private SearchBookActivity mSearch = null;
    @Before
    public void setUp() throws Exception {
        mSearch = mSearchActivityTestRule.getActivity();
    }

    @After
    public void tearDown() throws Exception {
        mSearch = null;
    }

    @Test
    public void onCreate() {
        View view = mSearch.findViewById(R.id.edtVwSearch);
        assertNotNull(view);
    }

    @Test
    public void searchMethod() {

    }
}