package nexon.cyphers.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import nexon.cyphers.app.Adapter.MatchingDetailRecycleAdapter;
import nexon.cyphers.app.Adapter.MatchingRecycleAdpater;
import nexon.cyphers.app.model.Player;
import nexon.cyphers.app.model.PlayerModel;
import nexon.cyphers.app.model.RecyclerViewModel.MatchingResultDetailModel;
import nexon.cyphers.app.model.RecyclerViewModel.matchResultRecycleModel;
import nexon.cyphers.app.model.matching_Detail.MatchingDetailModel;
import nexon.cyphers.app.retrofit2.RetrofitFactory;
import nexon.cyphers.app.retrofit2.RetrofitService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MatchingDeatilActivity extends AppCompatActivity {
    String matchId;
    private static final String TAG ="matching_Detail" ;
    RecyclerView recyclerView;
    MatchingDetailRecycleAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matching_deatil);
        matchId=getIntent().getStringExtra("matchId");
        recyclerView=findViewById(R.id.matching_Detail_recyclerview);
        RetrofitService networkService= RetrofitFactory.create();
        networkService.GetMatchingDetail(matchId,getString(R.string.API_KEY))
                .enqueue(new Callback<MatchingDetailModel>() {
                    @Override
                    public void onResponse(Call<MatchingDetailModel> call, Response<MatchingDetailModel> response) {
                        Log.d(TAG, "request 요청 성공 URL: " + call.request().url());
                        boolean[] arr=new boolean[16];
                        /* 이긴 팀*/
                        for (int i = 0; i < 10; i++) {
                            //Log.d(TAG,response.body().getTeams().get(1).getPlayers().get(i));
                            MatchingResultDetailModel data=new MatchingResultDetailModel();
                            if(i<5)
                            data.setMatchingResult("win");
                            else
                                data.setMatchingResult("lose");
                            data.setDealingDetailPoint((response.body().getPlayers().get(i).getPlayInfo().getDamagePoint())/1000 +"k");
                            data.setDamagedDetailPoint((response.body().getPlayers().get(i).getPlayInfo().getAttackPoint())/1000 +"k");
                            data.setNickname(response.body().getPlayers().get(i).getNickname());
                            Log.d("닉네임",response.body().getPlayers().get(i).getNickname());
                            data.setSightPointDetail(Integer.toString(response.body().getPlayers().get(i).getPlayInfo().getSightPoint()));
                            data.setCharacterDetailNameLevel(response.body().getPlayers().get(i).getPlayInfo().getCharacterName()+ " 레벨 : "+response.body().getPlayers().get(i).getPlayInfo().getLevel());
                            data.setBattlePointDetail(Integer.toString(response.body().getPlayers().get(i).getPlayInfo().getBattlePoint()));
                            data.setKDADetail(response.body().getPlayers().get(i).getPlayInfo().getKillCount()+"킬 "+response.body().getPlayers().get(i).getPlayInfo().getDeathCount()+"데스 "+response.body().getPlayers().get(i).getPlayInfo().getAssistCount()+"어시");
                            double killassi=(double)(response.body().getPlayers().get(i).getPlayInfo().getKillCount()+response.body().getPlayers().get(i).getPlayInfo().getAssistCount());
                            double deathcnt=(double)(response.body().getPlayers().get(i).getPlayInfo().getDeathCount());
                            data.setKDAPOINTDetail("KDA:"+String.format("%.2f",killassi/deathcnt));

                            data.setMatchingDetailCharacterPosition(response.body().getPlayers().get(i).getPosition().getName());
                            Log.d("포지션 ",response.body().getPlayers().get(i).getPosition().getName());
                            data.setMatchingDetailCharacterPositionAttribute1("https://img-api.neople.co.kr/cy/position-attributes/"+response.body().getPlayers().get(i).getPosition().getAttribute().get(0).getId());
                            data.setMatchingDetailCharacterPositionAttribute2("https://img-api.neople.co.kr/cy/position-attributes/"+response.body().getPlayers().get(i).getPosition().getAttribute().get(1).getId());
                            data.setMatchingDetailCharacterPositionAttribute3("https://img-api.neople.co.kr/cy/position-attributes/"+response.body().getPlayers().get(i).getPosition().getAttribute().get(2).getId());
                            data.setMatchingDetailCharacterImage("https://img-api.neople.co.kr/cy/characters/"+response.body().getPlayers().get(i).getPlayInfo().getCharacterId());
                                data.setDetailItem1("https://img-api.neople.co.kr/cy/items/" + response.body().getPlayers().get(i).getItems().get(0).getItemId());
                                data.setDetailItem2("https://img-api.neople.co.kr/cy/items/" + response.body().getPlayers().get(i).getItems().get(1).getItemId());
                                data.setDetailItem3("https://img-api.neople.co.kr/cy/items/" + response.body().getPlayers().get(i).getItems().get(2).getItemId());
                                data.setDetailItem4("https://img-api.neople.co.kr/cy/items/" + response.body().getPlayers().get(i).getItems().get(3).getItemId());
                                data.setDetailItem5("https://img-api.neople.co.kr/cy/items/" + response.body().getPlayers().get(i).getItems().get(4).getItemId());
                                data.setDetailItem6("https://img-api.neople.co.kr/cy/items/" + response.body().getPlayers().get(i).getItems().get(5).getItemId());
                                data.setDetailItem7("https://img-api.neople.co.kr/cy/items/" + response.body().getPlayers().get(i).getItems().get(6).getItemId());
                                data.setDetailItem8("https://img-api.neople.co.kr/cy/items/" + response.body().getPlayers().get(i).getItems().get(7).getItemId());
                                data.setDetailItem9("https://img-api.neople.co.kr/cy/items/" + response.body().getPlayers().get(i).getItems().get(8).getItemId());
                                data.setDetailItem10("https://img-api.neople.co.kr/cy/items/" + response.body().getPlayers().get(i).getItems().get(9).getItemId());
                                data.setDetailItem11("https://img-api.neople.co.kr/cy/items/" + response.body().getPlayers().get(i).getItems().get(10).getItemId());
                                data.setDetailItem12("https://img-api.neople.co.kr/cy/items/" + response.body().getPlayers().get(i).getItems().get(11).getItemId());
                                data.setDetailItem13("https://img-api.neople.co.kr/cy/items/" + response.body().getPlayers().get(i).getItems().get(12).getItemId());
                                data.setDetailItem14("https://img-api.neople.co.kr/cy/items/" + response.body().getPlayers().get(i).getItems().get(13).getItemId());
                                data.setDetailItem15("https://img-api.neople.co.kr/cy/items/" + response.body().getPlayers().get(i).getItems().get(14).getItemId());
                                data.setDetailItem16("https://img-api.neople.co.kr/cy/items/" + response.body().getPlayers().get(i).getItems().get(15).getItemId());
                            adapter.addItem(data);
                        }
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onFailure(Call<MatchingDetailModel> call, Throwable t) {
                        Log.d(TAG, "request 요청 실패 URL: "+call.request().url());
                    }
                });
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter=new MatchingDetailRecycleAdapter();
        recyclerView.setAdapter(adapter);
    }
}
