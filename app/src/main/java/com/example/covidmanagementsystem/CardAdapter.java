package com.example.covidmanagementsystem;

// CardAdapter.java
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.CardViewHolder> {

    private Context context;
    private ArrayList<Object> cardItemList;

    public CardAdapter(Context context, ArrayList<Object> cardItemList) {
        this.context = context;
        this.cardItemList = cardItemList;
    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_item, parent, false);
        return new CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, int position) {
        CardItem cardItem = (CardItem) cardItemList.get (position);
        holder.itemText.setText(cardItem.getItemTitle());
        holder.medicalStatus.setText(cardItem.getMedicalStatus());
        holder.uniqueId.setText(cardItem.getUniqueId());

        // Set image resource
        holder.itemImage.setImageResource(cardItem.getImageResourceId());
    }


    @Override
    public int getItemCount() {
        return cardItemList.size();
    }

    public static class CardViewHolder extends RecyclerView.ViewHolder {
        ImageView itemImage;
        TextView itemText;
        TextView medicalStatus;
        TextView uniqueId;

        public CardViewHolder(@NonNull View itemView) {
            super(itemView);
            itemImage = itemView.findViewById(R.id.item_image);
            itemText = itemView.findViewById(R.id.item_text);
            medicalStatus = itemView.findViewById(R.id.medical_status);
            uniqueId = itemView.findViewById(R.id.unique_id);
        }
    }
}
