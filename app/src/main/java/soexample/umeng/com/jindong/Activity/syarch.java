package soexample.umeng.com.jindong.Activity;

import soexample.umeng.com.jindong.ActivityPersenter.Syarchpersenter;
import soexample.umeng.com.jindong.mvp.persenter.BseaAcvitypersenter;

public class syarch  extends BseaAcvitypersenter<Syarchpersenter>{
    @Override
    public Class<Syarchpersenter> getClassAgeid() {
        return Syarchpersenter.class;
    }
}
