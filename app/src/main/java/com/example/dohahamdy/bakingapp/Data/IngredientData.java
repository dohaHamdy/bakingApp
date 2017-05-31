package com.example.dohahamdy.bakingapp.Data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by DOHA HAMDY on 5/28/2017.
 */

public class IngredientData implements Parcelable {
    private String quantity;
    private String measure;
    private String ingredient;



    public IngredientData(){

    }
    protected IngredientData(Parcel parcel){

        quantity=parcel.readString();
        measure=parcel.readString();
        ingredient=parcel.readString();
      }
    public static final Creator<IngredientData>CREATOR=new Creator<IngredientData>() {
        @Override
        public IngredientData createFromParcel(Parcel source) {
            return new IngredientData(source);
        }

        @Override
        public IngredientData[] newArray(int size) {
            return new IngredientData[size];
        }
    };

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
}
