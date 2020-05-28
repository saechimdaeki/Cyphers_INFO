package nexon.cyphers.app;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

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
       time=getIntent().getIntExtra("where",0);
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
