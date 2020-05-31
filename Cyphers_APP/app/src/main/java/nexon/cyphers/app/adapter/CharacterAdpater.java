package nexon.cyphers.app.adapter;

import android.content.Context;
import android.content.Intent;
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

import de.hdodenhof.circleimageview.CircleImageView;
import nexon.cyphers.app.R;
import nexon.cyphers.app.cypher.CharacterFragment;
import nexon.cyphers.app.cypher.CyphersInfoActivity;
import nexon.cyphers.app.model.CharacterInfo.CharacterModel;


public class CharacterAdpater extends RecyclerView.Adapter<CharacterAdpater.ItemViewHolder> {
    private ArrayList<CharacterModel> listData = new ArrayList<>();
    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.character_recycler_items, parent, false);
        return new ItemViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull final CharacterAdpater.ItemViewHolder holder, final int position) {
        holder.onBind(listData.get(position));
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Context context=view.getContext();
                ((CyphersInfoActivity)context).setFrag(position);
            }
        });
    }
    @Override
    public int getItemCount() {
        return listData.size();
    }
    public void addItem(CharacterModel data) {
        listData.add(data);
    }
    class ItemViewHolder extends RecyclerView.ViewHolder {
        private CircleImageView imageView;
        private TextView textView;
        private View mView;
        ItemViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.character_icon);
            textView=itemView.findViewById(R.id.character_name);
            mView=itemView;
        }
        void onBind(CharacterModel data) {
            textView.setText(data.getCharacterName());
            Glide.with(itemView.getContext()).load("https://img-api.neople.co.kr/cy/characters/"+data.getCharacterId()).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(imageView);
        }
    }
}
