package nexon.cyphers.app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import nexon.cyphers.app.model.GameTypeModel;
import nexon.cyphers.app.model.Player;
import nexon.cyphers.app.model.PlayerModel;
import nexon.cyphers.app.model.PlayerInfo;
import nexon.cyphers.app.model.TotalRank;
import nexon.cyphers.app.model.TotalRankRow;
import nexon.cyphers.app.model.matching_record.Matches;
import nexon.cyphers.app.model.matching_record.matchingRecordModel;
import nexon.cyphers.app.retrofit2.RetrofitFactory;
import nexon.cyphers.app.retrofit2.RetrofitService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class matching_result extends AppCompatActivity {
    private static final String TAG ="matching_result" ;
    TextView playerresult,info,allrecord;
    String nickname;
    private String playerUniqueID; /* Unique ID MUST */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matching_result);
        nickname=getIntent().getStringExtra("nick");
        playerresult=findViewById(R.id.player_result);
        info=findViewById(R.id.testinfo);
        allrecord=findViewById(R.id.testrecord);
        RetrofitService networkService= RetrofitFactory.create();
        networkService.GetPlayerBasic(nickname,getString(R.string.API_KEY))
                .enqueue(new Callback<PlayerModel>() {
                    @Override
                    public void onResponse(Call<PlayerModel> call, Response<PlayerModel> response) {
                        if(!response.isSuccessful()){
                            playerresult.setText(response.code());
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
                                playerresult.setText(nickname + "\n");
                                playerresult.append("등급:" + grade+"\n");
                                playerUniqueID = playerId;
                                /* 일단은 구현 나중에 스레드로 돌릴 함수들임.*/
                                PlayerInfo();
                                PlayerTotalRank();
                                PlayerAllMatchingRecord();
                            }
                        }else if(response.code()==400){
                            playerresult.setText("요청에 대한 유효성 검증 실패 또는 파라미터 에러입니다.");
                        }else if(response.code()==401){
                            playerresult.setText("인증 오류입니다");
                        }else if(response.code()==404){
                            playerresult.setText("존재하지 않는 리소스 또는 페이지 입니다");
                        }else if(response.code()==500){
                            playerresult.setText("시스템 오류입니다");
                        }else if(response.code()==503){
                            playerresult.setText("시스템 점검중입니다");
                        }
                        // Log.d(TAG, "request 요청 URL: "+call.request().url());
                    }
                    @Override
                    public void onFailure(Call<PlayerModel> call, Throwable t) {
                        playerresult.setText(t.getMessage());
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
                            info.setText(response.code());
                            return;
                        }
                        if(response.body().getClanName()==null)
                            info.append("소속 클랜: 없음\n");
                        else
                        info.append("소속 클랜: "+response.body().getClanName()+"\n");
                        info.append("현재 티어: "+response.body().getTierName()+"\n");
                        info.append("현재 점수: "+response.body().getRatingPoint()+"\n");
                        info.append("최고 점수: "+response.body().getMaxRatingPoint()+"\n");
                        List<GameTypeModel> models=response.body().getRecords();
                        for(int i=0; i<models.size(); i++)
                        {
                            if(i==0)
                                info.append("공식전 정보: \n");
                            else
                                info.append("일반전 정보:\n");
                            info.append("승리 수: "+models.get(i).getWinCount()+"\n");
                            info.append("패배 수:" +models.get(i).getLoseCount()+"\n");
                            info.append("중단 횟수:"+models.get(i).getStopCount()+"\n\n");
                        }
                        // Log.d(TAG, "request 요청 URL: "+call.request().url());
                    }
                    @Override
                    public void onFailure(Call<PlayerInfo> call, Throwable t) {
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
                            playerresult.append("랭킹내역이 없습니다.");
                        else
                        playerresult.append("통합 랭킹 :"+model.get(0).getRank()+"위 \n");
                    }
                    @Override
                    public void onFailure(Call<TotalRankRow> call, Throwable t) {
                    }
                });
    }
    public void PlayerAllMatchingRecord(){
        RetrofitService networkService=RetrofitFactory.create();
        networkService.GetPlayerMatchingRecord(playerUniqueID,"rating",getString(R.string.API_KEY))
                .enqueue(new Callback<matchingRecordModel>() {
                    @Override
                    public void onResponse(Call<matchingRecordModel> call, Response<matchingRecordModel> response) {
                        Log.d(TAG, "request 요청 URL성공:"+call.request().url());
                        allrecord.append("최근 10판동안 한 캐릭터에요!:\n");
                        for(int i=0; i<response.body().getMatches().getRows().size(); i++)
                        {
                            allrecord.append(response.body().getMatches().getRows().get(i).getPlayInfo().getCharacterName()+"\n");
                        }
                        Log.d(TAG,response.body().getMatches().getRows().get(0).getMap().getName());

                    }

                    @Override
                    public void onFailure(Call<matchingRecordModel> call, Throwable t) {
                        Log.d(TAG, "request 요청 URL실패:"+call.request().url());

                    }
                });
    }
}
