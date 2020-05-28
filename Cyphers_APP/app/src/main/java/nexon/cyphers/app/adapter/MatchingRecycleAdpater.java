package nexon.cyphers.app.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import nexon.cyphers.app.MatchingDeatilActivity;
import nexon.cyphers.app.R;
import nexon.cyphers.app.model.RecyclerViewModel.matchResultRecycleModel;

public class MatchingRecycleAdpater extends RecyclerView.Adapter<MatchingRecycleAdpater.MatchItemViewHolder> {


    private ArrayList<matchResultRecycleModel> listData = new ArrayList<>();



    @NonNull
    @Override
    public MatchItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.matching_all_result, parent, false);
        return new MatchItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MatchItemViewHolder holder, final int position) {
        holder.onBind(listData.get(position));
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Context context=view.getContext();
                Intent intent=new Intent(context, MatchingDeatilActivity.class);
                intent.putExtra("matchId",holder.matchId.getText());
                context.startActivity(intent);
                //Toast.makeText(context,holder.matchId.getText() , Toast.LENGTH_SHORT).show();

            }
        });
    }
    @Override
    public int getItemCount() {
        return listData.size();
    }
    public void addItem(matchResultRecycleModel data) {
        listData.add(data);
    }
    class MatchItemViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout linearLayout;
        private TextView gametype;
        private TextView CharacterName;
        private TextView AttackPoint;
        private TextView damagedPoint;
        private TextView kda;
        private TextView kdaPoint;
        private TextView BattlePoint;
        private TextView SightPoint;
        private TextView playtime;
        private CircleImageView CharacterImage;
        private CircleImageView MatchingPosition;
        private CircleImageView MatchingPositionAttribute1;
        private CircleImageView MatchingPositionAttribute2;
        private CircleImageView MatchingPositionAttribute3;
        private TextView matchId;
        private View mView;
        MatchItemViewHolder(View itemView) {
            super(itemView);
            gametype = itemView.findViewById(R.id.matching_default_info);
            CharacterName = itemView.findViewById(R.id.matching_character_NameAndLevel);
            AttackPoint = itemView.findViewById(R.id.player_deal_point);
            damagedPoint=itemView.findViewById(R.id.player_damaged_point);
            kda=itemView.findViewById(R.id.player_killdeathassist);
            kdaPoint=itemView.findViewById(R.id.player_killdeathassistresult);
            BattlePoint=itemView.findViewById(R.id.player_battlepoint);
            SightPoint=itemView.findViewById(R.id.player_sightpoint);
            CharacterImage=itemView.findViewById(R.id.matching_character_image);
            MatchingPosition=itemView.findViewById(R.id.matching_position_info);
            MatchingPositionAttribute1=itemView.findViewById(R.id.matching_position_info_attribute1);
            MatchingPositionAttribute2=itemView.findViewById(R.id.matching_position_info_attribute2);
            MatchingPositionAttribute3=itemView.findViewById(R.id.matching_position_info_attribute3);
            playtime=itemView.findViewById(R.id.player_playtime);
            linearLayout=itemView.findViewById(R.id.view_recycle);
            matchId=itemView.findViewById(R.id.matching_id);
            mView=itemView;
        }
        void onBind(matchResultRecycleModel data) {
            gametype.setText(data.getMatchingType());
            CharacterName.setText(data.getCharacterNameLevel());
            AttackPoint.setText("입힌 피해량: " +data.getDealingPoint());
            damagedPoint.setText("받은 피해량: "+data.getDamagedPoint());
            kda.setText(data.getKDA());
            kdaPoint.setText(" "+data.getKDAPOINT());
            BattlePoint.setText("전투 참여: " +data.getBattlePoint());
            SightPoint.setText("시야 점수: "+data.getSightPoint());
            playtime.setText(data.getPlaytime());
            Glide.with(itemView.getContext()).load(data.getMatchingCharacterImage()).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(CharacterImage);
            matchId.setText(data.getMatchId()); //매칭 상세정보 위한 ID값
            if(data.getMatchingCharacterPosition().contains("탱커"))
            MatchingPosition.setImageResource(R.drawable.tanker);
            else if(data.getMatchingCharacterPosition().contains("원거리"))
                MatchingPosition.setImageResource(R.drawable.longdealer);
            else if(data.getMatchingCharacterPosition().contains("근거리"))
                MatchingPosition.setImageResource(R.drawable.shortdealer);
            else if(data.getMatchingCharacterPosition().contains("서"))
                MatchingPosition.setImageResource(R.drawable.support);
            else{
                MatchingPosition.setImageResource(R.drawable.newbie);
            }
            if(data.getMatchingResult().equals("win"))
                linearLayout.setBackgroundResource(R.color.blue_100);
            else
                linearLayout.setBackgroundResource(R.color.pink_100);
            Glide.with(itemView.getContext()).load(data.getMatchingCharacterPositionAttribute1()).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(MatchingPositionAttribute1);
            Glide.with(itemView.getContext()).load(data.getMatchingCharacterPositionAttribute2()).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(MatchingPositionAttribute2);
            Glide.with(itemView.getContext()).load(data.getMatchingCharacterPositionAttribute3()).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(MatchingPositionAttribute3);
        }
    }
}
