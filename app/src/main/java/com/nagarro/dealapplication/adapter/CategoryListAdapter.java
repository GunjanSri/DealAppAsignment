package com.nagarro.dealapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nagarro.dealapplication.storage.CategoryStorage;
import com.nagarro.dealapplication.CouponsListActivity;
import com.nagarro.dealapplication.R;
import com.nagarro.dealapplication.model.Category;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CategoryListAdapter extends RecyclerView.Adapter<CategoryListAdapter.MyViewHolder> {

    private static final String SELECTED_CATEGORY_NAME = "selected_category_name";
    private List<Category> categoryList;
    private Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public ImageView icon;

        public MyViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.category);
            icon = view.findViewById(R.id.category_icon);
        }
    }

    public CategoryListAdapter(Context context) {
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.category_list_row_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final Category category = categoryList.get(position);
        holder.name.setText(category.getName());
        Picasso.get().load(category.getIcon()).into(holder.icon);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context , CouponsListActivity.class);
                intent.putExtra(SELECTED_CATEGORY_NAME , category.getName());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public void setCategoryList(List<Category> categoryList){
        this.categoryList = categoryList;
    }
}
