package soexample.umeng.com.jindong.Fluidlayout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import soexample.umeng.com.jindong.Bean.Lishi;
import soexample.umeng.com.jindong.R;

public class GoodView extends RelativeLayout {

    private LinearLayout mLyoutV;
    private View view;

    public GoodView(Context context) {
        super(context);
        init(context);
    }

    public GoodView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public GoodView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }
    private Context context;

    private void init(Context context) {
        this.context = context;
        //创建垂直的LinearLayout
        view = View.inflate(context, R.layout.good_v, null);
        mLyoutV = (LinearLayout) view.findViewById(R.id.lyout_v);
        //创建水平的
        addView(view);

    }
    public void setList(List<Lishi.DatasBean> list) {
        mLyoutV.removeAllViews();
        //创建水平layout
        LinearLayout h = (LinearLayout) View.inflate(context, R.layout.good_h, null);
        mLyoutV.addView(h);
        int len = 0;
        h.removeAllViews();
        for (int a = 0; a < list.size(); a++) {
            String msg = list.get(a).getName();
            len += msg.length();
            if (len > 22) {
                h = (LinearLayout) View.inflate(context, R.layout.good_h, null);
                mLyoutV.addView(h);
                len = 0;
            }
            //创建展示内容的layout
            View viewContent = View.inflate(context, R.layout.good_center, null);
            TextView textView = (TextView) viewContent.findViewById(R.id.tv_content);
            textView.setText(msg);
            h.addView(viewContent);

            //设置parmars
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) viewContent.getLayoutParams();
            params.weight = 1;
            params.leftMargin = 5;
            params.topMargin = 5;
            viewContent.setLayoutParams(params);
        }
    }

}
