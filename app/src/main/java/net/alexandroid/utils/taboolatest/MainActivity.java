package net.alexandroid.utils.taboolatest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import net.alexandroid.utils.taboolatest.model.Component;
import net.alexandroid.utils.taboolatest.model.HomeTaboolaComp;
import net.alexandroid.utils.taboolatest.model.InfiniteTaboolaComp;
import net.alexandroid.utils.taboolatest.model.OtherComp;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRecyclerView();
    }

    private void setRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new RecyclerViewAdapter(this, getListWithData()));
    }

    private List<Component> getListWithData() {
        ArrayList<Component> list = new ArrayList<>();
        list.add(new OtherComp("0"));
        list.add(new OtherComp("1"));
        list.add(new OtherComp("2"));
        list.add(new OtherComp("3"));
        list.add(new HomeTaboolaComp());
        list.add(new OtherComp("5"));
        list.add(new OtherComp("6"));
        list.add(new OtherComp("7"));
        list.add(new OtherComp("8"));
        list.add(new OtherComp("9"));
        list.add(new InfiniteTaboolaComp());
        return list;
    }
}
