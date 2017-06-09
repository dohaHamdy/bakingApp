package com.example.dohahamdy.bakingapp;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.dohahamdy.bakingapp.Activities.StepDetailActivity;
import com.example.dohahamdy.bakingapp.Fragments.StepDetailFragment;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    @Rule
    public ActivityTestRule<StepDetailActivity> mActivityRule = new ActivityTestRule<>(
            StepDetailActivity.class);
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.example.dohahamdy.bakingapp", appContext.getPackageName());
    }


    @Test
    public void ensureTextChangesWork() {
        // Type text and then press the button.

        onView(withId(R.id.nextStep))            // withId(R.id.my_view) is a ViewMatcher
                .perform(click())               // click() is a ViewAction
                .check(matches(isDisplayed()));

        // Check that the text was changed.
        // onView(withId(R.id.inputField)).check(matches(withText("Lalala")));
    }


}
