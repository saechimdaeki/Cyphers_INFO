package nexon.cyphers.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

import nexon.cyphers.app.Adapter.MainRecyclerAdpater;
import nexon.cyphers.app.model.MainRecycleModel;

public class MainActivity extends AppCompatActivity {
    private MainRecyclerAdpater adapter;
    private BackButtonPressHandler backButtonPressHandler;
    private static final String TAG ="Main" ;
    private String PlayerNickname;
    TextView textView;
    EditText editText;
    ImageView imageView;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState==null){
            Intent intent=new Intent(this,LoadingActivity.class);
            intent.putExtra("where",3000);
            startActivity(intent);
        }
        editText=findViewById(R.id.player_search);
        imageView=findViewById(R.id.goSerach);
        recyclerView=findViewById(R.id.main_recycle);
        backButtonPressHandler=new BackButtonPressHandler(this);
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if(i== EditorInfo.IME_ACTION_DONE)
                    imageView.performClick();
                return false;
            }
        });
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editText.getText().toString().length()==0){
                    Toast.makeText(MainActivity.this, "검색할 닉네임을 입력해주세용", Toast.LENGTH_SHORT).show();
                }else{
                    PlayerNickname=editText.getText().toString();
                    Intent intent=new Intent(MainActivity.this,matching_result.class);
                    intent.putExtra("nick",PlayerNickname);
                    startActivity(intent);
                }
            }
        });
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter=new MainRecyclerAdpater();
        recyclerView.setAdapter(adapter);

        GetRecycle();
    }
    private void GetRecycle(){
        List<String> ListTitle= Arrays.asList(
                "사이퍼즈 공식페이지 소식통",
                "캐릭터 정보",
                "캐릭터 랭킹 조회",
                "팬아트 게시판",
                "공략게시판",
                "포지션 특성 조회",
                "투신전 랭킹 조회",
                "사이퍼즈 세계관",
                "밸런스 토론장",
                "자유 게시판",
                "아이템 검색"
        );
        List<String> ListSubTitle=Arrays.asList(
                "사이퍼즈 이벤트/업데이트/오싸를 살펴봐용",
                "사이퍼즈의 사이퍼들에대해 집중탐구",
                "이 캐릭터는 내가 NO.1",
                "재미있는 게임캐릭터 아트들",
                "공략 게시판을 정독하면 티어상승",
                "각 포지션 특성에 대해 알아봅시다",
                "외침: 투신전 극 할사람 구해요 ",
                "세계관을 알고 게임을하면 백전백승",
                "사기케 너프좀",
                "자게이",
                "처음 보는 아이템들도 있단다"
                );
        List<Integer> listID=Arrays.asList(
                R.drawable.wesly,R.drawable.tien,R.drawable.ranking,R.drawable.louis,R.drawable.clare,R.drawable.position,R.drawable.ton,
                R.drawable.diemoos,R.drawable.alieshu,R.drawable.jagay,R.drawable.tara
        );
        for(int i=0; i<ListTitle.size(); i++){
            MainRecycleModel data=new MainRecycleModel();
            data.setTitle(ListTitle.get(i));
            data.setSubTitle(ListSubTitle.get(i));
            data.setId(listID.get(i));
            adapter.addItem(data);
        }
        adapter.notifyDataSetChanged();

    }


    @Override
    public void onBackPressed() {
        backButtonPressHandler.onBackPressed();
    }

}
