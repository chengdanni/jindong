package soexample.umeng.com.jindong.Activity;

import soexample.umeng.com.jindong.ActivityPersenter.Detailsqingpersenter;
import soexample.umeng.com.jindong.mvp.persenter.BseaAcvitypersenter;

public class Details extends BseaAcvitypersenter<Detailsqingpersenter> {
    @Override
    public Class<Detailsqingpersenter> getClassAgeid() {
        return Detailsqingpersenter.class;
    }
}
