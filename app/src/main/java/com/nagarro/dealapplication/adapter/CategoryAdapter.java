package com.nagarro.dealapplication.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.databinding.library.baseAdapters.BR;
import com.nagarro.dealapplication.R;
import com.nagarro.dealapplication.databinding.CategoryListRowItemBinding;
import com.nagarro.dealapplication.viewmodel.CategoryTypeViewModel;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MyViewHolder> {

    private List<CategoryTypeViewModel> categoryList;

    public class MyViewHolder extends RecyclerView.ViewHolder{

        private final CategoryListRowItemBinding binding;

        public MyViewHolder(CategoryListRowItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(CategoryTypeViewModel item) {
            binding.setVariable(BR.categoryTypeViewModel,item);
            binding.executePendingBindings();
        }
    }

    @NonNull
    @Override
    public CategoryAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        CategoryListRowItemBinding binding = DataBindingUtil.inflate(inflater,R.layout.category_list_row_item,
                parent,false);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.MyViewHolder holder, int position) {
        holder.bind(categoryList.get(position));
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public void setUpdatedList(List<CategoryTypeViewModel> categoryList){
        this.categoryList = categoryList;
    }
}