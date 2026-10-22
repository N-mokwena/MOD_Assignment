package com.example.smartpantry;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "ingredients")
public class Ingredient {

    @PrimaryKey(autoGenerate = true)
    public long id;

    @NonNull
    public String name;

    public int quantity;

    public String unit;

    public String expiryDate;

    public boolean favorite;

    public Ingredient(@NonNull String name, int quantity, String unit, String expiryDate, boolean favorite) {
        this.name = name;
        this.quantity = quantity;
        this.unit = unit;
        this.expiryDate = expiryDate;
        this.favorite = favorite;
    }
}