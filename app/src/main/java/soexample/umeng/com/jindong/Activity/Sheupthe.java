package soexample.umeng.com.jindong.Activity;

import android.content.Intent;

import soexample.umeng.com.jindong.ActivityPersenter.Sheupthepersenter;
import soexample.umeng.com.jindong.mvp.persenter.BseaAcvitypersenter;

public class Sheupthe extends BseaAcvitypersenter<Sheupthepersenter> {
    @Override
    public Class<Sheupthepersenter> getClassAgeid() {
        return Sheupthepersenter.class;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        delde.onActvity(requestCode, resultCode, data);
    }


}
