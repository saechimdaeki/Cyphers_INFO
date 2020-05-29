package nexon.cyphers.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import nexon.cyphers.app.adapter.PositionRecyclerAdpater;
import nexon.cyphers.app.databinding.ActivityPositionBinding;
import nexon.cyphers.app.model.position.PositionModel;

public class PositionActivity extends AppCompatActivity {
    ActivityPositionBinding binding;
    PositionRecyclerAdpater commonAdpater,tankerAdpater,shortAdpater,longAdpater,supportAdpater;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_position);
        binding.setPositiondata(this);
        commonAdpater=new PositionRecyclerAdpater();
        tankerAdpater=new PositionRecyclerAdpater();
        shortAdpater=new PositionRecyclerAdpater();
        longAdpater=new PositionRecyclerAdpater();
        supportAdpater=new PositionRecyclerAdpater();
        setup();
    }
    public void setup(){
        String[] commontitle =getResources().getStringArray(R.array.commonTitle);
        String[] commonSubtitle =getResources().getStringArray(R.array.commonSUBTitle);
        String[] commonImage =getResources().getStringArray(R.array.commonImageUrl);

        String[] tankertitle =getResources().getStringArray(R.array.tankerTitle);
        String[] tankerSubtitle =getResources().getStringArray(R.array.tankerSubTitle);
        String[] tankerImage =getResources().getStringArray(R.array.tankerImage);

        String[] shortTitle =getResources().getStringArray(R.array.shortTitle);
        String[] shortSubtitle =getResources().getStringArray(R.array.shortSubTitle);
        String[] shortImage =getResources().getStringArray(R.array.shortImage);

        String[] longTitle =getResources().getStringArray(R.array.longTitle);
        String[] longSubtitle =getResources().getStringArray(R.array.longSubTitle);
        String[] longImage =getResources().getStringArray(R.array.longImage);

        String[] supportTitle=getResources().getStringArray(R.array.supportTitle);
        String[] supportSubTitle=getResources().getStringArray(R.array.supportSubTitle);
        String[] supportImage=getResources().getStringArray(R.array.supportImage);

        for(int i=0;i<commontitle.length; i++)
        {
            PositionModel model=new PositionModel();
            model.setTitle(commontitle[i]);
            model.setImage(commonImage[i]);
            model.setSubtite(commonSubtitle[i]);
            model.setPos("공용");
            commonAdpater.addItem(model);
        }
        commonAdpater.notifyDataSetChanged();
        LinearLayoutManager linearLayoutManager1=new LinearLayoutManager(this);
        binding.commonRecyclerView.setLayoutManager(linearLayoutManager1);
        binding.commonRecyclerView.setAdapter(commonAdpater);

        for(int i=0; i<tankertitle.length; i++)
        {
            PositionModel model=new PositionModel();
            model.setTitle(tankertitle[i]);
            model.setSubtite(tankerSubtitle[i]);
            model.setImage(tankerImage[i]);
            model.setPos("탱커");
            tankerAdpater.addItem(model);
        }
        tankerAdpater.notifyDataSetChanged();;
        LinearLayoutManager linearLayoutManager2=new LinearLayoutManager(this);
        binding.tankerRecyclerView.setLayoutManager(linearLayoutManager2);
        binding.tankerRecyclerView.setAdapter(tankerAdpater);

        for(int i=0; i<shortTitle.length; i++)
        {
            PositionModel model=new PositionModel();
            model.setTitle(shortTitle[i]);
            model.setSubtite(shortSubtitle[i]);
            model.setImage(shortImage[i]);
            model.setPos("근거리딜러");
            shortAdpater.addItem(model);
        }
        shortAdpater.notifyDataSetChanged();
        LinearLayoutManager linearLayoutManager3=new LinearLayoutManager(this);
        binding.shortRecyclerView.setLayoutManager(linearLayoutManager3);
        binding.shortRecyclerView.setAdapter(shortAdpater);

        for(int i=0; i<longTitle.length; i++)
        {
            PositionModel model=new PositionModel();
            model.setTitle(longTitle[i]);
            model.setSubtite(longSubtitle[i]);
            model.setImage(longImage[i]);
            model.setPos("원거리딜러");
            longAdpater.addItem(model);
        }
        longAdpater.notifyDataSetChanged();;
        LinearLayoutManager linearLayoutManager4=new LinearLayoutManager(this);
        binding.longRecyclerView.setLayoutManager(linearLayoutManager4);
        binding.longRecyclerView.setAdapter(longAdpater);

        for(int i=0; i<supportTitle.length; i++)
        {
            PositionModel model=new PositionModel();
            model.setTitle(supportTitle[i]);
            model.setSubtite(supportSubTitle[i]);
            model.setImage(supportImage[i]);
            model.setPos("서포터");
            supportAdpater.addItem(model);
        }
        supportAdpater.notifyDataSetChanged();
        LinearLayoutManager linearLayoutManager5=new LinearLayoutManager(this);
        binding.supportRecyclerView.setLayoutManager(linearLayoutManager5);
        binding.supportRecyclerView.setAdapter(supportAdpater);



    }

}
