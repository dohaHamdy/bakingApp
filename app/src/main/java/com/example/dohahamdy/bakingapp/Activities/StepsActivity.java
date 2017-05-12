package com.example.dohahamdy.bakingapp.Activities;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.dohahamdy.bakingapp.Fragments.StepsFragment;
import com.example.dohahamdy.bakingapp.R;

public class StepsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_steps);

        StepsFragment fragment=new StepsFragment();
        Fragment fragment1=getSupportFragmentManager().findFragmentByTag(StepsFragment.TAG);

        Log.d("onCreate: ","in the activity");
        if(fragment1==null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.activity_recipe_detail, fragment, StepsFragment.TAG)
                    .commit();

        }

    }
}
