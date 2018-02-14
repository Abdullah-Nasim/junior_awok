package com.myown.juniorawok.adapters.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.myown.juniorawok.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Abdullah on 2/13/2018.
 *
 * This is a view holder for home product item.
 */

public class MainRecyclerViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.main_product_image)
    public ImageView imageView;

    @BindView(R.id.main_product_name)
    public TextView nameTextView;

    @BindView(R.id.main_product_old_price)
    public TextView oldPriceTextView;

    @BindView(R.id.main_product_new_price)
    public TextView newPriceTextView;

    public MainRecyclerViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }
}
