package soexample.umeng.com.jindong.Framnter;

import soexample.umeng.com.jindong.FramnterPersenter.pageFramnterPersenter;
import soexample.umeng.com.jindong.mvp.persenter.BseaFramnerpersenter;

public class ShouyeFramnter extends BseaFramnerpersenter<pageFramnterPersenter> {
    @Override
    public Class<pageFramnterPersenter> getClassAgeid() {
        return pageFramnterPersenter.class;
    }
}
