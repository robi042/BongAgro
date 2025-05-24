package com.project.bongoagro.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.project.bongoagro.Model.CropLifeCycle;
import com.project.bongoagro.Model.CultivationGuide;
import com.project.bongoagro.R;

import java.util.List;

public class CropLifeCycleViewModelAdapter extends RecyclerView.Adapter<CropLifeCycleViewModelAdapter.viewHolder> {
    private Context context;
    private List<CropLifeCycle> cropLifeCycles;
    private RecyclerView.Adapter adapter;
    public CropLifeCycleViewModelAdapter(List<CropLifeCycle> cropLifeCycles, Context context) {
        this.cropLifeCycles = cropLifeCycles;
        this.context = context;
    }
    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_view_crop_cycle, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        CropLifeCycle culGuide = cropLifeCycles.get(position);
        try {
            holder.subtitle.setText(cropLifeCycles.get(position).getSubTitle());
        }catch (NullPointerException e){}

        holder.cycle_details.setLayoutManager(new LinearLayoutManager(context));
        holder.cycle_details.setHasFixedSize(true);
        adapter = new CropLifeCycledetailsAdapter(culGuide.getCropLifeCycleDetails(), context);
        holder.cycle_details.setAdapter(adapter);

    }

    @Override
    public int getItemCount() {
        return cropLifeCycles == null ? 0 : cropLifeCycles.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView subtitle;
        RecyclerView cycle_details;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            subtitle = itemView.findViewById(R.id.subtitle);
            cycle_details = itemView.findViewById(R.id.cycle_details);
        }
    }
}
