package soexample.umeng.com.jindong.Adepter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import soexample.umeng.com.jindong.Bean.LeibiaoBean;
import soexample.umeng.com.jindong.R;

public class Thirdpage extends RecyclerView.Adapter<Thirdpage.ZinvrightPersenter> {
    private List<LeibiaoBean.DataBean.ListBean> list = new ArrayList<>();
    private Context context;

    public Thirdpage(List<LeibiaoBean.DataBean.ListBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public ZinvrightPersenter onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.zi, null);
        ZinvrightPersenter vi = new ZinvrightPersenter(view);
        return vi;

    }

    @Override
    public void onBindViewHolder(ZinvrightPersenter holder, int position) {
        holder.textView.setText(list.get(position).getName());
        String images = list.get(position).getIcon();
        String[] split = images.split("\\|");
        Picasso.with(context).load(list.get(position).getIcon()).fit().into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ZinvrightPersenter extends RecyclerView.ViewHolder {

        private final ImageView imageView;
        private final TextView textView;

        public ZinvrightPersenter(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.goods_pic);
            textView = (TextView) itemView.findViewById(R.id.tu_rigth);
        }
    }
}
