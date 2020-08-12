package nexon.cyphers.app.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

import nexon.cyphers.app.R;
import nexon.cyphers.app.cypher.CyphersInfoActivity;
import nexon.cyphers.app.model.todayCypherModel.TodayCypherModel;

public class todayCypherAdpater extends RecyclerView.Adapter<todayCypherAdpater.ItemViewHolder>{
    private ArrayList<TodayCypherModel> listData = new ArrayList<>();
    @NonNull
    @Override
    public todayCypherAdpater.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.todaycypherrecycle, parent, false);
        return new todayCypherAdpater.ItemViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull final todayCypherAdpater.ItemViewHolder holder, final int position) {
        holder.onBind(listData.get(position));
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String abcd="http://cyphers.nexon.com";
                final Context context=view.getContext();
                final Intent intent;
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse(abcd+holder.clicktext.getText()));
                context.startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount() {
        return listData.size();
    }
    public void addItem(TodayCypherModel data) {
        listData.add(data);
    }
    class ItemViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView titletext;
        private TextView datetext;
        private TextView clicktext;
        private TextView contenttext;
        private View mView;
        ItemViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.todayimage);
            titletext=itemView.findViewById(R.id.todaytitle);
            datetext=itemView.findViewById(R.id.todaydate);
            clicktext=itemView.findViewById(R.id.clickurl);
            contenttext=itemView.findViewById(R.id.todaycontent);
            mView=itemView;
        }
        void onBind(TodayCypherModel data) {
            Glide.with(itemView.getContext()).load(data.getImgurl()).diskCacheStrategy(DiskCacheStrategy.SOURCE).error(R.drawable.newbie).into(imageView);
            titletext.setText(data.getTitle());
            datetext.setText(data.getDate());
            clicktext.setText(data.getClickUrl());
            contenttext.setText(data.getContent());
        }
    }
}
