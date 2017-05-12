package com.example.dohahamdy.bakingapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.dohahamdy.bakingapp.Activities.StepsActivity;
import com.example.dohahamdy.bakingapp.Data.RecipeData;
import com.example.dohahamdy.bakingapp.Data.StepData;
import com.example.dohahamdy.bakingapp.Fragments.StepsFragment;
import com.example.dohahamdy.bakingapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DOHA HAMDY on 5/7/2017.
 */

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeAdapterViewHolder>{
    Context mcontext;
    private List<RecipeData> recipeDataList;

    private List<StepData>stepDataList;
    public interface RecipeAdapterOnClickHandler{
        void onClick(RecipeData recipeData );
    }
    public RecipeAdapter(@NonNull Context context){
        mcontext=context;
    }

    public class RecipeAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public ImageView mImageView;
        RecipeAdapterViewHolder(View view){
            super(view);
            mImageView=(ImageView)view.findViewById(R.id.resipeCard);
            view.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {

            stepDataList=new ArrayList<>();
            Context context = v.getContext();
            Intent intent = new Intent(context, StepsActivity.class);

            intent.putExtra(StepsFragment.TAG,recipeDataList.get(getAdapterPosition()).getId());

            context.startActivity(intent);
        }

    }

    @Override
    public void onBindViewHolder(RecipeAdapterViewHolder holder, int position) {
        RecipeData data=recipeDataList.get(position);
        Picasso.with(mcontext)
                .load(data.getImage())
                .into(holder.mImageView);
    }

    @Override
    public RecipeAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recipe_card,parent,false);
        return new RecipeAdapterViewHolder(view);
    }

    @Override
    public int getItemCount() {
        if(recipeDataList==null)
            return 0;
        return recipeDataList.size();
    }

    public void setRecipeData(List<RecipeData> recipeData){
        recipeDataList=recipeData;

        notifyDataSetChanged();
    }


}
