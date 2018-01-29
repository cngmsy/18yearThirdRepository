package com.example.lenovo.tuozhuaidome;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.jar.Manifest;
import static com.example.lenovo.tuozhuaidome.R.id.gridlayout_drageable;
import static com.example.lenovo.tuozhuaidome.R.id.gridlayout_undrageable;
import static com.example.lenovo.tuozhuaidome.R.id.guanbi;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TabLayout tab;
    private ViewPager vp;
    private Button xianshi;
    private PopupWindow popupWindow;
    private ArrayList<String> list1;
    private ArrayList<String> list2;
    private MyGridLayout gridlayout_drageable;
    private MyGridLayout gridlayout_undrageable;
    private ImageView guanbi;
    private List<Fragment> listF=new ArrayList<Fragment>();
    private Adapters adapters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();

    }

    private void initData() {
        list1 = new ArrayList<String>();
        for (int i = 0; i < 4; i++) {
            list1.add("A"+i);
            BlankFragment blankFragment = new BlankFragment();
//            Bundle bundle = new Bundle();
//            bundle.putString("name",list1.get(i));
//            blankFragment.setArguments(bundle);
            listF.add(blankFragment);
        }
        list2= new ArrayList<String>();
        for (int i = 0; i < 20; i++) {
            list2.add("B"+i);
        }
        adapters = new Adapters(getSupportFragmentManager(), listF, list1);
        tab.setupWithViewPager(vp);
        vp.setAdapter(adapters);
        vp.setCurrentItem(0);

    }

    private void initView() {
        tab = (TabLayout) findViewById(R.id.tab);
        vp = (ViewPager) findViewById(R.id.vp);
        xianshi = (Button) findViewById(R.id.xianshi);
        xianshi.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.xianshi:
                xianshifubiao();
                break;
        }
    }

    private void xianshifubiao(){
        View inflate = LayoutInflater.from(MainActivity.this).inflate(R.layout.layout_gridlayout_two, null);
        gridlayout_drageable = inflate.findViewById(R.id.gridlayout_drageable);
        gridlayout_undrageable = inflate.findViewById(R.id.gridlayout_undrageable);
        guanbi = inflate.findViewById(R.id.guanbi);
        //这是初始化布局为了显示PuPO
        //这是new一个对象后面的参数。第一个参数布局，第二个参数用容器调用一个系统的方法，后面的是旋转的度数
        popupWindow = new PopupWindow(inflate, ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
        initGridLayout();
        popupWindow.setFocusable(true);
        //new一个过期的方法点击屏幕以外消失
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        //这是i定义的引用动画
//                popupWindow.setAnimationStyle(R.style.qqq);
        // 这个是显示的意思
        popupWindow.showAsDropDown(inflate);
//                if(as){
//                    Chinabianji.setText("完成");
                    //点击下部，移除一个item在第一个里面添加一个item
                    gridlayout_undrageable.setOnItemClickLitener(new MyGridLayout.OnItemClickLitener() {
                        @Override
                        public void OnItemClickLitener(String strItem, View v) {
                            Log.e("3333333333333",strItem);
                            gridlayout_undrageable.removeView(v);
                            gridlayout_drageable.addTvItem(strItem);
                            for (int i = 0; i <list2.size() ; i++) {
                                if(strItem.equals(list2.get(i))){
                                    list2.remove(i);
                                    list1.add(strItem);
                                    BlankFragment blankFragment = new BlankFragment();
//                                    Bundle bundle = new Bundle();
//                                    bundle.putString("name",list1.get(i));
//                                    blankFragment.setArguments(bundle);
                                    listF.add(blankFragment);
                                }

                            }


                        }
                    });
//点击上部，移除一个item在第二个里面添加一个item
                    gridlayout_drageable.setOnItemClickLitener(new MyGridLayout.OnItemClickLitener() {
                        @Override
                        public void OnItemClickLitener(String strItem, View v) {
                            Log.e("44444444444444444", strItem);
                            if(list1.size()>4) {
                                gridlayout_drageable.removeView(v);
                                gridlayout_undrageable.addTvItem(strItem);
                                for (int i = 0; i <list1.size() ; i++) {
                                    if(strItem.equals(list1.get(i))){
                                        list1.remove(i);
                                        list2.add(strItem);
                                        listF.remove(i);
                                    }
                                }
                            }else{
                                Toast.makeText(MainActivity.this, "删除数量不能少于4条", Toast.LENGTH_SHORT).show();
                            }
//                    as=false;
//                }else{
//                    Chinabianji.setText("编辑");
//                    adapterFarmentChina.notifyDataSetChanged();
//                    gridlayout_undrageable.setOnItemClickLitener(new MyGridLayout.OnItemClickLitener() {
//                        @Override
//                        public void OnItemClickLitener(String strItem, View v) {
////                                    gridlayout_undrageable.removeView(v);
////                                    gridlayout_drageable.addTvItem(strItem);
//                        }
//                    });
////点击上部，移除一个item在第二个里面添加一个item
//                    gridlayout_drageable.setOnItemClickLitener(new MyGridLayout.OnItemClickLitener() {
//                        @Override
//                        public void OnItemClickLitener(String strItem, View v) {
////                                    gridlayout_drageable.removeView(v);
////                                    gridlayout_undrageable.addTvItem(strItem);
//                        }
//                    });
//                    as=true;
//                }
            }
        });
        guanbi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapters.notifyDataSetChanged();
                popupWindow.dismiss();
            }
        });
    }
    private void initGridLayout() {
        gridlayout_drageable.setGridLayoutItemDrageAble(true);
        gridlayout_drageable.addItems(list1);
        gridlayout_undrageable.setGridLayoutItemDrageAble(false);
        gridlayout_undrageable.addItems(list2);
    }
}
