package nexon.cyphers.app.story.supermoon;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.wajahatkarim3.easyflipviewpager.BookFlipPageTransformer;

import nexon.cyphers.app.R;
import nexon.cyphers.app.databinding.ActivitySuperMoonBinding;

public class SuperMoonActivity extends AppCompatActivity {
    ActivitySuperMoonBinding binding;
    SuperMoonFragment fragment1,fragment2,fragment3,fragment4,fragment5,fragment6,fragment7,fragment8,fragment9,fragment10;
    SuperMoonMainFragment fragment0;
    SuperMoonBookcover fragmentcover;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_super_moon);
        binding.setSuperData(this);
        binding.superMoonViewpager.setCurrentItem(0);
        binding.superMoonViewpager.setAdapter(new SuperMoonActivity.PagerAdapter(getSupportFragmentManager()));
        binding.supermoonIndicator.setViewPager(binding.superMoonViewpager);
        BookFlipPageTransformer bookFlipPageTransformer=new BookFlipPageTransformer();
        bookFlipPageTransformer.setEnableScale(true);
        bookFlipPageTransformer.setScaleAmountPercent(10f);
        binding.superMoonViewpager.setPageTransformer(true,bookFlipPageTransformer);
    }
    private class PagerAdapter extends FragmentPagerAdapter {
        public PagerAdapter(FragmentManager fm){
            super(fm);
            getItem(0);
        }
        public Fragment getItem(int position){
            if(position==0)
            {
                fragmentcover=new SuperMoonBookcover();
                return fragmentcover;
            }
            else if(position==1)
            {
                fragment0=new SuperMoonMainFragment();
                return fragment0;
            }
            else if(position==2)
            {
                fragment1=new SuperMoonFragment("http://cyphers.nexon.com/cyphers/pages/story/super/1");
                return fragment1;
            }else if(position==3){
                fragment2=new SuperMoonFragment("http://cyphers.nexon.com/cyphers/pages/story/super/2");
                return fragment2;
            }else if(position==4) {
                fragment3=new SuperMoonFragment("http://cyphers.nexon.com/cyphers/pages/story/super/3");
                return fragment3;
            }else if(position==5) {
                fragment4=new SuperMoonFragment("http://cyphers.nexon.com/cyphers/pages/story/super/4");
                return fragment4;
            }
            else if(position==6) {
                fragment5=new SuperMoonFragment("http://cyphers.nexon.com/cyphers/pages/story/super/5");
                return fragment5;
            }
            else if(position==7) {
                fragment6=new SuperMoonFragment("http://cyphers.nexon.com/cyphers/pages/story/super/6");
                return fragment6;
            }
            else if(position==8) {
                fragment7=new SuperMoonFragment("http://cyphers.nexon.com/cyphers/pages/story/super/7");
                return fragment7;
            }
            else if(position==9) {
                fragment8=new SuperMoonFragment("http://cyphers.nexon.com/cyphers/pages/story/super/8");
                return fragment8;
            }
            else if(position==10) {
                fragment9=new SuperMoonFragment("http://cyphers.nexon.com/cyphers/pages/story/super/9");
                return fragment9;
            }
            else{
                fragment10=new SuperMoonFragment("http://cyphers.nexon.com/cyphers/pages/story/super/10");
                return fragment10;
            }
        }
        public int getCount(){
            return 12;
        }

    }
}
