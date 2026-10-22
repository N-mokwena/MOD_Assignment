package com.example.smartpantry;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Ingredient.class}, version = 1, exportSchema = false)
public abstract class PantryDatabase extends RoomDatabase {

    private static volatile PantryDatabase INSTANCE;

    public abstract PantryDao pantryDao();

    public static PantryDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (PantryDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            PantryDatabase.class,
                            "pantry_db"
                    ).allowMainThreadQueries().build();
                }
            }
        }
        return INSTANCE;
    }
}