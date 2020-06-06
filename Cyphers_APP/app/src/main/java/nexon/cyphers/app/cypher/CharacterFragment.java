package nexon.cyphers.app.cypher;

import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import nexon.cyphers.app.R;
import nexon.cyphers.app.adapter.VoiceAdpater;
import nexon.cyphers.app.databinding.CharacterFragmentBinding;
import nexon.cyphers.app.model.Character.Voice;


public class CharacterFragment extends Fragment {
    VoiceAdpater premiumadpater,defaultadpater,mesageadpater;
    CharacterFragmentBinding binding;
    public  String targetsite;
    public String targetProfileUrl;
    public String voiceurl;
    //String charactername;
    int charactername;
    MediaPlayer mediaPlayer;
    public CharacterFragment(String targetsite,String targetProfileUrl,String voiceurl,int charactername) {
        this.targetsite = targetsite;
        this.targetProfileUrl=targetProfileUrl;
        this.voiceurl=voiceurl;
        this.charactername=charactername;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding= CharacterFragmentBinding.inflate(inflater,container,false);
        binding.setCharacterFragment(this);
        getdata();
        Glide.with(getActivity()).load(targetProfileUrl).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(binding.characterProfileImage);
        mediaPlayer=new MediaPlayer();


        playing(voiceurl);
        binding.characterProfileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playing(voiceurl);
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
                        for (Element element : skillExplain)
                            ListExplain.add(element.text());
                        for (Element element : skillName)
                            ListSkillName.add(element.text());
                        for (Element element : tip)
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
                            if (skillExplain.size() == 9) {
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
    public void playing(String url)
    {
        try{
            mediaPlayer.setDataSource(url);
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
    /*   내가 원하는 부분의 보이스박스 태그는 동적이라고함. 데이터분석을 해야 얻을수있다고한다. */
    public void onClickPremium(View view){
        premiumadpater=new VoiceAdpater();
        String[] premiumAction=getResources().getStringArray(R.array.premiumVoiceAction);
        String[] premiumVoiceUrl=new String[0];
        String[] premiumExplain = new String[0];
        /* 아래두개는 동적*/
        switch (charactername){
            case 0:
                premiumVoiceUrl=getResources().getStringArray(R.array.premiumVoicelorasUrl);
                premiumExplain=getResources().getStringArray(R.array.premiumVoicelorasExplain);
                break;
            case 1:
                premiumVoiceUrl=getResources().getStringArray(R.array.premiumVoicehutonUrl);
                premiumExplain=getResources().getStringArray(R.array.premiumVoicehutonExplain);
                break;
            case 2:
                premiumVoiceUrl=getResources().getStringArray(R.array.premiumVoicelouisUrl);
                premiumExplain=getResources().getStringArray(R.array.premiumVoicelouisExplain);
                break;
            case 3:
                premiumVoiceUrl=getResources().getStringArray(R.array.premiumVoicetaraUrl);
                premiumExplain=getResources().getStringArray(R.array.premiumVoicetaraExplain);
                break;
            case 4:
                premiumVoiceUrl=getResources().getStringArray(R.array.premiumVoicetriviaUrl);
                premiumExplain=getResources().getStringArray(R.array.premiumVoicetriviaExplain);
                break;
            case 5:
                premiumVoiceUrl=getResources().getStringArray(R.array.premiumVoicecainUrl);
                premiumExplain=getResources().getStringArray(R.array.premiumVoicecainExplain);
                break;
            case 6:
                premiumVoiceUrl=getResources().getStringArray(R.array.premiumVoicerenaUrl);
                premiumExplain=getResources().getStringArray(R.array.premiumVoicerenaExplain);
                break;
            case 7:
                premiumVoiceUrl=getResources().getStringArray(R.array.premiumVoicedrexlerUrl);
                premiumExplain=getResources().getStringArray(R.array.premiumVoicedrexlerExplain);
                break;
            case 8:
                premiumVoiceUrl=getResources().getStringArray(R.array.premiumVoicedoyleUrl);
                premiumExplain=getResources().getStringArray(R.array.premiumVoicedoyleExplain);
                break;
            case 9:
                premiumVoiceUrl=getResources().getStringArray(R.array.premiumVoicethomasUrl);
                premiumExplain=getResources().getStringArray(R.array.premiumVoicethomasExplain);
                break;
            case 10:
                premiumVoiceUrl=getResources().getStringArray(R.array.premiumViceniobeUrl);
                premiumExplain=getResources().getStringArray(R.array.premiumVoiceniobeExplain);
                break;
            case 11:
                premiumVoiceUrl=getResources().getStringArray(R.array.premiumVoiceshivaUrl);
                premiumExplain=getResources().getStringArray(R.array.premiumVoiceshivaExplain);
                break;
            case 12:
                premiumVoiceUrl=getResources().getStringArray(R.array.premiumVoiceweslyUrl);
                premiumExplain=getResources().getStringArray(R.array.premiumVoicewesleyExplain);
                break;
            case 13:
                premiumVoiceUrl=getResources().getStringArray(R.array.premiumVoicestellaUrl);
                premiumExplain=getResources().getStringArray(R.array.premiumVoicestellaExplain);
                break;
                //
            case 14:
                premiumVoiceUrl=getResources().getStringArray(R.array.premiumVoicealiciaUrl);
                premiumExplain=getResources().getStringArray(R.array.premiumVoicealiciaExplain);
                break;
            case 15:
                premiumVoiceUrl=getResources().getStringArray(R.array.premiumVoiclareUrl);
                premiumExplain=getResources().getStringArray(R.array.premiumVoiceclareExplain);
                break;
            case 16:
                premiumVoiceUrl=getResources().getStringArray(R.array.premiumVoicedeimusUrl);
                premiumExplain=getResources().getStringArray(R.array.premiumVoicedeimusExplain);
                break;
            case 17:
                premiumVoiceUrl=getResources().getStringArray(R.array.premiumVoiceeagleUrl);
                premiumExplain=getResources().getStringArray(R.array.premiumVoiceeagleExplain);
                break;
            case 18:
                premiumVoiceUrl=getResources().getStringArray(R.array.premiumVoicemarleneUrl);
                premiumExplain=getResources().getStringArray(R.array.premiumVoicemarleneExplain);
                break;
            case 19:
                premiumVoiceUrl=getResources().getStringArray(R.array.premiumVoicecharlotteUrl);
                premiumExplain=getResources().getStringArray(R.array.premiumVoicecharlotteExplain);
                break;
            case 20:
                premiumVoiceUrl=getResources().getStringArray(R.array.premiumVoicewillardUrl);
                premiumExplain=getResources().getStringArray(R.array.premiumVoicewillardExplain);
                break;
            case 21:
                premiumVoiceUrl=getResources().getStringArray(R.array.premiumVoicelleytonUrl);
                premiumExplain=getResources().getStringArray(R.array.premiumVoicelleytonExplain);
                break;
            case 22:
                premiumVoiceUrl=getResources().getStringArray(R.array.premiumVoicemichelleUrl);
                premiumExplain=getResources().getStringArray(R.array.premiumVoicemichelleExplain);
                break;
            case 23:
                premiumVoiceUrl=getResources().getStringArray(R.array.premiumVoicerinUrl);
                premiumExplain=getResources().getStringArray(R.array.premiumVoicerinExplain);
                break;
            case 24:
                premiumVoiceUrl=getResources().getStringArray(R.array.premiumVoicevictorUrl);
                premiumExplain=getResources().getStringArray(R.array.premiumVoicevictorExplain);
                break;
            case 25:
                premiumVoiceUrl=getResources().getStringArray(R.array.premiumVoicecarlosUrl);
                premiumExplain=getResources().getStringArray(R.array.premiumVoicecarlosExplain);
                break;
            case 26:
                premiumVoiceUrl=getResources().getStringArray(R.array.premiumVoicehotaruUrl);
                premiumExplain=getResources().getStringArray(R.array.premiumVoicehotaruExplain);
                break;
            case 27:
                premiumVoiceUrl=getResources().getStringArray(R.array.premiumVoicexrixieUrl);
                premiumExplain=getResources().getStringArray(R.array.premiumVoicetrixieExplain);
                break;
            case 28:
                premiumVoiceUrl=getResources().getStringArray(R.array.premiumVoicericardoUrl);
                premiumExplain=getResources().getStringArray(R.array.premiunVoicericardoExplain);
                break;
            case 29:
                premiumVoiceUrl=getResources().getStringArray(R.array.premiumVoicecamilleUrl);
                premiumExplain=getResources().getStringArray(R.array.premiumVoicecamilleExplain);
                break;
            case 30:
                premiumVoiceUrl=getResources().getStringArray(R.array.premiumVoicejannetteUrl);
                premiumExplain=getResources().getStringArray(R.array.premiumVoicejannetteExplain);
                break;
            case 31:
                premiumVoiceUrl=getResources().getStringArray(R.array.premiumVoicepeterUrl);
                premiumExplain=getResources().getStringArray(R.array.premiumVoicepeterExplain);
                break;
            case 32:
                premiumVoiceUrl=getResources().getStringArray(R.array.premiumVoiceissacUrl);
                premiumExplain=getResources().getStringArray(R.array.premiumVoiceissacExplain);
                break;
            case 33:
                premiumVoiceUrl=getResources().getStringArray(R.array.premiumVoicerebeccaUrl);
                premiumExplain=getResources().getStringArray(R.array.premiumVoicerebeccaExplain);
                break;
            case 34:
                premiumVoiceUrl=getResources().getStringArray(R.array.premiumVoiceellieUrl);
                premiumExplain=getResources().getStringArray(R.array.premiumVoiceellieExplain);
                break;
            case 35:
                premiumVoiceUrl=getResources().getStringArray(R.array.premiumVoicemartinUrl);
                premiumExplain=getResources().getStringArray(R.array.premiumVoicemartinExplain);
                break;
            case 36:
                premiumVoiceUrl=getResources().getStringArray(R.array.premiumbruceUrl);
                premiumExplain=getResources().getStringArray(R.array.premiumbruceExplain);
                break;
            case 37:
                premiumVoiceUrl=getResources().getStringArray(R.array.premiumVoicemiaUrl);
                premiumExplain=getResources().getStringArray(R.array.premiumVoicemiaExplain);
                break;
            case 38:
                premiumVoiceUrl=getResources().getStringArray(R.array.premiumVoicedenisUrl);
                premiumExplain=getResources().getStringArray(R.array.premiumVoicedeniseExplain);
                break;
            case 39:
                premiumVoiceUrl=getResources().getStringArray(R.array.premiumVoicegereonUrl);
                premiumExplain=getResources().getStringArray(R.array.premiumVoicegereonExplain);
                break;
            case 40:
                premiumVoiceUrl=getResources().getStringArray(R.array.premiumVoicelucyUrl);
                premiumExplain=getResources().getStringArray(R.array.premiumVoicelucyExplain);
                break;
            case 41:
                premiumVoiceUrl=getResources().getStringArray(R.array.premiumVoicetianUrl);
                premiumExplain=getResources().getStringArray(R.array.premiumVoicetianExplain);
                break;
            case 42:
                premiumVoiceUrl=getResources().getStringArray(R.array.premiumVoiceharangUrl);
                premiumExplain=getResources().getStringArray(R.array.premiumVoiceharangExplain);
                break;
            case 43:
                premiumVoiceUrl=getResources().getStringArray(R.array.premiumVoicejUrl);
                premiumExplain=getResources().getStringArray(R.array.premiumVoicejExplain);
                break;
            case 44:
                premiumVoiceUrl=getResources().getStringArray(R.array.premiumVoicebelzerUrl);
                premiumExplain=getResources().getStringArray(R.array.premiumVoicebelzerExplain);
                break;
                //
            case 45:
                premiumVoiceUrl=getResources().getStringArray(R.array.premiumVoicerichelUrl);
                premiumExplain=getResources().getStringArray(R.array.premiumVoicerichelExplain);
                break;
            case 46:
                premiumVoiceUrl=getResources().getStringArray(R.array.premiumVoicerisaUrl);
                premiumExplain=getResources().getStringArray(R.array.premiumVoicerisaExplain);
                break;
            case 47:
                premiumVoiceUrl=getResources().getStringArray(R.array.premiumVoicerickUrl);
                premiumExplain=getResources().getStringArray(R.array.premiumVoicerickExplain);
                break;
            case 48:
                premiumVoiceUrl=getResources().getStringArray(R.array.premiumVoicejekielUrl);
                premiumExplain=getResources().getStringArray(R.array.premiumVoicejekielExplain);
                break;
            case 49:
                premiumVoiceUrl=getResources().getStringArray(R.array.premiumVoicetanyaUrl);
                premiumExplain=getResources().getStringArray(R.array.premiumVoicetanyaExplain);
                break;
            case 50:
                premiumVoiceUrl=getResources().getStringArray(R.array.premiumVoicecarolUrl);
                premiumExplain=getResources().getStringArray(R.array.premiumVoicecarolExplain);
                break;

            //TODO 라이샌더

        }
        for(int i=0; i<premiumAction.length; i++)
        {
            Voice model=new Voice();
            model.setAction(premiumAction[i]);
            model.setVoiceUrl(premiumVoiceUrl[i]);
            model.setExplain(premiumExplain[i]);
            model.setClickUrl("http://static.cyphers.co.kr/img/data/btn_play2.png");
            premiumadpater.addItem(model);
        }
        premiumadpater.notifyDataSetChanged();
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
        binding.voiceRecycle.setLayoutManager(linearLayoutManager);
        binding.voiceRecycle.setAdapter(premiumadpater);
        binding.voiceRecycle.setVisibility(View.VISIBLE);
        binding.voiceRecycle.requestFocus();
    }
    public void onClickDefalut(View view){
        defaultadpater=new VoiceAdpater();

        /* 여기만 세개가 동적*/

        String[] defaultAction=new String[0];
        String[] defaultVoiceUrl=new String[0];
        String[] defaultExplain = new String[0];
        switch (charactername){
            case 0:
                defaultAction=getResources().getStringArray(R.array.defaultlorasAction);
                defaultVoiceUrl=getResources().getStringArray(R.array.defaultlorasVoiceUrl);
                defaultExplain=getResources().getStringArray(R.array.defaultlorasExplain);
                break;
            case 1:
                defaultAction=getResources().getStringArray(R.array.defaulthutonAction);
                defaultVoiceUrl=getResources().getStringArray(R.array.defaulthutonVoiceUrl);
                defaultExplain=getResources().getStringArray(R.array.defaulthutonExplain);
                break;
            case 2:
                defaultAction=getResources().getStringArray(R.array.defaultlouisAction);
                defaultVoiceUrl=getResources().getStringArray(R.array.defaultlouisVoiceUrl);
                defaultExplain=getResources().getStringArray(R.array.defaultlouisExplain);
                break;
            case 3:
                defaultAction=getResources().getStringArray(R.array.defaulttaraAction);
                defaultVoiceUrl=getResources().getStringArray(R.array.defaulttaraVoiceUrl);
                defaultExplain=getResources().getStringArray(R.array.defaulttaraExplain);
                break;
            case 4:
                defaultAction=getResources().getStringArray(R.array.defaulttriviaAction);
                defaultVoiceUrl=getResources().getStringArray(R.array.defaulttrivialVoiceUrl);
                defaultExplain=getResources().getStringArray(R.array.defaulttriviaExplain);
                break;
            case 5:
                defaultAction=getResources().getStringArray(R.array.defaultcainAction);
                defaultVoiceUrl=getResources().getStringArray(R.array.defaultcainVoiceUrl);
                defaultExplain=getResources().getStringArray(R.array.defaultcainExplain);
                break;
            case 6:
                defaultAction=getResources().getStringArray(R.array.defaultrenaAction);
                defaultVoiceUrl=getResources().getStringArray(R.array.defaultrenaVoiceUrl);
                defaultExplain=getResources().getStringArray(R.array.defaultrenaExplain);
                break;
            case 7:
                defaultAction=getResources().getStringArray(R.array.defaultdrexlerAction);
                defaultVoiceUrl=getResources().getStringArray(R.array.defaultdrexlerVoiceUrl);
                defaultExplain=getResources().getStringArray(R.array.defaultdrexlerExplain);
                break;
            case 8:
                defaultAction=getResources().getStringArray(R.array.defaultdoyleAction);
                defaultVoiceUrl=getResources().getStringArray(R.array.defaultdoyleVoiceUrl);
                defaultExplain=getResources().getStringArray(R.array.defaultdoyleExplain);
                break;
            case 9:
                defaultAction=getResources().getStringArray(R.array.defaultthomasAction);
                defaultVoiceUrl=getResources().getStringArray(R.array.defaultthomasUrl);
                defaultExplain=getResources().getStringArray(R.array.defaultthomasExplain);
                break;
            case 10:
                defaultAction=getResources().getStringArray(R.array.defaultniobeAction);
                defaultVoiceUrl=getResources().getStringArray(R.array.defaultniobeUrl);
                defaultExplain=getResources().getStringArray(R.array.defaultniobeExplain);
                break;
            case 11:
                defaultAction=getResources().getStringArray(R.array.defaultshivaAction);
                defaultVoiceUrl=getResources().getStringArray(R.array.defaultshivaUrl);
                defaultExplain=getResources().getStringArray(R.array.defaultshivaExplain);
                break;
            case 12:
                defaultAction=getResources().getStringArray(R.array.defaultweslyAction);
                defaultVoiceUrl=getResources().getStringArray(R.array.defaultweslyUrl);
                defaultExplain=getResources().getStringArray(R.array.defaultweslyExplain);
                break;
            case 13:
                defaultAction=getResources().getStringArray(R.array.defaultstellaAction);
                defaultVoiceUrl=getResources().getStringArray(R.array.defaultstellaUrl);
                defaultExplain=getResources().getStringArray(R.array.defaultstellaExplain);
                break;
                //
            case 14:
                defaultAction=getResources().getStringArray(R.array.defaultaliciaAction);
                defaultVoiceUrl=getResources().getStringArray(R.array.defaultaliciaUrl);
                defaultExplain=getResources().getStringArray(R.array.defaultaliciaExplain);
                break;
            case 15:
                defaultAction=getResources().getStringArray(R.array.defaultclareAction);
                defaultVoiceUrl=getResources().getStringArray(R.array.defaultclareUrl);
                defaultExplain=getResources().getStringArray(R.array.defaultclareExplain);
                break;
            case 16:
                defaultAction=getResources().getStringArray(R.array.defaultdeimusAction);
                defaultVoiceUrl=getResources().getStringArray(R.array.defaultdeimusUrl);
                defaultExplain=getResources().getStringArray(R.array.defaultdeimusExplain);
                break;
            case 17:
                defaultAction=getResources().getStringArray(R.array.defaulteagleAction);
                defaultVoiceUrl=getResources().getStringArray(R.array.defaulteagleUrl);
                defaultExplain=getResources().getStringArray(R.array.defaulteagleExplain);
                break;
            case 18:
                defaultAction=getResources().getStringArray(R.array.defaultmarleneAction);
                defaultVoiceUrl=getResources().getStringArray(R.array.defaultmarleneUrl);
                defaultExplain=getResources().getStringArray(R.array.defaultmarleneExplain);
                break;
            case 19:
                defaultAction=getResources().getStringArray(R.array.defaultcharlotteAction);
                defaultVoiceUrl=getResources().getStringArray(R.array.defaultcharlotteUrl);
                defaultExplain=getResources().getStringArray(R.array.defaultcharlotteExplain);
                break;
            case 20:
                defaultAction=getResources().getStringArray(R.array.defaultwillardAction);
                defaultVoiceUrl=getResources().getStringArray(R.array.defaultwillardUrl);
                defaultExplain=getResources().getStringArray(R.array.defaultwillardExplain);
                break;
            case 21:
                defaultAction=getResources().getStringArray(R.array.defaultlleytonAction);
                defaultVoiceUrl=getResources().getStringArray(R.array.defaultlleytonUrl);
                defaultExplain=getResources().getStringArray(R.array.defaultlleytonExplain);
                break;
            case 22:
                defaultAction=getResources().getStringArray(R.array.defaultmichelleAction);
                defaultVoiceUrl=getResources().getStringArray(R.array.defaultmichelleUrl);
                defaultExplain=getResources().getStringArray(R.array.defaultmichelleExplain);
                break;
            case 23:
                defaultAction=getResources().getStringArray(R.array.defaultrinExplain);
                defaultVoiceUrl=getResources().getStringArray(R.array.defaultrinUrl);
                defaultExplain=getResources().getStringArray(R.array.defaultrinExplain);
                break;
            case 24:
                defaultAction=getResources().getStringArray(R.array.defaultvictorAction);
                defaultVoiceUrl=getResources().getStringArray(R.array.defaultvictorUrl);
                defaultExplain=getResources().getStringArray(R.array.defaultvictorExlain);
                break;
            case 25:
                defaultAction=getResources().getStringArray(R.array.defaultcarlosAction);
                defaultVoiceUrl=getResources().getStringArray(R.array.defaultcarlosUrl);
                defaultExplain=getResources().getStringArray(R.array.defaultcarlosExplain);
                break;
            case 26:
                defaultAction=getResources().getStringArray(R.array.defaulthotaruAction);
                defaultVoiceUrl=getResources().getStringArray(R.array.defaulthotaruUrl);
                defaultExplain=getResources().getStringArray(R.array.defaulthotaruExplain);
                break;
            case 27:
                defaultAction=getResources().getStringArray(R.array.defaulttrixieAction);
                defaultVoiceUrl=getResources().getStringArray(R.array.defaulttrixieUrl);
                defaultExplain=getResources().getStringArray(R.array.defaulttrixieExplain);
                break;
            case 28:
                defaultAction=getResources().getStringArray(R.array.defaultricardoAction);
                defaultVoiceUrl=getResources().getStringArray(R.array.defaultricardoUrl);
                defaultExplain=getResources().getStringArray(R.array.defaultricardoExplain);
                break;
            case 29:
                defaultAction=getResources().getStringArray(R.array.defaultcamilleAction);
                defaultVoiceUrl=getResources().getStringArray(R.array.defaultcamilleUrl);
                defaultExplain=getResources().getStringArray(R.array.defaultcamilleExplain);
                break;
            case 30:
                defaultAction=getResources().getStringArray(R.array.defaultjannetteAction);
                defaultVoiceUrl=getResources().getStringArray(R.array.defaultjannetteUrl);
                defaultExplain=getResources().getStringArray(R.array.defaultjannetteExplain);
                break;
            case 31:
                defaultAction=getResources().getStringArray(R.array.defaultpeterAction);
                defaultVoiceUrl=getResources().getStringArray(R.array.defaultpeterUrl);
                defaultExplain=getResources().getStringArray(R.array.defaultpeterExplain);
                break;
            case 32:
                defaultAction=getResources().getStringArray(R.array.defaultissacAction);
                defaultVoiceUrl=getResources().getStringArray(R.array.defaultissacUrl);
                defaultExplain=getResources().getStringArray(R.array.defaultissacExplain);
                break;
            case 33:
                defaultAction=getResources().getStringArray(R.array.defaultrebeccaAction);
                defaultVoiceUrl=getResources().getStringArray(R.array.defaultrebeccaUrl);
                defaultExplain=getResources().getStringArray(R.array.defaultrebeccaExplain);
                break;
            case 34:
                defaultAction=getResources().getStringArray(R.array.defaultellieAction);
                defaultVoiceUrl=getResources().getStringArray(R.array.defaultellieUrl);
                defaultExplain=getResources().getStringArray(R.array.defaultellieExplain);
                break;
            case 35:
                defaultAction=getResources().getStringArray(R.array.defaultmartinAction);
                defaultVoiceUrl=getResources().getStringArray(R.array.defaultmartinUrl);
                defaultExplain=getResources().getStringArray(R.array.defaultmartinExplain);
                break;
            case 36:
                defaultAction=getResources().getStringArray(R.array.defaultbruceAction);
                defaultVoiceUrl=getResources().getStringArray(R.array.defaultbruceUrl);
                defaultExplain=getResources().getStringArray(R.array.defaultbruceExplain);
                break;
            case 37:
                defaultAction=getResources().getStringArray(R.array.defaultmiaAction);
                defaultVoiceUrl=getResources().getStringArray(R.array.defaultmiaUrl);
                defaultExplain=getResources().getStringArray(R.array.defaultmiaExplain);
                break;
                //
            case 38:
                defaultAction=getResources().getStringArray(R.array.defaultdeniseAction);
                defaultVoiceUrl=getResources().getStringArray(R.array.defaultdenisUrl);
                defaultExplain=getResources().getStringArray(R.array.defaultdenisExplain);
                break;
            case 39:
                defaultAction=getResources().getStringArray(R.array.defaultgereonAction);
                defaultVoiceUrl=getResources().getStringArray(R.array.defaultgereonUrl);
                defaultExplain=getResources().getStringArray(R.array.defaultgereonExplain);
                break;
            case 40:
                defaultAction=getResources().getStringArray(R.array.defaultlucyAction);
                defaultVoiceUrl=getResources().getStringArray(R.array.defaultlucyUrl);
                defaultExplain=getResources().getStringArray(R.array.defaultlucyExplain);
                break;
            case 41:
                defaultAction=getResources().getStringArray(R.array.defaulttianAction);
                defaultVoiceUrl=getResources().getStringArray(R.array.defaulttianUrl);
                defaultExplain=getResources().getStringArray(R.array.defaulttianExplain);
                break;
            case 42:
                defaultAction=getResources().getStringArray(R.array.defaultharangAction);
                defaultVoiceUrl=getResources().getStringArray(R.array.defaultharangUrl);
                defaultExplain=getResources().getStringArray(R.array.defaultharangExplain);
                break;
            case 43:
                defaultAction=getResources().getStringArray(R.array.defaultjAction);
                defaultVoiceUrl=getResources().getStringArray(R.array.defaultjUrl);
                defaultExplain=getResources().getStringArray(R.array.defaultjExplain);
                break;
            case 44:
                defaultAction=getResources().getStringArray(R.array.defaultbelzerAction);
                defaultVoiceUrl=getResources().getStringArray(R.array.defaultbelzerUrl);
                defaultExplain=getResources().getStringArray(R.array.defaultbelzerExplain);
                break;
                //
            case 45:
                defaultAction=getResources().getStringArray(R.array.defaultrichelAction);
                defaultVoiceUrl=getResources().getStringArray(R.array.defaultrichelUrl);
                defaultExplain=getResources().getStringArray(R.array.defaultrichelExplain);
                break;
            case 46:
                defaultAction=getResources().getStringArray(R.array.defaultrisaAction);
                defaultVoiceUrl=getResources().getStringArray(R.array.defaultrisaUrl);
                defaultExplain=getResources().getStringArray(R.array.defaultrisaExplain);
                break;
            case 47:
                defaultAction=getResources().getStringArray(R.array.defaultrickAction);
                defaultVoiceUrl=getResources().getStringArray(R.array.defaultrickUrl);
                defaultExplain=getResources().getStringArray(R.array.defaultrickExplain);
                break;
            case 48:
                defaultAction=getResources().getStringArray(R.array.defaultjekielAction);
                defaultVoiceUrl=getResources().getStringArray(R.array.defaultjekielUrl);
                defaultExplain=getResources().getStringArray(R.array.defaultjekielExplain);
                break;

            case 49:
                defaultAction=getResources().getStringArray(R.array.defaulttanyaAciton);
                defaultVoiceUrl=getResources().getStringArray(R.array.defaulttanyaUrl);
                defaultExplain=getResources().getStringArray(R.array.defaulttanyaExplain);
                break;
            case 50:
                defaultAction=getResources().getStringArray(R.array.defaultcarolAction);
                defaultVoiceUrl=getResources().getStringArray(R.array.defaultcarolUrl);
                defaultExplain=getResources().getStringArray(R.array.defaultcarolExplain);
                break;

        }
        for(int i=0; i<defaultAction.length; i++)
        {
            Voice model=new Voice();
            model.setAction(defaultAction[i]);
            model.setVoiceUrl(defaultVoiceUrl[i]);
            model.setExplain(defaultExplain[i]);
            model.setClickUrl("http://static.cyphers.co.kr/img/data/btn_play2.png");
            defaultadpater.addItem(model);
        }
        defaultadpater.notifyDataSetChanged();
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
        binding.voiceRecycle.setLayoutManager(linearLayoutManager);
        binding.voiceRecycle.setAdapter(defaultadpater);
        binding.voiceRecycle.setVisibility(View.VISIBLE);
        binding.voiceRecycle.requestFocus();

    }
    public void onclickMessage(View view){
        mesageadpater=new VoiceAdpater();
        String[] MessageAction=getResources().getStringArray(R.array.messageAction);
        /* 아래두개는 동적*/
        String[] MessageVoiceUrl=new String[0];
        String[] MessageExplain=new String[0];
        /*미아는 동적 크기가2라서 */
        switch (charactername){
            case 0:
                MessageVoiceUrl=getResources().getStringArray(R.array.messagelorasUrl);
                MessageExplain=getResources().getStringArray(R.array.messagelorasExplain);
                break;
            case 1:
                MessageVoiceUrl=getResources().getStringArray(R.array.messagehutonUrl);
                MessageExplain=getResources().getStringArray(R.array.messagehutonExplain);
                break;
            case 2:
                MessageVoiceUrl=getResources().getStringArray(R.array.messagelouisUrl);
                MessageExplain=getResources().getStringArray(R.array.messagelouisExplain);
                break;
            case 3:
                MessageVoiceUrl=getResources().getStringArray(R.array.messagetaraUrl);
                MessageExplain=getResources().getStringArray(R.array.messagetaraExplain);
                break;
            case 4:
                MessageVoiceUrl=getResources().getStringArray(R.array.messagetriviaUrl);
                MessageExplain=getResources().getStringArray(R.array.messagetriviaExplain);
                break;
            case 5:
                MessageVoiceUrl=getResources().getStringArray(R.array.messagecainUrl);
                MessageExplain=getResources().getStringArray(R.array.messagecainExplain);
                break;
            case 6:
                MessageVoiceUrl=getResources().getStringArray(R.array.messagerenaUrl);
                MessageExplain=getResources().getStringArray(R.array.messagerenaExplain);
                break;
            case 7:
                MessageVoiceUrl=getResources().getStringArray(R.array.messagedrexlerUrl);
                MessageExplain=getResources().getStringArray(R.array.messagedrexlerExplain);
                break;
            case 8:
                MessageVoiceUrl=getResources().getStringArray(R.array.messagedoyleUrl);
                MessageExplain=getResources().getStringArray(R.array.messagedoyleExplain);
                break;
            case 9:
                MessageVoiceUrl=getResources().getStringArray(R.array.messagethomasUrl);
                MessageExplain=getResources().getStringArray(R.array.messagethomasExplain);
                break;
            case 10:
                MessageVoiceUrl=getResources().getStringArray(R.array.messageniobeUrl);
                MessageExplain=getResources().getStringArray(R.array.messageniobeExplain);
                break;
            case 11:
                MessageVoiceUrl=getResources().getStringArray(R.array.messageshivaUrl);
                MessageExplain=getResources().getStringArray(R.array.messageshivaExplain);
                break;
            case 12:
                MessageVoiceUrl=getResources().getStringArray(R.array.messageweslyUrl);
                MessageExplain=getResources().getStringArray(R.array.messageweslyExplain);
                break;
            case 13:
                MessageVoiceUrl=getResources().getStringArray(R.array.messagestellaUrl);
                MessageExplain=getResources().getStringArray(R.array.messagestellaExplain);
                break;
                //
            case 14:
                MessageVoiceUrl=getResources().getStringArray(R.array.messagealiciaUrl);
                MessageExplain=getResources().getStringArray(R.array.messagealiciaExplain);
                break;
            case 15:
                MessageVoiceUrl=getResources().getStringArray(R.array.messageclareUrl);
                MessageExplain=getResources().getStringArray(R.array.messageclareExplain);
                break;
            case 16:
                MessageVoiceUrl=getResources().getStringArray(R.array.messagedimusUrl);
                MessageExplain=getResources().getStringArray(R.array.messagedeimusExplain);
                break;
            case 17:
                MessageVoiceUrl=getResources().getStringArray(R.array.messageeagleUrl);
                MessageExplain=getResources().getStringArray(R.array.messageeagleExplain);
                break;
            case 18:
                MessageVoiceUrl=getResources().getStringArray(R.array.messagemarleneUrl);
                MessageExplain=getResources().getStringArray(R.array.messagemarleneExpain);
                break;
            case 19:
                MessageVoiceUrl=getResources().getStringArray(R.array.messagecharlotteUrl);
                MessageExplain=getResources().getStringArray(R.array.messagecharlotteExplain);
                break;
            case 20:
                MessageVoiceUrl=getResources().getStringArray(R.array.messagewillardUrl);
                MessageExplain=getResources().getStringArray(R.array.messagewillardExplain);
                break;
            case 21:
                MessageVoiceUrl=getResources().getStringArray(R.array.messagelleytonUrl);
                MessageExplain=getResources().getStringArray(R.array.messagelleytonExplain);
                break;
            case 22:
                MessageVoiceUrl=getResources().getStringArray(R.array.messagemichelleUrl);
                MessageExplain=getResources().getStringArray(R.array.messagemichelleUrl);
                break;
            case 23:
                MessageVoiceUrl=getResources().getStringArray(R.array.messagerinUrl);
                MessageExplain=getResources().getStringArray(R.array.messagerinExplain);
                break;
            case 24:
                MessageVoiceUrl=getResources().getStringArray(R.array.messagevictorUrl);
                MessageExplain=getResources().getStringArray(R.array.messagevictorExplain);
                break;
            case 25:
                MessageVoiceUrl=getResources().getStringArray(R.array.messagecarlosUrl);
                MessageExplain=getResources().getStringArray(R.array.messagecarlosExplain);
                break;
            case 26:
                MessageVoiceUrl=getResources().getStringArray(R.array.messagehotaruUrl);
                MessageExplain=getResources().getStringArray(R.array.messagehotaruExplain);
                break;
            case 27:
                MessageVoiceUrl=getResources().getStringArray(R.array.messagetrixieUrl);
                MessageExplain=getResources().getStringArray(R.array.messagetrixieExplain);
                break;
            case 28:
                MessageVoiceUrl=getResources().getStringArray(R.array.messagericardoUrl);
                MessageExplain=getResources().getStringArray(R.array.messagericardoExplain);
                break;
            case 29:
                MessageVoiceUrl=getResources().getStringArray(R.array.messagecamilleUrl);
                MessageExplain=getResources().getStringArray(R.array.messagecamilleExplain);
                break;
            case 30:
                MessageVoiceUrl=getResources().getStringArray(R.array.messagejannetteUrl);
                MessageExplain=getResources().getStringArray(R.array.messagejannetteExplain);
                break;
            case 31:
                MessageVoiceUrl=getResources().getStringArray(R.array.messagepeterUrl);
                MessageExplain=getResources().getStringArray(R.array.messagepeterExplain);
                break;
            case 32:
                MessageVoiceUrl=getResources().getStringArray(R.array.messageissacUrl);
                MessageExplain=getResources().getStringArray(R.array.messageissacExplain);
                break;
            case 33:
                MessageVoiceUrl=getResources().getStringArray(R.array.messagerebeccaUrl);
                MessageExplain=getResources().getStringArray(R.array.messagerebeccaExplain);
                break;
            case 34:
                MessageVoiceUrl=getResources().getStringArray(R.array.messageellieUrl);
                MessageExplain=getResources().getStringArray(R.array.messageellieExplain);
                break;
            case 35:
                MessageVoiceUrl=getResources().getStringArray(R.array.messagemartinUrl);
                MessageExplain=getResources().getStringArray(R.array.messagemartinExplain);
                break;
            case 36:
                MessageVoiceUrl=getResources().getStringArray(R.array.messagebruceUrl);
                MessageExplain=getResources().getStringArray(R.array.messagebruceExplain);
                break;
            case 37:
                MessageAction=getResources().getStringArray(R.array.messagemiaAction);
                MessageVoiceUrl=getResources().getStringArray(R.array.messagemiaUrl);
                MessageExplain=getResources().getStringArray(R.array.messagemiaExplain);
                break;
                //
            case 38:
                MessageVoiceUrl=getResources().getStringArray(R.array.messagedeniseUrl);
                MessageExplain=getResources().getStringArray(R.array.messagedeniseExplain);
                break;
            case 39:
                MessageVoiceUrl=getResources().getStringArray(R.array.messagegereonUrl);
                MessageExplain=getResources().getStringArray(R.array.messagegereonExplain);
                break;
            case 40:
                MessageVoiceUrl=getResources().getStringArray(R.array.messagelucyUrl);
                MessageExplain=getResources().getStringArray(R.array.messagelucyExplain);
                break;
            case 41:
                MessageVoiceUrl=getResources().getStringArray(R.array.messagetianUrl);
                MessageExplain=getResources().getStringArray(R.array.messagetianExplain);
                break;
            case 42:
                MessageVoiceUrl=getResources().getStringArray(R.array.messageharangUrl);
                MessageExplain=getResources().getStringArray(R.array.messageharangExplain);
                break;
            case 43:
                MessageVoiceUrl=getResources().getStringArray(R.array.messagejUrl);
                MessageExplain=getResources().getStringArray(R.array.messagejExplain);
                break;
            case 44:
                MessageVoiceUrl=getResources().getStringArray(R.array.messagebelzerUrl);
                MessageExplain=getResources().getStringArray(R.array.messagebelzerExplain);
                break;
                //
            case 45:
                MessageVoiceUrl=getResources().getStringArray(R.array.messagerichelUrl);
                MessageExplain=getResources().getStringArray(R.array.messagerichelExplain);
                break;
            case 46:
                MessageVoiceUrl=getResources().getStringArray(R.array.messagerisaUrl);
                MessageExplain=getResources().getStringArray(R.array.messagerisaExplain);
                break;
            case 47:
                MessageVoiceUrl=getResources().getStringArray(R.array.messagerickUrl);
                MessageExplain=getResources().getStringArray(R.array.messagerickExplain);
                break;
            case 48:
                MessageVoiceUrl=getResources().getStringArray(R.array.messagejekielUrl);
                MessageExplain=getResources().getStringArray(R.array.messagejekielExplain);
                break;
            case 49:
                MessageAction=getResources().getStringArray(R.array.messagetanyaAction);
                MessageVoiceUrl=getResources().getStringArray(R.array.messagetanyaUrl);
                MessageExplain=getResources().getStringArray(R.array.messagetanyaExplain);
                break;
            case 50:
                MessageAction=getResources().getStringArray(R.array.messagecarolAction);
                MessageVoiceUrl=getResources().getStringArray(R.array.messagecarolUrl);
                MessageExplain=getResources().getStringArray(R.array.messagecarolExplain);
                break;


        }
        for(int i=0; i<MessageAction.length; i++)
        {
            Voice model=new Voice();
            model.setAction(MessageAction[i]);
            model.setVoiceUrl(MessageVoiceUrl[i]);
            model.setExplain(MessageExplain[i]);
            model.setClickUrl("http://static.cyphers.co.kr/img/data/btn_play2.png");
            mesageadpater.addItem(model);
        }
        mesageadpater.notifyDataSetChanged();
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
        binding.voiceRecycle.setLayoutManager(linearLayoutManager);
        binding.voiceRecycle.setAdapter(mesageadpater);
        binding.voiceRecycle.setVisibility(View.VISIBLE);
        binding.voiceRecycle.requestFocus();
    }

}
