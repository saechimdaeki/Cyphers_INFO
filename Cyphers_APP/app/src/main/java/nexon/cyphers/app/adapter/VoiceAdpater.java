package nexon.cyphers.app.adapter;

import android.content.Context;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import nexon.cyphers.app.R;
import nexon.cyphers.app.model.Character.Voice;

public class VoiceAdpater extends RecyclerView.Adapter<VoiceAdpater.ItemViewHolder>{
    private ArrayList<Voice> listData = new ArrayList<>();
    private int cnt=0;
    @NonNull
    @Override
    public VoiceAdpater.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.voice_recycle, parent, false);
        return new VoiceAdpater.ItemViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull final VoiceAdpater.ItemViewHolder holder, final int position) {
        holder.onBind(listData.get(position));


        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(cnt>=1)
                {
                    // 이렇게 함으로써 mediaplayer가 겹쳐서 죽지않음.
                }
                else{
                    MediaPlayer mediaPlayer = new MediaPlayer();
                    cnt = 1;
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            mediaPlayer.release();
                            cnt = 0;
                        }
                    });
                    if (mediaPlayer.isPlaying()) {
                        if (mediaPlayer != null)
                            mediaPlayer.stop();
                    } else {
                        try {
                            mediaPlayer.setDataSource(holder.voiceurl.getText().toString());
                            mediaPlayer.prepare();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        mediaPlayer.start();
                    }
                }
            }
        });

    }
    @Override
    public int getItemCount() {
        return listData.size();
    }
    public void addItem(Voice data) {
        listData.add(data);
    }
    class ItemViewHolder extends RecyclerView.ViewHolder {
        private CircleImageView imageView;
        private TextView textaction;
        private TextView voiceurl;
        private TextView explain;
        private View mView;
        ItemViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            textaction=itemView.findViewById(R.id.title);
            voiceurl=itemView.findViewById(R.id.voiceurl);
            explain=itemView.findViewById(R.id.titleexplain);
            mView=itemView;
        }
        void onBind(Voice data) {
            textaction.setText(data.getAction());
            voiceurl.setText(data.getVoiceUrl());
            explain.setText(data.getExplain());
            Glide.with(itemView.getContext()).load(data.getClickUrl()).diskCacheStrategy(DiskCacheStrategy.SOURCE).error(R.drawable.newbie).into(imageView);
        }
    }
}
