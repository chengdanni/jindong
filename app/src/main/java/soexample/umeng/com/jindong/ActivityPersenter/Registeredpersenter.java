package soexample.umeng.com.jindong.ActivityPersenter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import soexample.umeng.com.jindong.Activity.login;
import soexample.umeng.com.jindong.Bean.ZhuBean;
import soexample.umeng.com.jindong.R;
import soexample.umeng.com.jindong.mvp.view.AgeInter;
import soexample.umeng.com.jindong.net.HttpHelper;

public class Registeredpersenter extends AgeInter {
    private String name, pass;
    private EditText mName, mPass;

    @Override
    public int getLayoutId() {
        return R.layout.zhuce;
    }

    @Override
    public void initdata() {
        super.initdata();
        mName = get(R.id.et_name_insert);
        mPass = get(R.id.et_pass_insert);
        setCilck(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insert();
            }
        }, R.id.bt_insert_insert);

    }

    private Context context;

    @Override
    public void getContext(Context context) {
        this.context = context;
    }

    private void insert() {
        name = mName.getText().toString();
        pass = mPass.getText().toString().trim();
        if (name.length() != 11 || pass.length() < 6) {
            Toast.makeText(context, "请输入正确的手机号或密码", Toast.LENGTH_SHORT).show();
            return;
        }
        new HttpHelper().get("http://www.zhaoapi.cn/user/reg?mobile=" + name + "&password=" + pass).result(new HttpHelper.HttpListener() {
            @Override
            public void success(String data) {
                Gson gson = new Gson();
                ZhuBean beanLogin = gson.fromJson(data, ZhuBean.class);
                String code = beanLogin.getCode();
                if ("1".equals(code)) {
                    Toast.makeText(context, beanLogin.getMsg(), Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent = new Intent(context, login.class);
                context.startActivity(intent);
            }

            @Override
            public void fail(String error) {

            }
        });
    }

}
