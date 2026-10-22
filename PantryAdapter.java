package com.example.smartpantry;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class PantryAdapter extends RecyclerView.Adapter<PantryAdapter.PantryViewHolder> {

    public interface OnItemActionListener {
        void onEdit(Ingredient ingredient);
        void onDelete(Ingredient ingredient);
    }

    private List<Ingredient> ingredientList = new ArrayList<>();
    private OnItemActionListener listener;

    public void setIngredients(List<Ingredient> ingredients) {
        ingredientList = ingredients;
        notifyDataSetChanged();
    }

    public void setOnItemActionListener(OnItemActionListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public PantryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.pantry_item, parent, false);
        return new PantryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PantryViewHolder holder, int position) {
        Ingredient ingredient = ingredientList.get(position);
        holder.bind(ingredient, listener);
    }

    @Override
    public int getItemCount() {
        return ingredientList.size();
    }

    static class PantryViewHolder extends RecyclerView.ViewHolder {

        TextView itemName, itemDetails;
        Button editButton, deleteButton;

        PantryViewHolder(@NonNull View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.itemName);
            itemDetails = itemView.findViewById(R.id.itemDetails);
            editButton = itemView.findViewById(R.id.editButton);
            deleteButton = itemView.findViewById(R.id.deleteButton);
        }

        void bind(final Ingredient ingredient, final OnItemActionListener listener) {
            itemName.setText(ingredient.name);
            itemDetails.setText(
                    ingredient.quantity + " " + ingredient.unit + " | Expiry: " + ingredient.expiryDate
            );

            editButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onEdit(ingredient);
                    }
                }
            });

            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onDelete(ingredient);
                    }
                }
            });
        }
    }
}