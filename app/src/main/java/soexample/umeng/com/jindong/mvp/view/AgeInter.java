package soexample.umeng.com.jindong.mvp.view;

import android.content.Context;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class AgeInter implements IDetele {

    private View rooView;

    @Override
    public void initdata() {

    }

    private SparseArray<View> views = new SparseArray<>();

    public <T extends View> T get(int id) {
        T view = (T) views.get(id);
        if (view == null) {
            view = rooView().findViewById(id);
            views.put(id, view);
        }
        return view;
    }

    public void setCilck(View.OnClickListener listener, int... ids) {
        if (ids == null) {
            return;
        }
        for (int id : ids) {
            get(id).setOnClickListener(listener);
        }
    }

    @Override
    public void craete(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle) {
        rooView = inflater.inflate(getLayoutId(), viewGroup, false);
    }

    @Override
    public View rooView() {
        return rooView;
    }

    private Context context;
    @Override
    public void getContext(Context context) {
       this.context=context;
    }

    public abstract int getLayoutId();

    public void onresume() {
    }
}
