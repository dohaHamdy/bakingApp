package com.example.dohahamdy.bakingapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.VideoView;

import com.example.dohahamdy.bakingapp.Activities.StepDetailActivity;
import com.example.dohahamdy.bakingapp.Data.StepData;
import com.example.dohahamdy.bakingapp.Fragments.StepDetailFragment;
import com.example.dohahamdy.bakingapp.R;

import java.util.List;

/**
 * Created by DOHA HAMDY on 5/10/2017.
 */

public class StepDetailAdapter extends RecyclerView.Adapter<StepDetailAdapter.StepDetailAdapterViewHolder>{
    Context mcontext;
    private StepData stepData;

    public int getRecipeID() {
        return recipeID;
    }

    public void setRecipeID(int recipeID) {
        this.recipeID = recipeID;
    }

    private int recipeID;
   /* public interface StepDetailAdapterOnClickHandler{
        void onClick(StepData stepData );
    }*/
    public StepDetailAdapter(@NonNull Context context){
        mcontext=context;
    }

    public class StepDetailAdapterViewHolder extends RecyclerView.ViewHolder /*implements View.OnClickListener*/{

       // public Button mNext;
       // public TextView mShortDesc;
        StepDetailAdapterViewHolder(View view){
            super(view);
         //   mShortDesc=(TextView)view.findViewById(R.id.shortDescription);
           // mNext=(Button)view.findViewById(R.id.nextStep);
          /*  mNext.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Context context = v.getContext();
                    Intent intent = new Intent(context, StepDetailActivity.class);
                    intent.putExtra(StepDetailFragment.TAG,Integer.parseInt(stepData.getId())+1);
                    intent.putExtra("recipeID",recipeID);
                    context.startActivity(intent);
                }
            });*/
            //view.setOnClickListener(this);
        }

    }

    @Override
    public void onBindViewHolder(StepDetailAdapter.StepDetailAdapterViewHolder holder, int position) {
        //StepData data=stepDataList.get(position);
        //holder.mShortDesc.setText(data.getShortDescription());
    }

    @Override
    public StepDetailAdapter.StepDetailAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.step_card,parent,false);
        return new StepDetailAdapter.StepDetailAdapterViewHolder(view);
    }

    @Override
    public int getItemCount() {
       // if(stepDataList==null)
            return 0;
       // return stepDataList.size();
    }

    public void setStepDetailData(List<StepData> stepData){
        //stepDataList=stepData;
        //Log.d( "setStepData: ",stepDataList.size()+"");
        notifyDataSetChanged();
    }
}



