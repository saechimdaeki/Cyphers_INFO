package nexon.cyphers.app.Toosin;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import nexon.cyphers.app.R;
import nexon.cyphers.app.Toosin.Fragment.Toosin_Melee;
import nexon.cyphers.app.Toosin.Fragment.Toosin_Par;

public class ToosinActivity extends AppCompatActivity {

    private FragmentManager fm;
    private FragmentTransaction ft;
    private Toosin_Melee meleeFrag;
    private Toosin_Par parFrag;
    private BottomNavigationView bottomNavigationView;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toosin);
        meleeFrag=new Toosin_Melee();
        parFrag=new Toosin_Par();
        bottomNavigationView = findViewById(R.id.bottomNavi);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.toosin_punch:
                        setFrag(0);
                        break;
                    case R.id.toosin_par:
                        setFrag(1);
                        break;
                }
                return true;
            }
        });
      setFrag(0);

    }
    public void setFrag(int n) {
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        switch (n) {
            case 0:
                meleeFrag=new Toosin_Melee();
                ft.replace(R.id.main_frame, meleeFrag);
                ft.commit();
                Toast.makeText(this, "투신전 격(으)로 이동합니다", Toast.LENGTH_SHORT).show();
                break;
            case 1:
                parFrag=new Toosin_Par();
                ft.replace(R.id.main_frame, parFrag);
                ft.commit();
                Toast.makeText(this, "투신전 파(으)로 이동합니다", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
