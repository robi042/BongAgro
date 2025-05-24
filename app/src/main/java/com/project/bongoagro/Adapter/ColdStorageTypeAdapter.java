package com.project.bongoagro.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.project.bongoagro.Model.ColdStorageType;
import com.project.bongoagro.R;

import java.util.List;

public class ColdStorageTypeAdapter extends RecyclerView.Adapter<ColdStorageTypeAdapter.ViewHolder> {
    private Context context;
    private List<ColdStorageType> coldStorageTypes;

    public ColdStorageTypeAdapter(List<ColdStorageType> coldStorageTypes, Context context) {
        this.coldStorageTypes = coldStorageTypes;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_view_cold_type, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        try {
            //Log.d("ColdStorageTypes", "Item : " + coldStorageTypes.get(position).getName());
            holder.productName.setText(coldStorageTypes.get(position).getName());
        }catch (NullPointerException e){
            //Log.e("ColdStorageTypes", "Error: Invalid position " + position);
        }

    }

    @Override
    public int getItemCount() {
        return coldStorageTypes == null ? 0 : coldStorageTypes.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView productName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            productName = itemView.findViewById(R.id.product_name);
        }
    }
}
