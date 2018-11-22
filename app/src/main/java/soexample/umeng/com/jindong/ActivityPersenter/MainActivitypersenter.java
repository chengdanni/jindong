package soexample.umeng.com.jindong.ActivityPersenter;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import soexample.umeng.com.jindong.Activity.MainActivity;
import soexample.umeng.com.jindong.R;
import soexample.umeng.com.jindong.Activity.Zhuyemian;
import soexample.umeng.com.jindong.mvp.view.AgeInter;

public class MainActivitypersenter extends AgeInter {
    private TextView textView;
    private static final int MAG = 123;
    private int i = 3;
    private MyHanlder myHanlder = new MyHanlder();

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    private Context context;

    @Override
    public void getContext(Context context) {
        this.context = context;
    }

    @Override
    public void initdata() {
        super.initdata();
        textView = (TextView) get(R.id.tiao);
        myHanlder.sendEmptyMessageDelayed(0, 1000);
    }

    class MyHanlder extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            i--;
            textView.setText(i + "s");
            if (i <1) {
                myHanlder.removeCallbacksAndMessages(null);
                context.startActivity(new Intent(context, Zhuyemian.class));
                ((MainActivity) context).finish();
            } else {
                myHanlder.sendEmptyMessageDelayed(0, 1000);
            }
        }
    }

}
