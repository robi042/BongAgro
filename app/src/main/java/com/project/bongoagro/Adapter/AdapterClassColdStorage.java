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
import com.project.bongoagro.R;

import java.util.List;

public class AdapterClassColdStorage extends RecyclerView.Adapter<AdapterClassColdStorage.ColdStorageHolder> {
    Context context;
    private List<ColdStorageResponseModelClass> coldStorageResponseModelClasses;
    private RecyclerView.Adapter adapter;

    public AdapterClassColdStorage(List<ColdStorageResponseModelClass> coldStorageResponseModelClasses, Context context){
        this.coldStorageResponseModelClasses = coldStorageResponseModelClasses;
        this.context = context;
    }
    @NonNull
    @Override
    public ColdStorageHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recly_item_clod_storage, parent, false);
        return new ColdStorageHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ColdStorageHolder holder, int position) {
        ColdStorageResponseModelClass coldStorage = coldStorageResponseModelClasses.get(position);
        String originalUrl = coldStorageResponseModelClasses.get(position).getImageUrl();
        String imageUrl = originalUrl.replace("/file/d/", "/thumbnail?id=")
                .replace("/view?usp=drive_link", "");
        Glide.with(context)
                .load(imageUrl)
                .error(R.drawable.logo)
                .into(holder.log_image);
        try {
            holder.name.setText(coldStorageResponseModelClasses.get(position).getName());
        }catch (NullPointerException e){}
        try {
            holder.owner_name.setText(coldStorageResponseModelClasses.get(position).getOwnerName());
        }catch (NullPointerException e){}
        try {
            holder.lisense_number.setText(coldStorageResponseModelClasses.get(position).getLicenseNumber());
        }catch (NullPointerException e){}
        try {
            if(coldStorageResponseModelClasses.get(position).getDistrict() == null || coldStorageResponseModelClasses.get(position).getDistrict().trim().isEmpty()){
                holder.district.setVisibility(View.GONE);
            }else {
                holder.district.setText(coldStorageResponseModelClasses.get(position).getDistrict());
            }
        }catch (NullPointerException e){}
        try {
            holder.capacity.setText(coldStorageResponseModelClasses.get(position).getCapacity());
        }catch (NullPointerException e){}
        try {
            holder.address.setText(coldStorageResponseModelClasses.get(position).getAddress());
        }catch (NullPointerException e){}
        holder.recycler_cold_storage_type.setLayoutManager(new GridLayoutManager(context, 2));
        holder.recycler_cold_storage_type.setHasFixedSize(true);
        adapter = new ColdStorageTypeAdapter(coldStorage.getColdStorageTypes(), context);
        holder.recycler_cold_storage_type.setAdapter(adapter);

    }

    @Override
    public int getItemCount() {
        return coldStorageResponseModelClasses == null ? 0 : coldStorageResponseModelClasses.size();
    }

    public class ColdStorageHolder extends RecyclerView.ViewHolder {
        TextView name, owner_name, lisense_number, district, capacity, address;
        RecyclerView recycler_cold_storage_type;
        ImageView log_image;
        public ColdStorageHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            owner_name = itemView.findViewById(R.id.owner_name);
            lisense_number = itemView.findViewById(R.id.lisense_number);
            district = itemView.findViewById(R.id.district);
            capacity = itemView.findViewById(R.id.capacity);
            address = itemView.findViewById(R.id.address);
            recycler_cold_storage_type = itemView.findViewById(R.id.recycler_cold_storage_type);
            log_image = itemView.findViewById(R.id.log_image);
        }
    }
}
