package nexon.cyphers.app.cypher;

import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;


import nexon.cyphers.app.databinding.CharacterFragmentBinding;


public class CharacterFragment extends Fragment {

    CharacterFragmentBinding binding;
    public  String targetsite;
    public String targetProfileUrl;
    public String voiceurl;
    MediaPlayer mediaPlayer;
    public CharacterFragment(String targetsite,String targetProfileUrl,String voiceurl) {
        this.targetsite = targetsite;
        this.targetProfileUrl=targetProfileUrl;
        this.voiceurl=voiceurl;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding= CharacterFragmentBinding.inflate(inflater,container,false);
        binding.setCharacterFragment(this);
        getdata();
        Glide.with(getActivity()).load(targetProfileUrl).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(binding.characterProfileImage);
        mediaPlayer=new MediaPlayer();

        playing();
        binding.characterProfileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playing();
            }
        });
        return binding.getRoot();
    }
    private void getdata(){
        crawlingcharacter crawling=new crawlingcharacter();
        crawling.execute();
    }

    private class crawlingcharacter extends AsyncTask<Void , Void, Void> {
        ArrayList<String> ListExplain=new ArrayList<>();
        ArrayList<String> ListSkillName=new ArrayList<>();
        ArrayList<String> ListCharacter=new ArrayList<>();

        @Override
        protected Void doInBackground(Void... voids) {
            try {

                Document doc = Jsoup.connect(targetsite).get();
                final Elements skillExplain = doc.select("td.info");
                final Elements skillName=doc.select("th.sv_open a");
                final Elements tip=doc.select("div.char_attack table tbody td");
                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        for(Element element:skillExplain)
                            ListExplain.add(element.text());
                        for(Element element:skillName)
                            ListSkillName.add(element.text());
                        for(Element element:tip)
                            ListCharacter.add(element.text());
                        binding.skillname1.setText(ListSkillName.get(0));
                        binding.skillname2.setText(ListSkillName.get(1));
                        binding.skillname3.setText(ListSkillName.get(2));
                        binding.skillname4.setText(ListSkillName.get(3));
                        binding.skillname5.setText(ListSkillName.get(4));
                        binding.skillname6.setText(ListSkillName.get(5));
                        binding.skillname7.setText(ListSkillName.get(6));
                        binding.skillname8.setText(ListSkillName.get(7));
                        binding.skillexplain1.setText(ListExplain.get(0));
                        binding.skillexplain2.setText(ListExplain.get(1));
                        binding.skillexplain3.setText(ListExplain.get(2));
                        binding.skillexplain4.setText(ListExplain.get(3));
                        binding.skillexplain5.setText(ListExplain.get(4));
                        binding.skillexplain6.setText(ListExplain.get(5));
                        binding.skillexplain7.setText(ListExplain.get(6));
                        binding.skillexplain8.setText(ListExplain.get(7));
                        if(skillExplain.size()==9)
                        {
                            binding.skillexplain9.setText(ListExplain.get(8));
                            binding.skillname9.setText(ListSkillName.get(8));
                            binding.onlynine.setVisibility(View.VISIBLE);
                        }
                        binding.characterStrong.setText(ListCharacter.get(0));
                        binding.characterWeak.setText(ListCharacter.get(1));
                        binding.characterTip.setText(ListCharacter.get(2));
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
    public void playing()
    {
        try{
            mediaPlayer.setDataSource(voiceurl);
            mediaPlayer.prepare();
        }catch(Exception e){
            e.printStackTrace();
        }
        mediaPlayer.start();
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}
