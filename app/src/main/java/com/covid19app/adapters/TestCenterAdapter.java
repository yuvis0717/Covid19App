package com.covid19app.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.covid19app.R;
import com.covid19app.ui.helpline.ItemClickCallback;
import com.covid19app.ui.testcenter.TestCentersModel;

class TestCenterDiffUtil extends DiffUtil.ItemCallback<TestCentersModel> {

    @Override
    public boolean areItemsTheSame(@NonNull TestCentersModel oldItem, @NonNull TestCentersModel newItem) {
        return false;
    }

    @Override
    public boolean areContentsTheSame(@NonNull TestCentersModel oldItem, @NonNull TestCentersModel newItem) {
        return false;
    }
}

public class TestCenterAdapter extends ListAdapter<TestCentersModel, TestCenterAdapter.ViewHolder> {

    private ItemClickCallback callback;

    public TestCenterAdapter(ItemClickCallback callback) {
        super(new TestCenterDiffUtil());
        this.callback=callback;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_test_centers, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        TestCentersModel mBean = getItem(position);
        holder.tvName.setText(mBean.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.onItemClick(position);
            }
        });
    }
    public TestCentersModel getCurrentItem(int position){
        return getItem(position);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;

        public ViewHolder(View view) {
            super(view);
            tvName = view.findViewById(R.id.tvName);
        }
    }


}
