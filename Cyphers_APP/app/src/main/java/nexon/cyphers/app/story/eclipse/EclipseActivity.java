package nexon.cyphers.app.story.eclipse;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.wajahatkarim3.easyflipviewpager.BookFlipPageTransformer;

import nexon.cyphers.app.R;
import nexon.cyphers.app.databinding.ActivityEclipseBinding;

public class EclipseActivity extends AppCompatActivity {
    ActivityEclipseBinding binding;
    EclipseFragment fragment1,fragment2,fragment3,fragment4,fragment5,fragment6,fragment7,fragment8,fragment9,fragment10;
    EclipseMainFragment fragment0;
    EclipseBookcover fragmentcover;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_eclipse);
        binding.setEclipseData(this);
        binding.eclipseViewpager.setCurrentItem(0);
        binding.eclipseViewpager.setAdapter(new PagerAdapter(getSupportFragmentManager()));
        binding.circleindicator.setViewPager(binding.eclipseViewpager);
      BookFlipPageTransformer bookFlipPageTransformer=new BookFlipPageTransformer();
      bookFlipPageTransformer.setEnableScale(true);
      bookFlipPageTransformer.setScaleAmountPercent(10f);
      binding.eclipseViewpager.setPageTransformer(true,bookFlipPageTransformer);

    }

    private class PagerAdapter extends FragmentPagerAdapter {
        public PagerAdapter(FragmentManager fm){
            super(fm);
            getItem(0);
        }
        public Fragment getItem(int position){
            if(position==0)
            {
                fragmentcover=new EclipseBookcover();
                return fragmentcover;
            }
            else if(position==1)
            {
                fragment0=new EclipseMainFragment();
                return fragment0;
            }
            else if(position==2)
            {
                fragment1=new EclipseFragment("http://cyphers.nexon.com/cyphers/pages/story/eclipse/1");
                return fragment1;
            }else if(position==3){
                fragment2=new EclipseFragment("http://cyphers.nexon.com/cyphers/pages/story/eclipse/2");
                return fragment2;
            }else if(position==4) {
                fragment3=new EclipseFragment("http://cyphers.nexon.com/cyphers/pages/story/eclipse/3");
                return fragment3;
            }else if(position==5) {
                fragment4 = new EclipseFragment("http://cyphers.nexon.com/cyphers/pages/story/eclipse/4");
                return fragment4;
            }
            else if(position==6) {
                fragment5 = new EclipseFragment("http://cyphers.nexon.com/cyphers/pages/story/eclipse/5");
                return fragment5;
            }
            else if(position==7) {
                fragment6 = new EclipseFragment("http://cyphers.nexon.com/cyphers/pages/story/eclipse/6");
                return fragment6;
            }
            else if(position==8) {
                fragment7 = new EclipseFragment("http://cyphers.nexon.com/cyphers/pages/story/eclipse/7");
                return fragment7;
            }
            else if(position==9) {
                fragment8 = new EclipseFragment("http://cyphers.nexon.com/cyphers/pages/story/eclipse/8");
                return fragment8;
            }
            else if(position==10) {
                fragment9 = new EclipseFragment("http://cyphers.nexon.com/cyphers/pages/story/eclipse/9");
                return fragment9;
            }
            else{
                fragment10=new EclipseFragment("http://cyphers.nexon.com/cyphers/pages/story/eclipse/10");
                return fragment10;
            }
        }
        public int getCount(){
            return 12;
        }

    }
}
