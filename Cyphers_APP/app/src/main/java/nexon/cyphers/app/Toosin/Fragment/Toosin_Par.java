package nexon.cyphers.app.Toosin.Fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import nexon.cyphers.app.Matching_result;
import nexon.cyphers.app.R;
import nexon.cyphers.app.adapter.ToosinCrawlerParAdapter;
import nexon.cyphers.app.model.PlayerModel;
import nexon.cyphers.app.model.ToosinModel.Toosin;
import nexon.cyphers.app.model.ToosinModel.ToosinModel;
import nexon.cyphers.app.model.ToosinModel.Toosin_par_Crawling_model;
import nexon.cyphers.app.retrofit2.RetrofitFactory;
import nexon.cyphers.app.retrofit2.RetrofitService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Toosin_Par extends Fragment {
    private View view;
    EditText search;
    String PlayerNickname;
    LinearLayout linearLayout;
    TextView nickname;
    TextView winstreak;
    TextView ranking;
    TextView rankingpoint;
    TextView wincnt;
    TextView losecnt;
    CircleImageView circleImageView;
    ImageView gosearch;
    RecyclerView recyclerView;
    ToosinCrawlerParAdapter adpater;
    private String playerUniqueId;
    static String crawlingmelee="http://cyphers.nexon.com/cyphers/article/ranking/gof/s/1";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.toosin_par, container, false);
        search=view.findViewById(R.id.toosin_par_player_search);
        linearLayout=view.findViewById(R.id.after_search);
        nickname=view.findViewById(R.id.par_nickname);
        winstreak=view.findViewById(R.id.par_winstreak);
        ranking=view.findViewById(R.id.ranking_par);
        rankingpoint=view.findViewById(R.id.ranking_point_par);
        wincnt=view.findViewById(R.id.par_win_cnt);
        losecnt=view.findViewById(R.id.par_lose_cnt);
        gosearch=view.findViewById(R.id.goSerach_par);
        circleImageView=view.findViewById(R.id.result_par_image);
        recyclerView=view.findViewById(R.id.par_recycle);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        adpater=new ToosinCrawlerParAdapter();
        recyclerView.setAdapter(adpater);
        getdata();
        gosearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PlayerNickname=search.getText().toString();
                goResult();
            }
        });
        search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if(i== EditorInfo.IME_ACTION_DONE)
                    gosearch.performClick();
                return false;
            }
        });
        return view;
    }
    private void getdata(){
        parcrawling crawling=new parcrawling();
        crawling.execute();
    }
    public void goResult(){
        RetrofitService networkService= RetrofitFactory.create();
        networkService.GetPlayerBasic(PlayerNickname,getString(R.string.API_KEY))
                .enqueue(new Callback<PlayerModel>() {
                    @Override
                    public void onResponse(Call<PlayerModel> call, Response<PlayerModel> response) {
                        if(!response.isSuccessful()){
                            return;
                        }
                        Log.d("뀨", "request 요청 성공 URL: "+call.request().url());
                        if(response.code()==200) {
                            if (response.body().getRows().size() == 0){
                                Toast.makeText(getActivity(), "랭킹 1000위까지만 정보를 제공합니다.", Toast.LENGTH_SHORT).show();
                            }else{
                                playerUniqueId=response.body().getRows().get(0).getPlayerId();
                                gocontinue();
                            }
                        }else if(response.code()==400){
                            Toast.makeText(getActivity(), "요청에 대한 유효성 검증 실패 또는 파라미터 에러입니다.", Toast.LENGTH_SHORT).show();
                        }else if(response.code()==401){
                            Toast.makeText(getActivity(), "인증 오류입니다", Toast.LENGTH_SHORT).show();

                        }else if(response.code()==404){
                            Toast.makeText(getActivity(), "존재하지 않는 리소스 또는 페이지 입니다", Toast.LENGTH_SHORT).show();
                        }else if(response.code()==500){
                            Toast.makeText(getActivity(), "시스템 오류입니다", Toast.LENGTH_SHORT).show();

                        }else if(response.code()==503){
                            Toast.makeText(getActivity(), "시스템 점검중입니다", Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onFailure(Call<PlayerModel> call, Throwable t) {
                        Toast.makeText(getActivity(), "인터넷 상태가 원활하지 않습니다", Toast.LENGTH_SHORT).show();
                        Log.d("뀨", "request 요청 실패 URL: "+call.request().url());
                    }
                });
    }
    public void gocontinue(){
        RetrofitService networkService= RetrofitFactory.create();
        networkService.GetTooSin("ranged",playerUniqueId,getString(R.string.API_KEY))
                .enqueue(new Callback<ToosinModel>() {
                    @Override
                    public void onResponse(Call<ToosinModel> call, Response<ToosinModel> response) {
                        if (response.code() == 200) {
                            if (response.body().getRows().size() == 0) {
                                Toast.makeText(getActivity(), "등록되지 않은 닉네임입니다.", Toast.LENGTH_SHORT).show();
                                getActivity().finish();
                            } else {
                                Log.d("뀨", "request 요청 성공 URL: " + call.request().url());
                                List<Toosin> model = response.body().getRows();
                                nickname.setText(model.get(0).getNickname());
                                rankingpoint.setText("랭킹 포인트" + Integer.toString(model.get(0).getRatingPoint()));
                                wincnt.setText("승리 :" + Integer.toString(model.get(0).getWinCount()));
                                losecnt.setText("패배 :" + Integer.toString(model.get(0).getLoseCount()));
                                ranking.setText(model.get(0).getRank() + "위");
                                winstreak.setText("최대 연승:" + Integer.toString(model.get(0).getWinningStreak()));
                                int wincontinue = model.get(0).getWinningStreak();
                                if (wincontinue >= 15)
                                    Glide.with(getActivity()).load(R.drawable.grandmaster).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(circleImageView);
                                else if (wincontinue >= 10)
                                    Glide.with(getActivity()).load(R.drawable.master).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(circleImageView);
                                else if (wincontinue >= 5)
                                    Glide.with(getActivity()).load(R.drawable.dia).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(circleImageView);
                                else if (wincontinue >= 3)
                                    Glide.with(getActivity()).load(R.drawable.gold).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(circleImageView);
                                else
                                    Glide.with(getActivity()).load(R.drawable.nowinstreak).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(circleImageView);

                                linearLayout.setVisibility(View.VISIBLE);
                            }
                        } else if (response.code() == 400) {
                            Toast.makeText(getActivity(), "요청에 대한 유효성 검증 실패 또는 파라미터 에러입니다.", Toast.LENGTH_SHORT).show();
                            getActivity().finish();
                        } else if (response.code() == 401) {
                            Toast.makeText(getActivity(), "인증 오류입니다", Toast.LENGTH_SHORT).show();
                            getActivity().finish();
                        } else if (response.code() == 404) {
                            Toast.makeText(getActivity(), "존재하지 않는 리소스 또는 페이지 입니다", Toast.LENGTH_SHORT).show();
                            getActivity().finish();
                        } else if (response.code() == 500) {
                            Toast.makeText(getActivity(), "시스템 오류입니다", Toast.LENGTH_SHORT).show();
                            getActivity().finish();
                        } else if (response.code() == 503) {
                            Toast.makeText(getActivity(), "시스템 점검중입니다", Toast.LENGTH_SHORT).show();
                            getActivity().finish();
                        }
                    }

                    @Override
                    public void onFailure(Call<ToosinModel> call, Throwable t) {
                        Log.d("뀨", "request 요청 실패 URL: "+call.request().url());
                        Toast.makeText(getActivity(), "존재하지않는 닉네임입니다.", Toast.LENGTH_SHORT).show();
                    }
                });
    }


    private class parcrawling extends AsyncTask<Void , Void, Void> {
        ArrayList<String> listrp=new ArrayList<>();
        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Document doc = Jsoup.connect(crawlingmelee).get();
                final Elements rp = doc.select("tr td");
                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        int cnt=1;
                        String tmp1 = null,tmp2=null,tmp3=null,tmp4=null,tmp5=null;
                        for(Element element:rp)
                            listrp.add(element.text());
                        for (int i = 1; i < listrp.size();  i++) {
                            String s=listrp.get(i-1);
                            if(cnt==1) {
                                tmp1 = s;
                                cnt++;
                            }
                            else if(cnt==2) {
                                tmp2 = s;
                                cnt++;
                            }
                            else if(cnt==3) {
                                tmp3 = s;
                                cnt++;
                            }
                            else if(cnt==4) {
                                tmp4 = s;
                                cnt++;
                            }
                            else if(cnt==5)
                            {
                                tmp5=s;
                                Toosin_par_Crawling_model data = new Toosin_par_Crawling_model();
                                data.setRanking(tmp1);
                                data.setNickname(tmp2);
                                data.setRP(tmp3);
                                data.setWinStreak(tmp4);
                                data.setResult(tmp5);
                                adpater.addItem(data);
                                cnt=1;
                            }
                            adpater.notifyDataSetChanged();
                        }

                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }
    }

}
