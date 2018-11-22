package soexample.umeng.com.jindong.Adepter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import soexample.umeng.com.jindong.Activity.Details;
import soexample.umeng.com.jindong.Bean.ShangBean;
import soexample.umeng.com.jindong.R;

public class ShangAdapter extends RecyclerView.Adapter<ShangAdapter.sViewHolder> {
    private List<ShangBean.DataBean.DataList> list = new ArrayList<>();
    private Context context;


    public ShangAdapter(Context context, List<ShangBean.DataBean.DataList> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public sViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.shang, null);
        sViewHolder vi = new sViewHolder(view);
        return vi;

    }

//    private Pidds pids;
//
//    public void setPids(Pidds pids) {
//        this.pids = pids;
//    }

    @Override
    public void onBindViewHolder(sViewHolder holder, final int position) {
        holder.textView.setText(list.get(position).getTitle());
        String images = list.get(position).getImages();
        String[] split = images.split("\\|");
      //  holder.imageView.setImageURI(split[0]);
      Picasso.with(context).load(split[0]).fit().into(holder.imageView);
//        holder.lay.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                pids.setPid(list.get(position).getPid());
//
//            }
//        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Details.class);
                int pid = list.get(position).getPid();
                intent.putExtra("pid", pid + "");
                Log.i("aaaaa", pid + "");
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class sViewHolder extends RecyclerView.ViewHolder {

        private final ImageView imageView;
        private final TextView textView;
        private final LinearLayout lay;
        public sViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.img);
            textView = (TextView) itemView.findViewById(R.id.text);
            lay = (LinearLayout) itemView.findViewById(R.id.lay);
        }
    }

}
