package soexample.umeng.com.jindong.Activity;

import soexample.umeng.com.jindong.ActivityPersenter.loginpersenter;
import soexample.umeng.com.jindong.mvp.persenter.BseaAcvitypersenter;

public class login extends BseaAcvitypersenter<loginpersenter> {


    @Override
    public Class<loginpersenter> getClassAgeid() {
        return loginpersenter.class;
    }
}
