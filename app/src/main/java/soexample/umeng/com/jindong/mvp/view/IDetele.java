package soexample.umeng.com.jindong.mvp.view;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public interface IDetele {

    void initdata();

    void craete(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle);

    View rooView();

    void getContext(Context context);

}
