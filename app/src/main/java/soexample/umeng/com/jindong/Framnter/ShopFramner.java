package soexample.umeng.com.jindong.Framnter;

import soexample.umeng.com.jindong.FramnterPersenter.ShopFramnerpersenter;
import soexample.umeng.com.jindong.mvp.persenter.BseaFramnerpersenter;

public class ShopFramner extends BseaFramnerpersenter<ShopFramnerpersenter> {
    @Override
    public Class<ShopFramnerpersenter> getClassAgeid() {
        return ShopFramnerpersenter.class;
    }

    @Override
    public void onResume() {
        super.onResume();
        delde.onresume();
    }
}
