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
import com.project.bongoagro.Activity.ChartActivity;
import com.project.bongoagro.Activity.CustomerActivity;
import com.project.bongoagro.Activity.PriceActivity;
import com.project.bongoagro.Activity.SellerActivity;
import com.project.bongoagro.Activity.TransportActivity;
import com.project.bongoagro.Activity.WeatherActivity;
import com.project.bongoagro.Model.CategoryModel;
import com.project.bongoagro.Activity.ColdStorageActivity;
import com.project.bongoagro.R;
import com.project.bongoagro.Activity.SubCategoryActivity;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryHolder> {
    Context donor_context;
    private List<CategoryModel> categories;

    public CategoryAdapter(List<CategoryModel> categories, Context donor_context){
        this.categories = categories;
        this.donor_context = donor_context;
    }


    @NonNull
    @Override
    public CategoryAdapter.CategoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycle_item_category, parent, false);
        return new CategoryHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.CategoryHolder holder, @SuppressLint("RecyclerView") int position) {
        try {
            //Picasso.get().load("https://drive.google.com/thumbnail?id=18mv_0eP29MihvUjkr9lDP_nkqytxFlWp");
            String originalUrl = categories.get(position).getImageUrl();
            String imageUrl = originalUrl.replace("/file/d/", "/thumbnail?id=")
                    .replace("/view?usp=drive_link", "");
            Glide.with(donor_context)
                    .load(imageUrl)
                    .error(R.drawable.logo)  // Show error image if it fails to load
                    .into(holder.log_image);

        }catch (NullPointerException e){}
        try {
            holder.title.setText(categories.get(position).getName());
        }catch (NullPointerException e){}

        try {
            holder.card_view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(categories.get(position).getId() == 1 || categories.get(position).getId() == 2) {
                        Intent intent = new Intent(donor_context, SubCategoryActivity.class);
                        intent.putExtra("category_id", String.valueOf(categories.get(position).getId()));
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        donor_context.startActivity(intent);
                    }
                    else if(categories.get(position).getId() == 3){
                        Intent intent = new Intent(donor_context, ColdStorageActivity.class);
                        intent.putExtra("category_id", String.valueOf(categories.get(position).getId()));
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        donor_context.startActivity(intent);

                    }
                    else if(categories.get(position).getId() == 4){
                        Intent intent = new Intent(donor_context, CustomerActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        donor_context.startActivity(intent);

                    }
                    else if(categories.get(position).getId() == 6){
                        Intent intent = new Intent(donor_context, SellerActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        donor_context.startActivity(intent);

                    }
                    else if(categories.get(position).getId() == 5){
                        Intent intent = new Intent(donor_context, WeatherActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        donor_context.startActivity(intent);

                    }

                    else if(categories.get(position).getId() == 7){
                        Intent intent = new Intent(donor_context, PriceActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        donor_context.startActivity(intent);

                    }

                    else if(categories.get(position).getId() == 8){
                        Intent intent = new Intent(donor_context, TransportActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        donor_context.startActivity(intent);

                    }
                    else if(categories.get(position).getId() == 10){
                        Intent intent = new Intent(donor_context, ChartActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        donor_context.startActivity(intent);

                    }
                    else{
                        Toast.makeText(donor_context,
                                "Under Development!",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }catch (NullPointerException e){}
    }

    @Override
    public int getItemCount() {
        return categories == null ? 0 : categories.size();
    }

    public class CategoryHolder extends RecyclerView.ViewHolder {
        ImageView log_image;
        TextView title;
        CardView card_view;
        public CategoryHolder(@NonNull View itemView) {
            super(itemView);
            log_image = itemView.findViewById(R.id.log_image);
            title = itemView.findViewById(R.id.title);
            card_view = itemView.findViewById(R.id.card_view);
        }
    }
}
