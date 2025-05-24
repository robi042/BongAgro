package com.project.bongoagro.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.project.bongoagro.Model.ColdStorageResponseModelClass;
import com.project.bongoagro.Model.CultivationGuide;
import com.project.bongoagro.R;

import java.util.List;

public class AdapterClassCultivationGuide extends RecyclerView.Adapter<AdapterClassCultivationGuide.CultivateHolder> {
    private List<CultivationGuide> cultivationGuides;
    private Context context;
    private RecyclerView.Adapter adapter;

    public AdapterClassCultivationGuide(List<CultivationGuide> cultivationGuides, Context context) {
        this.cultivationGuides = cultivationGuides;
        this.context = context;
    }
    @NonNull
    @Override
    public CultivateHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recly_item_cultivate_guide, parent, false);
        return new CultivateHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterClassCultivationGuide.CultivateHolder holder, int position) {
        CultivationGuide culGuide = cultivationGuides.get(position);
        String originalUrl = cultivationGuides.get(position).getImageUrl();
        if (originalUrl == null || originalUrl.trim().isEmpty()) {
            holder.guide_image.setImageResource(R.drawable.logo);
        } else {
            String imageUrl = originalUrl.replace("/file/d/", "/thumbnail?id=")
                    .replace("/view?usp=drive_link", "");

            Glide.with(context)
                    .load(imageUrl)
                    .error(R.drawable.logo)
                    .into(holder.guide_image);
        }
        try {
            holder.title.setText(cultivationGuides.get(position).getTitle());
        }catch (NullPointerException e){}
        try {
            holder.description.setText(cultivationGuides.get(position).getDescription());
        }catch (NullPointerException e){}

        holder.crop_life_cycle.setLayoutManager(new LinearLayoutManager(context));
        holder.crop_life_cycle.setHasFixedSize(true);
        adapter = new CropLifeCycleViewModelAdapter(culGuide.getCropLifeCycleViewModels(), context);
        holder.crop_life_cycle.setAdapter(adapter);

    }

    @Override
    public int getItemCount() {
        return cultivationGuides == null ? 0 : cultivationGuides.size();
    }

    public class CultivateHolder extends RecyclerView.ViewHolder {
        TextView title, description;
        RecyclerView crop_life_cycle;
        ImageView guide_image;
        public CultivateHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            description = itemView.findViewById(R.id.description);
            crop_life_cycle = itemView.findViewById(R.id.crop_life_cycle);
            guide_image = itemView.findViewById(R.id.guide_image);
        }
    }
}
