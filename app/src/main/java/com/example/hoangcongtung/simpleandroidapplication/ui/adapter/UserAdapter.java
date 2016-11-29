package com.example.hoangcongtung.simpleandroidapplication.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hoangcongtung.simpleandroidapplication.R;
import com.example.hoangcongtung.simpleandroidapplication.models.model.UserItem;
import com.example.hoangcongtung.simpleandroidapplication.utils.DownloadImageTask;

import java.util.List;

/**
 * Created by hoangcongtung on 11/26/16.
 */
public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ItemHolder> {
    private List<UserItem> userItems;
    private int layoutId;

    public UserAdapter(List<UserItem> userItems) {
        this.userItems = userItems;
    }

    public int getLayoutId() {
        return layoutId;
    }

    public void setLayoutId(int layoutId) {
        this.layoutId = layoutId;
    }

    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflatedView = LayoutInflater.from(parent.getContext())
            .inflate(layoutId, parent, false);
        final ItemHolder holder = new ItemHolder(inflatedView);
        return holder;
    }

    @Override
    public void onBindViewHolder(ItemHolder holder, int position) {
        UserItem userItem = userItems.get(position);
        new DownloadImageTask(holder.itemImage)
            .execute(userItem.getAvatar_url());
        holder.itemTitle.setText(userItem.getLogin());
    }

    @Override
    public int getItemCount() {
        return userItems == null ? 0 : userItems.size();
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
        void onItemAddToCart(int position);
    }

    public static class ItemHolder extends RecyclerView.ViewHolder {
        private ImageView itemImage;
        private TextView itemTitle;

        public ItemHolder(View view) {
            super(view);
            itemImage = (ImageView) view.findViewById(R.id.image_avatar);
            itemTitle = (TextView) view.findViewById(R.id.text_login);
        }
    }
}