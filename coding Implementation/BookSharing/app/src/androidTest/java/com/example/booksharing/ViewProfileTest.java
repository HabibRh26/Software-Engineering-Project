package com.example.booksharing;

import android.app.Activity;
import android.app.Instrumentation;
import android.support.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

public class ViewProfileTest {

    @Rule
    public ActivityTestRule<ViewProfile> mViewProfileTestRule = new ActivityTestRule<ViewProfile>(ViewProfile.class);
    private ViewProfile mViewProfile = null;

    Instrumentation.ActivityMonitor monitor = getInstrumentation().addMonitor(SearchBookActivity.class.getName(),null,false);
    @Before
    public void setUp() throws Exception {
        mViewProfile = mViewProfileTestRule.getActivity();
    }
@Test
public void testOnLaunchSecondActivityBtnClick(){
        assertNotNull(mViewProfile.findViewById(R.id.editTextDisplayName));
        onView(withId(R.id.searcingBook)).perform(click());
    Activity searchActivity = getInstrumentation().waitForMonitorWithTimeout(monitor,5000);
    assertNotNull(searchActivity);
    searchActivity.finish();
}
    @After
    public void tearDown() throws Exception {
        mViewProfile = null;
    }
}