package com.example.dohahamdy.bakingapp.Network;

import android.net.Uri;
import android.util.Log;

import com.example.dohahamdy.bakingapp.Data.RecipeData;
import com.example.dohahamdy.bakingapp.Data.StepData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by DOHA HAMDY on 5/7/2017.
 */

public class Network {
    final static String BASEURL     ="https://d17h27t6h515a5.cloudfront.net/topher/2017/";
    final static String VIDEOURL    ="May/5907926b_baking";
    final static String IMAGEURL    ="March/58d1537b_baking";
    final static String FINALURL    ="/baking.json";

    public static URL buildTheUrl(int type){
        Uri bult;
       if(type==0)
        bult=Uri.parse(BASEURL+IMAGEURL+FINALURL)
                .buildUpon()
                .build();
        else
            bult=Uri.parse(BASEURL+VIDEOURL+FINALURL)
                    .buildUpon()
                    .build();
        URL url=null;
        try{
            url=new URL(bult.toString());
        }catch (MalformedURLException e){
            e.printStackTrace();
        }
        return url;
    }

    public static String getResponse(URL url,int type)throws IOException {
        HttpsURLConnection connection=(HttpsURLConnection)url.openConnection();
        connection.setConnectTimeout(5000);
        try {
            InputStream in=connection.getInputStream();
            Scanner scan;
            if(type==0)
                scan=new Scanner(in);
            else
                scan=new Scanner(in,"UTF-16");
            scan.useDelimiter("\\A");
            boolean hasInput=scan.hasNext();
            if (hasInput){
                String str=scan.next();
                return str;
            }else{
                return null;
            }
        }
        finally {
            connection.disconnect();
        }
    }

    public static class RecipeJson {

        public static List<RecipeData> getSimpleData(String jsonString)
                throws JSONException {

            List<RecipeData> recipeData=new ArrayList<>();
            JSONArray json = new JSONArray(jsonString);
            for (int i = 0; i < json.length(); i++) {
                JSONObject oneRecipe=json.getJSONObject(i);
                RecipeData recipe=new RecipeData();
                recipe.setId(oneRecipe.getString("id"));
                recipe.setName(oneRecipe.getString("name"));
                recipe.setServings(oneRecipe.getString("servings"));
                recipe.setImage(oneRecipe.getString("image"));

                recipeData.add(recipe);
            }
            return recipeData;
        }
    }

    public static class StepJson {

        public static List<StepData> getSimpleData(String jsonString, int index)
                throws JSONException {

            List<StepData> stepData=new ArrayList<>();
            JSONArray recipes=new JSONArray(jsonString);
            JSONObject oneRecipes=recipes.getJSONObject(index);
            JSONArray json = oneRecipes.getJSONArray("steps");

            for (int i = 0; i < json.length(); i++) {
                JSONObject oneStep=json.getJSONObject(i);
                StepData step=new StepData();

                step.setId(oneStep.getString("id"));
                step.setShortDescription(oneStep.getString("shortDescription"));
                step.setDescription(oneStep.getString("description"));
                step.setVideoURL(oneStep.getString("videoURL"));
                step.setThumbnailURL(oneStep.getString("thumbnailURL"));

                stepData.add(step);
            }
            return stepData;
        }

        public static String getVideo(String jsonString, int indexRecipe,int indexStep)
                throws JSONException {
            String video;
            JSONArray recipes=new JSONArray(jsonString);
            JSONObject oneRecipes=recipes.getJSONObject(indexRecipe);
            JSONArray steps=oneRecipes.getJSONArray("steps");
            JSONObject oneStep=null;
            for (int i = 0; i < steps.length(); i++) {
                oneStep=steps.getJSONObject(i);
                if(Integer.parseInt(oneStep.getString("id")) == indexStep) {
                    break;
                }
            }
            if(oneStep==null)
                return null;
            oneStep=steps.getJSONObject(indexStep);
            video = oneStep.getString("videoURL");
            return video;
        }

        public static String getDescription(String jsonString, int indexRecipe,int indexStep)
                throws JSONException {
            String video;
            JSONArray recipes=new JSONArray(jsonString);
            JSONObject oneRecipes=recipes.getJSONObject(indexRecipe);
            JSONArray steps=oneRecipes.getJSONArray("steps");
            JSONObject oneStep=null;
            for (int i = 0; i < steps.length(); i++) {
                oneStep=steps.getJSONObject(i);
                if(Integer.parseInt(oneStep.getString("id")) == indexStep) {
                    break;
                }
            }

            if(oneStep==null)
                return null;
            oneStep=steps.getJSONObject(indexStep);
            video = oneStep.getString("description");
            return video;
        }
    }
}

