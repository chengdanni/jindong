package soexample.umeng.com.jindong.Adepter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import soexample.umeng.com.jindong.Bean.LeibiaoBean;
import soexample.umeng.com.jindong.R;

public class Rigterfemale extends RecyclerView.Adapter<Rigterfemale.mViewHolder> {
    private List<LeibiaoBean.DataBean> list = new ArrayList<>();
    private Context context;

    public Rigterfemale(List<LeibiaoBean.DataBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public mViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.nvriget, null);
        mViewHolder vi = new mViewHolder(view);
        return vi;
    }

    @Override
    public void onBindViewHolder(mViewHolder holder, int position) {
        holder.recyclerView.setLayoutManager(new GridLayoutManager(context,3));
        List<LeibiaoBean.DataBean.ListBean> list1 = this.list.get(position).getList();
        holder.recyclerView.setAdapter(new Thirdpage(list1,context));
        holder.textView.setText(list.get(position).getName());
        //  String images = list.get(position).getIcon();
        //      String[] split = images.split("\\|");
      //  Picasso.with(context).load(list.get(position).getList().get(0).getIcon()).fit().into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class mViewHolder extends RecyclerView.ViewHolder {
        private final RecyclerView recyclerView;

//        private final ImageView imageView;
         private final TextView textView;

        public mViewHolder(View itemView) {
            super(itemView);
//            imageView = (ImageView) itemView.findViewById(R.id.goods_pic);
            textView = (TextView) itemView.findViewById(R.id.text_title);
            recyclerView = (RecyclerView) itemView.findViewById(R.id.view);
        }
    }

}
