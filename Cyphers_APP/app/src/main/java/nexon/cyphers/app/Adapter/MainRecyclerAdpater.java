package nexon.cyphers.app.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import nexon.cyphers.app.R;
import nexon.cyphers.app.model.MainRecycleModel;

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
                Toast.makeText(context, holder.textView1.getText(), Toast.LENGTH_SHORT).show();
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
            imageView.setImageResource(data.getId());
           // Glide.with(itemView.getContext()).load(data.getImageId()).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(imageView);
        }
    }
}