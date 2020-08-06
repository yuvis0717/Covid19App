package com.covid19app.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.covid19app.R;
import com.covid19app.ui.symptom.SymptomCheckModel;

class AdminListAdapterDiffUtil extends DiffUtil.ItemCallback<SymptomCheckModel> {

    @Override
    public boolean areItemsTheSame(@NonNull SymptomCheckModel oldItem, @NonNull SymptomCheckModel newItem) {
        return false;
    }

    @Override
    public boolean areContentsTheSame(@NonNull SymptomCheckModel oldItem, @NonNull SymptomCheckModel newItem) {
        return false;
    }
}

public class AdminListAdapter extends ListAdapter<SymptomCheckModel, AdminListAdapter.ViewHolder> {

    public AdminListAdapter() {
        super(new AdminListAdapterDiffUtil());
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_admin_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SymptomCheckModel mBean = getItem(position);
        holder.tvName.setText(mBean.getName());
        holder.tvGender.setText(mBean.getGender());
        holder.tvRisk.setText(mBean.getRisk());

        if (mBean.getRisk().equalsIgnoreCase("high")) {
            holder.tvRisk.setTextColor(ContextCompat.getColor(holder.tvRisk.getContext(), R.color.colorHigh));
        } else if (mBean.getRisk().equalsIgnoreCase("medium")) {
            holder.tvRisk.setTextColor(ContextCompat.getColor(holder.tvRisk.getContext(), R.color.colorMedium));
        } else {
            holder.tvRisk.setTextColor(ContextCompat.getColor(holder.tvRisk.getContext(), R.color.colorLow));
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        TextView tvGender;
        TextView tvRisk;

        ViewHolder(View view) {
            super(view);
            tvName = view.findViewById(R.id.tvName);
            tvGender = view.findViewById(R.id.tvGender);
            tvRisk = view.findViewById(R.id.tvRisk);
        }
    }


}
