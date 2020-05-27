package nexon.cyphers.app.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import nexon.cyphers.app.R;
import nexon.cyphers.app.model.ToosinModel.Toosin_par_Crawling_model;

public class ToosinCrawlerParAdapter extends RecyclerView.Adapter<ToosinCrawlerParAdapter.ItemViewHolder> {
    private ArrayList<Toosin_par_Crawling_model> listData = new ArrayList<>();
    @Override
    public ToosinCrawlerParAdapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.toosin_par_recycle, viewGroup, false);
        return new ToosinCrawlerParAdapter.ItemViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ToosinCrawlerParAdapter.ItemViewHolder itemViewHolder, int i) {
        itemViewHolder.onBind(listData.get(i));

    }
    @Override
    public int getItemCount() {
        return listData.size();
    }
    public void addItem(Toosin_par_Crawling_model data) {
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
            rank=itemView.findViewById(R.id.rankpar);
            nick=itemView.findViewById(R.id.nickpar);
            rp=itemView.findViewById(R.id.rppar);
            winstreak=itemView.findViewById(R.id.winstreakpar);
            result=itemView.findViewById(R.id.resultpar);

        }
        void onBind(Toosin_par_Crawling_model data){
            rank.setText(data.getRanking());
            nick.setText(data.getNickname());
            rp.setText(data.getRP());
            winstreak.setText(data.getWinStreak());
            result.setText(data.getResult());


        }
    }
}
