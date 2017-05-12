package com.example.dohahamdy.bakingapp.Activities;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.dohahamdy.bakingapp.Fragments.RecipesFragment;
import com.example.dohahamdy.bakingapp.R;

public class RecipesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes);

        RecipesFragment fragment=new RecipesFragment();
        Fragment fragment1=getSupportFragmentManager().findFragmentByTag(RecipesFragment.TAG);

        if(fragment1==null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.activity_main, fragment, RecipesFragment.TAG)
                    .commit();

        }

    }
}
