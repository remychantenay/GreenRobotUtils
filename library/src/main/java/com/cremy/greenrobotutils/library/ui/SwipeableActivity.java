package com.cremy.greenrobotutils.library.ui;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.cremy.greenrobotutils.library.R;

/**
 *
 * Created by Remy on 02/09/2015.
 */
public abstract class SwipeableActivity extends AppCompatActivity {


    protected SwipeRefreshLayout mSwipeRefreshLayout;
    protected static final int SWIPE_REFRESH_CHECK_DELAY = 1000; // In ms
    protected Handler handler = new Handler();
    protected boolean isRefreshing = false;


    abstract public void setUpSwipeRefreshLayout(View _rootView);


    /** Runnable that will check if data are loaded or not
     *
     */
    private final Runnable refreshingRunnable = new Runnable(){
        public void run(){
            try {
                if(isRefreshing){
                    handler.postDelayed(this, SWIPE_REFRESH_CHECK_DELAY);
                    stopRefreshing();
                }else{
                    // stop the animation after the data is fully loaded
                    mSwipeRefreshLayout.setRefreshing(false);

                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    /**
     * Allows to stop the refreshing effect
     *
     */
    public void stopRefreshing() {
        isRefreshing = false;
    }

    /**
     * Allows to start the refreshing effect
     */
    public void startRefreshing() {
        isRefreshing = true;
    }


    /**
     * Allows to set-up the refresh bar colors (default colors)
     */
    public void setRefreshBarColors(){
        // We set the different colors for the animation
        mSwipeRefreshLayout.setColorSchemeResources(R.color.gru_google_logo_blue, R.color.gru_google_logo_green,
                R.color.gru_google_logo_red, R.color.gru_google_logo_yellow);
    }


    /**
     * Allows to set-up the refresh bar colors
     * @param _color1
     * @param _color2
     * @param _color3
     * @param _color4
     */
    public void setRefreshBarColors(int _color1, int _color2, int _color3, int _color4){
        // We set the different colors for the animation
        mSwipeRefreshLayout.setColorSchemeResources(_color1, _color2,
                _color3, _color4);
    }

    /**
     * Allows to force the view to refresh itself
     */
    public void forceStartRefreshing() {
        mSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                if (!isRefreshing) {
                    mSwipeRefreshLayout.setRefreshing(true);
                }
            }
        });
    }


    public void forceStopRefreshing() {
        mSwipeRefreshLayout.setRefreshing(false);
    }
}
