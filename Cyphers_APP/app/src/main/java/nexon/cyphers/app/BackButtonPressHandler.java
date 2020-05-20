package nexon.cyphers.app;

import android.app.Activity;
import android.widget.Toast;

public class BackButtonPressHandler {
    private long backKeyPressedTime = 0;
    private Toast toast;
    private Activity activity;

    public BackButtonPressHandler(Activity context) {
        this.activity = context;
    }

    public void onBackPressed() {
        if (System.currentTimeMillis() > backKeyPressedTime + 2000) {
            backKeyPressedTime = System.currentTimeMillis();
            showGuide();
            return;
        }
        if (System.currentTimeMillis() <= backKeyPressedTime + 2000) {
            activity.finish();
            toast.cancel();
        }
    }

    public void showGuide() {
        toast = Toast.makeText(activity, "뒤로가기 버튼을 한번더 누를시 종료됩니다", Toast.LENGTH_SHORT);
        toast.show();
    }

}