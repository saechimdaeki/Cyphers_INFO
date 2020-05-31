package nexon.cyphers.app.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import nexon.cyphers.app.cypher.CyphersInfoActivity;
import nexon.cyphers.app.PositionActivity;
import nexon.cyphers.app.R;
import nexon.cyphers.app.Toosin.ToosinActivity;
import nexon.cyphers.app.WebContentActivity;
import nexon.cyphers.app.model.RecyclerViewModel.MainRecycleModel;
import nexon.cyphers.app.story.StoryActivity;

public class MainRecyclerAdpater extends RecyclerView.Adapter<MainRecyclerAdpater.ItemViewHolder> {


    private ArrayList<MainRecycleModel> listData = new ArrayList<>();



    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_recycle, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ItemViewHolder holder, final int position) {
        holder.onBind(listData.get(position));

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Context context=view.getContext();
                final Intent intent;
                Toast.makeText(context, holder.textView1.getText()+"(으)로 이동합니다.", Toast.LENGTH_SHORT).show();
                if(position==1)
                {
                    intent=new Intent(context, CyphersInfoActivity.class);
                    context.startActivity(intent);
                }
                else if(position==3)
                {
                    intent=new Intent(context,WebContentActivity.class);
                    intent.putExtra("url","http://cyphers.nexon.com/cyphers/article/art");
                    context.startActivity(intent);
                }
                else if(position==5)
                {
                    intent=new Intent(context, PositionActivity.class);
                    context.startActivity(intent);
                }
                else if(position==6)
                {
                    intent=new Intent(context, ToosinActivity.class);
                    context.startActivity(intent);
                }
                else if(position==7)
                {
                    intent=new Intent(context, StoryActivity.class);
                    context.startActivity(intent);
                }
                else if(position==8)
                {
                    intent=new Intent(context,WebContentActivity.class);
                    intent.putExtra("url","http://cyphers.nexon.com/cyphers/article/balance");
                    context.startActivity(intent);
                }
                else if(position==9)
                {
                    intent=new Intent(context,WebContentActivity.class);
                    intent.putExtra("url","http://cyphers.nexon.com/cyphers/article/free");
                    context.startActivity(intent);
                }else if(position==10)
                {
                    intent=new Intent(context, WebContentActivity.class);
                    intent.putExtra("url","http://cyphers.nexon.com/cyphers/game/item/itembox.mobile#2");
                    context.startActivity(intent);
                }
            }
        });
    }
    @Override
    public int getItemCount() {
        return listData.size();
    }
    public void addItem(MainRecycleModel data) {
        listData.add(data);
    }
    class ItemViewHolder extends RecyclerView.ViewHolder {

        private TextView textView1;
        private TextView textView2;
        private CircleImageView imageView;
        private View mView;
        ItemViewHolder(View itemView) {
            super(itemView);
            textView1 = itemView.findViewById(R.id.textView1);
            textView2 = itemView.findViewById(R.id.textView2);
            imageView = itemView.findViewById(R.id.imageView);
            mView=itemView;
        }
        void onBind(MainRecycleModel data) {
            textView1.setText(data.getTitle());
            textView2.setText(data.getSubTitle());
            Glide.with(itemView.getContext()).load(data.getId()).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(imageView);
        }
    }
}
