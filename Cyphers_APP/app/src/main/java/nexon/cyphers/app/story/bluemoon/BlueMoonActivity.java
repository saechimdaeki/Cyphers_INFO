package nexon.cyphers.app.story.bluemoon;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.wajahatkarim3.easyflipviewpager.BookFlipPageTransformer;

import nexon.cyphers.app.R;
import nexon.cyphers.app.databinding.ActivityBlueMoonBinding;


public class BlueMoonActivity extends AppCompatActivity {
    ActivityBlueMoonBinding binding;
    BlueMoonFragment fragment1,fragment2;
    BlueMonBookcover fragmentCover;
    BlueMoonMainFragment fragmentMain;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_blue_moon);
        binding.setBlueData(this);
        binding.blueMoonViewpager.setCurrentItem(0);
        binding.blueMoonViewpager.setAdapter(new BlueMoonActivity.PagerAdapter(getSupportFragmentManager()));
        binding.bluemoonIndicator.setViewPager(binding.blueMoonViewpager);
        BookFlipPageTransformer bookFlipPageTransformer=new BookFlipPageTransformer();
        bookFlipPageTransformer.setEnableScale(true);
        bookFlipPageTransformer.setScaleAmountPercent(10f);
        binding.blueMoonViewpager.setPageTransformer(true,bookFlipPageTransformer);
    }
    private class PagerAdapter extends FragmentPagerAdapter {
        public PagerAdapter(FragmentManager fm){
            super(fm);
            getItem(0);
        }
        public Fragment getItem(int position){
            if(position==0)
            {
                fragmentCover=new BlueMonBookcover();
                return fragmentCover;
            }
            else if(position==1)
            {
                fragmentMain=new BlueMoonMainFragment();
                return fragmentMain;
            }
            else if(position==2)
            {
                fragment1=new BlueMoonFragment("http://cyphers.nexon.com/cyphers/pages/story/bluemoon/1");
                return fragment1;
            }else{
                fragment2=new BlueMoonFragment("http://cyphers.nexon.com/cyphers/pages/story/bluemoon/2");
                return fragment2;
            }
        }
        public int getCount(){
            return 4;
        }

    }
}
