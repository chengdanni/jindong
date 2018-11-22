package soexample.umeng.com.jindong.Framnter;

import soexample.umeng.com.jindong.FramnterPersenter.ListFreamerpersenter;
import soexample.umeng.com.jindong.mvp.persenter.BseaFramnerpersenter;

public class LeibiaoFreamer extends BseaFramnerpersenter<ListFreamerpersenter> {
    @Override
    public Class<ListFreamerpersenter> getClassAgeid() {
        return ListFreamerpersenter.class;
    }
}
