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
 *
 * This is an adapter class for home products recycler.
 * This class will feature the implementation of the Recycler Adapter for home products recycler.
 * This adapter features two types of ViewHolders one for the horizontal flash products recycler and
 * one for the home product items.
 * */

public class MainRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private Context mContext;
    private List<FlashAPIResponse.ITEM> mDataSetFlash;
    private List<HomeAPIResponse.ITEM> mDataSetHome;

    private static final int HEADER = 0;
    private static final int OTHER = 1;

    /**
     * This is a constructor method for MainRecyclerAdapter which will be called by the MainActivity.
     *
     * @param mContext contains the context of the base activity. i.e MainActivity
     * @param mDataSetFlash contains the data set of the flash products which will be passed to the
     *                      FlashRecyclerAdapter.
     * @param mDataSetHome contains the data set of the home products which will be used by this
     *                     adapter to list down home products.
     */
    public MainRecyclerAdapter(Context mContext, List<FlashAPIResponse.ITEM> mDataSetFlash,
                               List<HomeAPIResponse.ITEM> mDataSetHome) {
        this.mContext = mContext;
        this.mDataSetFlash = mDataSetFlash;
        this.mDataSetHome = mDataSetHome;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;

        // Checking if the viewType is HEADER mean that the viewType == 0.
        if(viewType == HEADER){
            // Inflating horizontal recycler.
            viewHolder = new MainRecyclerHeaderViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.horizontal_recycler, parent, false));
        }else{
            // Inflating home product item.
            viewHolder = new MainRecyclerViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.main_product_item, parent, false));
        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        // Checking the holder instance type.
        if(holder instanceof MainRecyclerHeaderViewHolder){
            // If MainRecyclerHeaderViewHolder -> Initialize the FlashRecyclerAdapter and set the
            // RecyclerView.
            MainRecyclerHeaderViewHolder mainRecyclerHeaderViewHolder =
                    (MainRecyclerHeaderViewHolder)holder;
            mainRecyclerHeaderViewHolder.flashRecycler
                    .setLayoutManager(new LinearLayoutManager(mContext,
                    LinearLayoutManager.HORIZONTAL,false));
            mainRecyclerHeaderViewHolder.flashRecycler.
                    setAdapter(new FlashRecyclerAdapter(mContext,mDataSetFlash));
        }else{
            // If not MainRecyclerHeaderViewHolder -> Set up values of the UI Components.
            MainRecyclerViewHolder mainRecyclerViewHolder = (MainRecyclerViewHolder)holder;
            Picasso.with(mContext).load(getItem(position).getiMAGE().getsRC()).fit()
                    .into(mainRecyclerViewHolder.imageView);
            mainRecyclerViewHolder.nameTextView.setText(getItem(position).getnAME());
            String oldPriceText = getItem(position).getpRICES().getpRICE_OLD() + " AED";
            String newPriceText = getItem(position).getpRICES().getpRICE_NEW() + " AED";
            mainRecyclerViewHolder.oldPriceTextView.setText(oldPriceText);
            mainRecyclerViewHolder.oldPriceTextView.setPaintFlags(mainRecyclerViewHolder.oldPriceTextView.
                    getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            mainRecyclerViewHolder.newPriceTextView.setText(newPriceText);
        }
    }

    /**
     * This method will be used to get the correct item from the data set corresponding to its
     * position in RecyclerView.
     * Please note that we have added one item to the header. So, essentially our recycler position
     * will be +1 ahead of our home products data set.
     * Here I will decrement the position by 1 to align our data set with recycler correctly.
     *
     * @param position contains the recycler position.
     * @return the corresponding item in the data set for that position.
     */
    private HomeAPIResponse.ITEM getItem(int position){
        return mDataSetHome.get(position-1);
    }

    /**
     * This method is responsible for add more items into the previous data set of the recycler.
     * This method will be used on pagination to insert more items in the data set.
     *
     * @param items contains more home product items returned by the API on pagination.
     */
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
