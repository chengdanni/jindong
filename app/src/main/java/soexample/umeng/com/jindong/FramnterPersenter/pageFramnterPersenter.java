package soexample.umeng.com.jindong.FramnterPersenter;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.sunsky.marqueeview.MarqueeView;
import com.yzq.zxinglibrary.android.PermissionUtils;

import java.util.ArrayList;
import java.util.List;

import soexample.umeng.com.jindong.Activity.Flicking;
import soexample.umeng.com.jindong.Adepter.JiuAdapter;
import soexample.umeng.com.jindong.Adepter.ShangweiAdapter;
import soexample.umeng.com.jindong.Bean.JiuBean;
import soexample.umeng.com.jindong.Bean.LunBean;
import soexample.umeng.com.jindong.Bean.ShangBean;
import soexample.umeng.com.jindong.R;
import soexample.umeng.com.jindong.mvp.view.AgeInter;
import soexample.umeng.com.jindong.net.HttpHelper;

public class pageFramnterPersenter extends AgeInter {

    private JiuAdapter myAdapler;
    private RecyclerView recyclerView;
    private RecyclerView liebiao;
    private ViewPager view;
    private ImageView sao;
    private MarqueeView marqueeView1;
    private LinearLayout moreView;
    private ArrayList<Object> data;
    private List<View> views1;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0) {
                int i = view.getCurrentItem();
                if (i < list.size() - 1) {
                    i++;
                } else {
                    i = 0;
                }
                view.setCurrentItem(i);
                handler.sendEmptyMessageDelayed(0, 2000);
            }
        }
    };


    @Override
    public int getLayoutId() {
        return R.layout.shouye;
    }


    @Override
    public void initdata() {
        super.initdata();
        Fresco.initialize(context);
        view = (ViewPager) get(R.id.view);
        recyclerView = (RecyclerView) get(R.id.Review);
        liebiao = (RecyclerView) get(R.id.Reviewliebiao);
        sao = (ImageView) get(R.id.sao);
        sao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, Flicking.class));
            }
        });
        marqueeView1 = (MarqueeView) get(R.id.upview1);
        views1 = new ArrayList<>();
        initdata1();
        setViewSingleLine();
        marqueeView1.setViews(views1);
        doHttp();
        Jiugong();
        Shangping();
        handler.sendEmptyMessageDelayed(0, 2000);

    }

    private void initdata1() {
        data = new ArrayList<>();
        data.add("git常用命令");
        data.add("Git配置SSH访问GitHub(window)");
        data.add("关于java的抽象和接口");
        data.add("阿里HotFix2.0升级详解 畅谈热修复领域那些事");
    }

    private void setViewSingleLine() {
        views1.clear();
        // 记得加这句话，不然可能会产生重影现象
        for (int i = 0; i < data.size(); i++) {
            final int position = i;
            //设置滚动的单个布局 LinearLayout
            moreView = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.item_view_single, null);
            // 初始化布局的控件
            TextView tv1 = (TextView) moreView.findViewById(R.id.tv1);
            /** * 设置监听 */
            moreView.findViewById(R.id.rl).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context, position + "你点击了" + data.get(position).toString(), Toast.LENGTH_SHORT).show();
                }
            });
            //进行对控件赋值
            tv1.setText(data.get(i).toString());
            // 添加到循环滚动数组里面去
            views1.add(moreView);
        }
    }

    private void Shangping() {
        String shang = "http://www.zhaoapi.cn/product/getCarts?uid=71";
        new HttpHelper().get(shang).result(new HttpHelper.HttpListener() {
            private JiuAdapter myAdapler;
            private List<ShangBean.DataBean.DataList> list1 = new ArrayList<>();

            @Override
            public void success(String data) {
                if (data.contains("<")) {
                    Shangping();
                    return;
                }
                ShangBean tiao = new Gson().fromJson(data, ShangBean.class);
                List<ShangBean.DataBean> da = tiao.getData();
                for (int i = 0; i < da.size(); i++) {
                    if (da.get(i).getList() == null || da.get(i).getList().size() == 0) {

                    } else {
                        list1.addAll(da.get(i).getList());
                    }
                }
                ShangweiAdapter shangweiAdapter = new ShangweiAdapter(context, da);
                // ShangAdapter shangAdapter = new ShangAdapter(context, list1);

                LinearLayoutManager s = new LinearLayoutManager(context);
                s.setOrientation(LinearLayoutManager.VERTICAL);
                liebiao.setLayoutManager(s);
                liebiao.setAdapter(shangweiAdapter);
            }

            @Override
            public void fail(String error) {

            }
        });
    }

    //九宫格
    private void Jiugong() {

        String jiu = "http://www.zhaoapi.cn/product/getCatagory";
        new HttpHelper().get(jiu).result(new HttpHelper.HttpListener() {
            private List<JiuBean.DataBean> data2 = new ArrayList<>();

            @Override
            public void success(String data) {
                JiuBean tiao = new Gson().fromJson(data, JiuBean.class);
                data2 = tiao.getData();
                myAdapler = new JiuAdapter(data2, context);
                recyclerView.setAdapter(myAdapler);
                StaggeredGridLayoutManager s = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.HORIZONTAL);
                recyclerView.setLayoutManager(s);
            }

            @Override
            public void fail(String error) {
            }
        });
    }

    private MyAdate myAdate;
    private List<LunBean.DataBean> list = new ArrayList<>();

    //轮播适配
    class MyAdate extends PagerAdapter {

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView = new ImageView(context);
            String icon = list.get(position).getIcon();
            String replace = icon.replace("https", "http");
            Picasso.with(context).load(replace).fit().into(imageView);
            container.addView(imageView);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }


    //上下文
    private Context context;

    @Override
    public void getContext(Context context) {
        this.context = context;
    }

    //轮播
    private void doHttp() {
        String url = "http://www.zhaoapi.cn/ad/getAd";
        new HttpHelper().get(url).result(new HttpHelper.HttpListener() {
            @Override
            public void success(String data) {
                LunBean bean = new Gson().fromJson(data, LunBean.class);
                list = bean.getData();
                myAdate = new MyAdate();
                view.setAdapter(myAdate);
            }

            @Override
            public void fail(String error) {

            }
        });
    }
}
