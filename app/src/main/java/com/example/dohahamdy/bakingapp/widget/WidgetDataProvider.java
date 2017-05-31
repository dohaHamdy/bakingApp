package com.example.dohahamdy.bakingapp.widget;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.example.dohahamdy.bakingapp.Data.IngredientData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DOHA HAMDY on 5/31/2017.
 */

public class WidgetDataProvider implements RemoteViewsService.RemoteViewsFactory {

    private static final String TAG = "WidgetDataProvider";

    List<IngredientData> mIngredient = new ArrayList<>();
    List<String> mCollection = new ArrayList<>();
    Context mContext = null;

    public WidgetDataProvider(Context context, Intent intent) {
        mContext = context;
        Bundle extras = intent.getExtras();
        mIngredient= (List<IngredientData>) extras.get("in");
        //Toast.makeText(context,"aya **",Toast.LENGTH_LONG).show();


    }

    @Override
    public void onCreate() {
        initData();
    }

    @Override
    public void onDataSetChanged() {
        initData();
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        return mCollection.size();
    }

    @Override
    public RemoteViews getViewAt(int position) {
        //Toast.makeText(this.mContext,"aya",Toast.LENGTH_LONG).show();
        RemoteViews view = new RemoteViews(mContext.getPackageName(),
                android.R.layout.simple_list_item_1);
        view.setTextViewText(android.R.id.text1, mCollection.get(position));
        return view;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    private void initData() {

        //  Toast.makeText(this.mContext,"aya",Toast.LENGTH_LONG).show();
        mCollection.clear();
        try {


            for (int i = 0; i < mIngredient.size(); i++) {
                mCollection.add(mIngredient.get(i).getIngredient() + " " +
                        mIngredient.get(i).getQuantity() + " " +
                        mIngredient.get(i).getMeasure());
            }
        }catch (Exception e)
        {
            mCollection.add("sss");
        }
    }

}
