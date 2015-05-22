package project.equationinvasion;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher.ViewFactory;


public class Instructions extends AppCompatActivity {

	private static Audio noise;
	private boolean finished = false;

	//instruction texts for the slides
	private static final String One = "You must determine whether the given solution is true or false by tapping the corresponding button";
	private static final String Two = "Correctly answer five equations in a row, and 10 seconds will be added to your time.";
	private static final String Three = "Correctly answer ten equations in a row, and you will go up a level. The difficulty increases with each level.";
	private static final String Four = "If you answer an equation incorrectly the streak resets. If you answer three equations in a row incorrectly, you lose 5 seconds and go down one level.";
	private static final String Five = "BEDMAS";

	// Arrays for the instructions
	private static final String[] TEXTS = {Five, One, Two, Three, Four,};
	private static final int[] IMAGES = {R.drawable.instructions5, R.drawable.instructions1,
			R.drawable.instructions2, R.drawable.instructions3, R.drawable.instructions4};

	// Text and Image switcher objects
	private static TextSwitcher mTextSwitcher;
	private static ImageSwitcher mImageSwitcher;

	// position for the array
	private static int Position = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_instructions);

		//Font path
		final String chalkboardFontPath = "fonts/Chalkboard.ttf";

		//Load Font Face
		final Typeface chalkboardFont = Typeface.createFromAsset(getAssets(), chalkboardFontPath);

		// Sets up the text for the slides
		mTextSwitcher = (TextSwitcher) findViewById(R.id.textSwitcher);
		mTextSwitcher.setFactory(new ViewFactory() {
			@Override
			public View makeView() {
				final TextView textView = new TextView(Instructions.this);
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
				return new ImageView(Instructions.this);
			}
		});
		mImageSwitcher.setInAnimation(this, android.R.anim.slide_in_left);
		mImageSwitcher.setOutAnimation(this, android.R.anim.slide_out_right);

		switchNext(null);

		//audio set up
		noise = new Audio(Instructions.this);
	}

	// switch for the slides
	public void switchNext(View view) {
		Position++;
		Position %= 5;
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
		noise.transitionNoise();
		finished = true;
		finish();
	}

	//This method is called when user leaves screen.
	@Override
	protected void onUserLeaveHint() {
		super.onUserLeaveHint();
		noise.transitionNoise();
	}

	//Defines what happens when the user pauses the app via lock/home button.
	protected void onPause() {
		super.onPause();
		if (!finished) {
			noise.pauseMusic();
		}
	}

	//defines what happens when user returns to the activity from else where
	@Override
	protected void onResume() {
		super.onResume();
		noise.resumeMusic();
	}
}
