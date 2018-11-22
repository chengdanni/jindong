package soexample.umeng.com.jindong.Framnter;

import soexample.umeng.com.jindong.FramnterPersenter.MiFramnterpersenter;
import soexample.umeng.com.jindong.mvp.persenter.BseaFramnerpersenter;

public class MiFramnter extends BseaFramnerpersenter<MiFramnterpersenter> {
    @Override
    public Class<MiFramnterpersenter> getClassAgeid() {
        return MiFramnterpersenter.class;
    }
}
