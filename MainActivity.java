package com.example.smartpantry;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private EditText ingredientName, ingredientQuantity, ingredientUnit, expiryDate;
    private CheckBox favorite;
    private Button addIngredientButton, suggestButton;
    private RecyclerView pantryRecyclerView;
    private TextView suggestionText;

    private PantryDatabase database;
    private PantryAdapter adapter;

    private boolean isEditing = false;
    private long editingIngredientId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ingredientName = findViewById(R.id.ingredientName);
        ingredientQuantity = findViewById(R.id.ingredientQuantity);
        ingredientUnit = findViewById(R.id.ingredientUnit);
        expiryDate = findViewById(R.id.expiryDate);
        favorite = findViewById(R.id.favorite);
        addIngredientButton = findViewById(R.id.addIngredientButton);
        suggestButton = findViewById(R.id.suggestButton);
        pantryRecyclerView = findViewById(R.id.pantryRecyclerView);
        suggestionText = findViewById(R.id.suggestionText);

        database = PantryDatabase.getInstance(this);

        adapter = new PantryAdapter();
        adapter.setOnItemActionListener(new PantryAdapter.OnItemActionListener() {
            @Override
            public void onEdit(Ingredient ingredient) {
                editIngredient(ingredient);
            }

            @Override
            public void onDelete(Ingredient ingredient) {
                deleteIngredient(ingredient);
            }
        });

        pantryRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        pantryRecyclerView.setAdapter(adapter);

        loadIngredients();

        addIngredientButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addOrUpdateIngredient();
            }
        });

        suggestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                suggestRecipes();
            }
        });
    }

    private void addOrUpdateIngredient() {
        String name = ingredientName.getText().toString().trim();
        String quantityText = ingredientQuantity.getText().toString().trim();
        String unit = ingredientUnit.getText().toString().trim();
        String expiry = expiryDate.getText().toString().trim();

        if (name.isEmpty() || quantityText.isEmpty() || unit.isEmpty() || expiry.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        int quantity = Integer.parseInt(quantityText);

        if (isEditing) {
            Ingredient ingredient = new Ingredient(name, quantity, unit, expiry, favorite.isChecked());
            ingredient.id = editingIngredientId;

            database.pantryDao().update(ingredient);
            Toast.makeText(this, "Ingredient updated", Toast.LENGTH_SHORT).show();
            resetForm();
        } else {
            Ingredient ingredient = new Ingredient(name, quantity, unit, expiry, favorite.isChecked());
            database.pantryDao().insert(ingredient);
            Toast.makeText(this, "Ingredient added", Toast.LENGTH_SHORT).show();
            resetForm();
        }

        loadIngredients();
    }

    private void editIngredient(Ingredient ingredient) {
        ingredientName.setText(ingredient.name);
        ingredientQuantity.setText(String.valueOf(ingredient.quantity));
        ingredientUnit.setText(ingredient.unit);
        expiryDate.setText(ingredient.expiryDate);
        favorite.setChecked(ingredient.favorite);

        isEditing = true;
        editingIngredientId = ingredient.id;

        addIngredientButton.setText("Update Ingredient");
    }

    private void deleteIngredient(Ingredient ingredient) {
        database.pantryDao().delete(ingredient);
        Toast.makeText(this, ingredient.name + " deleted", Toast.LENGTH_SHORT).show();
        loadIngredients();

        if (isEditing && editingIngredientId == ingredient.id) {
            resetForm();
        }
    }

    private void resetForm() {
        ingredientName.setText("");
        ingredientQuantity.setText("");
        ingredientUnit.setText("");
        expiryDate.setText("");
        favorite.setChecked(false);

        isEditing = false;
        editingIngredientId = -1;

        addIngredientButton.setText("Add Ingredient");
    }

    private void loadIngredients() {
        List<Ingredient> ingredients = database.pantryDao().getAllIngredients();
        adapter.setIngredients(ingredients);
    }

    private void suggestRecipes() {
        List<Ingredient> pantry = database.pantryDao().getAllIngredients();
        Set<String> pantrySet = new HashSet<>();

        for (Ingredient ingredient : pantry) {
            pantrySet.add(ingredient.name.toLowerCase());
        }

        List<String> recipes = new ArrayList<>();

        if (pantrySet.contains("tomato") && pantrySet.contains("pasta")) {
            recipes.add("Tomato Pasta");
        }
        if (pantrySet.contains("rice") && pantrySet.contains("chicken")) {
            recipes.add("Chicken Rice Bowl");
        }
        if (pantrySet.contains("bread") && pantrySet.contains("egg")) {
            recipes.add("Egg Sandwich");
        }
        if (pantrySet.contains("potato") && pantrySet.contains("onion")) {
            recipes.add("Potato Onion Fry");
        }
        if (pantrySet.contains("banana") && pantrySet.contains("milk")) {
            recipes.add("Banana Smoothie");
        }

        if (recipes.isEmpty()) {
            suggestionText.setText("No recipe suggestion found. Add more ingredients.");
        } else {
            suggestionText.setText("Suggested recipes:\n" + String.join("\n", recipes));
        }
    }
}