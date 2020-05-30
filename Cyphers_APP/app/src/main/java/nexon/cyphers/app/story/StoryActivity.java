package nexon.cyphers.app.story;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.Arrays;
import java.util.List;

import nexon.cyphers.app.R;
import nexon.cyphers.app.adapter.StoryAdapter;
import nexon.cyphers.app.databinding.ActivityStoryBinding;
import nexon.cyphers.app.model.RecyclerViewModel.StoryModel;

public class StoryActivity extends AppCompatActivity {
    ActivityStoryBinding binding;
    StoryAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_story);
        binding.setStoryData(this);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        binding.storyRecycle.setLayoutManager(linearLayoutManager);
        adapter=new StoryAdapter();
        binding.storyRecycle.setAdapter(adapter);
        getRecycle();
    }
    public void getRecycle(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                final List<String> ListTitle= Arrays.asList(
                        "ECLIPSE",
                        "SUPER MOON",
                        "BLUE MOON"
                );
                final List<String> ListUrl= Arrays.asList(
                        "http://static.cyphers.co.kr/img/story/img_head_eclipse.jpg",
                        "http://static.cyphers.co.kr/img/story/img_head_super.jpg",
                        "http://static.cyphers.co.kr/img/story/img_head_bluemoon.jpg"
                );
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        for(int i=0; i<ListTitle.size(); i++){
                            StoryModel data=new StoryModel();
                            data.setTitle(ListTitle.get(i));
                            data.setImageUrl(ListUrl.get(i));
                            adapter.addItem(data);
                        }
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        }).start();
    }
}
