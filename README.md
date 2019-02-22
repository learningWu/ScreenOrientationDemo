# ScreenOrientationDemo
使用监听屏幕旋转角度，实现屏幕旋转重力感应问题

## Download
Gradle:
```
dependencies {
  implementation 'com.eddiezx:screen-orientation-listener:1.0.1'
}
```

Or Maven:
```
<dependency>
  ><groupId>com.eddiezx</groupId>
  ><artifactId>screen-orientation-listener</artifactId>
  ><version>1.0.1</version>
  ><type>pom</type>
</dependency>
```

## How do I use the library?
```
//for a example
//实现接口
public class MainActivity extends AppCompatActivity implements OrientationWatchDog.OnOrientationListener {
//省略无关代码...


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //省略无关代码...
        initOrientationWatchdog();//初始化屏幕角度监听器
        mTvStartSpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            //开启屏幕角度监听
                if (mOrientationWatchDog != null) {
                    mOrientationWatchDog.startWatch();
                }
            }
        });
        mTvStopSpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            //关闭屏幕角度监听
                if (mOrientationWatchDog != null) {
                    mOrientationWatchDog.stopWatch();
                }
            }
        });
   /**
   * 初始化屏幕方向监听器。用来监听屏幕方向。结果通过OrientationListener回调出去。
   */
    private void initOrientationWatchdog() {
        mOrientationWatchDog = new OrientationWatchDog(this);
        mOrientationWatchDog.setOnOrientationListener(this);
    }
    /**
    * 实现接口方法 changedToLandScape，changedToPortrait
    */
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
    }

}
```



## [csdn 介绍](https://blog.csdn.net/alearningWu/article/details/83751441)
