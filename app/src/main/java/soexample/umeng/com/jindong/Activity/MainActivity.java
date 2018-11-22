package soexample.umeng.com.jindong.Activity;

import soexample.umeng.com.jindong.ActivityPersenter.MainActivitypersenter;
import soexample.umeng.com.jindong.mvp.persenter.BseaAcvitypersenter;

public class MainActivity extends BseaAcvitypersenter<MainActivitypersenter> {


    @Override
    public Class<MainActivitypersenter> getClassAgeid() {
        return MainActivitypersenter.class;
    }

}
