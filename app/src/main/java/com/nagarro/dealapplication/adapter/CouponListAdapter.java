package com.nagarro.dealapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nagarro.dealapplication.AvailOfferActivity;
import com.nagarro.dealapplication.CouponsListActivity;
import com.nagarro.dealapplication.R;
import com.nagarro.dealapplication.model.Category;
import com.nagarro.dealapplication.model.Coupon;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CouponListAdapter extends RecyclerView.Adapter<CouponListAdapter.MyViewHolder> {

    private static final String SELECTED_CATEGORY_POSITION = "selected_category_position";
    private List<Coupon> couponList;
    private Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView code , description , validity , location;
        public ImageView icon;

        public MyViewHolder(View view) {
            super(view);
            code = (TextView) view.findViewById(R.id.codeTextView);
            description = (TextView) view.findViewById(R.id.descriptionTextView);
            validity = (TextView) view.findViewById(R.id.validitytextView);
            location = (TextView) view.findViewById(R.id.locationTextView);
            icon = (ImageView)view.findViewById(R.id.iconImageView);
        }
    }

    public CouponListAdapter(Context context) {
        this.context = context;
    }

    @Override
    public CouponListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.coupon_list_row, parent, false);

        return new CouponListAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CouponListAdapter.MyViewHolder holder, final int position) {
        Coupon coupon = couponList.get(position);
        holder.code.setText(coupon.getCode());
        holder.description.setText(coupon.getDescription());
        holder.validity.setText(coupon.getValidity());
        holder.location.setText(coupon.getLocation());

        Picasso.get().load(coupon.getIcon()).into(holder.icon);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context , AvailOfferActivity.class);
                context.startActivity(intent);
            }
        });


        /*Picasso.get().load(category.getIcon()).into(holder.icon);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context , CouponsListActivity.class);
                intent.putExtra(SELECTED_CATEGORY_POSITION , position);
                context.startActivity(intent);
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return couponList.size();
    }

    public void setCouponList(List<Coupon> couponList){
        this.couponList = couponList;
    }
}
