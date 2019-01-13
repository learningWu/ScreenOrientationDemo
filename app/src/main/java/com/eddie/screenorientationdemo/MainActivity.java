package com.eddie.screenorientationdemo;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.eddie.screenorientation.OrientationWatchDog;



public class MainActivity extends AppCompatActivity implements OrientationWatchDog.OnOrientationListener {

    TextView mTvStartSpan;
    TextView mTvStopSpan;
    /**
     * 监听类
     */
    private OrientationWatchDog mOrientationWatchDog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTvStartSpan = findViewById(R.id.tv_start_span);
        mTvStopSpan = findViewById(R.id.tv_stop_span);
        initOrientationWatchdog();
        mTvStartSpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOrientationWatchDog != null) {
                    mOrientationWatchDog.startWatch();
                }
            }
        });
        mTvStopSpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOrientationWatchDog != null) {
                    mOrientationWatchDog.stopWatch();
                }
            }
        });
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void changedToLandScape(boolean fromPort) {
        //如果不是从竖屏变过来，也就是一直是横屏的时候，就不用操作了
        if (!fromPort) {
            return;
        }
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    }

    @Override
    public void changedToPortrait(boolean fromLand) {
        //如果没有转到过横屏，就不让他转了。防止竖屏的时候点横屏之后，又立即转回来的现象
        if (!fromLand) {
            return;
        }
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    /**
     * 初始化屏幕方向监听器。用来监听屏幕方向。结果通过OrientationListener回调出去。
     */
    private void initOrientationWatchdog() {
        mOrientationWatchDog = new OrientationWatchDog(this);
        mOrientationWatchDog.setOnOrientationListener(this);
    }
}
