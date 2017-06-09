package com.example.dohahamdy.bakingapp;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.dohahamdy.bakingapp.Activities.RecipesActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.core.deps.guava.base.Predicates.instanceOf;
import static org.hamcrest.CoreMatchers.is;

/**
 * Created by DOHA HAMDY on 6/9/2017.
 */

@RunWith(AndroidJUnit4.class)
public class MainTest {

    @Rule
    public ActivityTestRule<RecipesActivity> mActivityRule = new ActivityTestRule<>(
            RecipesActivity.class);

    @Test
    public void GridViewTest()
    {
        onData(
                is(instanceOf(String.class))
        );
    }
}
