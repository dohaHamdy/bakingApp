package com.example.dohahamdy.bakingapp.Fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.dohahamdy.bakingapp.Adapter.RecipeAdapter;
import com.example.dohahamdy.bakingapp.Data.RecipeData;
import com.example.dohahamdy.bakingapp.Network.Network;
import com.example.dohahamdy.bakingapp.R;

import java.net.URL;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipesFragment extends Fragment implements RecipeAdapter.RecipeAdapterOnClickHandler
{

    public static String TAG="Recipe";
    @BindView(R.id.loading) ProgressBar mLoading;
    @BindView(R.id.cyclerView) RecyclerView mcyclerView;
  //  private  ProgressBar mLoading;
  //  private  RecyclerView mcyclerView;
    private RecyclerView.LayoutManager mLayoutManger;
    private RecipeAdapter adapt;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_recipes, container, false);
        ButterKnife.bind(this,view);

      //  mLoading=(ProgressBar)view.findViewById(R.id.loading);
      //  mcyclerView=(RecyclerView) view.findViewById(R.id.cyclerView);


        adapt=new RecipeAdapter( getContext());
        mcyclerView.setAdapter(adapt);

        mLayoutManger=new GridLayoutManager(getContext(),1);
        mcyclerView.setLayoutManager(mLayoutManger);
        mcyclerView.setHasFixedSize(true);

        showLoading();
        loadMovieData(0);
        return view;
    }

    @Override
    public void onClick( RecipeData data) {

    }

    private void loadMovieData(int type){
        showMovieData();
        new FetchRecipeData().execute(0);
    }

    private void showMovieData(){
        mLoading.setVisibility(View.INVISIBLE);
        mcyclerView.setVisibility(View.VISIBLE);
    }

    private void showErrorMsg(){
        mcyclerView.setVisibility(View.INVISIBLE);
        mLoading.setVisibility(View.VISIBLE);
    }

    private void showLoading(){
        mcyclerView.setVisibility(View.INVISIBLE);
        mLoading.setVisibility(View.VISIBLE);
    }

    public class FetchRecipeData extends AsyncTask<Integer,Void,List<RecipeData>> {
        @Override
        protected List<RecipeData> doInBackground(Integer... params) {
            URL requestUrl= Network.buildTheUrl(params[0]);
            try {
                String jsonresponse=Network.getResponse(requestUrl,params[0]);
                List<RecipeData> recipeDataJSON= Network.RecipeJson.getSimpleData(/*getContext(),*/jsonresponse);
                return recipeDataJSON;
            }catch (Exception e){
                return null;
            }


        }

        @Override
        protected void onPostExecute(List<RecipeData> datas) {
            if(datas !=null){
                showMovieData();
                adapt.setRecipeData(datas);

            }
            else {
                showErrorMsg();
            }
        }
    }

}

