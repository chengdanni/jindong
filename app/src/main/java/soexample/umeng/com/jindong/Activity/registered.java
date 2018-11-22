package soexample.umeng.com.jindong.Activity;

import soexample.umeng.com.jindong.ActivityPersenter.Registeredpersenter;
import soexample.umeng.com.jindong.mvp.persenter.BseaAcvitypersenter;

public class registered extends BseaAcvitypersenter<Registeredpersenter> {
    @Override
    public Class<Registeredpersenter> getClassAgeid() {
        return Registeredpersenter.class;
    }
}
