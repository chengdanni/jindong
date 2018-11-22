package soexample.umeng.com.jindong.mvp.persenter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import soexample.umeng.com.jindong.mvp.view.AgeInter;


public abstract class BseaFramnerpersenter<T extends AgeInter> extends Fragment {
    public T delde;

    public abstract Class<T> getClassAgeid();

    public BseaFramnerpersenter() {
        try {
            delde = getClassAgeid().newInstance();
        } catch (java.lang.InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        delde.craete(inflater, container, savedInstanceState);
        delde.getContext(getActivity());
        delde.initdata();
        return delde.rooView();
    }

}
