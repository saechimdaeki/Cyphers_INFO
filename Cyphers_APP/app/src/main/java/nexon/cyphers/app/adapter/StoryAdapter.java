package nexon.cyphers.app.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import java.util.ArrayList;
import nexon.cyphers.app.R;
import nexon.cyphers.app.model.RecyclerViewModel.StoryModel;
import nexon.cyphers.app.story.bluemoon.BlueMoonActivity;
import nexon.cyphers.app.story.eclipse.EclipseActivity;
import nexon.cyphers.app.story.supermoon.SuperMoonActivity;

public class StoryAdapter extends RecyclerView.Adapter<StoryAdapter.ItemViewHolder> {


    private ArrayList<StoryModel> listData = new ArrayList<>();



    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.story_recycle, parent, false);
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
                if(position==0)
                {
                    intent=new Intent(context, EclipseActivity.class);
                    context.startActivity(intent);
                }else if(position==1)
                {
                    intent=new Intent(context, SuperMoonActivity.class);
                    context.startActivity(intent);
                }else if(position==2)
                {
                    intent=new Intent(context, BlueMoonActivity.class);
                    context.startActivity(intent);
                }
            }
        });
    }
    @Override
    public int getItemCount() {
        return listData.size();
    }
    public void addItem(StoryModel data) {
        listData.add(data);
    }
    class ItemViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private View mView;
        ItemViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.story_back);
            mView=itemView;
        }
        void onBind(StoryModel data) {
            Glide.with(itemView.getContext()).load(data.getImageUrl()).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(imageView);
        }
    }
}