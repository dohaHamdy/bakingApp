package com.example.dohahamdy.bakingapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dohahamdy.bakingapp.Activities.StepDetailActivity;
import com.example.dohahamdy.bakingapp.Data.StepData;
import com.example.dohahamdy.bakingapp.Fragments.StepDetailFragment;
import com.example.dohahamdy.bakingapp.R;

import java.util.List;

/**
 * Created by DOHA HAMDY on 5/7/2017.
 */

public class StepAdapter extends RecyclerView.Adapter<StepAdapter.StepAdapterViewHolder>{
    Context mcontext;
    private List<StepData> stepDataList;

    public int getRecipeID() {
        return recipeID;
    }

    public void setRecipeID(int recipeID) {
        this.recipeID = recipeID;
    }

    private int recipeID;
    public interface StepAdapterOnClickHandler{
        void onClick(StepData stepData );
    }
    public StepAdapter(@NonNull Context context){
        mcontext=context;
    }

    public class StepAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView mShortDesc;
        StepAdapterViewHolder(View view){
            super(view);
            mShortDesc=(TextView)view.findViewById(R.id.shortDescription);
            view.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
            Context context = v.getContext();
            Intent intent = new Intent(context, StepDetailActivity.class);
            intent.putExtra(StepDetailFragment.TAG,stepDataList.get(getAdapterPosition()).getId());
            intent.putExtra("recipeID",recipeID);
            context.startActivity(intent);
        }

    }

    @Override
    public void onBindViewHolder(StepAdapterViewHolder holder, int position) {
        StepData data=stepDataList.get(position);
        holder.mShortDesc.setText(data.getShortDescription());
    }

    @Override
    public StepAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.step_card,parent,false);
        return new StepAdapter.StepAdapterViewHolder(view);
    }

    @Override
    public int getItemCount() {
        if(stepDataList==null)
            return 0;
        return stepDataList.size();
    }

    public void setStepData(List<StepData> stepData){
        stepDataList=stepData;
        Log.d( "setStepData: ",stepDataList.size()+"");
        notifyDataSetChanged();
    }
}


