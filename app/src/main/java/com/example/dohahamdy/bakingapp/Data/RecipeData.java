package com.example.dohahamdy.bakingapp.Data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by DOHA HAMDY on 5/7/2017.
 */

public class RecipeData implements Parcelable {
    String id;
    String name;
    //List<IngredientData> ingredients;
    //List<StepData> steps;
    String servings;
    String image;

    public RecipeData(){

    }
    protected RecipeData(Parcel parcel){

        id=parcel.readString();
        name=parcel.readString();
        servings=parcel.readString();
        image=parcel.readString();
  /*      ingredients=new ArrayList<>();
        parcel.readList(ingredients, IngredientData.class.getClassLoader());
        steps=new ArrayList<>();
        parcel.readTypedList(steps,StepData.CREATOR);
        Log.d( "RecipeData: ",""+steps.size());
    */}
    public static final Creator<RecipeData>CREATOR=new Creator<RecipeData>() {
        @Override
        public RecipeData createFromParcel(Parcel source) {
            return new RecipeData(source);
        }

        @Override
        public RecipeData[] newArray(int size) {
            return new RecipeData[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    /*
        public List<IngredientData> getIngredients() {
            return ingredients;
        }

        public void setIngredients(List<IngredientData> ingredients) {
            this.ingredients = ingredients;
        }

        public List<StepData> getSteps() {
            return steps;
        }

        public void setSteps(List<StepData> steps) {
            this.steps = steps;
        }
    */
    public String getServings() {
        return servings;
    }

    public void setServings(String servings) {
        this.servings = servings;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(servings);
        dest.writeString(image);
//        dest.writeList(steps);
        //      dest.writeList(ingredients);
    }
}

