package soexample.umeng.com.jindong.ActivityPersenter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import soexample.umeng.com.jindong.Adepter.LeftAdapter;
import soexample.umeng.com.jindong.Adepter.SyarchAdapter;
import soexample.umeng.com.jindong.Bean.syarchbean;
import soexample.umeng.com.jindong.R;
import soexample.umeng.com.jindong.mvp.view.AgeInter;
import soexample.umeng.com.jindong.net.HttpHelper;

public class Syarchpersenter extends AgeInter {

    private RecyclerView aou;
    private SyarchAdapter myAdapler;
    private List<syarchbean.DataBean> data1=new ArrayList<>();
    private Button bsou;
    private EditText text;

    @Override
    public int getLayoutId() {
        return R.layout.syarch;
    }

    @Override
    public void initdata() {
        super.initdata();
        aou = (RecyclerView) get(R.id.sou);
        bsou = (Button) get(R.id.bsou);
        text = (EditText) get(R.id.text);
        bsou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(text.getText().toString()!=null){
                    okhttp(text.getText().toString());
                }
            }
        });

    }

    private void okhttp(String text) {
        String url="http://www.zhaoapi.cn/product/searchProducts?keywords="+text+"&page=1";
        new HttpHelper().get(url).result(new HttpHelper.HttpListener() {
            @Override
            public void success(String data) {
                syarchbean bean = new Gson().fromJson(data, syarchbean.class);
                data1 = bean.getData();
                myAdapler = new SyarchAdapter(data1, context);
                StaggeredGridLayoutManager s = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
                aou.setLayoutManager(s);
                aou.setAdapter(myAdapler);

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
