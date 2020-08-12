package nexon.cyphers.app.cypher;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.os.Bundle;
import android.util.Log;

import java.util.LinkedList;
import java.util.List;

import me.everything.android.ui.overscroll.OverScrollDecoratorHelper;
import nexon.cyphers.app.INTERFACE.OnItemClick;
import nexon.cyphers.app.R;
import nexon.cyphers.app.adapter.CharacterAdpater;
import nexon.cyphers.app.databinding.ActivityCyphersInfoBinding;
import nexon.cyphers.app.model.Character.CharacterInformation;
import nexon.cyphers.app.model.Character.character;
import nexon.cyphers.app.model.CharacterInfo.CharacterModel;
import nexon.cyphers.app.retrofit2.RetrofitFactory;
import nexon.cyphers.app.retrofit2.RetrofitService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CyphersInfoActivity extends AppCompatActivity implements OnItemClick {
    CharacterAdpater adapter;
    ActivityCyphersInfoBinding binding;
    List<String> Id=new LinkedList<>(),name=new LinkedList<>();
    //67개의 캐릭터
    CharacterFragment frag0,frag1,frag2,frag3,frag4,frag5,frag6,frag7,frag8,frag9,frag10,frag11,frag12,frag13,frag14,frag15;
    CharacterFragment frag16,frag17,frag18,frag19,frag20,frag21,frag22,frag23,frag24,frag25,frag26,frag27,frag28,frag29;
    CharacterFragment frag30,frag31,frag32,frag33,frag34,frag35,frag36,frag37,frag38,frag39,frag40,frag41,frag42,frag43;
    CharacterFragment frag44,frag45,frag46,frag47,frag48,frag49,frag50,frag51,frag52,frag53,frag54,frag55,frag56,frag57;
    CharacterFragment frag58,frag59,frag60,frag61,frag62,frag63,frag64,frag65,frag66,frag67;
    private FragmentManager fm;
    private FragmentTransaction ft;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_cyphers_info);
        binding.setChpyersdata(this);
        RetrofitService networkService= RetrofitFactory.create();
        networkService.GetCharacterUniqueID(getString(R.string.API_KEY))
                .enqueue(new Callback<CharacterInformation>() {
                    @Override
                    public void onResponse(Call<CharacterInformation> call, Response<CharacterInformation> response) {
                        List<character> model=response.body().getRows();
                        for(int i=0; i<model.size(); i++)
                        {
                            Id.add(model.get(i).getCharacterId());
                            name.add(model.get(i).getCharacterName());
                        }
                        for(int i=0; i<Id.size(); i++)
                        {
                            CharacterModel data=new CharacterModel();
                            data.setCharacterId(Id.get(i));
                            data.setCharacterName(name.get(i));
                            adapter.addItem(data);
                        }
                        adapter.notifyDataSetChanged();
                    }
                    @Override
                    public void onFailure(Call<CharacterInformation> call, Throwable t) {
                    }
                });
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        binding.cyphersRecycler.setLayoutManager(linearLayoutManager);
        adapter=new CharacterAdpater();
        binding.cyphersRecycler.setAdapter(adapter);
        OverScrollDecoratorHelper.setUpOverScroll(binding.cyphersRecycler,OverScrollDecoratorHelper.ORIENTATION_HORIZONTAL);
        setFrag(0);
    }
    public void setFrag(int n){
        fm=getSupportFragmentManager();
        ft=fm.beginTransaction();
        switch (n){
            case 0:
                frag0=new CharacterFragment("http://cyphers.nexon.com/cyphers/pages/character/loras/1","http://static.cyphers.co.kr/img/ginfo/char_loras.jpg?1944","http://static.cyphers.co.kr/media/voicebox/loras/v_loras_enter.mp3",0);
                ft.replace(R.id.character_frame,frag0);
                ft.commit();
                break;
            case 1:
                frag1=new CharacterFragment("http://cyphers.nexon.com/cyphers/pages/character/huton/1","http://static.cyphers.co.kr/img/ginfo/char_huton.jpg?1944","http://static.cyphers.co.kr/media/voicebox/huton/v_huton_enter.mp3",1);
                ft.replace(R.id.character_frame,frag1);
                ft.commit();
                break;
            case 2:
                frag2=new CharacterFragment("http://cyphers.nexon.com/cyphers/pages/character/louis/1","http://static.cyphers.co.kr/img/ginfo/char_louis.jpg?1944","http://static.cyphers.co.kr/media/voicebox/louis/v_louis_enter.mp3",2);
                ft.replace(R.id.character_frame,frag2);
                ft.commit();
                break;
            case 3:
                frag3=new CharacterFragment("http://cyphers.nexon.com/cyphers/pages/character/tara/1","http://static.cyphers.co.kr/img/ginfo/char_tara.jpg?1944","http://static.cyphers.co.kr/media/voicebox/tara/v_tara_enter.mp3",3);
                ft.replace(R.id.character_frame,frag3);
                ft.commit();
                break;
            case 4:
                frag4=new CharacterFragment("http://cyphers.nexon.com/cyphers/pages/character/trivia/1","http://static.cyphers.co.kr/img/ginfo/char_trivia.jpg?1944","http://static.cyphers.co.kr/media/voicebox/trivia/v_trivia_enter.mp3",4);
                ft.replace(R.id.character_frame,frag4);
                ft.commit();
                break;

            case 5:
                frag5=new CharacterFragment("http://cyphers.nexon.com/cyphers/pages/character/cain/1","http://static.cyphers.co.kr/img/ginfo/char_cain.jpg?1944","http://static.cyphers.co.kr/media/voicebox/cain/v_cain_enter.mp3",5);
                ft.replace(R.id.character_frame,frag5);
                ft.commit();
                break;
            case 6:
                frag6=new CharacterFragment("http://cyphers.nexon.com/cyphers/pages/character/rena/1","http://static.cyphers.co.kr/img/ginfo/char_rena.jpg?1944","http://static.cyphers.co.kr/media/voicebox/rena/v_rena_enter.mp3",6);
                ft.replace(R.id.character_frame,frag6);
                ft.commit();
                break;
            case 7:
                frag7=new CharacterFragment("http://cyphers.nexon.com/cyphers/pages/character/drexler/1","http://static.cyphers.co.kr/img/ginfo/char_drexler.jpg?1944","http://static.cyphers.co.kr/media/voicebox/drexler/v_drexler_enter.mp3",7);
                ft.replace(R.id.character_frame,frag7);
                ft.commit();
                break;
            case 8:
                frag8=new CharacterFragment("http://cyphers.nexon.com/cyphers/pages/character/doyle/1","http://static.cyphers.co.kr/img/ginfo/char_doyle.jpg?1944","http://static.cyphers.co.kr/media/voicebox/doyle/v_doyle_enter.mp3",8);
                ft.replace(R.id.character_frame,frag8);
                ft.commit();
                break;
            case 9:
                frag9=new CharacterFragment("http://cyphers.nexon.com/cyphers/pages/character/thomas/1","http://static.cyphers.co.kr/img/ginfo/char_thomas.jpg?1944","http://static.cyphers.co.kr/media/voicebox/thomas/v_thomas_enter.mp3",9);
                ft.replace(R.id.character_frame,frag9);
                ft.commit();
                break;

            case 10:
                frag10=new CharacterFragment("http://cyphers.nexon.com/cyphers/pages/character/niobe/1","http://static.cyphers.co.kr/img/ginfo/char_niobe.jpg?1944","http://static.cyphers.co.kr/media/voicebox/niobe/v_niobe_enter.mp3",10);
                ft.replace(R.id.character_frame,frag10);
                ft.commit();
                break;

            case 11:
                frag11=new CharacterFragment("http://cyphers.nexon.com/cyphers/pages/character/shiva/1","http://static.cyphers.co.kr/img/ginfo/char_shiva.jpg?1944","http://static.cyphers.co.kr/media/voicebox/shiva/v_shiva_enter.mp3",11);
                ft.replace(R.id.character_frame,frag11);
                ft.commit();
                break;
            case 12:
                frag12=new CharacterFragment("http://cyphers.nexon.com/cyphers/pages/character/wesley/1","http://static.cyphers.co.kr/img/ginfo/char_wesley.jpg?1944","http://static.cyphers.co.kr/media/voicebox/wesley/v_wesley_enter.mp3",12);
                ft.replace(R.id.character_frame,frag12);
                ft.commit();
                break;


            case 13:
                frag13=new CharacterFragment("http://cyphers.nexon.com/cyphers/pages/character/stella/1","http://static.cyphers.co.kr/img/ginfo/char_stella.jpg?1944","http://static.cyphers.co.kr/media/voicebox/stella/v_stella_enter.mp3",13);
                ft.replace(R.id.character_frame,frag13);
                ft.commit();
                break;

            case 14:
                frag14=new CharacterFragment("http://cyphers.nexon.com/cyphers/pages/character/alicia/1","http://static.cyphers.co.kr/img/ginfo/char_alicia.jpg?1944","http://static.cyphers.co.kr/media/voicebox/alicia/v_alicia_enter.mp3",14);
                ft.replace(R.id.character_frame,frag14);
                ft.commit();
                break;
            case 15:
                frag15=new CharacterFragment("http://cyphers.nexon.com/cyphers/pages/character/clare/1","http://static.cyphers.co.kr/img/ginfo/char_clare.jpg?1944","http://static.cyphers.co.kr/media/voicebox/clare/v_clare_enter.mp3",15);
                ft.replace(R.id.character_frame,frag15);
                ft.commit();
                break;
            case 16:
                frag16=new CharacterFragment("http://cyphers.nexon.com/cyphers/pages/character/deimus/1","http://static.cyphers.co.kr/img/ginfo/char_deimus.jpg?1944","http://static.cyphers.co.kr/media/voicebox/deimus/v_deimus_enter.mp3",16);
                ft.replace(R.id.character_frame,frag16);
                ft.commit();
                break;
            case 17:
                frag17=new CharacterFragment("http://cyphers.nexon.com/cyphers/pages/character/eagle/1","http://static.cyphers.co.kr/img/ginfo/char_eagle.jpg?1944","http://static.cyphers.co.kr/media/voicebox/eagle/v_eagle_enter.mp3",17);
                ft.replace(R.id.character_frame,frag17);
                ft.commit();
                break;
            case 18:
                frag18=new CharacterFragment("http://cyphers.nexon.com/cyphers/pages/character/marlene/1","http://static.cyphers.co.kr/img/ginfo/char_marlene.jpg?1944","http://static.cyphers.co.kr/media/voicebox/marlene/v_marlene_enter.mp3",18);
                ft.replace(R.id.character_frame,frag18);
                ft.commit();
                break;
            case 19:
                frag19=new CharacterFragment("http://cyphers.nexon.com/cyphers/pages/character/charlotte/1","http://static.cyphers.co.kr/img/ginfo/char_charlotte.jpg?1944","http://static.cyphers.co.kr/media/voicebox/charlotte/v_charlotte_enter.mp3",19);
                ft.replace(R.id.character_frame,frag19);
                ft.commit();
                break;
            case 20:
                frag20=new CharacterFragment("http://cyphers.nexon.com/cyphers/pages/character/willard/1","http://static.cyphers.co.kr/img/ginfo/char_willard.jpg?1944","http://static.cyphers.co.kr/media/voicebox/willard/v_willard_enter.mp3",20);
                ft.replace(R.id.character_frame,frag20);
                ft.commit();
                break;
            case 21:
                frag21=new CharacterFragment("http://cyphers.nexon.com/cyphers/pages/character/lleyton/1","http://static.cyphers.co.kr/img/ginfo/char_lleyton.jpg?1944","http://static.cyphers.co.kr/media/voicebox/lleyton/v_lleyton_enter.mp3",21);
                ft.replace(R.id.character_frame,frag21);
                ft.commit();
                break;

            case 22:
                frag22=new CharacterFragment("http://cyphers.nexon.com/cyphers/pages/character/michelle/1","http://static.cyphers.co.kr/img/ginfo/char_michelle.jpg?1944","http://static.cyphers.co.kr/media/voicebox/michelle/v_michelle_enter.mp3",22);
                ft.replace(R.id.character_frame,frag22);
                ft.commit();
                break;
            case 23:
                frag23=new CharacterFragment("http://cyphers.nexon.com/cyphers/pages/character/rin/1","http://static.cyphers.co.kr/img/ginfo/char_rin.jpg?1944","http://static.cyphers.co.kr/media/voicebox/rin/v_rin_enter.mp3",23);
                ft.replace(R.id.character_frame,frag23);
                ft.commit();
                break;
            case 24:
                frag24=new CharacterFragment("http://cyphers.nexon.com/cyphers/pages/character/viktor/1","http://static.cyphers.co.kr/img/ginfo/char_viktor.jpg?1944","http://static.cyphers.co.kr/media/voicebox/viktor/v_viktor_enter_01.mp3",24);
                ft.replace(R.id.character_frame,frag24);
                ft.commit();
                break;
            case 25:
                frag25=new CharacterFragment("http://cyphers.nexon.com/cyphers/pages/character/carlos/1","http://static.cyphers.co.kr/img/ginfo/char_carlos.jpg?1944","http://static.cyphers.co.kr/media/voicebox/carlos/v_carlos_enter_01.mp3",25);
                ft.replace(R.id.character_frame,frag25);
                ft.commit();
                break;

            case 26:
                frag26=new CharacterFragment("http://cyphers.nexon.com/cyphers/pages/character/hotaru/1","http://static.cyphers.co.kr/img/ginfo/char_hotaru.jpg?1944","http://static.cyphers.co.kr/media/voicebox/hotaru/v_hotaru_enter.mp3",26);
                ft.replace(R.id.character_frame,frag26);
                ft.commit();
                break;
            case 27:
                frag27=new CharacterFragment("http://cyphers.nexon.com/cyphers/pages/character/trixie/1","http://static.cyphers.co.kr/img/ginfo/char_trixie.jpg?1944","http://static.cyphers.co.kr/media/voicebox/trixie/v_trixie_enter_01.mp3",27);
                ft.replace(R.id.character_frame,frag27);
                ft.commit();
                break;

            case 28:
                frag28=new CharacterFragment("http://cyphers.nexon.com/cyphers/pages/character/ricardo/1","http://static.cyphers.co.kr/img/ginfo/char_ricardo.jpg?1944","http://static.cyphers.co.kr/media/voicebox/ricardo/v_ricardo_enter_01.mp3",28);
                ft.replace(R.id.character_frame,frag28);
                ft.commit();
                break;
            case 29:
                frag29=new CharacterFragment("http://cyphers.nexon.com/cyphers/pages/character/camille/1","http://static.cyphers.co.kr/img/ginfo/char_camille.jpg?1944","http://static.cyphers.co.kr/media/voicebox/camille/v_camille_enter_01.mp3",29);
                ft.replace(R.id.character_frame,frag29);
                ft.commit();
                break;
            case 30:
                frag30=new CharacterFragment("http://cyphers.nexon.com/cyphers/pages/character/jannette/1","http://static.cyphers.co.kr/img/ginfo/char_jannette.jpg?1944","http://static.cyphers.co.kr/media/voicebox/jannette/v_jannette_enter_01.mp3",30);
                ft.replace(R.id.character_frame,frag30);
                ft.commit();
                break;
            case 31:
                frag31=new CharacterFragment("http://cyphers.nexon.com/cyphers/pages/character/peter/1","http://static.cyphers.co.kr/img/ginfo/char_peter.jpg?1944","http://static.cyphers.co.kr/media/voicebox/peter/v_peter_enter_01.mp3",31);
                ft.replace(R.id.character_frame,frag31);
                ft.commit();
                break;
            case 32:
                frag32=new CharacterFragment("http://cyphers.nexon.com/cyphers/pages/character/issac/1","http://static.cyphers.co.kr/img/ginfo/char_issac.jpg?1944","http://static.cyphers.co.kr/media/voicebox/issac/v_issac_enter_01.mp3",32);
                ft.replace(R.id.character_frame,frag32);
                ft.commit();
                break;
            case 33:
                frag33=new CharacterFragment("http://cyphers.nexon.com/cyphers/pages/character/rebecca/1","http://static.cyphers.co.kr/img/ginfo/char_rebecca.jpg?1944","http://static.cyphers.co.kr/media/voicebox/rebecca/v_rebecca_enter_01.mp3",33);
                ft.replace(R.id.character_frame,frag33);
                ft.commit();
                break;
            case 34:
                frag34=new CharacterFragment("http://cyphers.nexon.com/cyphers/pages/character/ellie/1","http://static.cyphers.co.kr/img/ginfo/char_ellie.jpg?1944","http://static.cyphers.co.kr/media/voicebox/ellie/v_ellie_enter_01.mp3",34);
                ft.replace(R.id.character_frame,frag34);
                ft.commit();
                break;
            case 35:
                frag35=new CharacterFragment("http://cyphers.nexon.com/cyphers/pages/character/martin/1","http://static.cyphers.co.kr/img/ginfo/char_martin.jpg?1944","http://static.cyphers.co.kr/media/voicebox/martin/v_martin_enter_01.mp3",35);
                ft.replace(R.id.character_frame,frag35);
                ft.commit();
                break;
            case 36:
                frag36=new CharacterFragment("http://cyphers.nexon.com/cyphers/pages/character/bruce/1","http://static.cyphers.co.kr/img/ginfo/char_bruce.jpg?1944","http://static.cyphers.co.kr/media/voicebox/bruce/v_bruce_enter_01.mp3",36);
                ft.replace(R.id.character_frame,frag36);
                ft.commit();
                break;
            case 37:
                frag37=new CharacterFragment("http://cyphers.nexon.com/cyphers/pages/character/mia/1","http://static.cyphers.co.kr/img/ginfo/char_mia.jpg?1944","http://static.cyphers.co.kr/media/voicebox/mia/v_mia_enter_01.mp3",37);
                ft.replace(R.id.character_frame,frag37);
                ft.commit();
                break;
            case 38:
                frag38=new CharacterFragment("http://cyphers.nexon.com/cyphers/pages/character/denise/1","http://static.cyphers.co.kr/img/ginfo/char_denise.jpg?1944","http://static.cyphers.co.kr/media/voicebox/denise/v_denise_enter_01.mp3",38);
                ft.replace(R.id.character_frame,frag38);
                ft.commit();
                break;
            case 39:
                frag39=new CharacterFragment("http://cyphers.nexon.com/cyphers/pages/character/gereon/1","http://static.cyphers.co.kr/img/ginfo/char_gereon.jpg?1944","http://static.cyphers.co.kr/media/voicebox/gereon/v_gereon_enter_01.mp3",39);
                ft.replace(R.id.character_frame,frag39);
                ft.commit();
                break;
            case 40:
                frag40=new CharacterFragment("http://cyphers.nexon.com/cyphers/pages/character/lucy/1","http://static.cyphers.co.kr/img/ginfo/char_lucy.jpg?1944","http://static.cyphers.co.kr/media/voicebox/lucy/v_lucy_enter_01.mp3",40);
                ft.replace(R.id.character_frame,frag40);
                ft.commit();
                break;
            case 41:
                frag41=new CharacterFragment("http://cyphers.nexon.com/cyphers/pages/character/tian/1","http://static.cyphers.co.kr/img/ginfo/char_tian.jpg?1944","http://static.cyphers.co.kr/media/voicebox/tian/v_tian_enter_01.mp3",41);
                ft.replace(R.id.character_frame,frag41);
                ft.commit();
                break;
            case 42:
                frag42=new CharacterFragment("http://cyphers.nexon.com/cyphers/pages/character/harang/1","http://static.cyphers.co.kr/img/ginfo/char_harang.jpg?1944","http://static.cyphers.co.kr/media/voicebox/harang/v_harang_enter_01.mp3",42);
                ft.replace(R.id.character_frame,frag42);
                ft.commit();
                break;
            case 43:
                frag43=new CharacterFragment("http://cyphers.nexon.com/cyphers/pages/character/j/1","http://static.cyphers.co.kr/img/ginfo/char_j.jpg?1944","http://static.cyphers.co.kr/media/voicebox/j/v_J_enter_01.mp3",43);
                ft.replace(R.id.character_frame,frag43);
                ft.commit();
                break;
            case 44:
                frag44=new CharacterFragment("http://cyphers.nexon.com/cyphers/pages/character/belzer/1","http://static.cyphers.co.kr/img/ginfo/char_belzer.jpg?1944","http://static.cyphers.co.kr/media/voicebox/belzer/v_belzer_enter_01.mp3",44);
                ft.replace(R.id.character_frame,frag44);
                ft.commit();
                break;
            case 45:
                frag45=new CharacterFragment("http://cyphers.nexon.com/cyphers/pages/character/richel/1","http://static.cyphers.co.kr/img/ginfo/char_richel.jpg?1944","http://static.cyphers.co.kr/media/voicebox/richel/v_richel_enter_01.mp3",45);
                ft.replace(R.id.character_frame,frag45);
                ft.commit();
                break;
            case 46:
                frag46=new CharacterFragment("http://cyphers.nexon.com/cyphers/pages/character/risa/1","http://static.cyphers.co.kr/img/ginfo/char_risa.jpg?1944","http://static.cyphers.co.kr/media/voicebox/risa/v_risa_enter_01.mp3",46);
                ft.replace(R.id.character_frame,frag46);
                ft.commit();
                break;
            case 47:
                frag47=new CharacterFragment("http://cyphers.nexon.com/cyphers/pages/character/rick/1","http://static.cyphers.co.kr/img/ginfo/char_rick.jpg?1944","http://static.cyphers.co.kr/media/voicebox/rick/v_rick_enter_01.mp3",47);
                ft.replace(R.id.character_frame,frag47);
                ft.commit();
                break;
            case 48:
                frag48=new CharacterFragment("http://cyphers.nexon.com/cyphers/pages/character/jekiel/1","http://static.cyphers.co.kr/img/ginfo/char_jekiel.jpg?1944","http://static.cyphers.co.kr/media/voicebox/jekiel/v_jekiel_enter_01.mp3",48);
                ft.replace(R.id.character_frame,frag48);
                ft.commit();
                break;
            case 49:
                frag49=new CharacterFragment("http://cyphers.nexon.com/cyphers/pages/character/tanya/1","http://static.cyphers.co.kr/img/ginfo/char_tanya.jpg?1944","http://static.cyphers.co.kr/media/voicebox/tanya/v_tanya_enter_01.mp3",49);
                ft.replace(R.id.character_frame,frag49);
                ft.commit();
                break;
            case 50:
                frag50=new CharacterFragment("http://cyphers.nexon.com/cyphers/pages/character/carol/1","http://static.cyphers.co.kr/img/ginfo/char_carol.jpg?1944","http://static.cyphers.co.kr/media/voicebox/carol/v_carol_enter_01.mp3",50);
                ft.replace(R.id.character_frame,frag50);
                ft.commit();
                break;
            case 51:
                frag51=new CharacterFragment("http://cyphers.nexon.com/cyphers/pages/character/lysander/1","http://static.cyphers.co.kr/img/ginfo/char_lysander.jpg?1944","http://static.cyphers.co.kr/media/voicebox/lysander/v_lysander_enter_01.mp3",51);
                ft.replace(R.id.character_frame,frag51);
                ft.commit();
                break;
            case 52:
                frag52=new CharacterFragment("http://cyphers.nexon.com/cyphers/pages/character/ludwig/1","http://static.cyphers.co.kr/img/ginfo/char_ludwig.jpg?1944","http://static.cyphers.co.kr/media/voicebox/ludwig/v_ludwig_enter_01.mp3",52);
                ft.replace(R.id.character_frame,frag52);
                ft.commit();
                break;
            case 53:
                frag53=new CharacterFragment("http://cyphers.nexon.com/cyphers/pages/character/melvin/1","http://static.cyphers.co.kr/img/ginfo/char_melvin.jpg?1944","http://static.cyphers.co.kr/media/voicebox/melvin/v_melvin_enter_01.mp3",53);
                ft.replace(R.id.character_frame,frag53);
                ft.commit();
                break;
            case 54:
                frag54=new CharacterFragment("http://cyphers.nexon.com/cyphers/pages/character/diana/1","http://static.cyphers.co.kr/img/ginfo/char_diana.jpg?1944","http://static.cyphers.co.kr/media/voicebox/diana/v_diana_enter_01.mp3",54);
                ft.replace(R.id.character_frame,frag54);
                ft.commit();
                break;
            case 55:
                frag55=new CharacterFragment("http://cyphers.nexon.com/cyphers/pages/character/clive/1","http://static.cyphers.co.kr/img/ginfo/char_clive.jpg?1944","http://static.cyphers.co.kr/media/voicebox/clive/v_clive_enter_01.mp3",55);
                ft.replace(R.id.character_frame,frag55);
                ft.commit();
                break;

            case 56:
                frag56=new CharacterFragment("http://cyphers.nexon.com/cyphers/pages/character/helena/1","http://static.cyphers.co.kr/img/ginfo/char_helena.jpg?1944","http://static.cyphers.co.kr/media/voicebox/helena/v_helena_enter_02.mp3",56);
                ft.replace(R.id.character_frame,frag56);
                ft.commit();
                break;
            case 57:
                frag57=new CharacterFragment("http://cyphers.nexon.com/cyphers/pages/character/eva/1","http://static.cyphers.co.kr/img/ginfo/char_eva.jpg?1944","http://static.cyphers.co.kr/media/voicebox/eva/v_eva_enter_02.mp3",57);
                ft.replace(R.id.character_frame,frag57);
                ft.commit();
                break;
            case 58:
                frag58=new CharacterFragment("http://cyphers.nexon.com/cyphers/pages/character/ron/1","http://static.cyphers.co.kr/img/ginfo/char_ron.jpg?1944","http://static.cyphers.co.kr/media/voicebox/ron/v_ron_enter_01.mp3",58);
                ft.replace(R.id.character_frame,frag58);
                ft.commit();
                break;
            case 59:
                frag59=new CharacterFragment("http://cyphers.nexon.com/cyphers/pages/character/leonor/1","http://static.cyphers.co.kr/img/ginfo/char_leonor.jpg?1944","http://static.cyphers.co.kr/media/voicebox/leonor/v_spawn_voice_01.mp3",59);
                ft.replace(R.id.character_frame,frag59);
                ft.commit();
                break;
            case 60:
                frag60=new CharacterFragment("http://cyphers.nexon.com/cyphers/pages/character/sidney/1","http://static.cyphers.co.kr/img/ginfo/char_sidney.jpg?1944","http://static.cyphers.co.kr/media/voicebox/sidney/v_spawn_voice_01.mp3",60);
                ft.replace(R.id.character_frame,frag60);
                ft.commit();
                break;
            case 61:
                frag61=new CharacterFragment("http://cyphers.nexon.com/cyphers/pages/character/tei/1","http://static.cyphers.co.kr/img/ginfo/char_tei.jpg?1944","http://static.cyphers.co.kr/media/voicebox/tei/v_spawn_voice_01.mp3",61);
                ft.replace(R.id.character_frame,frag61);
                ft.commit();
                break;
            case 62:
                frag62=new CharacterFragment("http://cyphers.nexon.com/cyphers/pages/character/timothy/1","http://static.cyphers.co.kr/img/ginfo/char_timothy.jpg?1944","http://static.cyphers.co.kr/media/voicebox/timothy/v_spawn_voice_01.mp3",62);
                ft.replace(R.id.character_frame,frag62);
                ft.commit();
                break;
            case 63:
                frag63=new CharacterFragment("http://cyphers.nexon.com/cyphers/pages/character/elfriede/1","http://static.cyphers.co.kr/img/ginfo/char_elfriede.jpg?1944","http://static.cyphers.co.kr/media/voicebox/elfriede/v_spawn_voice_01.mp3",63);
                ft.replace(R.id.character_frame,frag63);
                ft.commit();
                break;
            case 64:
                frag64=new CharacterFragment("http://cyphers.nexon.com/cyphers/pages/character/tisha/1","http://static.cyphers.co.kr/img/ginfo/char_tisha.jpg?1944","http://static.cyphers.co.kr/media/voicebox/tisha/v_spawn_voice_01.mp3",64);
                ft.replace(R.id.character_frame,frag64);
                ft.commit();
                break;
            case 65:
                frag65=new CharacterFragment("http://cyphers.nexon.com/cyphers/pages/character/carocho/1","http://static.cyphers.co.kr/img/ginfo/char_carocho.jpg?1944","http://static.cyphers.co.kr/media/voicebox/carocho/v_spawn_voice_01.mp3",65);
                ft.replace(R.id.character_frame,frag65);
                ft.commit();
                break;
            case 66:
                frag66=new CharacterFragment("http://cyphers.nexon.com/cyphers/pages/character/ryan/1","http://static.cyphers.co.kr/img/ginfo/char_ryan.jpg?1944","http://static.cyphers.co.kr/media/voicebox/ryan/v_spawn_voice_01.mp3",66);
                ft.replace(R.id.character_frame,frag66);
                ft.commit();
                break;
            case 67:
                frag67=new CharacterFragment("http://cyphers.nexon.com/cyphers/pages/character/watcher/1","http://static.cyphers.co.kr/img/ginfo/char_watcher.jpg?1944","http://static.cyphers.co.kr/media/voicebox/watcher/v_spawn_voice_01.mp3",67);
                ft.replace(R.id.character_frame,frag67);
                ft.commit();
                break;


        }
    }
    @Override
    public void onClick(int value) {

    }
}
