package soexample.umeng.com.jindong.ActivityPersenter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import soexample.umeng.com.jindong.Activity.Details;
import soexample.umeng.com.jindong.Activity.Zhuyemian;
import soexample.umeng.com.jindong.Bean.Tianbbean;
import soexample.umeng.com.jindong.Bean.Xiangbean;
import soexample.umeng.com.jindong.Framnter.ShopFramner;
import soexample.umeng.com.jindong.FramnterPersenter.ShopFramnerpersenter;
import soexample.umeng.com.jindong.R;
import soexample.umeng.com.jindong.mvp.view.AgeInter;
import soexample.umeng.com.jindong.net.HttpHelper;

public class Detailsqingpersenter extends AgeInter {

    private TextView textView;
    private String pid;
    private ImageView img;
    private Button tian;
    private String mobile;
    private String uid;
    private ImageView gwc;

    @Override
    public int getLayoutId() {
        return R.layout.xiangqing;
    }

    private Context context;

    @Override
    public void getContext(Context context) {
        this.context = context;
    }

    @Override
    public void initdata() {
        super.initdata();
        textView = (TextView) get(R.id.xia);
        img = (ImageView) get(R.id.img);
        gwc = (ImageView) get(R.id.gwc);
        tian = (Button) get(R.id.tianjia);
        gwc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, Zhuyemian.class));

            }
        });
        tian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences config = ((Details) context).getSharedPreferences("config", Context.MODE_PRIVATE);
                mobile = config.getString("mobile", "");
                String token = config.getString("token", "");
                uid = config.getString("uid", "");
                dohttp();
            }
        });
        Intent intent = ((Details) context).getIntent();
        pid = intent.getStringExtra("pid");
        textView.setText(pid);
        doHttp();

    }
    private void dohttp() {
        SharedPreferences config = ((Details) context).getSharedPreferences("config", Context.MODE_PRIVATE);
        String uid = config.getString("uid", "");
        String url="http://www.zhaoapi.cn/product/addCart?"+"pid="+pid+"&uid="+uid;
        new HttpHelper().get(url).result(new HttpHelper.HttpListener() {
            @Override
            public void success(String data) {
                Tianbbean bean = new Gson().fromJson(data, Tianbbean.class);
                Toast.makeText(context, bean.getMsg(), Toast.LENGTH_SHORT).show();
            }
            @Override
            public void fail(String error) {

            }
        });
    }

    private void doHttp() {
        String uel = "http://www.zhaoapi.cn/product/getProductDetail?pid=" + pid;
        new HttpHelper().get(uel).result(new HttpHelper.HttpListener() {
            @Override
            public void success(String data) {
                Xiangbean xiangbean = new Gson().fromJson(data, Xiangbean.class);
                Xiangbean.DataBean data1 = xiangbean.getData();

                String images = data1.getImages();
                String[] split = images.split("\\|");
                Picasso.with(context).load(split[0]).fit().into(img);
                textView.setText(data1.getTitle());
            }

            @Override
            public void fail(String error) {

            }
        });
    }




}
