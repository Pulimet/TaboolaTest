package net.alexandroid.utils.taboolatest;

import android.content.Context;
import android.util.AttributeSet;

import com.taboola.android.TaboolaWidget;
import com.taboola.android.listeners.TaboolaEventListener;

public class InfiniteTaboolaView extends TaboolaWidget {

    private boolean isGetContentCalled;

    public InfiniteTaboolaView(Context context) {
        super(context);
        init();
    }

    public InfiniteTaboolaView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public InfiniteTaboolaView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    protected void init() {
        setPublisher("ynet-ynet-app");
        setMode("thumbnails-a");
        setPlacement("Below Article Feed SDK");
        setTargetType("mix");
        setPageUrl("https://www.ynet.co.il");
        setPageType("article");
        setAutoResizeHeight(true);
        setScrollEnabled(false); //It is a default value but I added it to make it clear

        setTaboolaEventListener(new TaboolaEventListener() {
            @Override
            public boolean taboolaViewItemClickHandler(String s, boolean b) {
                return false;
            }

            @Override
            public void taboolaViewResizeHandler(TaboolaWidget taboolaWidget, int i) {
            }
        });

        getContent();
    }

    public void getContent() {
        if (!isGetContentCalled) {
            isGetContentCalled = true;
            fetchContent();
        }
    }

}
