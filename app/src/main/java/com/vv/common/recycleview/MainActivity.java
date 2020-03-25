package com.vv.common.recycleview;

import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    Timer timer = new Timer();
    final List<CategoryEn> dataList = getData();
    int last = -1;
    MyAdapter adapter = new MyAdapter(dataList);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.rv_content);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2, RecyclerView.HORIZONTAL, false);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                CategoryEn categoryEn = dataList.get(position);
                if (categoryEn.isBig()) {
                    return 2;
                } else {
                    return 1;
                }
            }
        });

        recyclerView.setLayoutManager(gridLayoutManager);
        DividerItemDecoration decor = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        decor.setDrawable(getDrawable(R.drawable.rv_divider_h));

        DividerItemDecoration decorWidth = new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL);
        decorWidth.setDrawable(getDrawable(R.drawable.rv_divider_w));

        recyclerView.addItemDecoration(decor);
        recyclerView.addItemDecoration(decorWidth);
        recyclerView.setAdapter(adapter);

        doTimer();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        timer.cancel();
    }

    private void doTimer() {
        final Handler handler = new Handler();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (last >= 0) {
                    dataList.get(last).isFocus = false;
                }
                if (last == dataList.size() - 1) {
                    last = 0;
                } else {
                    last = last + 1;
                }
                dataList.get(last).isFocus = true;
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        }, 500, 500);

    }


    public List<CategoryEn> getData() {
        List<CategoryEn> dataList = new ArrayList<>();
        dataList.add(new CategoryEn("蔬菜豆类", new String[]{"多种维生素矿物质", "蛋白质"}, CategoryEn.TYPE_BIG));
        dataList.add(new CategoryEn("肉禽蛋", new String[]{"多种维生素矿物质", "蛋白质"}, CategoryEn.TYPE_SMALL));
        dataList.add(new CategoryEn("水果", new String[]{"多种维生素矿物质", "蛋白质"}, CategoryEn.TYPE_SMALL));
        dataList.add(new CategoryEn("蔬菜豆类", new String[]{"多种维生素矿物质", "蛋白质"}, CategoryEn.TYPE_BIG));
        dataList.add(new CategoryEn("蔬菜豆类", new String[]{"多种维生素矿物质", "蛋白质"}, CategoryEn.TYPE_SMALL));
        dataList.add(new CategoryEn("蔬菜豆类", new String[]{"多种维生素矿物质", "蛋白质"}, CategoryEn.TYPE_SMALL));
        return dataList;
    }


}
