package soexample.umeng.com.jindong.ActivityPersenter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import soexample.umeng.com.jindong.Activity.login;
import soexample.umeng.com.jindong.Activity.registered;
import soexample.umeng.com.jindong.Bean.DenBean;
import soexample.umeng.com.jindong.R;
import soexample.umeng.com.jindong.mvp.view.AgeInter;
import soexample.umeng.com.jindong.net.HttpHelper;

public class loginpersenter extends AgeInter {
    @Override
    public int getLayoutId() {
        return R.layout.denglu;
    }
    private EditText mName;
    private EditText mPass;
    private SharedPreferences sp;
    private String name;
    private String pass;

    private Context context;

    @Override
    public void getContext(Context context) {
        this.context = context;
    }

    @Override
    public void initdata() {
        sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        mName = get(R.id.zhanghao);
        mPass = get(R.id.mima);
        setCilck(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Intent intent = new Intent(context, Zhuce.class);
                context.startActivity(new Intent(context,registered.class));
            }
        }, R.id.tv_insert_login);
        setCilck(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        }, R.id.bt_login_login);

    }

    private void login() {
        name = mName.getText().toString();
        pass = mPass.getText().toString().trim();
        if (name.length() != 11 || pass.length() < 6) {
            Toast.makeText(context, "请输入正确的手机号或密码", Toast.LENGTH_SHORT).show();
            return;
        }
        new HttpHelper().get("http://www.zhaoapi.cn/user/login?mobile=" + name + "&password=" + pass).result(new HttpHelper.HttpListener() {
            @Override
            public void success(String data) {
                Gson gson = new Gson();
                DenBean beanLogin = gson.fromJson(data, DenBean.class);
                String code = beanLogin.getCode();
                if ("1".equals(code)) {
                    Toast.makeText(context, beanLogin.getMsg(), Toast.LENGTH_SHORT).show();
                    return;
                }
                String token = beanLogin.getData().getToken();
                int uid = beanLogin.getData().getUid();
                Log.i("nnnn","uid"+uid);
                sp.edit().putString("uid",uid+"").putString("mobile", name).putString("password", pass).putString("token", token).putBoolean("islogin", true).commit();
                ((login)context).finish();

            }

            @Override
            public void fail(String error) {

            }
        });
    }
}
