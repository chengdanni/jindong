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

import soexample.umeng.com.jindong.Bean.JiuBean;
import soexample.umeng.com.jindong.R;

public class JiuAdapter extends RecyclerView.Adapter<JiuAdapter.viewHoler>{
    private List<JiuBean.DataBean> list = new ArrayList<>();
    private Context context;

    public JiuAdapter(List<JiuBean.DataBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public JiuAdapter.viewHoler onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.jiu, null);
        viewHoler vi = new viewHoler(view);
        return vi;
    }

    @Override
    public void onBindViewHolder(JiuAdapter.viewHoler holder, int position) {
        holder.textView.setText(list.get(position).getName());
        String images = list.get(position).getIcon();
        String[] split = images.split("\\|");
        Picasso.with(context).load(split[0]).fit().into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHoler extends RecyclerView.ViewHolder {

        private final ImageView imageView;
        private final TextView textView;

        public viewHoler(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.imng);
            textView = (TextView) itemView.findViewById(R.id.text);
        }
    }
}
