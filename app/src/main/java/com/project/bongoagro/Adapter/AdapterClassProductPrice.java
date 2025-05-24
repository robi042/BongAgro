package com.project.bongoagro.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.project.bongoagro.Model.PriceItem;
import com.project.bongoagro.R;

import java.util.List;

public class AdapterClassProductPrice extends RecyclerView.Adapter<AdapterClassProductPrice.PriceHolder> {
    private List<PriceItem> priceItems;
    private Context context;

    public AdapterClassProductPrice(List<PriceItem> priceItems, Context context) {
        this.priceItems = priceItems;
        this.context = context;
    }

    @NonNull
    @Override
    public PriceHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycle_item_price, parent, false);
        return new PriceHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PriceHolder holder, int position) {
        try {
            holder.tvSerial.setText(String.valueOf(position+1));
        }catch (NullPointerException e){}

        try {
            String[] parts = priceItems.get(position).getSellDate().split("T");
            String dateOnly = parts[0];
            holder.tvDate.setText(dateOnly);
        }catch (NullPointerException e){}

        try {
            holder.tvPrice.setText(String.valueOf(priceItems.get(position).getProductPrice()));
        }catch (NullPointerException e){}
    }

    @Override
    public int getItemCount() {
        return priceItems == null ? 0 : priceItems.size();
    }

    public class PriceHolder extends RecyclerView.ViewHolder {
        TextView tvSerial, tvDate, tvPrice;
        public PriceHolder(@NonNull View itemView) {
            super(itemView);
            tvSerial = itemView.findViewById(R.id.tvSerial);
            tvDate = itemView.findViewById(R.id.tvDate);
            tvPrice = itemView.findViewById(R.id.tvPrice);
        }
    }
}
