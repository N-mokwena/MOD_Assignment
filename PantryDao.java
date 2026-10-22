package com.example.smartpantry;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface PantryDao {

    @Insert
    void insert(Ingredient ingredient);

    @Delete
    void delete(Ingredient ingredient);

    @Update
    void update(Ingredient ingredient);

    @Query("SELECT * FROM ingredients ORDER BY expiryDate ASC")
    List<Ingredient> getAllIngredients();

    @Query("SELECT * FROM ingredients WHERE name LIKE :search ORDER BY expiryDate ASC")
    List<Ingredient> searchIngredients(String search);
}