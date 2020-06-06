package nexon.cyphers.app.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
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
import nexon.cyphers.app.model.todayCypherModel.TodayCypherModel;
import nexon.cyphers.app.model.todayCypherModel.TodaytextModel;

public class todaytitleAdpater  extends RecyclerView.Adapter<todaytitleAdpater.ItemViewHolder>{
    private ArrayList<TodaytextModel> listData = new ArrayList<>();
    @NonNull
    @Override
    public todaytitleAdpater.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.todaytextrecycle, parent, false);
        return new todaytitleAdpater.ItemViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull final todaytitleAdpater.ItemViewHolder holder, final int position) {
        holder.onBind(listData.get(position));
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String abcd="http://cyphers.nexon.com";
                final Context context=view.getContext();
                final Intent intent;
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse(abcd+holder.url.getText()));
                context.startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount() {
        return listData.size();
    }
    public void addItem(TodaytextModel data) {
        listData.add(data);
    }
    class ItemViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private TextView url;
        private View mView;
        ItemViewHolder(View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.todaytexttitle);
            url=itemView.findViewById(R.id.todayurl);
            mView=itemView;
        }
        void onBind(TodaytextModel data) {
           title.setText(data.getTitle());
           url.setText(data.getUrl());
        }
    }
}
