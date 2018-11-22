package soexample.umeng.com.jindong.Fluidlayout;

import soexample.umeng.com.jindong.mvp.persenter.BseaAcvitypersenter;

public class Fluid extends BseaAcvitypersenter<FluidPersenter> {
    @Override
    public Class<FluidPersenter> getClassAgeid() {
        return FluidPersenter.class;
    }
}
