package net.alexandroid.utils.taboolatest;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.taboola.android.TaboolaWidget;

import net.alexandroid.utils.taboolatest.model.Component;
import net.alexandroid.utils.taboolatest.model.HomeTaboolaComp;
import net.alexandroid.utils.taboolatest.model.InfiniteTaboolaComp;
import net.alexandroid.utils.taboolatest.model.OtherComp;

import java.util.List;

class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int TYPE_OTHER = 0;
    public static final int TYPE_HOME_TABOOLA = 1;
    public static final int TYPE_INFINITE_TABOOLA = 2;

    private HomeTaboolaView mHomeTaboolaView;
    private InfiniteTaboolaView mInfiniteTaboolaView;

    private List<Component> data;

    public RecyclerViewAdapter(List<Component> list, Context context) {
        data = list;
        mHomeTaboolaView = new HomeTaboolaView(context);
        mInfiniteTaboolaView = new InfiniteTaboolaView(context);
    }

    @Override
    public int getItemViewType(int position) {
        if (data.get(position) instanceof HomeTaboolaComp) {
            return TYPE_HOME_TABOOLA;
        } else if (data.get(position) instanceof InfiniteTaboolaComp) {
            return TYPE_INFINITE_TABOOLA;
        } else {
            return TYPE_OTHER;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        switch (viewType) {
            case TYPE_HOME_TABOOLA:
                return new TaboolaViewHolder(mHomeTaboolaView);
            case TYPE_INFINITE_TABOOLA:
                return new TaboolaViewHolder(mInfiniteTaboolaView);
            default:
                View otherView = LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.item_other, viewGroup, false);
                return new OtherViewHolder(otherView);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case TYPE_HOME_TABOOLA:
                mHomeTaboolaView.getContent();
                break;
            case TYPE_INFINITE_TABOOLA:
                mInfiniteTaboolaView.getContent();
                break;
            default:
                OtherViewHolder otherViewHolder = (OtherViewHolder) holder;
                OtherComp otherComp = (OtherComp) data.get(position);
                otherViewHolder.mTextView.setText(otherComp.getName());
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class TaboolaViewHolder extends RecyclerView.ViewHolder {
        public TaboolaWidget mTaboolaWidget;

        public TaboolaViewHolder(@NonNull View itemView) {
            super(itemView);
            mTaboolaWidget = (TaboolaWidget) itemView;
        }
    }

    public static class OtherViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;

        public OtherViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextView = (TextView) itemView;
        }
    }
}
