package com.example.imagegallery.Utilities;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.imagegallery.R;
import com.example.imagegallery.Data.FeaturedItem;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ItemViewHolder> {
    private List<FeaturedItem> mItems;
    private OnItemListener mOnItemListener;

    public  class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView mItemName;
        public TextView mItemDesc;
        public ImageView mImageView;
        OnItemListener onItemListener;

        public ItemViewHolder(View v, OnItemListener onItemListener) {
            super(v);
            mImageView = (ImageView) v.findViewById(R.id.item_image);
            this.onItemListener = onItemListener;

            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onItemListener.onItemClick(getAdapterPosition());
        }
    }

    public interface OnItemListener {
        void onItemClick(int position);
    }

    public HomeAdapter(List<FeaturedItem> items, OnItemListener onItemListener) {
        mItems = items;
        this.mOnItemListener = onItemListener;
    }

    @Override
    public HomeAdapter.ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.featured_view, parent, false);

        ItemViewHolder itemHolder = new ItemViewHolder(v, mOnItemListener);
        return itemHolder;
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        Glide.with(holder.mImageView.getContext()).load(mItems.get(position).getThumb()).centerCrop()
                .into(holder.mImageView);
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }
}