package com.project.bongoagro.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.project.bongoagro.Model.CultivationGuide;
import com.project.bongoagro.R;

import java.util.List;

public class AdapterClassDisease extends RecyclerView.Adapter<AdapterClassDisease.ViewHolder> {
    private List<CultivationGuide> diseaseResponses;
    private Context context;
    private RecyclerView.Adapter adapter, adapter1;
    public AdapterClassDisease(List<CultivationGuide> diseaseResponses, Context context) {
        this.diseaseResponses = diseaseResponses;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recly_item_disease, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CultivationGuide culGuide = diseaseResponses.get(position);
        String originalUrl = diseaseResponses.get(position).getImageUrl();
        String imageUrl = originalUrl.replace("/file/d/", "/thumbnail?id=")
                .replace("/view?usp=drive_link", "");
        Glide.with(context)
                .load(imageUrl)
                .error(R.drawable.logo)
                .into(holder.disease_image);
        try {
            holder.name.setText(diseaseResponses.get(position).getName());
        }catch (NullPointerException e){}

        holder.symptoms.setLayoutManager(new LinearLayoutManager(context));
        holder.symptoms.setHasFixedSize(true);
        adapter = new AdapterClassSymtomesAndTreatment(culGuide.getSymptoms(), context);
        holder.symptoms.setAdapter(adapter);

        holder.treatments.setLayoutManager(new LinearLayoutManager(context));
        holder.treatments.setHasFixedSize(true);
        adapter1 = new AdapterClassSymtomesAndTreatment(culGuide.getSymptoms(), context);
        holder.treatments.setAdapter(adapter1);

    }

    @Override
    public int getItemCount() {
        return diseaseResponses == null ? 0 : diseaseResponses.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        RecyclerView symptoms, treatments;
        ImageView disease_image;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            symptoms = itemView.findViewById(R.id.symptoms);
            treatments = itemView.findViewById(R.id.treatments);
            disease_image = itemView.findViewById(R.id.disease_image);
        }
    }
}
