package com.project.bongoagro.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.project.bongoagro.Activity.ProductDetailsActivity;
import com.project.bongoagro.Model.ProductModel;
import com.project.bongoagro.R;

import java.util.List;

public class AdapterClassProduct extends RecyclerView.Adapter<AdapterClassProduct.ProductHolder> {
    private List<ProductModel> products;
    private Context context;
    public AdapterClassProduct(List<ProductModel> products, Context context) {
        this.products = products;
        this.context = context;
    }

    @NonNull
    @Override
    public ProductHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycle_item_category, parent, false);
        return new ProductHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductHolder holder, @SuppressLint("RecyclerView") int position) {
        try {
            //Picasso.get().load("https://drive.google.com/thumbnail?id=18mv_0eP29MihvUjkr9lDP_nkqytxFlWp");
            String originalUrl = products.get(position).getImageUrl();
            String imageUrl = originalUrl.replace("/file/d/", "/thumbnail?id=")
                    .replace("/view?usp=drive_link", "");
            Glide.with(context)
                    .load(imageUrl)
                    .error(R.drawable.logo)  // Show error image if it fails to load
                    .into(holder.log_image);

        }catch (NullPointerException e){}
        try {
            holder.title.setText(products.get(position).getName());
        }catch (NullPointerException e){}

        try {
            holder.card_view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ProductDetailsActivity.class);
                    intent.putExtra("product_id", String.valueOf(products.get(position).getId()));
                    intent.putExtra("back_id", String.valueOf(products.get(position).getSubCategoryId()));
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    context.startActivity(intent);
                }
            });
        }catch (NullPointerException e){}
    }

    @Override
    public int getItemCount() {
        return products == null ? 0 : products.size();
    }

    public class ProductHolder extends RecyclerView.ViewHolder {
        ImageView log_image;
        TextView title;
        CardView card_view;
        public ProductHolder(@NonNull View itemView) {
            super(itemView);
            log_image = itemView.findViewById(R.id.log_image);
            title = itemView.findViewById(R.id.title);
            card_view = itemView.findViewById(R.id.card_view);
        }
    }
}
