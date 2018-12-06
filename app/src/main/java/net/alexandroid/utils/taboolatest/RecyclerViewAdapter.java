package net.alexandroid.utils.taboolatest;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.taboola.android.TaboolaWidget;
import com.taboola.android.utils.SdkDetailsHelper;

import net.alexandroid.utils.taboolatest.model.Component;
import net.alexandroid.utils.taboolatest.model.HomeTaboolaComp;
import net.alexandroid.utils.taboolatest.model.InfiniteTaboolaComp;
import net.alexandroid.utils.taboolatest.model.OtherComp;

import java.util.List;

class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_OTHER = 0;
    private static final int TYPE_HOME_TABOOLA = 1;
    private static final int TYPE_INFINITE_TABOOLA = 2;

    private List<Component> data;

    RecyclerViewAdapter(List<Component> list) {
        data = list;
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
                return new TaboolaViewHolder(createMiddleTaboolaView(viewGroup.getContext()));
            case TYPE_INFINITE_TABOOLA:
                return new TaboolaViewHolder(createInfiniteTaboolaView(viewGroup.getContext()));
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
                break;
            case TYPE_INFINITE_TABOOLA:
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
        TaboolaWidget mTaboolaWidget;

        TaboolaViewHolder(@NonNull View itemView) {
            super(itemView);
            mTaboolaWidget = (TaboolaWidget) itemView;
        }
    }

    public static class OtherViewHolder extends RecyclerView.ViewHolder {
        TextView mTextView;

        OtherViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextView = (TextView) itemView;
        }
    }

    private static TaboolaWidget createMiddleTaboolaView(Context context) {
        TaboolaWidget taboolaWidget = new TaboolaWidget(context);
        taboolaWidget
                .setPageType("article")
                .setPageUrl("https://www.ynet.co.il")
                .setMode("alternating-thumbnails-sdk")
                .setPlacement("Below Article Thumbnails SDK")
                .setTargetType("mix")
                .setPublisher("ynet-ynet-app");
        buildTaboolaWidget(context, taboolaWidget, false);
        return taboolaWidget;
    }

    private static TaboolaWidget createInfiniteTaboolaView(Context context) {
        TaboolaWidget taboolaWidget = new TaboolaWidget(context);
        taboolaWidget
                .setPageType("article")
                .setPageUrl("https://www.ynet.co.il")
                .setMode("thumbnails-a")
                .setPlacement("Below Article Feed SDK")
                .setTargetType("mix")
                .setPublisher("ynet-ynet-app");
        buildTaboolaWidget(context, taboolaWidget, true);
        return taboolaWidget;

    }

    private static void buildTaboolaWidget(Context context, TaboolaWidget taboolaWidget, boolean infiniteWidget) {
        final int displayHeight = SdkDetailsHelper.getDisplayHeight(context);
        int height = infiniteWidget ? displayHeight * 2 : displayHeight;
        ViewGroup.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height);
        taboolaWidget.setLayoutParams(params);

        if (infiniteWidget) {
            taboolaWidget.setInterceptScroll(true);
        }
        taboolaWidget.fetchContent();
    }
}
