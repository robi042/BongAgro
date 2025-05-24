package com.project.bongoagro.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.project.bongoagro.Activity.ColdStorageActivity;
import com.project.bongoagro.Activity.ComponantDetailsActivity;
import com.project.bongoagro.Activity.DiseaseActivity;
import com.project.bongoagro.Activity.SubCategoryActivity;
import com.project.bongoagro.R;
import com.project.bongoagro.Model.productDetailsModel;

import java.util.List;

public class AdapterClassProductDetails extends RecyclerView.Adapter<AdapterClassProductDetails.ProductDetailsHolder> {

    private List<productDetailsModel> productDetails;
    private Context context;

    public AdapterClassProductDetails(List<productDetailsModel> productDetails, Context context) {
        this.productDetails = productDetails;
        this.context = context;
    }

    @NonNull
    @Override
    public ProductDetailsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycle_item_product_details, parent, false);
        return new ProductDetailsHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductDetailsHolder holder, @SuppressLint("RecyclerView") int position) {
        try {
            //Picasso.get().load("https://drive.google.com/thumbnail?id=18mv_0eP29MihvUjkr9lDP_nkqytxFlWp");
            String originalUrl = productDetails.get(position).getImageUrl();
            String imageUrl = originalUrl.replace("/file/d/", "/thumbnail?id=")
                    .replace("/view?usp=drive_link", "");
            Glide.with(context)
                    .load(imageUrl)
                    .error(R.drawable.logo)  // Show error image if it fails to load
                    .into(holder.category_image);

        }catch (NullPointerException e){}
        try {
            holder.category_name.setText(productDetails.get(position).getTitle());
        }catch (NullPointerException e){}
        try {
            holder.card_view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(productDetails.get(position).getCode().equals("DISEASE")){
                        Intent intent = new Intent(context, DiseaseActivity.class);
                        intent.putExtra("productId", String.valueOf(productDetails.get(position).getProductId()));
                        intent.putExtra("code", productDetails.get(position).getCode());
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        context.startActivity(intent);
                    }
                    else{
                        Intent intent = new Intent(context, ComponantDetailsActivity.class);
                        intent.putExtra("productId", String.valueOf(productDetails.get(position).getProductId()));
                        intent.putExtra("code", productDetails.get(position).getCode());
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        context.startActivity(intent);
                    }
                }
            });

        }catch (NullPointerException e){}

    }

    @Override
    public int getItemCount() {
        return productDetails == null ? 0 : productDetails.size();
    }

    public class ProductDetailsHolder extends RecyclerView.ViewHolder {
        ImageView category_image;
        TextView category_name;
        CardView card_view;
        public ProductDetailsHolder(@NonNull View itemView) {
            super(itemView);
            category_image = itemView.findViewById(R.id.category_image);
            category_name = itemView.findViewById(R.id.category_name);
            card_view = itemView.findViewById(R.id.card_view);
        }
    }
}
