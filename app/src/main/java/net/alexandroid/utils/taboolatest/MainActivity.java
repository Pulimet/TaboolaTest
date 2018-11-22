package net.alexandroid.utils.taboolatest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.taboola.android.TaboolaWidget;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTaboolaWidget();
    }

    private void setTaboolaWidget() {
        TaboolaWidget taboolaWidget = findViewById(R.id.taboolaWidget);

        taboolaWidget.setPublisher("sdk-tester")
                .setMode("thumbs-feed-01")
                .setPlacement("Feed without video")
                .setPageType("article")
                .setPageUrl("https://www.ynet.co.il/articles/0,7340,L-5411684,00.html")
                .setTargetType("mix");

        taboolaWidget.fetchContent();
    }
}
