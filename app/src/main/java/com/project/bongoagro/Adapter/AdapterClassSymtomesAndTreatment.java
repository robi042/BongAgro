package com.project.bongoagro.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.project.bongoagro.Model.CropLifeCycle;
import com.project.bongoagro.Model.Symptom;
import com.project.bongoagro.R;

import java.util.List;

public class AdapterClassSymtomesAndTreatment extends RecyclerView.Adapter<AdapterClassSymtomesAndTreatment.ViewHolder> {
    private Context context;
    private List<Symptom> symptoms;
    public AdapterClassSymtomesAndTreatment(List<Symptom> symptoms, Context context) {
        this.symptoms = symptoms;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_view_crop_cycle_details, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        try {
            int a = position+1;
            holder.content.setText(symptoms.get(position).getContent());
        }catch (NullPointerException e){}
    }

    @Override
    public int getItemCount() {
        return symptoms == null ? 0 : symptoms.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView content;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            content = itemView.findViewById(R.id.content);
        }
    }
}
