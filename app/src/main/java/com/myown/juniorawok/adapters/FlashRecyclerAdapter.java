package com.myown.juniorawok.adapters;

import android.content.Context;
import android.graphics.Paint;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.myown.juniorawok.R;
import com.myown.juniorawok.adapters.viewholders.FlashRecyclerViewHolder;
import com.myown.juniorawok.network.models.FlashAPIResponse;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Netaq on 2/14/2018.
 */

public class FlashRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<FlashAPIResponse.ITEM> mDataSet;

    private int mDataSetKey = 0;

    FlashRecyclerAdapter(Context mContext, List<FlashAPIResponse.ITEM> mDataSet) {
        this.mContext = mContext;
        this.mDataSet = mDataSet;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new FlashRecyclerViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.flash_product_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        FlashRecyclerViewHolder flashRecyclerViewHolder = (FlashRecyclerViewHolder)holder;

        if(mDataSetKey<mDataSet.size()){
            Picasso.with(mContext).load(mDataSet.get(mDataSetKey).getiMAGE().getsRC()).fit().into(flashRecyclerViewHolder.leftImageView);
            flashRecyclerViewHolder.leftProductName.setText(mDataSet.get(mDataSetKey).getnAME());
            flashRecyclerViewHolder.leftProductNewPrice.setText(mDataSet.get(mDataSetKey).getpRICES().getpRICE_NEW());
            flashRecyclerViewHolder.LeftProductOldPrice.setText(mDataSet.get(mDataSetKey).getpRICES().getpRICE_OLD());
            flashRecyclerViewHolder.LeftProductOldPrice.setPaintFlags(flashRecyclerViewHolder.LeftProductOldPrice.
                    getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            if(mDataSet.get(mDataSetKey).getsTATE().equals("ACTIVE")){
                flashRecyclerViewHolder.leftProductSale.setText(R.string.buy_now);
                flashRecyclerViewHolder.leftSaleContainer.setBackgroundColor(ContextCompat.getColor(mContext, R.color.colorAccent));
            }else if(mDataSet.get(mDataSetKey).getsTATE().equals("UPCOMING")){
                flashRecyclerViewHolder.leftProductSale.setText(R.string.upcoming);
                flashRecyclerViewHolder.leftSaleContainer.setBackgroundColor(ContextCompat.getColor(mContext, R.color.colorGreen));
            }else if(mDataSet.get(mDataSetKey).getsTATE().equals("EXPIRED")){
                flashRecyclerViewHolder.leftProductSale.setText(R.string.sold_out);
                flashRecyclerViewHolder.leftSaleContainer.setBackgroundColor(ContextCompat.getColor(mContext, R.color.colorPrimaryDark));
            }

            mDataSetKey+=1;

            Picasso.with(mContext).load(mDataSet.get(mDataSetKey).getiMAGE().getsRC()).fit().into(flashRecyclerViewHolder.rightImageView);
            flashRecyclerViewHolder.rightProductName.setText(mDataSet.get(mDataSetKey).getnAME());
            flashRecyclerViewHolder.rightProductNewPrice.setText(mDataSet.get(mDataSetKey).getpRICES().getpRICE_NEW());
            flashRecyclerViewHolder.rightProductOldPrice.setText(mDataSet.get(mDataSetKey).getpRICES().getpRICE_OLD());
            flashRecyclerViewHolder.rightProductOldPrice.setPaintFlags(flashRecyclerViewHolder.rightProductOldPrice.
                    getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            if(mDataSet.get(mDataSetKey).getsTATE().equals("ACTIVE")){
                flashRecyclerViewHolder.rightProductSale.setText(R.string.buy_now);
                flashRecyclerViewHolder.rightSaleContainer.setBackgroundColor(ContextCompat.getColor(mContext, R.color.colorAccent));
            }else if(mDataSet.get(mDataSetKey).getsTATE().equals("UPCOMING")){
                flashRecyclerViewHolder.rightProductSale.setText(R.string.upcoming);
                flashRecyclerViewHolder.rightSaleContainer.setBackgroundColor(ContextCompat.getColor(mContext, R.color.colorGreen));
            }else if(mDataSet.get(mDataSetKey).getsTATE().equals("EXPIRED")){
                flashRecyclerViewHolder.rightProductSale.setText(R.string.sold_out);
                flashRecyclerViewHolder.rightSaleContainer.setBackgroundColor(ContextCompat.getColor(mContext, R.color.colorPrimaryDark));
            }

            mDataSetKey+=1;
        }
    }

    @Override
    public int getItemCount() {
        return mDataSet.size()/2;
    }
}
