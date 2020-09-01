package com.covid19app.ui.news;

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
import com.covid19app.models.news.ArticlesItem;

class NewsDiffUtil extends DiffUtil.ItemCallback<ArticlesItem> {

    @Override
    public boolean areItemsTheSame(@NonNull ArticlesItem oldItem, @NonNull ArticlesItem newItem) {
        return false;
    }

    @Override
    public boolean areContentsTheSame(@NonNull ArticlesItem oldItem, @NonNull ArticlesItem newItem) {
        return false;
    }
}

public class NewsAdapter extends ListAdapter<ArticlesItem, NewsAdapter.ViewHolder> {

    public NewsAdapter() {
        super(new NewsDiffUtil());
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        ArticlesItem mBean = getItem(position);
        holder.tvTitle.setText(mBean.getTitle());
        holder.tvBody.setText(mBean.getDescription());
    }

    public ArticlesItem getCurrentItem(int position) {
        return getItem(position);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvBody;

        public ViewHolder(View view) {
            super(view);
            tvTitle = view.findViewById(R.id.tvTitle);
            tvBody = view.findViewById(R.id.tvBody);
        }
    }


}
