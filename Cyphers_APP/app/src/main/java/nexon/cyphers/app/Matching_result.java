package nexon.cyphers.app;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nexon.cyphers.app.adapter.MatchingRecycleAdpater;
import nexon.cyphers.app.databinding.ActivityMatchingResultBinding;
import nexon.cyphers.app.model.GameTypeModel;
import nexon.cyphers.app.model.Player;
import nexon.cyphers.app.model.PlayerInfo;
import nexon.cyphers.app.model.PlayerModel;
import nexon.cyphers.app.model.RecyclerViewModel.matchResultRecycleModel;
import nexon.cyphers.app.model.TotalRank;
import nexon.cyphers.app.model.TotalRankRow;
import nexon.cyphers.app.model.matching_record.matchingRecordModel;
import nexon.cyphers.app.retrofit2.RetrofitFactory;
import nexon.cyphers.app.retrofit2.RetrofitService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Matching_result extends AppCompatActivity {

    private static final String TAG ="matching_result" ;
    MatchingRecycleAdpater adapter;
    Map<String,Map<String,Integer>> RecentTenMatch=new HashMap<>();
    boolean ratingClick=false;
    boolean normalClick=false;
    /* dataBinding */
    public String PlayerTierName;
    public  String nickname;
    public String playerRanking;
    private String playerUniqueID; /* Unique ID MUST */
    String mostlyRecentCharacter;
    ActivityMatchingResultBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_matching_result);
        binding.setResultdata(this);
        if(savedInstanceState==null){
            Intent intent=new Intent(this,LoadingActivity.class);
            intent.putExtra("where",1000);
            startActivity(intent);
        }


        nickname=getIntent().getStringExtra("nick");
        RetrofitService networkService= RetrofitFactory.create();
        networkService.GetPlayerBasic(nickname,getString(R.string.API_KEY))
                .enqueue(new Callback<PlayerModel>() {
                    @Override
                    public void onResponse(Call<PlayerModel> call, Response<PlayerModel> response) {
                        if(!response.isSuccessful()){
                            return;
                        }
                        if(response.code()==200) {
                            if (response.body().getRows().size() == 0){
                                Toast.makeText(Matching_result.this, "등록되지 않은 닉네임입니다.", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                            else {
                                List<Player> model = response.body().getRows();
                                String playerId = model.get(0).getPlayerId();
                                String nickname = model.get(0).getNickname();
                                int grade = model.get(0).getGrade();
                                binding.playerGrade.append(grade +"급");
                                playerUniqueID = playerId;
                                /*  UI 스레드에서 처리  */
                                PlayerInfo();
                                PlayerTotalRank();
                                PlayerAllMatchingRatingRecord();
                            }
                        }else if(response.code()==400){
                            Toast.makeText(Matching_result.this, "요청에 대한 유효성 검증 실패 또는 파라미터 에러입니다.", Toast.LENGTH_SHORT).show();
                            finish();
                        }else if(response.code()==401){
                            Toast.makeText(Matching_result.this, "인증 오류입니다", Toast.LENGTH_SHORT).show();
                            finish();
                        }else if(response.code()==404){
                            Toast.makeText(Matching_result.this, "존재하지 않는 리소스 또는 페이지 입니다", Toast.LENGTH_SHORT).show();
                            finish();
                        }else if(response.code()==500){
                            Toast.makeText(Matching_result.this, "시스템 오류입니다", Toast.LENGTH_SHORT).show();
                            finish();
                        }else if(response.code()==503){
                            Toast.makeText(Matching_result.this, "시스템 점검중입니다", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }
                    @Override
                    public void onFailure(Call<PlayerModel> call, Throwable t) {
                        Log.d(TAG,"뀨뀨 오류 나갑니다:!!"+t.getMessage());
                        Toast.makeText(Matching_result.this, "인터넷 상태가 원활하지 않습니다", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
    }
    public void PlayerInfo(){
        new Thread(new Runnable() {
            @Override
            public void run() {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
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
                                            binding.playerClanName.append(" 없음 ");
                                        else
                                            binding.playerClanName.append(response.body().getClanName());
                                        binding.rankingTierName.setText(response.body().getTierName());

                                        if(response.body().getTierName()==null)
                                            binding.tierImage.setImageResource(R.drawable.newbie);
                                        else if(response.body().getTierName().contains("JOKER"))
                                            binding.tierImage.setImageResource(R.drawable.joker_tier);
                                        else if(response.body().getTierName().contains("BRONZE"))
                                            binding.tierImage.setImageResource(R.drawable.bronze_tier);
                                        else if(response.body().getTierName().contains("SILVER"))
                                            binding.tierImage.setImageResource(R.drawable.silver_tier);
                                        else if(response.body().getTierName().contains("GOLD"))
                                            binding.tierImage.setImageResource(R.drawable.gold_tier);
                                        else if(response.body().getTierName().contains("ACE"))
                                            binding.tierImage.setImageResource(R.drawable.ace_tier);
                                        binding.rankingNowPoint.append(Integer.toString(response.body().getRatingPoint()));
                                        binding.rankingMaxPoint.append(Integer.toString(response.body().getMaxRatingPoint()));
                                        List<GameTypeModel> models=response.body().getRecords();
                                        for(int i=0; i<models.size(); i++)
                                        {
                                            if(i==0) {
                                                binding.rankingWinningNumber.append( Integer.toString(models.get(i).getWinCount()));
                                                binding.rankingLoseNumber.append(Integer.toString(models.get(i).getLoseCount()));
                                                binding.rankingStopNumber.append(Integer.toString(models.get(i).getStopCount()));
                                                if(models.get(i).getWinCount()>0 && models.get(i).getLoseCount()>0)
                                                    binding.rankingWinRate.append((models.get(i).getWinCount()*100)/(models.get(i).getWinCount()+models.get(i).getLoseCount())+"%");
                                                else
                                                    binding.rankingWinRate.append("진행한 게임이 없어 표시되지 않습니다");
                                            }
                                            else{
                                                binding.ilbanWinningNumber.append(Integer.toString(models.get(i).getWinCount()));
                                                binding.ilbanLoseNumber.append(Integer.toString(models.get(i).getLoseCount()));
                                                binding.ilbanStopNumber.append(Integer.toString(models.get(i).getStopCount()));
                                                if(models.get(i).getWinCount()>0 && models.get(i).getLoseCount()>0)
                                                    binding.ilbanWinRate.append((models.get(i).getWinCount()*100)/(models.get(i).getWinCount()+models.get(i).getLoseCount())+"%");
                                                else
                                                    binding.ilbanWinRate.append("진행한 게임이 없어 표시되지 않습니다");
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
                });
            }
        }).start();

    }
    public void PlayerTotalRank(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        RetrofitService networkService= RetrofitFactory.create();
                        networkService.GetTotalRANKING(playerUniqueID,getString(R.string.API_KEY))
                                .enqueue(new Callback<TotalRankRow>() {
                                    @Override
                                    public void onResponse(Call<TotalRankRow> call, Response<TotalRankRow> response) {
                                        List<TotalRank> model = response.body().getRows();
                                        if(model.size()==0)
                                            binding.playerTotalRank.append("\n랭킹내역이 없습니다.");
                                        else
                                            binding.playerTotalRank.append(model.get(0).getRank()+"위");
                                    }
                                    @Override
                                    public void onFailure(Call<TotalRankRow> call, Throwable t) {
                                    }}
                                    );
                    }
                });
            }
        }).start();

    }

    //최근 10판 매치 기록.
    public void PlayerAllMatchingRatingRecord(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(Matching_result.this);
                        binding.matchingRecordRecyclerview.setLayoutManager(linearLayoutManager);
                        adapter=new MatchingRecycleAdpater();
                        binding.matchingRecordRecyclerview.setAdapter(adapter);
                        RetrofitService networkService=RetrofitFactory.create();
                        networkService.GetPlayerMatchingRecord(playerUniqueID,"rating",70,getString(R.string.API_KEY))
                                .enqueue(new Callback<matchingRecordModel>() {
                                    @RequiresApi(api = Build.VERSION_CODES.N)
                                    @Override
                                    public void onResponse(Call<matchingRecordModel> call, Response<matchingRecordModel> response) {
                                        Log.d(TAG,Integer.toString(response.body().getMatches().getRows().size()));

                                        if (response.body().getMatches().getRows().size() != 0) {
                                            ArrayList<String> strings=new ArrayList<>();
                                            for (int i = 0; i < response.body().getMatches().getRows().size(); i++) {
                                                strings.add(response.body().getMatches().getRows().get(i).getPlayInfo().getCharacterId());
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
                                            Glide.with(Matching_result.this)
                                                    .load("https://img-api.neople.co.kr/cy/characters/"+mostlyRecentCharacter)
                                                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                                                    .into(binding.playerRecentMostCharacter);
                                            for(int i=0; i<response.body().getMatches().getRows().size(); i++)
                                            {
                                                matchResultRecycleModel data=new matchResultRecycleModel();
                                                data.setBattlePoint(Integer.toString(response.body().getMatches().getRows().get(i).getPlayInfo().getBattlePoint()));
                                                data.setKDA(response.body().getMatches().getRows().get(i).getPlayInfo().getKillCount()+"킬 / "+response.body().getMatches().getRows().get(i).getPlayInfo().getDeathCount()+"데스 / "+response.body().getMatches().getRows().get(i).getPlayInfo().getAssistCount()+"어시");
                                                double killassi= response.body().getMatches().getRows().get(i).getPlayInfo().getKillCount()+response.body().getMatches().getRows().get(i).getPlayInfo().getAssistCount();
                                                double deathcnt=(double)(response.body().getMatches().getRows().get(i).getPlayInfo().getDeathCount());
                                                data.setKDAPOINT("KDA:"+String.format("%.2f",killassi/deathcnt));
                                                data.setCharacterNameLevel(response.body().getMatches().getRows().get(i).getPlayInfo().getCharacterName()+" 레벨: "+response.body().getMatches().getRows().get(i).getPlayInfo().getLevel());
                                                data.setMatchingCharacterImage("https://img-api.neople.co.kr/cy/characters/"+response.body().getMatches().getRows().get(i).getPlayInfo().getCharacterId());
                                                int damagePoint= (int) (response.body().getMatches().getRows().get(i).getPlayInfo().getDamagePoint()/1000);
                                                int dealPoint=(int)(response.body().getMatches().getRows().get(i).getPlayInfo().getAttackPoint()/1000);
                                                data.setDamagedPoint(damagePoint +"K");
                                                data.setDealingPoint(dealPoint +"K");
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
                                            Glide.with(Matching_result.this)
                                                    .load(R.drawable.newbie)
                                                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                                                    .into(binding.playerRecentMostCharacter);
                                            Toast.makeText(Matching_result.this, "최근 10일간 경기 기록이없어요!!", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                    @Override
                                    public void onFailure(Call<matchingRecordModel> call, Throwable t) {
                                        Log.d(TAG, "request 요청 URL 실패:"+call.request().url());
                                    }
                                });
                    }
                });
            }
        }).start();
    }
    public void PlayerAllMatching1banRecord(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(Matching_result.this);
                        binding.matchingRecordRecyclerview.setLayoutManager(linearLayoutManager);
                        adapter=new MatchingRecycleAdpater();
                        binding.matchingRecordRecyclerview.setAdapter(adapter);
                        RetrofitService networkService=RetrofitFactory.create();
                        networkService.GetPlayerMatchingRecord(playerUniqueID,"normal",70,getString(R.string.API_KEY))
                                .enqueue(new Callback<matchingRecordModel>() {
                                    @RequiresApi(api = Build.VERSION_CODES.N)
                                    @Override
                                    public void onResponse(Call<matchingRecordModel> call, Response<matchingRecordModel> response) {
                                        if (response.body().getMatches().getRows().size() != 0) {
                                            Log.d("뀨",Integer.toString(response.body().getMatches().getRows().size()));
                                            ArrayList<String> strings=new ArrayList<>();
                                            for (int i = 0; i < response.body().getMatches().getRows().size(); i++) {
                                                strings.add(response.body().getMatches().getRows().get(i).getPlayInfo().getCharacterId());
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
                                            Glide.with(Matching_result.this)
                                                    .load("https://img-api.neople.co.kr/cy/characters/"+mostlyRecentCharacter)
                                                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                                                    .into(binding.playerRecentMostCharacter);
                                            for(int i=0; i<response.body().getMatches().getRows().size(); i++)
                                            {
                                                matchResultRecycleModel data=new matchResultRecycleModel();
                                                data.setBattlePoint(Integer.toString(response.body().getMatches().getRows().get(i).getPlayInfo().getBattlePoint()));
                                                data.setKDA(response.body().getMatches().getRows().get(i).getPlayInfo().getKillCount()+"킬 / "+response.body().getMatches().getRows().get(i).getPlayInfo().getDeathCount()+"데스 / "+response.body().getMatches().getRows().get(i).getPlayInfo().getAssistCount()+"어시");
                                                double killassi= response.body().getMatches().getRows().get(i).getPlayInfo().getKillCount()+response.body().getMatches().getRows().get(i).getPlayInfo().getAssistCount();
                                                double deathcnt=(double)(response.body().getMatches().getRows().get(i).getPlayInfo().getDeathCount());
                                                data.setKDAPOINT("KDA:"+String.format("%.2f",killassi/deathcnt));
                                                data.setCharacterNameLevel(response.body().getMatches().getRows().get(i).getPlayInfo().getCharacterName()+" 레벨: "+response.body().getMatches().getRows().get(i).getPlayInfo().getLevel());
                                                data.setMatchingCharacterImage("https://img-api.neople.co.kr/cy/characters/"+response.body().getMatches().getRows().get(i).getPlayInfo().getCharacterId());
                                                int damagePoint= (int) (response.body().getMatches().getRows().get(i).getPlayInfo().getDamagePoint()/1000);
                                                int dealPoint=(int)(response.body().getMatches().getRows().get(i).getPlayInfo().getAttackPoint()/1000);
                                                data.setDamagedPoint(damagePoint +"K");
                                                data.setDealingPoint(dealPoint +"K");
                                                Log.d(TAG,"딜량:"+damagePoint+"k");
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
                                            Glide.with(Matching_result.this)
                                                    .load(R.drawable.newbie)
                                                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                                                    .into(binding.playerRecentMostCharacter);
                                            Toast.makeText(Matching_result.this, "최근 10일간 경기 기록이없어요!!", Toast.LENGTH_SHORT).show();
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<matchingRecordModel> call, Throwable t) {
                                        Log.d(TAG, "request 요청 URL 실패:"+call.request().url());

                                    }
                                });
                    }
                });
            }
        }).start();


    }
    public void onnormalClick(View view){
        if(!normalClick)
        {
            ratingClick=false;
            normalClick=true;
            PlayerAllMatching1banRecord();
        }
    }
    public void onRatingClick(View view){
        if(!ratingClick)
        {
            ratingClick=true;
            normalClick=false;
            PlayerAllMatchingRatingRecord();
        }
    }

}
