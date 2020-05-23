package nexon.cyphers.app;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import de.hdodenhof.circleimageview.CircleImageView;
import nexon.cyphers.app.Adapter.MatchingRecycleAdpater;
import nexon.cyphers.app.model.Character.CharacterInformation;
import nexon.cyphers.app.model.Character.character;
import nexon.cyphers.app.model.GameTypeModel;
import nexon.cyphers.app.model.Player;
import nexon.cyphers.app.model.PlayerModel;
import nexon.cyphers.app.model.PlayerInfo;
import nexon.cyphers.app.model.TotalRank;
import nexon.cyphers.app.model.TotalRankRow;
import nexon.cyphers.app.model.matchResultRecycleModel;
import nexon.cyphers.app.model.matching_record.matchingRecordModel;
import nexon.cyphers.app.retrofit2.RetrofitFactory;
import nexon.cyphers.app.retrofit2.RetrofitService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class matching_result extends AppCompatActivity {

    private static final String TAG ="matching_result" ;
    ImageView ratingResult;
    ImageView normalResult;
    RecyclerView recyclerView;
    MatchingRecycleAdpater adapter;
    Map<String,Map<String,Integer>> RecentTenMatch=new HashMap<>();
    NestedScrollView nestedScrollView;
    TextView PlayerNickname,PlayerRanking;
    TextView PlayerClanName,PlayerGrade;
    TextView PlayerTierName,PlayerRankingNowPoint;
    TextView PlayerRankingMaxPoint,PlayerRankingWinCount;
    TextView PlayerRankingLoseCount,PlayerRankingStopCount;
    TextView Player1banWinCount,Player1banLoseCount,Player1banStopcount;
    TextView PlayerRankingWinRate,Player1banWinRate;
    CircleImageView TierImage;
    CircleImageView mostlyRecentCharacterImage;
    boolean ratingClick=false;
    boolean normalClick=false;
    String nickname;
    private String playerUniqueID; /* Unique ID MUST */
    String mostlyRecentCharacter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matching_result);
        if(savedInstanceState==null){
            Intent intent=new Intent(this,LoadingActivity.class);
            intent.putExtra("where",1000);
            startActivity(intent);
        }
        nestedScrollView=findViewById(R.id.result_scrollview);
        nickname=getIntent().getStringExtra("nick");
        PlayerNickname=findViewById(R.id.player_nickname);
        PlayerRanking=findViewById(R.id.player_total_rank);
        PlayerClanName=findViewById(R.id.player_clan_name);
        PlayerGrade=findViewById(R.id.player_grade);
        PlayerTierName=findViewById(R.id.ranking_tier_name);
        PlayerRankingNowPoint=findViewById(R.id.ranking_now_point);
        PlayerRankingMaxPoint=findViewById(R.id.ranking_max_point);
        PlayerRankingWinCount=findViewById(R.id.ranking_winning_number);
        PlayerRankingLoseCount=findViewById(R.id.ranking_lose_number);
        PlayerRankingStopCount=findViewById(R.id.ranking_stop_number);
        Player1banWinCount=findViewById(R.id.ilban_winning_number);
        Player1banLoseCount=findViewById(R.id.ilban_lose_number);
        Player1banStopcount=findViewById(R.id.ilban_stop_number);
        TierImage=findViewById(R.id.tier_image);
        PlayerRankingWinRate=findViewById(R.id.ranking_win_rate);
        Player1banWinRate=findViewById(R.id.ilban_win_rate);
       // PlayerRecentCharacter=findViewById(R.id.Player_recent_character);
        mostlyRecentCharacterImage=findViewById(R.id.player_recent_most_character);
        recyclerView=findViewById(R.id.matching_record_recyclerview);
        ratingResult=findViewById(R.id.goratinggame);
        normalResult=findViewById(R.id.gonormalgame);

        ratingResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!ratingClick)
                {
                    PlayerAllMatchingRatingRecord();
                    ratingClick=true;
                    normalClick=false;
                }

            }
        });
        normalResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!normalClick)
                {
                    PlayerAllMatching1banRecord();
                    ratingClick=false;
                    normalClick=true;
                }

            }
        });

        /*
        Glide.with(this)
                .load(R.drawable.twilight)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(new SimpleTarget<GlideDrawable>() {
                    @Override
                    public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                        nestedScrollView.setBackground(resource)
                        ;
                    }
                });

         */
        RetrofitService networkService= RetrofitFactory.create();
        networkService.GetPlayerBasic(nickname,getString(R.string.API_KEY))
                .enqueue(new Callback<PlayerModel>() {
                    @Override
                    public void onResponse(Call<PlayerModel> call, Response<PlayerModel> response) {
                        if(!response.isSuccessful()){
                           // PlayerResult.setText(response.code());
                            return;
                        }
                        /*먼저 여기서 playerID를 얻어야 이후 기능들 작동*/
                        if(response.code()==200) {
                            if (response.body().getRows().size() == 0){
                                Toast.makeText(matching_result.this, "등록되지 않은 닉네임입니다.", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                            else {
                                List<Player> model = response.body().getRows();
                                String playerId = model.get(0).getPlayerId();
                                String nickname = model.get(0).getNickname();
                                int grade = model.get(0).getGrade();
                                PlayerNickname.setText(nickname);
                                PlayerGrade.append(Integer.toString(grade)+"급");
                                playerUniqueID = playerId;
                                /* 일단은 구현 나중에 스레드로 돌릴 함수들임.*/
                                PlayerInfo();
                                PlayerTotalRank();
                                PlayerAllMatchingRatingRecord();  // Default 공식전.
                                //getAllCharacterId();
                            }
                        }else if(response.code()==400){
                            Toast.makeText(matching_result.this, "요청에 대한 유효성 검증 실패 또는 파라미터 에러입니다.", Toast.LENGTH_SHORT).show();
                            finish();
                        }else if(response.code()==401){
                            Toast.makeText(matching_result.this, "인증 오류입니다", Toast.LENGTH_SHORT).show();
                            finish();
                        }else if(response.code()==404){
                            Toast.makeText(matching_result.this, "존재하지 않는 리소스 또는 페이지 입니다", Toast.LENGTH_SHORT).show();
                            finish();
                        }else if(response.code()==500){
                            Toast.makeText(matching_result.this, "시스템 오류입니다", Toast.LENGTH_SHORT).show();
                            finish();
                        }else if(response.code()==503){
                            Toast.makeText(matching_result.this, "시스템 점검중입니다", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                        // Log.d(TAG, "request 요청 URL: "+call.request().url());
                    }
                    @Override
                    public void onFailure(Call<PlayerModel> call, Throwable t) {
                        Log.d(TAG,"뀨뀨 오류 나갑니다:!!"+t.getMessage());
                    }
                });
    }

    public void PlayerInfo(){
        RetrofitService networkService= RetrofitFactory.create();
        networkService.GetPlayerDetail(playerUniqueID,getString(R.string.API_KEY))
                .enqueue(new Callback<PlayerInfo>() {
                    @Override
                    public void onResponse(Call<PlayerInfo> call, Response<PlayerInfo> response) {
                        if(!response.isSuccessful()){
                            Log.d(TAG, String.valueOf(response.code()));
                            return;
                        }
                        if(response.body().getClanName()==null)
                            PlayerClanName.append(" 없음 ");
                        else
                        PlayerClanName.append(response.body().getClanName());
                        PlayerTierName.setText(response.body().getTierName());
                        Log.d(TAG,"anr:"+response.body().getTierName());
                        if(response.body().getTierName()==null)
                            TierImage.setImageResource(R.drawable.newbie);
                        else if(response.body().getTierName().contains("JOKER"))
                            TierImage.setImageResource(R.drawable.joker_tier);
                        else if(response.body().getTierName().contains("BRONZE"))
                            TierImage.setImageResource(R.drawable.bronze_tier);
                        else if(response.body().getTierName().contains("SILVER"))
                            TierImage.setImageResource(R.drawable.silver_tier);
                        else if(response.body().getTierName().contains("GOLD"))
                            TierImage.setImageResource(R.drawable.gold_tier);
                        else if(response.body().getTierName().contains("ACE"))
                            TierImage.setImageResource(R.drawable.ace_tier);

                        PlayerRankingNowPoint.append(Integer.toString(response.body().getRatingPoint()));
                        PlayerRankingMaxPoint.append(Integer.toString(response.body().getMaxRatingPoint()));
                        List<GameTypeModel> models=response.body().getRecords();

                        for(int i=0; i<models.size(); i++)
                        {
                            if(i==0) {
                                PlayerRankingWinCount.append( Integer.toString(models.get(i).getWinCount()));
                                PlayerRankingLoseCount.append(Integer.toString(models.get(i).getLoseCount()));
                                PlayerRankingStopCount.append(Integer.toString(models.get(i).getStopCount()));
                                if(models.get(i).getWinCount()>0 && models.get(i).getLoseCount()>0)
                                PlayerRankingWinRate.append((models.get(i).getWinCount()*100)/(models.get(i).getWinCount()+models.get(i).getLoseCount())+"%");
                                else
                                    PlayerRankingWinRate.append("진행한 게임이 없어 표시되지 않습니다");
                            }
                            else{
                                Player1banWinCount.append(Integer.toString(models.get(i).getWinCount()));
                                Player1banLoseCount.append(Integer.toString(models.get(i).getLoseCount()));
                                Player1banStopcount.append(Integer.toString(models.get(i).getStopCount()));
                                if(models.get(i).getWinCount()>0 && models.get(i).getLoseCount()>0)
                                    Player1banWinRate.append((models.get(i).getWinCount()*100)/(models.get(i).getWinCount()+models.get(i).getLoseCount())+"%");
                                else
                                    Player1banWinRate.append("진행한 게임이 없어 표시되지 않습니다");
                            }
                        }
                        // Log.d(TAG, "request 요청 URL: "+call.request().url());
                    }
                    @Override
                    public void onFailure(Call<PlayerInfo> call, Throwable t) {
                        Log.d(TAG,"뀨뀨뀨 오류"+t.getMessage());
                    }
                });
    }
    public void PlayerTotalRank(){
        RetrofitService networkService= RetrofitFactory.create();
        networkService.GetTotalRANKING(playerUniqueID,getString(R.string.API_KEY))
                .enqueue(new Callback<TotalRankRow>() {
                    @Override
                    public void onResponse(Call<TotalRankRow> call, Response<TotalRankRow> response) {
                        List<TotalRank> model = response.body().getRows();
                        if(model.size()==0)
                            PlayerRanking.setText("랭킹내역이 없습니다.");
                        else
                            PlayerRanking.append(Integer.toString(model.get(0).getRank())+"위");
                    }
                    @Override
                    public void onFailure(Call<TotalRankRow> call, Throwable t) {
                    }
                });
    }

    //최근 10판 매치 기록.
    public void PlayerAllMatchingRatingRecord(){
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter=new MatchingRecycleAdpater();
        recyclerView.setAdapter(adapter);
        RetrofitService networkService=RetrofitFactory.create();
        networkService.GetPlayerMatchingRecord(playerUniqueID,"rating",getString(R.string.API_KEY))
                .enqueue(new Callback<matchingRecordModel>() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onResponse(Call<matchingRecordModel> call, Response<matchingRecordModel> response) {
                        Log.d(TAG, "request 요청 URL 성공:" + call.request().url());

                        if (response.body().getMatches().getRows().size() != 0) {
                            ArrayList<String> strings=new ArrayList<>();
                            for (int i = 0; i < response.body().getMatches().getRows().size(); i++) {
                                strings.add(response.body().getMatches().getRows().get(i).getPlayInfo().getCharacterId());
                                Log.d(TAG,"최근 한 캐릭터 id들: "+response.body().getMatches().getRows().get(i).getPlayInfo().getCharacterId());

                            }
                           Map<String,Integer> count=new HashMap<>();
                            for(String word:strings){
                                if(!count.containsKey(word))
                                count.put(word,1);
                                else{
                                    int value=count.get(word);
                                    value++;
                                    count.put(word,value);
                                }
                            }
                            List<String> mostFrequent=new ArrayList<>();
                            for(Map.Entry<String,Integer> e:count.entrySet()){
                                if(e.getValue()== Collections.max(count.values()))
                                    mostFrequent.add(e.getKey());
                            }
                            mostlyRecentCharacter=mostFrequent.get(0);
                            // Log.d(TAG,"가장 많이 나온 캐릭터의 ID 값은? : "+mostFrequent.get(0));
                            Glide.with(matching_result.this)
                                    .load("https://img-api.neople.co.kr/cy/characters/"+mostlyRecentCharacter)
                                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                                    .into(mostlyRecentCharacterImage);
                            for(int i=0; i<response.body().getMatches().getRows().size(); i++)
                            {
                                matchResultRecycleModel data=new matchResultRecycleModel();
                                data.setBattlePoint(Integer.toString(response.body().getMatches().getRows().get(i).getPlayInfo().getBattlePoint()));
                                data.setKDA(response.body().getMatches().getRows().get(i).getPlayInfo().getKillCount()+"킬 "+response.body().getMatches().getRows().get(i).getPlayInfo().getDeathCount()+"데스 "+response.body().getMatches().getRows().get(i).getPlayInfo().getAssistCount()+"어시");
                                double killassi=(double)(response.body().getMatches().getRows().get(i).getPlayInfo().getKillCount()+response.body().getMatches().getRows().get(i).getPlayInfo().getAssistCount());
                                double deathcnt=(double)(response.body().getMatches().getRows().get(i).getPlayInfo().getDeathCount());
                                data.setKDAPOINT("KDA:"+String.format("%.2f",killassi/deathcnt));
                                data.setCharacterNameLevel(response.body().getMatches().getRows().get(i).getPlayInfo().getCharacterName()+" 레벨: "+response.body().getMatches().getRows().get(i).getPlayInfo().getLevel());
                                data.setMatchingCharacterImage("https://img-api.neople.co.kr/cy/characters/"+response.body().getMatches().getRows().get(i).getPlayInfo().getCharacterId());

                                data.setDamagedPoint(((double)response.body().getMatches().getRows().get(i).getPlayInfo().getDamagePoint())/1000 +"k");
                                data.setDealingPoint(((double)response.body().getMatches().getRows().get(i).getPlayInfo().getAttackPoint())/1000 +"k");
                                data.setSightPoint(Integer.toString(response.body().getMatches().getRows().get(i).getPlayInfo().getSightPoint()));
                                data.setMatchingCharacterPositionAttribute1("https://img-api.neople.co.kr/cy/position-attributes/"+response.body().getMatches().getRows().get(i).getPosition().getAttribute().get(0).getId());
                                data.setMatchingCharacterPositionAttribute2("https://img-api.neople.co.kr/cy/position-attributes/"+response.body().getMatches().getRows().get(i).getPosition().getAttribute().get(1).getId());
                                data.setMatchingCharacterPositionAttribute3("https://img-api.neople.co.kr/cy/position-attributes/"+response.body().getMatches().getRows().get(i).getPosition().getAttribute().get(2).getId());
                                data.setPlaytime(response.body().getMatches().getRows().get(i).getDate());
                                data.setMatchingCharacterPosition(response.body().getMatches().getRows().get(i).getPosition().getName());
                                data.setMatchId(response.body().getMatches().getRows().get(i).getMatchId());   ///매칭 상세정보 보기위함.
                                String partycount="";
                                if(response.body().getMatches().getRows().get(i).getPlayInfo().getPartyUserCount()==0)
                                    partycount="솔로";
                                else
                                    partycount="파티: "+response.body().getMatches().getRows().get(i).getPlayInfo().getPartyUserCount()+"명";
                                data.setMatchingType("공식전 / "+partycount+" / "+response.body().getMatches().getRows().get(i).getMap().getName()+" / "+response.body().getMatches().getRows().get(i).getPlayInfo().getPlayTime()/60+"분   "+response.body().getMatches().getRows().get(i).getPlayInfo().getResult());
                                data.setMatchingResult(response.body().getMatches().getRows().get(i).getPlayInfo().getResult());
                                adapter.addItem(data);
                            }
                            adapter.notifyDataSetChanged();
                        }else
                        {
                                Glide.with(matching_result.this)
                                        .load(R.drawable.newbie)
                                        .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                                        .into(mostlyRecentCharacterImage);
                            }



                    }

                    @Override
                    public void onFailure(Call<matchingRecordModel> call, Throwable t) {
                        Log.d(TAG, "request 요청 URL 실패:"+call.request().url());

                    }
                });
    }
    public void PlayerAllMatching1banRecord(){
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter=new MatchingRecycleAdpater();
        recyclerView.setAdapter(adapter);
        RetrofitService networkService=RetrofitFactory.create();
        networkService.GetPlayerMatchingRecord(playerUniqueID,"normal",getString(R.string.API_KEY))
                .enqueue(new Callback<matchingRecordModel>() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onResponse(Call<matchingRecordModel> call, Response<matchingRecordModel> response) {
                        Log.d(TAG, "request 요청 URL 성공:" + call.request().url());

                        if (response.body().getMatches().getRows().size() != 0) {
                            ArrayList<String> strings=new ArrayList<>();
                            for (int i = 0; i < response.body().getMatches().getRows().size(); i++) {
                                strings.add(response.body().getMatches().getRows().get(i).getPlayInfo().getCharacterId());
                                Log.d(TAG,"최근 한 캐릭터 id들: "+response.body().getMatches().getRows().get(i).getPlayInfo().getCharacterId());
                               // PlayerRecentCharacter.append(response.body().getMatches().getRows().get(i).getPlayInfo().getCharacterName()+"\n");
                            }
                            Map<String,Integer> count=new HashMap<>();
                            for(String word:strings){
                                if(!count.containsKey(word))
                                    count.put(word,1);
                                else{
                                    int value=count.get(word);
                                    value++;
                                    count.put(word,value);
                                }
                            }
                            List<String> mostFrequent=new ArrayList<>();
                            for(Map.Entry<String,Integer> e:count.entrySet()){
                                if(e.getValue()== Collections.max(count.values()))
                                    mostFrequent.add(e.getKey());
                            }
                            mostlyRecentCharacter=mostFrequent.get(0);
                            // Log.d(TAG,"가장 많이 나온 캐릭터의 ID 값은? : "+mostFrequent.get(0));
                            Glide.with(matching_result.this)
                                    .load("https://img-api.neople.co.kr/cy/characters/"+mostlyRecentCharacter)
                                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                                    .into(mostlyRecentCharacterImage);
                            for(int i=0; i<response.body().getMatches().getRows().size(); i++)
                            {
                                matchResultRecycleModel data=new matchResultRecycleModel();
                                data.setBattlePoint(Integer.toString(response.body().getMatches().getRows().get(i).getPlayInfo().getBattlePoint()));
                                data.setKDA(response.body().getMatches().getRows().get(i).getPlayInfo().getKillCount()+"킬 "+response.body().getMatches().getRows().get(i).getPlayInfo().getDeathCount()+"데스 "+response.body().getMatches().getRows().get(i).getPlayInfo().getAssistCount()+"어시");
                                double killassi=(double)(response.body().getMatches().getRows().get(i).getPlayInfo().getKillCount()+response.body().getMatches().getRows().get(i).getPlayInfo().getAssistCount());
                                double deathcnt=(double)(response.body().getMatches().getRows().get(i).getPlayInfo().getDeathCount());
                                data.setKDAPOINT("KDA:"+String.format("%.2f",killassi/deathcnt));
                                data.setCharacterNameLevel(response.body().getMatches().getRows().get(i).getPlayInfo().getCharacterName()+" 레벨: "+response.body().getMatches().getRows().get(i).getPlayInfo().getLevel());
                                data.setMatchingCharacterImage("https://img-api.neople.co.kr/cy/characters/"+response.body().getMatches().getRows().get(i).getPlayInfo().getCharacterId());
                                data.setDamagedPoint(((double)response.body().getMatches().getRows().get(i).getPlayInfo().getDamagePoint())/1000 +"k");
                                data.setDealingPoint(((double)response.body().getMatches().getRows().get(i).getPlayInfo().getAttackPoint())/1000 +"k");
                                data.setSightPoint(Integer.toString(response.body().getMatches().getRows().get(i).getPlayInfo().getSightPoint()));
                                data.setMatchingCharacterPositionAttribute1("https://img-api.neople.co.kr/cy/position-attributes/"+response.body().getMatches().getRows().get(i).getPosition().getAttribute().get(0).getId());
                                data.setMatchingCharacterPositionAttribute2("https://img-api.neople.co.kr/cy/position-attributes/"+response.body().getMatches().getRows().get(i).getPosition().getAttribute().get(1).getId());
                                data.setMatchingCharacterPositionAttribute3("https://img-api.neople.co.kr/cy/position-attributes/"+response.body().getMatches().getRows().get(i).getPosition().getAttribute().get(2).getId());
                                data.setPlaytime(response.body().getMatches().getRows().get(i).getDate());
                                data.setMatchingCharacterPosition(response.body().getMatches().getRows().get(i).getPosition().getName());
                                data.setMatchId(response.body().getMatches().getRows().get(i).getMatchId());   ///매칭 상세정보 보기위함.
                                String partycount="";
                                if(response.body().getMatches().getRows().get(i).getPlayInfo().getPartyUserCount()==0)
                                    partycount="솔로";
                                else
                                    partycount="파티: "+response.body().getMatches().getRows().get(i).getPlayInfo().getPartyUserCount()+"명";
                                data.setMatchingType("일반전 / "+partycount+" / "+response.body().getMatches().getRows().get(i).getMap().getName()+" / "+response.body().getMatches().getRows().get(i).getPlayInfo().getPlayTime()/60+"분   "+response.body().getMatches().getRows().get(i).getPlayInfo().getResult());
                                data.setMatchingResult(response.body().getMatches().getRows().get(i).getPlayInfo().getResult());
                                adapter.addItem(data);
                            }
                            adapter.notifyDataSetChanged();


                        }else
                        {
                            Glide.with(matching_result.this)
                                    .load(R.drawable.newbie)
                                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                                    .into(mostlyRecentCharacterImage);
                        }
                    }

                    @Override
                    public void onFailure(Call<matchingRecordModel> call, Throwable t) {
                        Log.d(TAG, "request 요청 URL 실패:"+call.request().url());

                    }
                });
    }
    /*
    public void getAllCharacterId(){
        RetrofitService networkService=RetrofitFactory.create();
        networkService.GetCharacterUniqueID(getString(R.string.API_KEY))
                .enqueue(new Callback<CharacterInformation>() {
                    @Override
                    public void onResponse(Call<CharacterInformation> call, Response<CharacterInformation> response) {
                        Log.d(TAG, "request 요청 성공 URL : "+call.request().url());
                        List<character> model=response.body().getRows();
                        Log.d(TAG,"로라스를 출력해야 정상 출력값: "+model.get(0).getCharacterName());
                    }

                    @Override
                    public void onFailure(Call<CharacterInformation> call, Throwable t) {
                        Log.d(TAG, "request 요청 실패 URL : "+call.request().url());
                    }
                });
    }

     */
}
