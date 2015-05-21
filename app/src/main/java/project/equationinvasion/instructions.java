package project.equationinvasion;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
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

    //instruction texts for the slides
    private static final String One = "You must determine whether the given solution is true or false by tapping the corresponding button";
    private static final String Two = "Correctly answer five equations in a row, and 10 seconds will be added to your time.";
    private static final String Three = "Correctly answer ten equations in a row, and you will go up a level. The difficulty increases with each level.";
    private static final String Four = "If you answer an equation incorrectly the streak resets. If you answer three equations in a row incorrectly, you lose 5 seconds and go down one level.";
    private static final String Five = "BEDMAS";

    // arrrays for the instructions
    private static final String[] TEXTS = {Five, One, Two, Three, Four,};
    private static final int[] IMAGES = {R.drawable.instructions5, R.drawable.instructions1, R.drawable.instructions2, R.drawable.instructions3, R.drawable.instructions4};

    // Text and Image switcher objects
    private TextSwitcher mTextSwitcher;
    private ImageSwitcher mImageSwitcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructions);

        //Font path
        String chalkboardFontPath = "fonts/Chalkboard.ttf";

        //Load Font Face
        final Typeface chalkboardFont = Typeface.createFromAsset(getAssets(),chalkboardFontPath);

        // Sets up the text for the slides
        mTextSwitcher = (TextSwitcher) findViewById(R.id.textSwitcher);
        mTextSwitcher.setFactory(new ViewFactory() {
            @Override
            public View makeView() {
                TextView textView = new TextView(instructions.this);
                textView.setGravity(Gravity.CENTER);
                textView.setTypeface(chalkboardFont);
                textView.setTextColor(Color.rgb(255, 201, 14));
                return textView;
            }
        });
        mTextSwitcher.setInAnimation(this, android.R.anim.fade_in);
        mTextSwitcher.setOutAnimation(this, android.R.anim.fade_out);

        //sets up the Images for the slide
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

       //audio set up
        noise = new Audio(instructions.this);
    }

    // position for the array
    private int Position = 0;

    // goes to the next slide
    public void switchNext(View view) {
        Position++;
        if (Position == 5) {
            Position = 0;
        }
        mTextSwitcher.setText(TEXTS[Position]);
        mImageSwitcher.setBackgroundResource(IMAGES[Position]);

    }
    // Goes to the previous slide
    public void switchPrevious(View view) {
        Position--;
        if (Position == -1) {
            Position = 4;
        }
        mTextSwitcher.setText(TEXTS[Position]);
        mImageSwitcher.setBackgroundResource(IMAGES[Position]);
    }

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
