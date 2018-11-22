package soexample.umeng.com.jindong.Adepter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import soexample.umeng.com.jindong.Bean.GouBean;
import soexample.umeng.com.jindong.R;

public class GouwucheAdapter extends RecyclerView.Adapter<GouwucheAdapter.mViewHolder> {
    private List<GouBean.DataBean> list = new ArrayList<>();
    private Context context;

    public GouwucheAdapter(Context context) {
        this.context = context;
    }

    @Override
    public GouwucheAdapter.mViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View view = View.inflate(context, R.layout.gouwuche, null);
        mViewHolder myViewHoler = new mViewHolder(view);
        return myViewHoler;
    }

    @Override
    public void onBindViewHolder(final GouwucheAdapter.mViewHolder holder, final int i) {
        Log.i("ccc",i+""+list.size());
        holder.sellerName.setText(list.get(i).getSellerName());//设置商家的名字
        GouwuShop gouwuche = new GouwuShop(context, list.get(i).getList());
        if(list.get(i).isCheck()){
            holder.img.setImageResource(R.drawable.cricle_yes);
        }else{
            holder.img.setImageResource(R.drawable.cricle_no);
        }
        final List<GouBean.DataBean.ListBean> lista = this.list.get(i).getList();
        holder.img.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if(list.get(i).isCheck()){
                    list.get(i).setCheck(false);
                    for(int a=0;a<lista.size();a++){
                        lista.get(a).setCheck(false);

                    }
                }else{
                   list.get(i).setCheck(true);
                    for(int a=0;a<lista.size();a++){
                        lista.get(a).setCheck(true);
                    }
                }
               notifyItemChanged(i);
                listener.callBack(list);
            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        holder.mRecyclerView.setLayoutManager(linearLayoutManager);
        holder.mRecyclerView.setAdapter(gouwuche);
        gouwuche.setListener(new GouwuShop.ShopCallBackListener() {
            @Override
            public void callBack() {
                //从商品适配里回调回来
                listener.callBack(GouwucheAdapter.this.list);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setList(List<GouBean.DataBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }


    public class mViewHolder extends RecyclerView.ViewHolder {

       ImageView img;
        TextView sellerName;
        RecyclerView mRecyclerView;

        public mViewHolder(View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.imgyuan);
            sellerName = (TextView) itemView.findViewById(R.id.seller_name);
            mRecyclerView = (RecyclerView) itemView.findViewById(R.id.seller_recyclerview);
        }
    }

    private ShopCallBackListener listener;

    public void setListener(ShopCallBackListener listener) {
        this.listener = listener;
    }

    public interface ShopCallBackListener {
        void callBack(List<GouBean.DataBean> list);
    }


}
