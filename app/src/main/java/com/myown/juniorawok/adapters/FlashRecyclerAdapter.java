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
import com.myown.juniorawok.utils.Utils;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Abdullah on 2/14/2018.
 *
 * This is an adapter class for flash products recycler.
 * This class will feature the implementation of the Recycler Adapter for flash products recycler.
 */

public class FlashRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<FlashAPIResponse.ITEM> mDataSet;

    /**
     * This is the constructor method for FlashRecyclerAdapter which will be called by the
     * MainRecyclerAdapter.
     *
     * @param mContext will contain the context of the base activity.
     * @param mDataSet will contain the data set based on which the listing will be created.
     */
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

        // In the following line I am calculating half of the device screen size and dp and
        // allocating it to the width of particular item.
        flashRecyclerViewHolder.parentContainer.getLayoutParams().width = Utils.getScreenWidth(mContext) / 2-12;

        // Setting up the values of the UI components based on their positions
        Picasso.with(mContext).load(mDataSet.get(position).getiMAGE().getsRC()).fit().
                into(flashRecyclerViewHolder.imageView);
        flashRecyclerViewHolder.productName.setText(mDataSet.get(position).getnAME());
        flashRecyclerViewHolder.productNewPrice.setText(mDataSet.get(position).getpRICES().getpRICE_NEW());
        flashRecyclerViewHolder.productOldPrice.setText(mDataSet.get(position).getpRICES().getpRICE_OLD());
        flashRecyclerViewHolder.productOldPrice.setPaintFlags(flashRecyclerViewHolder.productOldPrice.
                getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

        // Checking the present state of product sale.
        if(mDataSet.get(position).getsTATE().equals("ACTIVE")){
            flashRecyclerViewHolder.productSale.setText(R.string.buy_now);
            flashRecyclerViewHolder.saleContainer.setBackgroundColor(ContextCompat.getColor(mContext,
                    R.color.colorAccent));
        }else if(mDataSet.get(position).getsTATE().equals("UPCOMING")){
            flashRecyclerViewHolder.productSale.setText(R.string.upcoming);
            flashRecyclerViewHolder.saleContainer.setBackgroundColor(ContextCompat.getColor(mContext,
                    R.color.colorGreen));
        }else if(mDataSet.get(position).getsTATE().equals("EXPIRED")){
            flashRecyclerViewHolder.productSale.setText(R.string.sold_out);
            flashRecyclerViewHolder.saleContainer.setBackgroundColor(ContextCompat.getColor(mContext,
                    R.color.colorPrimaryDark));
        }
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }
}
