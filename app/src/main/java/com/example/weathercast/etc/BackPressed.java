package com.example.weathercast.etc;

import android.app.Activity;
import android.widget.Toast;

public class BackPressed {
    private long backspacePressedTime = 0;
    private final Activity activity;
    private Toast toast;

    public BackPressed (Activity activity) {
        this.activity = activity;
    }

    private void showToast(String msg) {
        toast = Toast.makeText(activity, msg, Toast.LENGTH_SHORT);
        toast.show();
    }

    // 뒤로가기 눌렸을 때
    public void onBackPressed(String msg) {
        // System.currentTimeMillis : 70년 1월 1일 00시부터 현재까지의 시간을 가져옴
        if (System.currentTimeMillis() > backspacePressedTime + 2000) {
            backspacePressedTime = System.currentTimeMillis();
            showToast(msg);
            return;
        }

        // 두번 누르면 종료시키고 Toast msg도 캔슬한다.
        if (System.currentTimeMillis() <= backspacePressedTime + 2000) {
            activity.finish();
            toast.cancel();
        }
    }
}
