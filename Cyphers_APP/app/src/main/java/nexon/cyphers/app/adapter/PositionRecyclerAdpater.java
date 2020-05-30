package nexon.cyphers.app.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.shreyaspatil.MaterialDialog.MaterialDialog;
import com.shreyaspatil.MaterialDialog.interfaces.DialogInterface;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import nexon.cyphers.app.R;
import nexon.cyphers.app.model.position.PositionModel;

public class PositionRecyclerAdpater extends RecyclerView.Adapter<PositionRecyclerAdpater.ItemViewHolder> {


    private ArrayList<PositionModel> listData = new ArrayList<>();



    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.position_recycle, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ItemViewHolder holder, final int position) {
        holder.onBind(listData.get(position));

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Context context=view.getContext();
                String title= (String) holder.title.getText();
                String subtitle=(String)holder.subtitle.getText();
                if(holder.pos.getText().equals("탱커")) {
                    MaterialDialog mDialog = new MaterialDialog.Builder((Activity) context)
                            .setTitle(title)
                            .setMessage(subtitle)
                            .setCancelable(true)
                            .setAnimation("shield.json")
                            .setPositiveButton("확인", R.drawable.ic_check, new MaterialDialog.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int which) {
                                    dialogInterface.dismiss();
                                }
                            })
                            .build();
                    mDialog.show();
                }else if(holder.pos.getText().equals("원거리딜러"))
                {
                    MaterialDialog mDialog = new MaterialDialog.Builder((Activity) context)
                            .setTitle(title)
                            .setMessage(subtitle)
                            .setCancelable(true)
                            .setAnimation("target.json")
                            .setPositiveButton("확인", R.drawable.ic_check, new MaterialDialog.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int which) {
                                    dialogInterface.dismiss();
                                }
                            })
                            .build();
                    mDialog.show();
                }else if(holder.pos.getText().equals("근거리딜러"))
                {
                    MaterialDialog mDialog = new MaterialDialog.Builder((Activity) context)
                            .setTitle(title)
                            .setMessage(subtitle)
                            .setCancelable(true)
                            .setAnimation("punch.json")
                            .setPositiveButton("확인", R.drawable.ic_check, new MaterialDialog.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int which) {
                                    dialogInterface.dismiss();
                                }
                            })
                            .build();
                    mDialog.show();
                }else if(holder.pos.getText().equals("서포터")){
                    MaterialDialog mDialog = new MaterialDialog.Builder((Activity) context)
                            .setTitle(title)
                            .setMessage(subtitle)
                            .setCancelable(true)
                            .setAnimation("support.json")
                            .setPositiveButton("확인", R.drawable.ic_check, new MaterialDialog.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int which) {
                                    dialogInterface.dismiss();
                                }
                            })
                            .build();
                    mDialog.show();
                }else{
                    MaterialDialog mDialog = new MaterialDialog.Builder((Activity) context)
                            .setTitle(title)
                            .setMessage(subtitle)
                            .setCancelable(true)
                            .setAnimation("thumbs.json")
                            .setPositiveButton("확인", R.drawable.ic_check, new MaterialDialog.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int which) {
                                    dialogInterface.dismiss();
                                }
                            })
                            .build();
                    mDialog.show();
                }


            }
        });
    }
    @Override
    public int getItemCount() {
        return listData.size();
    }
    public void addItem(PositionModel data) {
        listData.add(data);
    }
    class ItemViewHolder extends RecyclerView.ViewHolder {

        private TextView title;
        private TextView subtitle;
        private CircleImageView position_image;
        private TextView pos;
        private View mView;
        ItemViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.position_title);
            subtitle = itemView.findViewById(R.id.position_subtitle);
            position_image = itemView.findViewById(R.id.position_image);
            pos=itemView.findViewById(R.id.imageid);
            mView=itemView;
        }
        void onBind(PositionModel data) {
            title.setText(data.getTitle());
            subtitle.setText(data.getSubtite());
            pos.setText(data.getPos());
            Glide.with(itemView.getContext()).load(data.getImage()).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(position_image);
        }
    }
}