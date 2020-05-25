package nexon.cyphers.app.Adapter;

import android.content.Context;
import android.content.Intent;
import android.nfc.Tag;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import org.w3c.dom.Text;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import nexon.cyphers.app.MatchingDeatilActivity;
import nexon.cyphers.app.R;
import nexon.cyphers.app.matching_result;
import nexon.cyphers.app.model.RecyclerViewModel.MatchingResultDetailModel;
import nexon.cyphers.app.model.RecyclerViewModel.matchResultRecycleModel;

public class MatchingDetailRecycleAdapter extends RecyclerView.Adapter<MatchingDetailRecycleAdapter.MatchItemViewHolder> {


    private ArrayList<MatchingResultDetailModel> listData = new ArrayList<>();



    @NonNull
    @Override
    public MatchItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.matching_all_result_detail, parent, false);
        return new MatchItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MatchItemViewHolder holder, final int position) {
        holder.onBind(listData.get(position));
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Context context=view.getContext();
                Intent intent=new Intent(context, matching_result.class);
                intent.putExtra("nick",holder.nickname.getText());
                context.startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount() {
        return listData.size();
    }
    public void addItem(MatchingResultDetailModel data) {
        listData.add(data);
    }
    class MatchItemViewHolder extends RecyclerView.ViewHolder {
        private TextView TeamResult;
        private LinearLayout linearLayout;
        private TextView TAG;
        private TextView dealPoint;
        private TextView damagedPoint;
        private TextView characterNameLevel;
        private TextView killDeathAssist;
        private TextView kda;
        private TextView battlePoint;
        private TextView sightPoint;
        private CircleImageView characterImage;
        private CircleImageView characterPosition;
        private CircleImageView Attribute1;
        private CircleImageView Attribute2;
        private CircleImageView Attribute3;
        private CircleImageView item1;
        private CircleImageView item2;
        private CircleImageView item3;
        private CircleImageView item4;
        private CircleImageView item5;
        private CircleImageView item6;
        private CircleImageView item7;
        private CircleImageView item8;
        private CircleImageView item9;
        private CircleImageView item10;
        private CircleImageView item11;
        private CircleImageView item12;
        private CircleImageView item13;
        private CircleImageView item14;
        private CircleImageView item15;
        private CircleImageView item16;
        private TextView nickname;
        private TextView eachPlayerID;
        private View mView;
        MatchItemViewHolder(View itemView) {
            super(itemView);
            linearLayout=itemView.findViewById(R.id.detail_recycle);
            TAG=itemView.findViewById(R.id.matching_Detail_TAG);
            dealPoint=itemView.findViewById(R.id.player_detail_deal_point);
            damagedPoint=itemView.findViewById(R.id.player_detail_damaged_point);
            characterNameLevel=itemView.findViewById(R.id.matching_character_detail_NameAndLevel);
            killDeathAssist=itemView.findViewById(R.id.player_detail_killdeathassist);
            kda=itemView.findViewById(R.id.player_detail_killdeathassistresult);
            battlePoint=itemView.findViewById(R.id.player_detail_battlepoint);
            sightPoint=itemView.findViewById(R.id.player_detail_sightpoint);
            characterImage=itemView.findViewById(R.id.matching_character_detail_image);
            characterPosition=itemView.findViewById(R.id.matching_detail_position_info);
            Attribute1=itemView.findViewById(R.id.matching_position_info_detail_attribute1);
            Attribute2=itemView.findViewById(R.id.matching_position_info_detail_attribute2);
            Attribute3=itemView.findViewById(R.id.matching_position_info_detail_attribute3);
            item1=itemView.findViewById(R.id.matcing_detail_item1);
            item2=itemView.findViewById(R.id.matcing_detail_item2);
            item3=itemView.findViewById(R.id.matcing_detail_item3);
            item4=itemView.findViewById(R.id.matcing_detail_item4);
            item5=itemView.findViewById(R.id.matcing_detail_item5);
            item6=itemView.findViewById(R.id.matcing_detail_item6);
            item7=itemView.findViewById(R.id.matcing_detail_item7);
            item8=itemView.findViewById(R.id.matcing_detail_item8);
            item9=itemView.findViewById(R.id.matcing_detail_item9);
            item10=itemView.findViewById(R.id.matcing_detail_item10);
            item11=itemView.findViewById(R.id.matcing_detail_item11);
            item12=itemView.findViewById(R.id.matcing_detail_item12);
            item13=itemView.findViewById(R.id.matcing_detail_item13);
            item14=itemView.findViewById(R.id.matcing_detail_item14);
            item15=itemView.findViewById(R.id.matcing_detail_item15);
            item16=itemView.findViewById(R.id.matcing_detail_item16);
            nickname=itemView.findViewById(R.id.player_detail_nickname);
            mView=itemView;

        }
        void onBind(MatchingResultDetailModel data) {
            TAG.setText(data.getMatchingDetailTag());
            dealPoint.setText("입힌 피해량: " +data.getDealingDetailPoint());
            damagedPoint.setText("받은 피해량: "+data.getDamagedDetailPoint());
            killDeathAssist.setText(data.getKDADetail());
            kda.setText(" "+data.getKDAPOINTDetail());
            battlePoint.setText("전투 참여: " +data.getBattlePointDetail());
            sightPoint.setText("시야 점수: "+data.getSightPointDetail());
            Glide.with(itemView.getContext()).load(data.getMatchingDetailCharacterImage()).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(characterImage);
            if(data.getMatchingDetailCharacterPosition().contains("탱커"))
                characterPosition.setImageResource(R.drawable.tanker);
            else if(data.getMatchingDetailCharacterPosition().contains("원거리"))
                characterPosition.setImageResource(R.drawable.longdealer);
            else if(data.getMatchingDetailCharacterPosition().contains("근거리"))
                characterPosition.setImageResource(R.drawable.shortdealer);
            else if(data.getMatchingDetailCharacterPosition().contains("서"))
                characterPosition.setImageResource(R.drawable.support);
            else{
                characterPosition.setImageResource(R.drawable.newbie);
            }
           if(data.getMatchingResult().equals("win"))
                linearLayout.setBackgroundResource(R.color.blue_100);
            else
               linearLayout.setBackgroundResource(R.color.pink_100);
            characterNameLevel.setText(data.getCharacterDetailNameLevel());
            nickname.setText(data.getNickname());
            /* 특성 */
            Glide.with(itemView.getContext()).load(data.getMatchingDetailCharacterPositionAttribute1()).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(Attribute1);
            Glide.with(itemView.getContext()).load(data.getMatchingDetailCharacterPositionAttribute2()).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(Attribute2);
            Glide.with(itemView.getContext()).load(data.getMatchingDetailCharacterPositionAttribute3()).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(Attribute3);
            /* 아이템 */
            Glide.with(itemView.getContext()).load(data.getDetailItem1()).diskCacheStrategy(DiskCacheStrategy.SOURCE).error(R.drawable.noitem).into(item1);
            Glide.with(itemView.getContext()).load(data.getDetailItem2()).diskCacheStrategy(DiskCacheStrategy.SOURCE).error(R.drawable.noitem).into(item2);
            Glide.with(itemView.getContext()).load(data.getDetailItem3()).diskCacheStrategy(DiskCacheStrategy.SOURCE).error(R.drawable.noitem).into(item3);
            Glide.with(itemView.getContext()).load(data.getDetailItem4()).diskCacheStrategy(DiskCacheStrategy.SOURCE).error(R.drawable.noitem).into(item4);
            Glide.with(itemView.getContext()).load(data.getDetailItem5()).diskCacheStrategy(DiskCacheStrategy.SOURCE).error(R.drawable.noitem).into(item5);
            Glide.with(itemView.getContext()).load(data.getDetailItem6()).diskCacheStrategy(DiskCacheStrategy.SOURCE).error(R.drawable.noitem).into(item6);
            Glide.with(itemView.getContext()).load(data.getDetailItem7()).diskCacheStrategy(DiskCacheStrategy.SOURCE).error(R.drawable.noitem).into(item7);
            Glide.with(itemView.getContext()).load(data.getDetailItem8()).diskCacheStrategy(DiskCacheStrategy.SOURCE).error(R.drawable.noitem).into(item8);
            Glide.with(itemView.getContext()).load(data.getDetailItem9()).diskCacheStrategy(DiskCacheStrategy.SOURCE).error(R.drawable.noitem).into(item9);
            Glide.with(itemView.getContext()).load(data.getDetailItem10()).diskCacheStrategy(DiskCacheStrategy.SOURCE).error(R.drawable.noitem).into(item10);
            Glide.with(itemView.getContext()).load(data.getDetailItem11()).diskCacheStrategy(DiskCacheStrategy.SOURCE).error(R.drawable.noitem).into(item11);
            Glide.with(itemView.getContext()).load(data.getDetailItem12()).diskCacheStrategy(DiskCacheStrategy.SOURCE).error(R.drawable.noitem).into(item12);
            Glide.with(itemView.getContext()).load(data.getDetailItem13()).diskCacheStrategy(DiskCacheStrategy.SOURCE).error(R.drawable.noitem).into(item13);
            Glide.with(itemView.getContext()).load(data.getDetailItem14()).diskCacheStrategy(DiskCacheStrategy.SOURCE).error(R.drawable.noitem).into(item14);
            Glide.with(itemView.getContext()).load(data.getDetailItem15()).diskCacheStrategy(DiskCacheStrategy.SOURCE).error(R.drawable.noitem).into(item15);
            Glide.with(itemView.getContext()).load(data.getDetailItem16()).diskCacheStrategy(DiskCacheStrategy.SOURCE).error(R.drawable.noitem).into(item16);
        }
    }
}
