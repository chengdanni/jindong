package soexample.umeng.com.jindong.FramnterPersenter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import soexample.umeng.com.jindong.Activity.syarch;
import soexample.umeng.com.jindong.Adepter.LeftAdapter;
import soexample.umeng.com.jindong.Adepter.Rigterfemale;
import soexample.umeng.com.jindong.Bean.JiuBean;
import soexample.umeng.com.jindong.Bean.LeibiaoBean;
import soexample.umeng.com.jindong.R;
import soexample.umeng.com.jindong.mvp.view.AgeInter;
import soexample.umeng.com.jindong.net.HttpHelper;

public class ListFreamerpersenter extends AgeInter {
    private RecyclerView listView;
    private RecyclerView xlistview;
    private LeftAdapter myAdapler;
    private TextView te;

    @Override
    public int getLayoutId() {
        return R.layout.leibiao;
    }

    @Override
    public void initdata() {
        super.initdata();
        listView = (RecyclerView) get(R.id.listview);
        xlistview = (RecyclerView) get(R.id.xlistview);
        te = (TextView) get(R.id.goods_txt);
        te.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context,syarch.class));
            }
        });
        okHttp();
    }

    private void okHttp() {
        String lei = "http://www.zhaoapi.cn/product/getCatagory";
        new HttpHelper().get(lei).result(new HttpHelper.HttpListener() {
            List<JiuBean.DataBean> data2 = new ArrayList<>();
            @Override
            public void success(String data) {
                JiuBean tiao = new Gson().fromJson(data, JiuBean.class);
                data2 = tiao.getData();
                myAdapler = new LeftAdapter(data2, context);
                StaggeredGridLayoutManager s = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
                listView.setLayoutManager(s);
                listView.setAdapter(myAdapler);
                myAdapler.result(new LeftAdapter.Tablistener() {
                    @Override
                    public void setTabList(int cid) {
                        new HttpHelper().get("http://www.zhaoapi.cn/product/getProductCatagory?cid="+cid).result(new HttpHelper.HttpListener() {
                            @Override
                            public void success(String data) {
                                LeibiaoBean bean = new Gson().fromJson(data, LeibiaoBean.class);
                                List<LeibiaoBean.DataBean> data1 = bean.getData();
                                Toast.makeText(context, "==", Toast.LENGTH_SHORT).show();
                                Rigterfemale nvRigter = new Rigterfemale(data1,context);
                                LinearLayoutManager layoutManager = new LinearLayoutManager(context);
                                layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                                xlistview.setLayoutManager(layoutManager);
                                xlistview.setAdapter(nvRigter);
                            }

                            @Override
                            public void fail(String error) {

                            }
                        });
                    }
                });

            }

            @Override
            public void fail(String error) {
            }
        });
    }

    private Context context;

    @Override
    public void getContext(Context context) {
        this.context = context;
    }

}
