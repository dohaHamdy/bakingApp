package com.example.dohahamdy.bakingapp.Activities;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.dohahamdy.bakingapp.Fragments.StepDetailFragment;
import com.example.dohahamdy.bakingapp.R;

public class StepDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_detail);
        StepDetailFragment fragment=new StepDetailFragment();
        Fragment fragment1=getSupportFragmentManager().findFragmentByTag(StepDetailFragment.TAG);

        if(fragment1==null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.activity_video, fragment, StepDetailFragment.TAG)
                    .commit();

        }

    }
}
