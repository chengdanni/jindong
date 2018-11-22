package soexample.umeng.com.jindong.Activity;

import soexample.umeng.com.jindong.ActivityPersenter.Zhuyemianpersenter;
import soexample.umeng.com.jindong.mvp.persenter.BseaAcvitypersenter;

public class Zhuyemian extends BseaAcvitypersenter<Zhuyemianpersenter> {
    @Override
    public Class<Zhuyemianpersenter> getClassAgeid() {
        return Zhuyemianpersenter.class;
    }

}
