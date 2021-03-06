package com.myown.juniorawok.adapters.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.myown.juniorawok.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Abdullah on 2/13/2018.
 *
 * This is a view holder for a horizontal recycler for flash products.
 */

public class MainRecyclerHeaderViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.flash_recycler)
    public RecyclerView flashRecycler;

    public MainRecyclerHeaderViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
