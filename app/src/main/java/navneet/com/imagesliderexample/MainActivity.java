package navneet.com.imagesliderexample;

import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Slide;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.Scroller;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Handler handler=new Handler();
    private final int delay = 2000;
    private int page = 0;
    private boolean isLeftDir;
    private ViewPager viewPager;
    private ViewPagerAdapter pagerAdapter;

    private ArrayList<SlideItem> slideItems=new ArrayList<>();

    Runnable runnable = new Runnable() {
        public void run() {

            if (pagerAdapter!= null) {
                if (page + 1 == pagerAdapter.getCount()) {
                    page--;
                    isLeftDir = true;
                } else {
                    if (page == 0)
                        isLeftDir = false;
                    if (isLeftDir) {
                        page--;
                    } else {
                        page++;
                    }
                }

                viewPager.setCurrentItem(page, true);
                if (pagerAdapter.getCount() > 1) {
                    handler.postDelayed(this, delay);
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        populateSlides();
        Field mScroller = null;
        try {
            mScroller = ViewPager.class.getDeclaredField("mScroller");
            mScroller.setAccessible(true);
            Scroller scroller = new Scroller(this, new DecelerateInterpolator());
            mScroller.set(viewPager, scroller);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private void populateSlides() {
        SlideItem slideItem=new SlideItem("benz","red","");
        SlideItem slideItem1=new SlideItem("bmw","red","");
        SlideItem slideItem2=new SlideItem("bentley","red","");
        SlideItem slideItem3=new SlideItem("volvo","red","");

        slideItems.add(slideItem);
        slideItems.add(slideItem1);
        slideItems.add(slideItem2);
        slideItems.add(slideItem3);

        pagerAdapter=new ViewPagerAdapter(this,slideItems,4);
        viewPager.setAdapter(pagerAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        handler.postDelayed(runnable, delay);
    }

    @Override
    public void onPause() {
        super.onPause();
        handler.removeCallbacks(runnable);
    }

}
