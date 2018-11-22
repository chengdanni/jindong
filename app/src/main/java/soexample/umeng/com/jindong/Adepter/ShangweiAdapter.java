package soexample.umeng.com.jindong.Adepter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import soexample.umeng.com.jindong.Bean.ShangBean;
import soexample.umeng.com.jindong.Listliner.Pidds;
import soexample.umeng.com.jindong.R;

public class ShangweiAdapter extends RecyclerView.Adapter<ShangweiAdapter.SviewHolder> {
    private List<ShangBean.DataBean> list = new ArrayList<>();
    private Context context;


    public ShangweiAdapter(Context context, List<ShangBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public SviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = View.inflate(context, R.layout.shangwei, null);
            SviewHolder vi = new SviewHolder(view);
            return vi;
    }

    @Override
    public void onBindViewHolder(SviewHolder holder, final int position) {
        Log.i("ggg",list.get(position).getSellerName());
        holder.sellerName.setText(list.get(position).getSellerName());//设置商家的名字
        ShangAdapter gouwuche = new ShangAdapter(context, list.get(position).getList());
        StaggeredGridLayoutManager linearLayoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        holder.mRecyclerView.setLayoutManager(linearLayoutManager);
        holder.mRecyclerView.setAdapter(gouwuche);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class SviewHolder  extends RecyclerView.ViewHolder{

        private final TextView sellerName;
        private final RecyclerView mRecyclerView;


        public SviewHolder(View itemView) {
            super(itemView);
            sellerName = (TextView) itemView.findViewById(R.id.name);
            mRecyclerView = (RecyclerView) itemView.findViewById(R.id.view);

        }
    }
}
