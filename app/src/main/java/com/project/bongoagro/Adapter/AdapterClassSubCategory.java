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
import com.project.bongoagro.Activity.ProductActivity;
import com.project.bongoagro.R;
import com.project.bongoagro.Model.SubCategoryModel;

import java.util.List;

public class AdapterClassSubCategory  extends RecyclerView.Adapter<AdapterClassSubCategory.SubcatefiryHolder> {
    Context context;
    private List<SubCategoryModel> sub_categories;

    public AdapterClassSubCategory(List<SubCategoryModel> sub_categories, Context context){
        this.sub_categories = sub_categories;
        this.context = context;
    }
    @NonNull
    @Override
    public SubcatefiryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycle_item_category, parent, false);
        return new SubcatefiryHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SubcatefiryHolder holder, @SuppressLint("RecyclerView") int position) {
        try {
            //Picasso.get().load("https://drive.google.com/thumbnail?id=18mv_0eP29MihvUjkr9lDP_nkqytxFlWp");
            String originalUrl = sub_categories.get(position).getImageUrl();
            String imageUrl = originalUrl.replace("/file/d/", "/thumbnail?id=")
                    .replace("/view?usp=drive_link", "");
            Glide.with(context)
                    .load(imageUrl)
                    .error(R.drawable.logo)  // Show error image if it fails to load
                    .into(holder.log_image);

        }catch (NullPointerException e){}
        try {
            holder.title.setText(sub_categories.get(position).getName());
        }catch (NullPointerException e){}

        try {
            holder.card_view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ProductActivity.class);
                    intent.putExtra("sub_category_id", String.valueOf(sub_categories.get(position).getId()));
                    intent.putExtra("back_id", String.valueOf(sub_categories.get(position).getCategoryId()));
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    context.startActivity(intent);
                }
            });
        }catch (NullPointerException e){}
    }

    @Override
    public int getItemCount() {
        return sub_categories == null ? 0 : sub_categories.size();
    }

    public class SubcatefiryHolder extends RecyclerView.ViewHolder {
        ImageView log_image;
        TextView title;
        CardView card_view;
        public SubcatefiryHolder(@NonNull View itemView) {
            super(itemView);
            log_image = itemView.findViewById(R.id.log_image);
            title = itemView.findViewById(R.id.title);
            card_view = itemView.findViewById(R.id.card_view);
        }
    }
}
