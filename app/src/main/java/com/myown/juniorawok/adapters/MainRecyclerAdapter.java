package com.myown.juniorawok.adapters;

import android.content.Context;
import android.graphics.Paint;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.myown.juniorawok.R;
import com.myown.juniorawok.adapters.viewholders.MainRecyclerHeaderViewHolder;
import com.myown.juniorawok.adapters.viewholders.MainRecyclerViewHolder;
import com.myown.juniorawok.network.models.FlashAPIResponse;
import com.myown.juniorawok.network.models.HomeAPIResponse;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Abdullah on 2/13/2018.
 */

public class MainRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private Context mContext;
    private List<FlashAPIResponse.ITEM> mDataSetFlash;
    private List<HomeAPIResponse.ITEM> mDataSetHome;

    private static final int HEADER = 0;
    private static final int OTHER = 1;

    public MainRecyclerAdapter(Context mContext, List<FlashAPIResponse.ITEM> mDataSetFlash, List<HomeAPIResponse.ITEM> mDataSetHome) {
        this.mContext = mContext;
        this.mDataSetFlash = mDataSetFlash;
        this.mDataSetHome = mDataSetHome;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;

        if(viewType == 0){
            viewHolder = new MainRecyclerHeaderViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.horizontal_recycler, parent, false));
        }else{
            viewHolder = new MainRecyclerViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.main_product_item, parent, false));
        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if(holder instanceof MainRecyclerHeaderViewHolder){
            MainRecyclerHeaderViewHolder mainRecyclerHeaderViewHolder = (MainRecyclerHeaderViewHolder)holder;
            mainRecyclerHeaderViewHolder.flashRecycler.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.HORIZONTAL,false));
            mainRecyclerHeaderViewHolder.flashRecycler.setAdapter(new FlashRecyclerAdapter(mContext,mDataSetFlash));
        }else{
            MainRecyclerViewHolder mainRecyclerViewHolder = (MainRecyclerViewHolder)holder;
            Picasso.with(mContext).load(getItem(position).getiMAGE().getsRC()).fit().into(mainRecyclerViewHolder.imageView);
            mainRecyclerViewHolder.nameTextView.setText(getItem(position).getnAME());
            String oldPriceText = getItem(position).getpRICES().getpRICE_OLD() + " AED";
            String newPriceText = getItem(position).getpRICES().getpRICE_NEW() + " AED";
            mainRecyclerViewHolder.oldPriceTextView.setText(oldPriceText);
            mainRecyclerViewHolder.oldPriceTextView.setPaintFlags(mainRecyclerViewHolder.oldPriceTextView.
                    getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            mainRecyclerViewHolder.newPriceTextView.setText(newPriceText);
        }
    }

    private HomeAPIResponse.ITEM getItem(int position){
        return mDataSetHome.get(position-1);
    }

    public void loadMoreItems(List<HomeAPIResponse.ITEM> items){
        mDataSetHome.addAll(items);
    }

    @Override
    public int getItemViewType(int position) {
        if (position == HEADER)
            return HEADER;
        else
            return OTHER;
    }

    @Override
    public int getItemCount() {
        return mDataSetHome.size() + 1;
    }
}
