package soexample.umeng.com.jindong.FramnterPersenter;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import soexample.umeng.com.jindong.Activity.Zhuyemian;
import soexample.umeng.com.jindong.Adepter.GouwucheAdapter;
import soexample.umeng.com.jindong.Bean.GouBean;
import soexample.umeng.com.jindong.R;
import soexample.umeng.com.jindong.mvp.view.AgeInter;
import soexample.umeng.com.jindong.net.HttpHelper;

public class ShopFramnerpersenter extends AgeInter implements View.OnClickListener {

    private List<GouBean.DataBean> data1 = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private GouwucheAdapter mShopSellerAdapter;
    private ImageView ivCricle;
    private TextView allPriceTxt;
    private TextView sunPrice;
    private TextView shangchu;
    private String uid;
    private String pid;

    @Override
    public int getLayoutId() {
        return R.layout.shop;
    }

    @Override
    public void initdata() {
        super.initdata();
        mRecyclerView = (RecyclerView) get(R.id.layout_top1);
        ivCricle = (ImageView) get(R.id.iv_cricle);
        allPriceTxt = (TextView) get(R.id.all_price);
        sunPrice = (TextView) get(R.id.sum_price_txt);
        shangchu = (TextView) get(R.id.shangchu);

        SharedPreferences config = ((Zhuyemian) context).getSharedPreferences("config", Context.MODE_PRIVATE);
        uid = config.getString("uid", "");
        doHttp();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        mRecyclerView.setLayoutManager(linearLayoutManager);
        mShopSellerAdapter = new GouwucheAdapter(context);
        mRecyclerView.setAdapter(mShopSellerAdapter);

        get(R.id.layout_all).setOnClickListener(this);
        shangchu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                detele();
            }

        });

        mShopSellerAdapter.setListener(new GouwucheAdapter.ShopCallBackListener() {
            @Override
            public void callBack(List<GouBean.DataBean> list) {
                double allPrice = 0;
                int num = 0;
                int numAll = 0;
                for (int a = 0; a < list.size(); a++) {
                    List<GouBean.DataBean.ListBean> listAll = list.get(a).getList();//获取商家里商品
                    for (int i = 0; i < listAll.size(); i++) {
                        numAll = numAll + listAll.get(i).getNum();
                        if (listAll.get(i).isCheck()) {//取选中的状态
                            allPrice = allPrice + (listAll.get(i).getPrice() * listAll.get(i).getNum());
                            num = num + listAll.get(i).getNum();
                        }
                    }
                }
                if (num < numAll) {//不是全部选中
                    ivCricle.setImageResource(R.drawable.cricle_no);
                    isClick = true;
                } else {
                    //是全部选中
                    ivCricle.setImageResource(R.drawable.cricle_yes);
                    isClick = false;
                }
                //改变价格和个数
                String format = String.format("%.2f", allPrice);
                allPriceTxt.setText("合计：" + format);
                sunPrice.setText("去结算(" + num + ")");
            }
        });


    }

    private void detele() {
        for (int a = 0; a < data1.size(); a++) {
            List<GouBean.DataBean.ListBean> listAll = data1.get(a).getList();//获取商家里商品
            for (int i = 0; i < listAll.size(); i++) {
                if (listAll.get(i).isCheck()) {//取选中的状态
                    new HttpHelper().get("http://www.zhaoapi.cn/product/deleteCart?uid=" + uid + "&pid=" + listAll.get(i).getPid()).result(new HttpHelper.HttpListener() {
                        @Override
                        public void success(String data) {
                            Toast.makeText(context, data, Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void fail(String error) {

                        }
                    });
                }
            }
            doHttp();
            mShopSellerAdapter.notifyDataSetChanged();
        }
    }

    private void doHttp() {

        if (TextUtils.isEmpty(uid)) {
            Toast.makeText(context, "拜拜小可爱", Toast.LENGTH_LONG).show();
        } else {
            String url = "http://www.zhaoapi.cn/product/getCarts?uid=" + uid;
            new HttpHelper().get(url).result(new HttpHelper.HttpListener() {
                @Override
                public void success(String data) {
                    if (data.contains("<")) {
                        doHttp();
                        return;
                    }
                    GouBean gouBean = new Gson().fromJson(data, GouBean.class);
                    data1 = gouBean.getData();
                    // Log.i("data",data);
                    // data1.remove(0);
                    mShopSellerAdapter.setList(data1);
                }

                @Override
                public void fail(String error) {

                }
            });
        }
    }

    private Context context;

    @Override
    public void getContext(Context context) {
        this.context = context;
    }

    private boolean isClick = true;

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.layout_all://全选
                if (isClick) {
                    ivCricle.setImageResource(R.drawable.cricle_yes);
                    isClick = false;
                    checkSeller(true);
                } else {
                    ivCricle.setImageResource(R.drawable.cricle_no);
                    isClick = true;
                    checkSeller(false);
                }
                break;
        }
    }

    private void checkSeller(boolean bool) {
        double allPrice = 0;
        int num = 0;
        for (int a = 0; a < data1.size(); a++) {
            data1.get(a).setCheck(bool);
            List<GouBean.DataBean.ListBean> listAll = data1.get(a).getList();
            for (int i = 0; i < listAll.size(); i++) {
                listAll.get(i).setCheck(bool);
                allPrice = allPrice + (listAll.get(i).getPrice() * listAll.get(i).getNum());
                num = num + listAll.get(i).getNum();
            }
        }

        if (bool) {
            String format = String.format("%.2f", allPrice);
            allPriceTxt.setText("合计：" + format);
            sunPrice.setText("去结算(" + num + ")");
        } else {
            allPriceTxt.setText("合计：0.00");
            sunPrice.setText("去结算(0)");
        }
        mShopSellerAdapter.notifyDataSetChanged();
    }

    @Override
    public void onresume() {
        super.onresume();
        doHttp();
        mShopSellerAdapter.notifyDataSetChanged();
    }


}
