package nexon.cyphers.app;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import de.hdodenhof.circleimageview.CircleImageView;
import nexon.cyphers.app.databinding.ActivityLoadingBinding;

public class LoadingActivity extends AppCompatActivity {

    int time;
    ActivityLoadingBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       binding= DataBindingUtil.setContentView(this,R.layout.activity_loading);
       binding.setLoadingdata(this);
      // Glide.with(this).load(R.drawable.c).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(binding.cyphersLoadingImage);
       time=getIntent().getIntExtra("where",0);
        binding.animationGear.setAnimation("junseong_gear2.json");
        if(time!=3000)
            binding.onlyLoadingShown.setVisibility(View.GONE);
        binding.animationGear.loop(true);
        binding.animationGear.playAnimation();
        StartLoading();
    }
    private void StartLoading(){
        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
            }
        }, time);
    }
    public void onclick(View view){
        finish();
    }
}
