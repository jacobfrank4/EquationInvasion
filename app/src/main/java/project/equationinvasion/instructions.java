package project.equationinvasion;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher.ViewFactory;


public class instructions extends AppCompatActivity {

    private Audio noise;
    private boolean finished = false;

    private static final String[] TEXTS = {"How to play", "Streak/Add Time", "Level Up", "lose time/level Down", "BEDMAS"};
    private static final int[] IMAGES = {R.drawable.instructions1, R.drawable.instructions2, R.drawable.instructions3, R.drawable.instructions4, R.drawable.instructions5};
    private int mPosition = 0;
    private TextSwitcher mTextSwitcher;
    private ImageSwitcher mImageSwitcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructions);

        // slide set up
        mTextSwitcher = (TextSwitcher) findViewById(R.id.textSwitcher);
        mTextSwitcher.setFactory(new ViewFactory() {
            @Override
            public View makeView() {
                TextView textView = new TextView(instructions.this);
                textView.setGravity(Gravity.CENTER);
                return textView;
            }
        });
        mTextSwitcher.setInAnimation(this, android.R.anim.fade_in);
        mTextSwitcher.setOutAnimation(this, android.R.anim.fade_out);

        mImageSwitcher = (ImageSwitcher) findViewById(R.id.imageSwitcher);
        mImageSwitcher.setFactory(new ViewFactory() {
            @Override
            public View makeView() {
                ImageView imageView = new ImageView(instructions.this);
                return imageView;
            }
        });
        mImageSwitcher.setInAnimation(this, android.R.anim.slide_in_left);
        mImageSwitcher.setOutAnimation(this, android.R.anim.slide_out_right);

        switchNext(null);
//        switchLeft(null);

       //audio set up
        noise = new Audio(instructions.this);
//
//        //Font path
//        String chalkboardFontPath = "fonts/Chalkboard.ttf";
//
//        //Load Font Face
//        Typeface chalkboardFont = Typeface.createFromAsset(getAssets(),chalkboardFontPath);
//
//        TextView instructions = (TextView) findViewById(R.id.instructions);
//
//        instructions.setTypeface(chalkboardFont);
    }

    // switch for the slides
    public void switchNext(View view) {
        mTextSwitcher.setText(TEXTS[mPosition]);
        mImageSwitcher.setBackgroundResource(IMAGES[mPosition]);
        mPosition = (mPosition + 1) % TEXTS.length;
    }

//    public void switchLeft(View view) {
//        mTextSwitcher.setText(TEXTS[mPosition]);
//        mImageSwitcher.setBackgroundResource(IMAGES[mPosition]);
//        mPosition = (mPosition - 1) % TEXTS.length;
//    }


    public void goBack(View view) {
        onBackPressed();
    }

    //Returns you to the extras screen when back is oushed, and close this activity.
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        noise.setSoundState(0);
        noise.buttonNoise();
        finished = true;
        finish();
    }

    //This method is called when user leaves screen.
    @Override
    protected void onUserLeaveHint() {
        super.onUserLeaveHint();
        noise.setSoundState(0);
        noise.buttonNoise();
        if (!finished){
            noise.pauseMusic();
        }
    }

    //called upon return from home button pressed.
    @Override
    protected void onResume() {
        super.onResume();
        noise.resumeMusic();
    }
}
