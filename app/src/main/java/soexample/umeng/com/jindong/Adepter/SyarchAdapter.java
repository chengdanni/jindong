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

import soexample.umeng.com.jindong.Bean.syarchbean;
import soexample.umeng.com.jindong.R;

public class SyarchAdapter extends RecyclerView.Adapter<SyarchAdapter.sviewholder> {
    private List<syarchbean.DataBean> list = new ArrayList<>();
    private Context context;

    public SyarchAdapter(List<syarchbean.DataBean> list, Context context) {
        this.list = list;
        this.context = context;
    }
    @Override
    public sviewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.starch, null);
        sviewholder vi = new sviewholder(view);
        return vi;
    }

    @Override
    public void onBindViewHolder(sviewholder holder, int position) {
        holder.textView.setText(list.get(position).getTitle());
        String images = list.get(position).getImages();
        String[] split = images.split("\\|");
        //  holder.imageView.setImageURI(split[0]);
        Picasso.with(context).load(split[0]).fit().into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class sviewholder extends RecyclerView.ViewHolder{
        private final ImageView imageView;
        private final TextView textView;

        public sviewholder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.stimg);
            textView = (TextView) itemView.findViewById(R.id.sttext);
        }
    }
}
