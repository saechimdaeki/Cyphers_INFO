package nexon.cyphers.app.Adapter;

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
import nexon.cyphers.app.model.ToosinModel.Toosin_melee_Crawling_model;

public class ToosinCrawlerMeleeAdpater extends RecyclerView.Adapter<ToosinCrawlerMeleeAdpater.ItemViewHolder> {
    private ArrayList<Toosin_melee_Crawling_model> listData = new ArrayList<>();
    @Override
    public ToosinCrawlerMeleeAdpater.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.toosin_melee_recycle, viewGroup, false);
        return new ItemViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ToosinCrawlerMeleeAdpater.ItemViewHolder itemViewHolder, int i) {
        itemViewHolder.onBind(listData.get(i));

    }
    @Override
    public int getItemCount() {
        return listData.size();
    }
    public void addItem(Toosin_melee_Crawling_model data) {
        listData.add(data);
    }
    class ItemViewHolder extends RecyclerView.ViewHolder{
        private TextView rank;
        private TextView nick;
        private TextView rp;
        private TextView winstreak;
        private TextView result;
        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            rank=itemView.findViewById(R.id.rank);
            nick=itemView.findViewById(R.id.nick);
            rp=itemView.findViewById(R.id.rp);
            winstreak=itemView.findViewById(R.id.winstreak);
            result=itemView.findViewById(R.id.result);

        }
        void onBind(Toosin_melee_Crawling_model data){
            rank.setText(data.getRanking());
            nick.setText(data.getNickname());
            rp.setText(data.getRP());
            winstreak.setText(data.getWinStreak());
            result.setText(data.getResult());


        }
    }
}