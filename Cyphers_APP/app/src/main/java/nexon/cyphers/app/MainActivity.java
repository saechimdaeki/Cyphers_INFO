package nexon.cyphers.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.Arrays;
import java.util.List;

import me.everything.android.ui.overscroll.OverScrollDecoratorHelper;
import nexon.cyphers.app.adapter.MainRecyclerAdpater;
import nexon.cyphers.app.databinding.ActivityMainBinding;
import nexon.cyphers.app.model.RecyclerViewModel.MainRecycleModel;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    private MainRecyclerAdpater adapter;
    private BackButtonPressHandler backButtonPressHandler;
    private static final String TAG ="Main" ;
    private String PlayerNickname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_main);
        binding.setMaindata(this);

        if(savedInstanceState==null){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent=new Intent(MainActivity.this,LoadingActivity.class);
                            intent.putExtra("where",3000);
                            startActivity(intent);
                        }
                    });
                }
            }).start();
        }
        backButtonPressHandler=new BackButtonPressHandler(this);
        binding.playerSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if(i== EditorInfo.IME_ACTION_SEARCH)
                    onButtonClick(textView);
                return false;
            }
        });
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        binding.mainRecycle.setLayoutManager(linearLayoutManager);
        adapter=new MainRecyclerAdpater();
        binding.mainRecycle.setAdapter(adapter);
       // OverScrollDecoratorHelper.setUpOverScroll(binding.mainRecycle, OverScrollDecoratorHelper.ORIENTATION_VERTICAL);
        GetRecycle();
    }
    private void GetRecycle(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                final List<String> ListTitle= Arrays.asList(
                        "사이퍼즈 공식페이지 소식통",
                        "캐릭터 정보/보이스 박스",
                        "포지션 특성 조회",
                        "투신전 랭킹 조회",
                        "사이퍼즈 세계관",
                        "게임 가이드",
                        "동영상 갤러리",
                        "팬아트 게시판",
                        "공략게시판",
                        "밸런스 토론장",
                        "자유 게시판",
                        "아이템 검색"
                );
                final List<String> ListSubTitle=Arrays.asList(
                        "사이퍼즈 이벤트/업데이트/오싸를 살펴봐용",
                        "사이퍼들에 대한 정보,보이스박스 제공",
                        "각 포지션 특성에 대해 알아봅시다",
                        "외침: 투신전 극 할사람 구해요 ",
                        "세계관을 알고 게임을하면 재미가 두배",
                        "(웹 뷰) 사이퍼즈의 게임가이드를 살펴봐요",
                        "(웹 뷰) 사이퍼즈의 공식영상들을 볼 수 있습니다.",
                        "(웹 뷰) 재미있는 게임캐릭터 아트들",
                        "(웹 뷰) 공략 게시판을 정독하면 티어상승",
                        "(웹 뷰) 사기케 너프좀",
                        "(웹 뷰) 자게이",
                        "(웹 뷰) 처음 보는 아이템들도 있단다"
                );
                final List<Integer> listID=Arrays.asList(
                        R.drawable.wesly,R.drawable.tien,R.drawable.position,R.drawable.ton,
                        R.drawable.diemoos,R.drawable.ranking,R.drawable.caro,R.drawable.louis,R.drawable.clare,R.drawable.alieshu,R.drawable.jagay,R.drawable.tara
                );
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        for(int i=0; i<ListTitle.size(); i++){
                            MainRecycleModel data=new MainRecycleModel();
                            data.setTitle(ListTitle.get(i));
                            data.setSubTitle(ListSubTitle.get(i));
                            data.setId(listID.get(i));
                            adapter.addItem(data);
                        }
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        }).start();
    }
    public void onButtonClick(View view){
        new Thread(new Runnable() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(binding.playerSearch.getText().toString().length()==0){
                            Toast.makeText(MainActivity.this, "검색할 닉네임을 입력해주세용", Toast.LENGTH_SHORT).show();
                        }else{
                            PlayerNickname=binding.playerSearch.getText().toString();
                            Intent intent=new Intent(MainActivity.this, Matching_result.class);
                            intent.putExtra("nick",PlayerNickname);
                            startActivity(intent);
                        }
                    }
                });
            }
        }).start();
    }

    @Override
    public void onBackPressed() {
        backButtonPressHandler.onBackPressed();
    }

}
