package core.be.ui;

import android.app.Activity;
import android.os.Bundle;

import android.os.Looper;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

public class BEPreloadActivity extends Activity {

    private ImageView ivLogo;
    private static final int SLEEPTIME = 5000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_bepreload);

        initComponentView();


//        logoMoveUp();
        new timerThread().start();
    }

    class timerThread extends Thread{
        public void run(){

            logoMoveUp();
        }
    }


    private void initComponentView(){
        ivLogo = (ImageView)findViewById(R.id.ivLogo);

    }

    private void logoMoveUp(){
        TranslateAnimation logoMovesUpAnimation;

        logoMovesUpAnimation = new TranslateAnimation(0, 0, 0, -100);
        logoMovesUpAnimation.setDuration(1500);
        logoMovesUpAnimation.setRepeatCount(0);

        logoMovesUpAnimation.setFillAfter(true);
        ivLogo.setAnimation(logoMovesUpAnimation);
        logoMovesUpAnimation.setStartOffset(5000);
        logoMovesUpAnimation.start();

        logoMovesUpAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}
