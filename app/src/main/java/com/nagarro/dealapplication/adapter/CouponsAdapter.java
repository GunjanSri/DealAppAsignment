package com.nagarro.dealapplication.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.databinding.library.baseAdapters.BR;
import com.nagarro.dealapplication.R;
import com.nagarro.dealapplication.databinding.CouponListRowBinding;
import com.nagarro.dealapplication.viewmodel.CouponViewModel;

import java.util.List;

public class CouponsAdapter extends RecyclerView.Adapter<CouponsAdapter.MyViewHolder> {

    private final Context context;
    private List<CouponViewModel> couponList;

    public CouponsAdapter(Context context){
        this.context = context;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        CouponListRowBinding binding;
        public MyViewHolder(CouponListRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(CouponListRowBinding item) {
            binding.setVariable(BR.couponViewModel,item);
            binding.executePendingBindings();
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        CouponListRowBinding binding = DataBindingUtil.inflate(inflater, R.layout.coupon_list_row,
                parent,false);
        return new CouponsAdapter.MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.bind(couponList.get(position));

        /*holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context , OfferListActivity.class);
                intent.putExtra(SELECTED_CATEGORY_POSITION , position);
                context.startActivity(intent);
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return couponList.size();
    }

    public void setCouponList(List<CouponViewModel> couponList){
        this.couponList = couponList;
    }
}
