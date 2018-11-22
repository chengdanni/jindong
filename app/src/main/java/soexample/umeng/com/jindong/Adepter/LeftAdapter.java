package soexample.umeng.com.jindong.Adepter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import soexample.umeng.com.jindong.Bean.JiuBean;
import soexample.umeng.com.jindong.R;

public class LeftAdapter extends RecyclerView.Adapter<LeftAdapter.nvViewHolder> {
    private List<JiuBean.DataBean> list = new ArrayList<>();
    private Context context;

    public LeftAdapter(List<JiuBean.DataBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public nvViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.nv, null);
        LeftAdapter.nvViewHolder vi = new LeftAdapter.nvViewHolder(view);
        return vi;

    }

    @Override
    public void onBindViewHolder(nvViewHolder holder, final int position) {
        holder.textView.setText(list.get(position).getName());
//        String images = list.get(position).getIcon();
//        String[] split = images.split("\\|");
        // Picasso.with(context).load(split[0]).fit().into(holder.imageView);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(context, list.get(position).getList() + "", Toast.LENGTH_SHORT).show();
                tablistener.setTabList(list.get(position).getCid());
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class nvViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;

        public nvViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.text_nv);
        }
    }


    public Tablistener tablistener;

    public void result(Tablistener tablistener) {
        this.tablistener = tablistener;
    }

    public interface Tablistener {
        void setTabList(int cid);
    }


}
