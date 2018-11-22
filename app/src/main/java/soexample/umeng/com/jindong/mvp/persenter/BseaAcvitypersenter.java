package soexample.umeng.com.jindong.mvp.persenter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import soexample.umeng.com.jindong.mvp.view.AgeInter;


public abstract class BseaAcvitypersenter<T extends AgeInter>extends AppCompatActivity {
    public T delde;

    public abstract Class<T> getClassAgeid();

    public BseaAcvitypersenter() {
        try {
            delde = getClassAgeid().newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        delde.craete(getLayoutInflater(), null, savedInstanceState);
        setContentView(delde.rooView());
        delde.getContext(this);
        Butt
        delde.initdata();

    }
}
