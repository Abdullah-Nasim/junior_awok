package com.myown.juniorawok.adapters.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.myown.juniorawok.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Abdullah on 2/13/2018.
 */

public class FlashRecyclerViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.flash_product_left_image)
    public ImageView leftImageView;

    @BindView(R.id.flash_product_right_image)
    public ImageView rightImageView;

    @BindView(R.id.flash_product_left_name)
    public TextView leftProductName;

    @BindView(R.id.flash_product_right_name)
    public TextView rightProductName;

    @BindView(R.id.flash_product_left_old_price)
    public TextView LeftProductOldPrice;

    @BindView(R.id.flash_product_right_old_price)
    public TextView rightProductOldPrice;

    @BindView(R.id.flash_product_left_new_price)
    public TextView leftProductNewPrice;

    @BindView(R.id.flash_product_right_new_price)
    public TextView rightProductNewPrice;

    @BindView(R.id.flash_product_left_sale)
    public TextView leftProductSale;

    @BindView(R.id.flash_product_right_sale)
    public TextView rightProductSale;

    @BindView(R.id.flash_product_left_sale_status_container)
    public LinearLayout leftSaleContainer;

    @BindView(R.id.flash_product_right_sale_status_container)
    public LinearLayout rightSaleContainer;

    public FlashRecyclerViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
