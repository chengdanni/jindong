package soexample.umeng.com.jindong.Fluidlayout;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;

import soexample.umeng.com.jindong.Bean.Lishi;
import soexample.umeng.com.jindong.R;
import soexample.umeng.com.jindong.mvp.view.AgeInter;
import soexample.umeng.com.jindong.net.HttpHelper;

public class FluidPersenter extends AgeInter implements View.OnClickListener{

    private GoodView mGoodsView;
    private GoodView mGoodsViewB;
    private EditText mEditText;

    @Override
    public int getLayoutId() {
        return R.layout.goog;
    }

    @Override
    public void initdata() {
        super.initdata();
        mGoodsView = (GoodView) get(R.id.goods_view_top);
        mGoodsViewB = (GoodView) get(R.id.goods_view_bottom);
        mEditText = (EditText) get(R.id.goods_txt);

       // doHttpHot();

        setCilck(this,R.id.seach_msg);

    }

    private  Context context;
    @Override
    public void getContext(Context context) {
        this.context=context;
    }

    List<Lishi.DatasBean> list = new ArrayList<>();
    private void doHttpHot() {
        String url="http://ftp6252741.host709.zhujiwu.me/goods/goods_hot.txt";
        new HttpHelper().get(url).result(new HttpHelper.HttpListener() {
            @Override
            public void success(String data) {
                Lishi lishi = new Gson().fromJson(data, Lishi.class);
                List<Lishi.DatasBean> datas = lishi.getDatas();
                mGoodsView.setList(datas);
            }
            @Override
            public void fail(String error) {

            }

        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.seach_msg://搜索
                String messs = mEditText.getText().toString().trim();
                if (TextUtils.isEmpty(messs)) {
                    Toast.makeText(context, "请输入要搜索的商品", Toast.LENGTH_SHORT).show();
                    return;
                }
                Lishi.DatasBean bean = new Lishi.DatasBean();
                bean.setName(messs);
                list.add(bean);
                mGoodsViewB.setList(list);
                break;
        }
    }
}
