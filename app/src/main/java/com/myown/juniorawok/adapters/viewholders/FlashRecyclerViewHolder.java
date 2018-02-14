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
 *
 * This is a view holder for flash product item.
 */

public class FlashRecyclerViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.flash_parent_container)
    public LinearLayout parentContainer;

    @BindView(R.id.flash_product_image)
    public ImageView imageView;

    @BindView(R.id.flash_product_name)
    public TextView productName;

    @BindView(R.id.flash_product_old_price)
    public TextView productOldPrice;

    @BindView(R.id.flash_product_new_price)
    public TextView productNewPrice;

    @BindView(R.id.flash_product_sale)
    public TextView productSale;

    @BindView(R.id.flash_product_sale_status_container)
    public LinearLayout saleContainer;

    public FlashRecyclerViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
