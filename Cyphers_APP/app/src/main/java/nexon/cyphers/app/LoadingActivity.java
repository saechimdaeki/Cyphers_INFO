package nexon.cyphers.app;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.Random;

import de.hdodenhof.circleimageview.CircleImageView;

public class LoadingActivity extends AppCompatActivity {
    CircleImageView imageView;
    /*
    String[] ImageUrl={
            "http://static.cyphers.co.kr/img/data/fankit_carocho.jpg",
            "http://static.cyphers.co.kr/img/data/fankit_tisha.jpg",
            "http://static.cyphers.co.kr/img/data/fankit_elfriede.jpg",
            "http://static.cyphers.co.kr/img/data/fankit_timothy.jpg",
            "http://static.cyphers.co.kr/img/data/fankit_tei.jpg",
            "http://static.cyphers.co.kr/img/data/fankit_sidney.jpg",
            "http://static.cyphers.co.kr/img/data/fankit_leonor.jpg",
            "http://static.cyphers.co.kr/img/data/fankit_corgi.jpg",
    };
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        imageView=findViewById(R.id.cyphers_loading_image);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        StartLoading();
    }
    private void StartLoading(){
        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
            }
        }, 3000);
    }
}
