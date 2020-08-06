package com.covid19app.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.covid19app.R;
import com.covid19app.ui.helpline.HelplineModel;
import com.covid19app.ui.helpline.ItemClickCallback;

class HelplineDiffUtil extends DiffUtil.ItemCallback<HelplineModel> {

    @Override
    public boolean areItemsTheSame(@NonNull HelplineModel oldItem, @NonNull HelplineModel newItem) {
        return false;
    }

    @Override
    public boolean areContentsTheSame(@NonNull HelplineModel oldItem, @NonNull HelplineModel newItem) {
        return false;
    }
}

public class HelplineAdapter extends ListAdapter<HelplineModel, HelplineAdapter.ViewHolder> {

    private ItemClickCallback callback;

    public HelplineAdapter(ItemClickCallback callback) {
        super(new HelplineDiffUtil());
        this.callback=callback;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_helpline, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        HelplineModel mBean=getItem(position);
        holder.tvName.setText(mBean.getName());
        holder.flCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.onItemClick(position);
            }
        });
    }

    public HelplineModel getCurrentItem(int position){
        return getItem(position);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        FrameLayout flCall;

        public ViewHolder(View view) {
            super(view);
            tvName = view.findViewById(R.id.tvName);
            flCall=view.findViewById(R.id.flCall);
        }
    }


}
