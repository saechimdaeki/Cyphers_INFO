package nexon.cyphers.app;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nexon.cyphers.app.Adapter.MatchingDetailRecycleAdapter;
import nexon.cyphers.app.model.RecyclerViewModel.MatchingResultDetailModel;
import nexon.cyphers.app.model.matching_Detail.MatchingDetailModel;
import nexon.cyphers.app.model.matching_Detail.Player;
import nexon.cyphers.app.retrofit2.RetrofitFactory;
import nexon.cyphers.app.retrofit2.RetrofitService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MatchingDeatilActivity extends AppCompatActivity {
    String matchId;
    /* key값은 nickname, 나머지는 점수 */
    Map<String,Double> dealPoint =new HashMap<>(),damagedPoint=new HashMap<>(),loseTeamDealPoint=new HashMap<>();
    Map<String,Integer> battlePoint=new HashMap<>(),SightPoint=new HashMap<>(),killPoint=new HashMap<>(),assistPoint=new HashMap<>(),deathPoint=new HashMap<>();
    Map<String ,Double> kdaPoint=new HashMap<>();
    Map.Entry<String,Double> maxDeal=null,maxDamaged=null,maxKdaPoint=null,loseTeamMaxdDeal=null;
    Map.Entry<String,Integer> maxBattle=null,maxSight=null,maxKill=null,maxAssist=null,maxDeath=null;
    private static final String TAG ="matching_Detail" ;
    RecyclerView recyclerViewForWinner,recyclerViewForLooser;
    MatchingDetailRecycleAdapter adapterForWinner,adapterForLooser;

    String maxDealUser,maxDamagedUser,maxBattleUser,maxSightUser,maxKillUser,maxAssistUser,maxDeathUser,maxKdaUser,losebutdealUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState==null)
        {
            Intent intent=new Intent(this,LoadingActivity.class);
            intent.putExtra("where",1000);
            startActivity(intent);
        }
        setContentView(R.layout.activity_matching_deatil);
        matchId=getIntent().getStringExtra("matchId");
        recyclerViewForWinner=findViewById(R.id.matching_Detail_winner_recyclerview);
        recyclerViewForLooser=findViewById(R.id.matching_Detail_looser_recyclerview);

        RetrofitService networkService= RetrofitFactory.create();
        networkService.GetMatchingDetail(matchId,getString(R.string.API_KEY))
                .enqueue(new Callback<MatchingDetailModel>() {
                    @Override
                    public void onResponse(Call<MatchingDetailModel> call, Response<MatchingDetailModel> response) {
                        Log.d(TAG, "request 요청 성공 URL: " + call.request().url());
                        Map<String, List<String>> resultMap = new HashMap<>();
                        for (int i = 0; i < response.body().getTeams().size(); i++)
                            resultMap.put(response.body().getTeams().get(i).getResult(), response.body().getTeams().get(i).getPlayers());
                        List<String> winnerTeam = resultMap.get("win");
                        List<String> loseTeam = resultMap.get("lose");
                        List<Player> players = response.body().getPlayers();
                        for (int i = 0; i < 10; i++) {
                            String playerName = players.get(i).getNickname();

                            dealPoint.put(playerName, response.body().getPlayers().get(i).getPlayInfo().getAttackPoint());
                            damagedPoint.put(playerName, response.body().getPlayers().get(i).getPlayInfo().getDamagePoint());
                            battlePoint.put(playerName, response.body().getPlayers().get(i).getPlayInfo().getBattlePoint());
                            SightPoint.put(playerName, response.body().getPlayers().get(i).getPlayInfo().getSightPoint());
                            killPoint.put(playerName, response.body().getPlayers().get(i).getPlayInfo().getKillCount());
                            assistPoint.put(playerName, response.body().getPlayers().get(i).getPlayInfo().getAssistCount());
                            deathPoint.put(playerName, response.body().getPlayers().get(i).getPlayInfo().getDeathCount());
                            double killassi = players.get(i).getPlayInfo().getKillCount() + players.get(i).getPlayInfo().getAssistCount();
                            double deathcnt = (double) (players.get(i).getPlayInfo().getDeathCount());
                            double kda = (killassi / deathcnt);
                            kdaPoint.put(players.get(i).getNickname(), kda);
                        }
                        calculate();
                        for(int i=0; i<winnerTeam.size(); i++)
                        {
                            MatchingResultDetailModel data=new MatchingResultDetailModel();
                            for(int j=0; j<10; j++)
                            {
                                if(players.get(j).getPlayerId().contains(winnerTeam.get(i)))
                                {
                                    String tmp="";
                                    Map<String,String> map=new HashMap<>();
                                    for(int k=0; k<players.get(j).getItems().size();  k++)
                                        map.put(players.get(j).getItems().get(k).getSlotCode(),"https://img-api.neople.co.kr/cy/items/"+players.get(j).getItems().get(k).getItemId());
                                    data.setMatchingResult("win");
                                    data.setBattlePointDetail(Integer.toString(players.get(j).getPlayInfo().getBattlePoint()));
                                    data.setSightPointDetail(Integer.toString(players.get(j).getPlayInfo().getSightPoint()));
                                    data.setNickname(players.get(j).getNickname());
                                    data.setPlayerId(players.get(j).getPlayerId());
                                    int damagePoint= (int) (players.get(j).getPlayInfo().getDamagePoint()/1000);
                                    int dealPoint=(int)(players.get(j).getPlayInfo().getAttackPoint()/1000);
                                    data.setDealingDetailPoint(dealPoint+"K");
                                    data.setDamagedDetailPoint(damagePoint+"K");
                                    double killassi= players.get(j).getPlayInfo().getKillCount()+players.get(j).getPlayInfo().getAssistCount();
                                    double deathcnt=(double)(players.get(j).getPlayInfo().getDeathCount());
                                    double kda=(killassi/deathcnt);
                                    data.setKDADetail(players.get(j).getPlayInfo().getKillCount()+" 킬 "+players.get(j).getPlayInfo().getDeathCount()+" 데스 "+players.get(j).getPlayInfo().getAssistCount()+"어시");
                                    data.setKDAPOINTDetail("KDA:"+String.format("%.2f",killassi/deathcnt));
                                    data.setCharacterDetailNameLevel(players.get(j).getPlayInfo().getCharacterName()+" 레벨: "+players.get(j).getPlayInfo().getLevel());
                                    data.setMatchingDetailCharacterImage("https://img-api.neople.co.kr/cy/characters/"+players.get(j).getPlayInfo().getCharacterId());
                                    data.setMatchingDetailCharacterPosition(players.get(j).getPosition().getName());
                                    data.setMatchingDetailCharacterPositionAttribute1("https://img-api.neople.co.kr/cy/position-attributes/"+players.get(j).getPosition().getAttribute().get(0).getId());
                                    data.setMatchingDetailCharacterPositionAttribute2("https://img-api.neople.co.kr/cy/position-attributes/"+players.get(j).getPosition().getAttribute().get(1).getId());
                                    data.setMatchingDetailCharacterPositionAttribute3("https://img-api.neople.co.kr/cy/position-attributes/"+players.get(j).getPosition().getAttribute().get(2).getId());
                                    if(players.get(j).getNickname().equals(maxKillUser))
                                        tmp+=" #학살자 ";
                                    if(players.get(j).getNickname().equals(maxAssistUser))
                                        tmp+=" #최고 도우미 ";
                                    if(players.get(j).getNickname().equals(maxBattleUser))
                                        tmp+=" #싸움꾼 ";
                                    if(players.get(j).getNickname().equals(maxDamagedUser))
                                        tmp+=" #동네북 ";
                                    if(players.get(j).getNickname().equals(maxSightUser))
                                        tmp+=" #이동형 센트리 ";
                                    if(players.get(j).getNickname().equals(maxDeathUser))
                                        tmp+=" #코인 셔틀 ";
                                    if(players.get(j).getNickname().equals(maxDealUser))
                                        tmp+=" #최고의 딜러 ";
                                    if(players.get(j).getNickname().equals(maxKdaUser))
                                        tmp+=" #킬뎃왕 " ;

                                    data.setMatchingDetailTag(tmp);
                                    /* 메소드 모델 설정을 이렇게 하였기 때문에 일일이 할수밖에없음 */
                                    if(map.containsKey("101"))
                                        data.setDetailItem1(map.get("101"));
                                    else
                                        data.setDetailItem1(getString(R.string.noitem));
                                    if(map.containsKey("102"))
                                        data.setDetailItem2(map.get("102"));
                                    else
                                        data.setDetailItem2(getString(R.string.noitem));
                                    if(map.containsKey("103"))
                                        data.setDetailItem3(map.get("103"));
                                    else
                                        data.setDetailItem3(getString(R.string.noitem));
                                    if(map.containsKey("104"))
                                        data.setDetailItem4(map.get("104"));
                                    else
                                        data.setDetailItem4(getString(R.string.noitem));
                                    if(map.containsKey("105"))
                                        data.setDetailItem5(map.get("105"));
                                    else
                                        data.setDetailItem5(getString(R.string.noitem));
                                    if(map.containsKey("106"))
                                        data.setDetailItem6(map.get("106"));
                                    else
                                        data.setDetailItem6(getString(R.string.noitem));
                                    if(map.containsKey("202"))
                                        data.setDetailItem7(map.get("202"));
                                    else
                                        data.setDetailItem7(getString(R.string.noitem));
                                    if(map.containsKey("203"))
                                        data.setDetailItem8(map.get("203"));
                                    else
                                        data.setDetailItem8(getString(R.string.noitem));
                                    if(map.containsKey("301"))
                                        data.setDetailItem9(map.get("301"));
                                    else
                                        data.setDetailItem9(getString(R.string.noitem));
                                    if(map.containsKey("302"))
                                        data.setDetailItem10(map.get("302"));
                                    else
                                        data.setDetailItem10(getString(R.string.noitem));
                                    if(map.containsKey("303"))
                                        data.setDetailItem11(map.get("303"));
                                    else
                                        data.setDetailItem11(getString(R.string.noitem));
                                    if(map.containsKey("304"))
                                        data.setDetailItem12(map.get("304"));
                                    else
                                        data.setDetailItem12(getString(R.string.noitem));
                                    if(map.containsKey("305"))
                                        data.setDetailItem13(map.get("305"));
                                    else
                                        data.setDetailItem13(getString(R.string.noitem));
                                    if(map.containsKey("107"))
                                        data.setDetailItem14(map.get("107"));
                                    else
                                        data.setDetailItem14(getString(R.string.noitem));
                                    if(map.containsKey("204"))
                                        data.setDetailItem15(map.get("204"));
                                    else
                                        data.setDetailItem15(getString(R.string.noitem));
                                    if(map.containsKey("205"))
                                        data.setDetailItem16(map.get("205"));
                                    else
                                        data.setDetailItem16(getString(R.string.noitem));
                                    adapterForWinner.addItem(data);
                                }
                            }
                            adapterForWinner.notifyDataSetChanged();
                        }
                        for(int i=0; i<loseTeam.size(); i++)
                        {
                            MatchingResultDetailModel data=new MatchingResultDetailModel();
                            for(int j=0; j<10; j++)
                            {
                                if(players.get(j).getPlayerId().contains(loseTeam.get(i)))
                                {
                                    String tmp="";
                                    Map<String,String> map=new HashMap<>();
                                    for(int k=0; k<players.get(j).getItems().size();  k++)
                                        map.put(players.get(j).getItems().get(k).getSlotCode(),"https://img-api.neople.co.kr/cy/items/"+players.get(j).getItems().get(k).getItemId());
                                    data.setMatchingResult("lose");
                                    data.setBattlePointDetail(Integer.toString(players.get(j).getPlayInfo().getBattlePoint()));
                                    data.setSightPointDetail(Integer.toString(players.get(j).getPlayInfo().getSightPoint()));
                                    data.setNickname(players.get(j).getNickname());
                                    data.setPlayerId(players.get(j).getPlayerId());
                                    data.setDealingDetailPoint((players.get(j).getPlayInfo().getAttackPoint())/1000 +"k");
                                    data.setDamagedDetailPoint((players.get(j).getPlayInfo().getDamagePoint())/1000 +"k");
                                    double killassi= players.get(j).getPlayInfo().getKillCount()+players.get(j).getPlayInfo().getAssistCount();
                                    double deathcnt=(double)(players.get(j).getPlayInfo().getDeathCount());
                                    data.setKDADetail(players.get(j).getPlayInfo().getKillCount()+" 킬 "+players.get(j).getPlayInfo().getDeathCount()+" 데스 "+players.get(j).getPlayInfo().getAssistCount()+"어시");
                                    data.setKDAPOINTDetail("KDA:"+String.format("%.2f",killassi/deathcnt));
                                    data.setCharacterDetailNameLevel(players.get(j).getPlayInfo().getCharacterName()+" 레벨: "+players.get(j).getPlayInfo().getLevel());
                                    data.setMatchingDetailCharacterImage("https://img-api.neople.co.kr/cy/characters/"+players.get(j).getPlayInfo().getCharacterId());
                                    data.setMatchingDetailCharacterPosition(players.get(j).getPosition().getName());
                                    data.setMatchingDetailCharacterPositionAttribute1("https://img-api.neople.co.kr/cy/position-attributes/"+players.get(j).getPosition().getAttribute().get(0).getId());
                                    data.setMatchingDetailCharacterPositionAttribute2("https://img-api.neople.co.kr/cy/position-attributes/"+players.get(j).getPosition().getAttribute().get(1).getId());
                                    data.setMatchingDetailCharacterPositionAttribute3("https://img-api.neople.co.kr/cy/position-attributes/"+players.get(j).getPosition().getAttribute().get(2).getId());
                                    if(players.get(j).getNickname().equals(maxKillUser)) {
                                        tmp += " #학살자 ";
                                    }
                                    if(players.get(j).getNickname().equals(maxAssistUser))
                                        tmp+=" #최고 도우미 ";
                                   if(players.get(j).getNickname().equals(maxBattleUser))
                                       tmp+=" #싸움꾼 ";
                                    if(players.get(j).getNickname().equals(maxDamagedUser))
                                        tmp+=" #동네북 ";
                                    if(players.get(j).getNickname().equals(maxSightUser))
                                        tmp+=" #이동형 센트리 ";
                                    if(players.get(j).getNickname().equals(maxDeathUser))
                                        tmp+=" #코인 셔틀 ";
                                    if(players.get(j).getNickname().equals(maxDealUser))
                                        tmp+=" #최고의 딜러 ";
                                    if(players.get(j).getNickname().equals(maxKdaUser))
                                        tmp+=" #킬뎃왕 " ;
                                    if(players.get(j).getNickname().equals(losebutdealUser))
                                        tmp+="졌잘싸";
                                    data.setMatchingDetailTag(tmp);
                                    /* 메소드 모델 설정을 이렇게 하였기 때문에 일일이 할수밖에없음 */
                                    if(map.containsKey("101"))
                                        data.setDetailItem1(map.get("101"));
                                    else
                                        data.setDetailItem1(getString(R.string.noitem));
                                    if(map.containsKey("102"))
                                        data.setDetailItem2(map.get("102"));
                                    else
                                        data.setDetailItem2(getString(R.string.noitem));
                                    if(map.containsKey("103"))
                                        data.setDetailItem3(map.get("103"));
                                    else
                                        data.setDetailItem3(getString(R.string.noitem));
                                    if(map.containsKey("104"))
                                        data.setDetailItem4(map.get("104"));
                                    else
                                        data.setDetailItem4(getString(R.string.noitem));
                                    if(map.containsKey("105"))
                                        data.setDetailItem5(map.get("105"));
                                    else
                                        data.setDetailItem5(getString(R.string.noitem));
                                    if(map.containsKey("106"))
                                        data.setDetailItem6(map.get("106"));
                                    else
                                        data.setDetailItem6(getString(R.string.noitem));
                                    if(map.containsKey("202"))
                                        data.setDetailItem7(map.get("202"));
                                    else
                                        data.setDetailItem7(getString(R.string.noitem));
                                    if(map.containsKey("203"))
                                        data.setDetailItem8(map.get("203"));
                                    else
                                        data.setDetailItem8(getString(R.string.noitem));
                                    if(map.containsKey("301"))
                                        data.setDetailItem9(map.get("301"));
                                    else
                                        data.setDetailItem9(getString(R.string.noitem));
                                    if(map.containsKey("302"))
                                        data.setDetailItem10(map.get("302"));
                                    else
                                        data.setDetailItem10(getString(R.string.noitem));
                                    if(map.containsKey("303"))
                                        data.setDetailItem11(map.get("303"));
                                    else
                                        data.setDetailItem11(getString(R.string.noitem));
                                    if(map.containsKey("304"))
                                        data.setDetailItem12(map.get("304"));
                                    else
                                        data.setDetailItem12(getString(R.string.noitem));
                                    if(map.containsKey("305"))
                                        data.setDetailItem13(map.get("305"));
                                    else
                                        data.setDetailItem13(getString(R.string.noitem));
                                    if(map.containsKey("107"))
                                        data.setDetailItem14(map.get("107"));
                                    else
                                        data.setDetailItem14(getString(R.string.noitem));
                                    if(map.containsKey("204"))
                                        data.setDetailItem15(map.get("204"));
                                    else
                                        data.setDetailItem15(getString(R.string.noitem));
                                    if(map.containsKey("205"))
                                        data.setDetailItem16(map.get("205"));
                                    else
                                        data.setDetailItem16(getString(R.string.noitem));
                                    adapterForLooser.addItem(data);
                                }
                            }
                            adapterForLooser.notifyDataSetChanged();
                        }
                    }
                    @Override
                    public void onFailure(Call<MatchingDetailModel> call, Throwable t) {
                        Log.d(TAG, "request 요청 실패 URL: "+call.request().url());
                        Toast.makeText(MatchingDeatilActivity.this, "연결 상태가 원활하지 않습니다.", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
        LinearLayoutManager linearLayoutManager1=new LinearLayoutManager(this);
        LinearLayoutManager linearLayoutManager2=new LinearLayoutManager(this);
        recyclerViewForWinner.setLayoutManager(linearLayoutManager1);
        recyclerViewForLooser.setLayoutManager(linearLayoutManager2);
        adapterForWinner=new MatchingDetailRecycleAdapter();
        adapterForLooser=new MatchingDetailRecycleAdapter();
        recyclerViewForWinner.setAdapter(adapterForWinner);
        recyclerViewForLooser.setAdapter(adapterForLooser);
    }
    public void calculate(){
        for(Map.Entry<String,Double> entry:dealPoint.entrySet())
        {
            if(maxDeal==null||entry.getValue().compareTo(maxDeal.getValue())>0)
            {
                maxDeal=entry;
            }
        }
        for(Map.Entry<String,Double> entry:damagedPoint.entrySet())
        {
            if(maxDamaged==null || entry.getValue().compareTo(maxDamaged.getValue())>0)
                maxDamaged=entry;
        }
        for(Map.Entry<String,Integer> entry:battlePoint.entrySet())
        {
            if(maxBattle==null || entry.getValue().compareTo(maxBattle.getValue())>0)
                maxBattle=entry;
        }

        for(Map.Entry<String,Integer> entry:SightPoint.entrySet())
        {
            if(maxSight==null || entry.getValue().compareTo(maxSight.getValue())>0)
                maxSight=entry;
        }

        for(Map.Entry<String,Integer> entry:assistPoint.entrySet())
        {
            if(maxAssist==null || entry.getValue().compareTo(maxAssist.getValue())>0)
                maxAssist=entry;
        }
        for(Map.Entry<String,Integer> entry:deathPoint.entrySet())
        {
            if(maxDeath==null || entry.getValue().compareTo(maxDeath.getValue())>0)
                maxDeath=entry;
        }
        for(Map.Entry<String,Integer> entry:killPoint.entrySet())
        {
            if(maxKill==null ||entry.getValue().compareTo(maxKill.getValue())>0)
                maxKill=entry;
        }
        for(Map.Entry<String,Double> entry:kdaPoint.entrySet())
        {
            if(maxKdaPoint==null ||entry.getValue().compareTo(maxKdaPoint.getValue())>0)
                maxKdaPoint=entry;
        }

        maxDealUser=maxDeal.getKey();
        maxAssistUser=maxAssist.getKey();
        maxBattleUser=maxBattle.getKey();
        maxSightUser=maxSight.getKey();
        maxDeathUser=maxDeath.getKey();
        maxDamagedUser=maxDamaged.getKey();
        maxKillUser=maxKill.getKey();
        maxKdaUser=maxKdaPoint.getKey();

    }
}
