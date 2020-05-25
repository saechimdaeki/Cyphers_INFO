package nexon.cyphers.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nexon.cyphers.app.Adapter.MatchingDetailRecycleAdapter;
import nexon.cyphers.app.Adapter.MatchingRecycleAdpater;
import nexon.cyphers.app.model.PlayerModel;
import nexon.cyphers.app.model.RecyclerViewModel.MatchingResultDetailModel;
import nexon.cyphers.app.model.RecyclerViewModel.matchResultRecycleModel;
import nexon.cyphers.app.model.matching_Detail.MatchingDetailModel;
import nexon.cyphers.app.model.matching_Detail.Player;
import nexon.cyphers.app.model.matching_Detail.Team;
import nexon.cyphers.app.retrofit2.RetrofitFactory;
import nexon.cyphers.app.retrofit2.RetrofitService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MatchingDeatilActivity extends AppCompatActivity {
    String matchId;
    private static final String TAG ="matching_Detail" ;
    RecyclerView recyclerViewForWinner,recyclerViewForLooser;
    MatchingDetailRecycleAdapter adapterForWinner,adapterForLooser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
                        List<String> winnerTeam=response.body().getTeams().get(0).getPlayers();
                        List<String> loseTeam=response.body().getTeams().get(1).getPlayers();
                        List<Player> players=response.body().getPlayers();
                        for(int i=0; i<winnerTeam.size(); i++)
                        {
                            MatchingResultDetailModel data=new MatchingResultDetailModel();
                            for(int j=0; j<10; j++)
                            {
                                if(players.get(j).getPlayerId().contains(winnerTeam.get(i)))
                                {
                                    Map<String,String> map=new HashMap<>();
                                    for(int k=0; k<players.get(j).getItems().size();  k++)
                                        map.put(players.get(j).getItems().get(k).getSlotCode(),"https://img-api.neople.co.kr/cy/items/"+players.get(j).getItems().get(k).getItemId());
                                    data.setMatchingResult("win");
                                    data.setBattlePointDetail(Integer.toString(players.get(j).getPlayInfo().getBattlePoint()));
                                    data.setSightPointDetail(Integer.toString(players.get(j).getPlayInfo().getSightPoint()));
                                    data.setNickname(players.get(j).getNickname());
                                    data.setPlayerId(players.get(j).getPlayerId());
                                    data.setDealingDetailPoint((players.get(j).getPlayInfo().getAttackPoint())/1000 +"k");
                                    data.setDamagedDetailPoint((players.get(j).getPlayInfo().getDamagePoint())/1000 +"k");
                                    double killassi=(double)(players.get(j).getPlayInfo().getKillCount()+players.get(j).getPlayInfo().getAssistCount());
                                    double deathcnt=(double)(players.get(j).getPlayInfo().getDeathCount());
                                    data.setKDADetail(players.get(j).getPlayInfo().getKillCount()+" 킬 "+players.get(j).getPlayInfo().getDeathCount()+" 데스 "+players.get(j).getPlayInfo().getAssistCount()+"어시스트");
                                    data.setKDAPOINTDetail("KDA:"+String.format("%.2f",killassi/deathcnt));
                                    data.setCharacterDetailNameLevel(players.get(j).getPlayInfo().getCharacterName()+" 레벨: "+players.get(j).getPlayInfo().getLevel());
                                    data.setMatchingDetailCharacterImage("https://img-api.neople.co.kr/cy/characters/"+players.get(j).getPlayInfo().getCharacterId());

                                    data.setMatchingDetailCharacterPosition(players.get(j).getPosition().getName());
                                    data.setMatchingDetailCharacterPositionAttribute1("https://img-api.neople.co.kr/cy/position-attributes/"+players.get(j).getPosition().getAttribute().get(0).getId());
                                    data.setMatchingDetailCharacterPositionAttribute2("https://img-api.neople.co.kr/cy/position-attributes/"+players.get(j).getPosition().getAttribute().get(1).getId());
                                    data.setMatchingDetailCharacterPositionAttribute3("https://img-api.neople.co.kr/cy/position-attributes/"+players.get(j).getPosition().getAttribute().get(2).getId());

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
                                    double killassi=(double)(players.get(j).getPlayInfo().getKillCount()+players.get(j).getPlayInfo().getAssistCount());
                                    double deathcnt=(double)(players.get(j).getPlayInfo().getDeathCount());
                                    data.setKDADetail(players.get(j).getPlayInfo().getKillCount()+" 킬 "+players.get(j).getPlayInfo().getDeathCount()+" 데스 "+players.get(j).getPlayInfo().getAssistCount()+"어시스트");
                                    data.setKDAPOINTDetail("KDA:"+String.format("%.2f",killassi/deathcnt));
                                    data.setCharacterDetailNameLevel(players.get(j).getPlayInfo().getCharacterName()+" 레벨: "+players.get(j).getPlayInfo().getLevel());
                                    data.setMatchingDetailCharacterImage("https://img-api.neople.co.kr/cy/characters/"+players.get(j).getPlayInfo().getCharacterId());

                                    data.setMatchingDetailCharacterPosition(players.get(j).getPosition().getName());
                                    data.setMatchingDetailCharacterPositionAttribute1("https://img-api.neople.co.kr/cy/position-attributes/"+players.get(j).getPosition().getAttribute().get(0).getId());
                                    data.setMatchingDetailCharacterPositionAttribute2("https://img-api.neople.co.kr/cy/position-attributes/"+players.get(j).getPosition().getAttribute().get(1).getId());
                                    data.setMatchingDetailCharacterPositionAttribute3("https://img-api.neople.co.kr/cy/position-attributes/"+players.get(j).getPosition().getAttribute().get(2).getId());

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
}
