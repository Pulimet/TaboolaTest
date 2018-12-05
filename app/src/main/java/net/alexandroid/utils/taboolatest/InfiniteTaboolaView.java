package net.alexandroid.utils.taboolatest;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.taboola.android.TaboolaWidget;
import com.taboola.android.listeners.TaboolaEventListener;
import com.taboola.android.utils.SdkDetailsHelper;

public class InfiniteTaboolaView extends TaboolaWidget {

    public InfiniteTaboolaView(Context context) {
        super(context);
        init(context);
    }

    protected void init(Context context) {

        int height = SdkDetailsHelper.getDisplayHeight(context);
        ViewGroup.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height);
        setLayoutParams(params);

        setPublisher("ynet-ynet-app");
        setMode("thumbnails-a");
        setPlacement("Below Article Feed SDK");
        setTargetType("mix");
        setPageUrl("https://www.ynet.co.il");
        setPageType("article");
        //setAutoResizeHeight(true);
        setInterceptScroll(true);
        //setScrollEnabled(false); //It is a default value but I added it to make it clear

        setTaboolaEventListener(new TaboolaEventListener() {
            @Override
            public boolean taboolaViewItemClickHandler(String s, boolean b) {
                return false;
            }

            @Override
            public void taboolaViewResizeHandler(TaboolaWidget taboolaWidget, int i) {
            }
        });

        fetchContent();
    }

}
