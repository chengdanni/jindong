package soexample.umeng.com.jindong.FramnterPersenter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import soexample.umeng.com.jindong.Activity.Sheupthe;
import soexample.umeng.com.jindong.Activity.login;
import soexample.umeng.com.jindong.Activity.Waterlines;
import soexample.umeng.com.jindong.Fluidlayout.Fluid;
import soexample.umeng.com.jindong.R;
import soexample.umeng.com.jindong.mvp.view.AgeInter;

public class MiFramnterpersenter extends AgeInter {

    private ImageView she;
    private ImageView imageView;

    @Override
    public int getLayoutId() {
        return R.layout.mi;
    }

    @Override
    public void initdata() {
        super.initdata();
        imageView = (ImageView) get(R.id.img);
        she = (ImageView) get(R.id.shizhi);
        Waterlines mWaterView = (Waterlines) get(R.id.wave_view);
        final RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) imageView.getLayoutParams();
        mWaterView.animation(new Waterlines.AnimationListener() {
            @Override
            public void animation(float y) {
                params.setMargins(0, 0, 0, (int) y);
                imageView.setLayoutParams(params);
            }
        });
        she.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, Sheupthe.class));
            }
        });
        get(R.id.img).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, login.class));
            }
        });
        get(R.id.liu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, Fluid.class));
            }
        });

    }

    private Context context;

    @Override
    public void getContext(Context context) {
        this.context = context;
    }

}
