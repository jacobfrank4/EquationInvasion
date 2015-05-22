package project.equationinvasion;

/**
 * Copyright 2015 Avium Studios
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.games.Games;
import com.google.android.gms.plus.Plus;
import com.google.example.games.basegameutils.BaseGameUtils;

import java.text.DecimalFormat;

public class Play extends AppCompatActivity implements View.OnClickListener,
		GoogleApiClient.ConnectionCallbacks,
		GoogleApiClient.OnConnectionFailedListener {

	// The Google API client
	private static GoogleApiClient googleApiClient;

	// Are we currently resolving a connection failure?
	private static boolean resolvingConnectionFailure = false;

	// Has the user clicked the sign-in button?
	private static boolean signInClicked = false;

	// Automatically start the sign-in flow when the Activity starts
	private static boolean autoStartSignInFlow = true;

	private static final int RC_SIGN_IN = 9001;

	// Milliseconds in Seconds
	private static final int MILLI_IN_SECOND = 1000;

	// Start Score on this value
	private static final int STARTING_SCORE = 0;

	//Initial time for the game
	private static final int START_TIME = 60;

	// Start level at this value
	private static final int LEVEL_START = 1;

	//How much the score will increment for each right answer
	private static final int INCREMENT_SCORE = 100;

	// Seconds in a minute
	private static final int SECOND_IN_MINUTE = 60;

	// Milliseconds in a minute
	private static final int MILLI_IN_MINUTE = 60000;

	//How much time will be added every full streak bar
	private static final int INCREMENT_TIME = 10;

	//Base amount of seconds to subtract from every full fail streak
	private static final int DECREMENT_TIME = 5;

	/*
	 * If the timer is less than this (5 seconds * MILLI_IN_SECOND), do not decrement time,
	 * set time to 0
	 */
	private static final int MIN_TIME_DECREMENT = DECREMENT_TIME * MILLI_IN_SECOND;

	// Maximum streak before it resets
	private static final int STREAK_LIMIT = 5;

	/// Maximum level count
	private static final int MAX_LEVEL = 6;

	// Maximum times you can fail a question in a row before we penalize the player
	private static final int MAX_FAIL_STREAK = 3;

	//The color red
	private static final int RED = Color.rgb(255, 0, 0);

	//The color White
	private static final int WHITE = Color.rgb(255, 255, 255);

	//Ten seconds in milliseconds
	private static final int TEN_SECONDS = 10000;

	// Declaration for the textView that displays the equation.
	private static TextView equation;

	// Declaration for the textView that displays the possible answer
	private static TextView answer;

	// The textView that contains the countdown before game start
	private static TextView startGameTime;

	//Declaration for the display that either shows a check mark or X after answering
	private static ImageView feedback;

	// Declaration for the display that lets the user know 10 seconds have been added to the time
	private static ImageView addTime;

	// Declaration for the generator in EquationGenerator class
	private static EquationGenerator mathGen;

	// Declarations for the streak counter
	private static final ImageView[] pips = new ImageView[5];
	private static int streak;
	private static int failStreak;

	//Level increase streak
	private static int levelStreak;

	// Maximum level streak before it resets
	private static final int LEVEL_STREAK_LIMIT = 10;

	// Declarations for the countdown Timer
	private static final DecimalFormat fmt = new DecimalFormat("00");
	private static boolean running = false;
	private static long currentMilli = 0;
	private static CountDownTimer timer;
	private static TextView time;

	//Array of feedBackTimers, one for every element that needs one
	private static final feedBackTimer[] feedBackDelay = new feedBackTimer[4];

	// Level Changer
	private static TextView levelView;
	private static int currentLevel;

	// Score tracking
	private int score = STARTING_SCORE;
	private static TextView scoreDisplay;

	// Audio variables for this page.
	private static Audio noise;
	private boolean finished = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_play);

		// Instantiating everything for streak counter
		streak = 0;
		failStreak = 0;
		pips[0] = (ImageView) findViewById(R.id.imageView);
		pips[1] = (ImageView) findViewById(R.id.imageView2);
		pips[2] = (ImageView) findViewById(R.id.imageView3);
		pips[3] = (ImageView) findViewById(R.id.imageView4);
		pips[4] = (ImageView) findViewById(R.id.imageView5);

		// Instantiating level streak counter
		levelStreak = 0;

		// Create the google Api Client with access to the play Game services
		googleApiClient = new GoogleApiClient.Builder(this)
				.addConnectionCallbacks(this)
				.addOnConnectionFailedListener(this)
				.addApi(Plus.API).addScope(Plus.SCOPE_PLUS_LOGIN)
				.addApi(Games.API).addScope(Games.SCOPE_GAMES)
				.build();

		// Instantiating what I need for the timer
		time = (TextView) findViewById(R.id.time);
		timer = new MyTimer(START_TIME * MILLI_IN_SECOND);

		startGameTime = (TextView) findViewById(R.id.countDown);

		// Level System
		levelView = (TextView) findViewById(R.id.levelView);
		currentLevel = LEVEL_START;

		// Adding score
		scoreDisplay = (TextView) findViewById(R.id.scoreDisplay);

		// True and False buttons
		final Button TRUE = (Button) findViewById(R.id.trueBtn);
		final Button FALSE = (Button) findViewById(R.id.falseBtn);
		TRUE.setOnClickListener(this);
		FALSE.setOnClickListener(this);

		equation = (TextView) findViewById(R.id.leftEquation);
		answer = (TextView) findViewById(R.id.solution);
		mathGen = new EquationGenerator();
		feedback = (ImageView) findViewById(R.id.feedback);
		addTime = (ImageView) findViewById(R.id.addTime);

		// Setting font style
		//Font path
		String chalkboardFontPath = "fonts/Chalkboard.ttf";

		//text view label
		TextView txtEquals = (TextView) findViewById(R.id.equals);

		//Load Font Face
		Typeface chalkboardFont = Typeface.createFromAsset(getAssets(), chalkboardFontPath);

		//Applying font
		equation.setTypeface(chalkboardFont);
		time.setTypeface(chalkboardFont);
		scoreDisplay.setTypeface(chalkboardFont);
		answer.setTypeface(chalkboardFont);
		txtEquals.setTypeface(chalkboardFont);
		levelView.setTypeface(chalkboardFont);
		startGameTime.setTypeface(chalkboardFont);

		//generating first equation
		mathGen.generate(currentLevel);

		//Starting up audio functionality
		noise = new Audio(Play.this);
		noise.playBGM();

		/**
		 * 0 - Pip Timer
		 * 1 - Feedback Timer
		 * 2 - AddTime Timer
		 */
		feedBackDelay[0] = new feedBackTimer(pips);
		feedBackDelay[1] = new feedBackTimer(feedback);
		feedBackDelay[2] = new feedBackTimer(addTime);
		feedBackDelay[3] = new feedBackTimer();

		startGame(startGameTime);
	}

	@Override
	public void onClick(View view) {
		if (view.getId() == R.id.trueBtn || view.getId() == R.id.falseBtn) {
			if (view.getId() == R.id.trueBtn) {
				truthChecker();
			} else {
				falseChecker();
			}
			mathGen.generate(currentLevel);
		}
	}

	@Override
	public void onConnected(Bundle bundle) {
	}

	@Override
	public void onConnectionSuspended(int i) {
		googleApiClient.connect();
	}

	@Override
	public void onConnectionFailed(ConnectionResult connectionResult) {
		if (resolvingConnectionFailure) {
			return;
		}
		//If the sign-in button was clicked or if auto sign-in is enabled,
		//launch the sign-in flow
		if (signInClicked || autoStartSignInFlow) {
			autoStartSignInFlow = false;
			signInClicked = false;
			resolvingConnectionFailure = true;

			// Attempt to resolve the connection failure using BaseGameUtils.
			if (!BaseGameUtils.resolveConnectionFailure(this, googleApiClient, connectionResult,
					//Replace the following string with a generic error message in Strings.xml
					RC_SIGN_IN, getResources().getString(R.string.signin_error))) {
				resolvingConnectionFailure = false;
			}
		}
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
		if (requestCode == RC_SIGN_IN) {
			signInClicked = false;
			resolvingConnectionFailure = false;
			if (resultCode == RESULT_OK) {
				googleApiClient.connect();
			} else {
				// Bring up an error dialog to alert the user that sign-in failed.
				BaseGameUtils.showActivityResultError(this,
						requestCode, resultCode, R.string.signin_error);
			}
		}
	}

	@Override
	protected void onStart() {
		super.onStart();
		googleApiClient.connect();
	}

	@Override
	protected void onStop() {
		super.onStop();
		googleApiClient.disconnect();
	}

	private boolean isSignedIn() {
		return (googleApiClient != null && googleApiClient.isConnected());
	}

	// Moved on button click of add time to a method to call
	private void addTime() {
		if (running) {
			timer.cancel();
			timer = new MyTimer(currentMilli + (INCREMENT_TIME * MILLI_IN_SECOND));
			timer.start();
		}
	}

	// Subtract time method
	private void subtractTime() {
		if (running && currentMilli > MIN_TIME_DECREMENT) {
			timer.cancel();
			timer = new MyTimer(currentMilli - (MIN_TIME_DECREMENT));
			timer.start();
		} else {
			timer.cancel();
			currentMilli = 0;
			timer = new MyTimer(currentMilli);
			timer.start();
		}
	}

	/**
	 * Switch statement utilizes fall-through to keep pips highlighted depending on the value
	 * of streak. There's a break to prevent it falling into the default case.
	 */
	private void pipChanger() {
		switch (streak) {
			case 5:
				addTime.setVisibility(View.VISIBLE);
				feedBackDelay[2].cancel();
				pips[4].setImageResource(R.drawable.streakpipon);
				feedBackDelay[0].start();
				addTime.setImageResource(R.drawable.plusten);
				if (levelStreak == LEVEL_STREAK_LIMIT) {
					levelChanger();
				}
				addTime();
				feedBackDelay[2].start();
			case 4:
				pips[3].setImageResource(R.drawable.streakpipon);
			case 3:
				pips[2].setImageResource(R.drawable.streakpipon);
			case 2:
				pips[1].setImageResource(R.drawable.streakpipon);
			case 1:
				pips[0].setImageResource(R.drawable.streakpipon);
				break;
			default:
				for (ImageView pip : pips) {
					pip.setImageResource(R.drawable.streakpipoff);
				}
		}
		streak %= STREAK_LIMIT;
		levelStreak %= LEVEL_STREAK_LIMIT;
	}

	// method to change level on method call
	private void levelChanger() {
		if (currentLevel < MAX_LEVEL) {
			currentLevel++;
			// Level Achievements
			if (isSignedIn()) {
				switch (currentLevel) {
					case 2:
						Games.Achievements.unlock(googleApiClient, "CgkIsIanxbIGEAIQAA");
						break;
					case 3:
						Games.Achievements.unlock(googleApiClient, "CgkIsIanxbIGEAIQAQ");
						break;
					case 4:
						Games.Achievements.unlock(googleApiClient, "CgkIsIanxbIGEAIQAg");
						break;
					case 5:
						Games.Achievements.unlock(googleApiClient, "CgkIsIanxbIGEAIQAw");
						break;
					case 6:
						Games.Achievements.unlock(googleApiClient, "CgkIsIanxbIGEAIQBA");
				}
			}
		}
		levelView.setText("Level: " + currentLevel);
	}

	// Increments the score.
	private void scoreCounter() {
		score += (INCREMENT_SCORE * currentLevel);
		scoreDisplay.setText("Score: " + score);
		// Score Achievements
		if (isSignedIn()) {
			if (score >= 5000) {
				Games.Achievements.unlock(googleApiClient, "CgkIsIanxbIGEAIQCg");
			}
			if (score >= 1000) {
				Games.Achievements.unlock(googleApiClient, "CgkIsIanxbIGEAIQCw");
			}
			if (score >= 15000) {
				Games.Achievements.unlock(googleApiClient, "CgkIsIanxbIGEAIQDA");
			}
			if (score >= 20000) {
				Games.Achievements.unlock(googleApiClient, "CgkIsIanxbIGEAIQDQ");
			}
			if (score >= 35000) {
					Games.Achievements.unlock(googleApiClient, "CgkIsIanxbIGEAIQDg");
			}
		}
	}

	public static TextView getEquationTextView() {
		return equation;
	}

	public static TextView getAnswerTextView() {
		return answer;
	}

	/**
	 * Getter for the current level so as to generate correct equation
	 * @return currentLevel
	 *          The current level of the game
	 */
	public static int getCurrentLevel() {
		return currentLevel;
	}

	private static TextView getStartCountDown() {
		return startGameTime;
	}

	/**
	 * validation for the truth button
	 *      Sets the visibility of the picture to visible
	 *      deletes any old timer that removes the picture of checkmark/X
	 *      checks for correctness
	 *      if correct
	 *          display checkmark
	 *          increase score
	 *          change changes
	 *      if incorrect
	 *          displays X
	 *          resets streak
	 */
	private void truthChecker() {
		feedback.setVisibility(View.VISIBLE);
		feedBackDelay[1].cancel();
		if (mathGen.getAnswer() == mathGen.getEquation()) {
			feedback.setImageResource(R.drawable.checkmark);
			failStreak = 0;
			streak++;
			levelStreak++;
			scoreCounter();
			pipChanger();
			noise.rightNoise();
		} else {
			feedback.setImageResource(R.drawable.x);
			streak = 0;
			levelStreak = 0;
			failStreak++;
			if (failStreak == MAX_FAIL_STREAK) {
				subtractTime();
				addTime.setImageResource(R.drawable.minusfive);
				addTime.setVisibility(View.VISIBLE);
				if (currentLevel > LEVEL_START) {
					currentLevel--;
					levelView.setText("Level: " + currentLevel);
					Games.Achievements.unlock(googleApiClient, "CgkIsIanxbIGEAIQDw");
				}
				failStreak = 0;
				feedBackDelay[2].start();
			}
			pipChanger();
			noise.wrongNoise();
		}
		feedBackDelay[1].start();
	}

	/**
	 * validation for the false button
	 *      Sets the visibility of the picture to visible
	 *      deletes any old timer that removes the picture of checkmark/X
	 *      checks for correctness
	 *      if correct
	 *          display checkmark
	 *          increase score
	 *          change changes
	 *      if incorrect
	 *          displays X
	 *          resets streak
	 *
	 */
	private void falseChecker() {
		feedback.setVisibility(View.VISIBLE);
		feedBackDelay[1].cancel();
		if (mathGen.getAnswer() != mathGen.getEquation()) {
			feedback.setImageResource(R.drawable.checkmark);
			failStreak = 0;
			streak++;
			levelStreak++;
			scoreCounter();
			pipChanger();
			noise.rightNoise();
		} else {
			feedback.setImageResource(R.drawable.x);
			streak = 0;
			levelStreak = 0;
			failStreak++;
			if (failStreak == 3) {
				subtractTime();
				addTime.setImageResource(R.drawable.minusfive);
				addTime.setVisibility(View.VISIBLE);
				if (currentLevel > LEVEL_START) {
					currentLevel--;
					levelView.setText("Level: " + currentLevel);
					Games.Achievements.unlock(googleApiClient, "CgkIsIanxbIGEAIQDw");
				}
				failStreak = 0;
				feedBackDelay[2].start();
			}
			pipChanger();
			noise.wrongNoise();
		}
		feedBackDelay[1].start();
	}

	private void playGame(View view) {
		//Iterates through all the views on the activity
		final ViewGroup allViews = (ViewGroup) view.getParent();
		for (int i = 0; i < allViews.getChildCount(); i++) {
			allViews.getChildAt(i).setVisibility(View.VISIBLE);
		}
		//This is startGameTime, the countdown before the game starts, don't want to show it on game start
		view.setVisibility(View.INVISIBLE);

		//Starting the timer
		timer.start();
		running = true;
	}

	private void startGame(View view) {
		final ViewGroup allViews = (ViewGroup) view.getParent();
		for (int i = 0; i < allViews.getChildCount(); i++) {
			allViews.getChildAt(i).setVisibility(View.INVISIBLE);
		}
		view.setVisibility(View.VISIBLE);
		feedBackDelay[3].start();
	}

	// The main timer class for the game
	private class MyTimer extends CountDownTimer {
		public MyTimer(long duration) {
			super(duration, 1000L);
		}

		/**
		 * Ticks every second and changes the text of the TextView to represent that change
		 *
		 * @param millisUntilFinished The amount of time left on the timer. THIS IS THE ONLY WAY IT CAN BE VIEWED OR ACCESSED.
		 */
		@Override
		public void onTick(long millisUntilFinished) {
			if (millisUntilFinished <= TEN_SECONDS) {
				time.setTextColor(RED);
			} else {
				time.setTextColor(WHITE);
			}
			time.setText(formatTime(millisUntilFinished));
			currentMilli = millisUntilFinished;
		}

		// Method called when the time reaches zero
		@Override
		public void onFinish() {
			time.setText("Game Over");
			if (!finished) {
				Intent intent = new Intent(getApplicationContext(), GameOver.class);
				intent.putExtra("Score", score);
				noise.stopMusic();
				startActivity(intent);
				finish();
			}
		}

		// My method to format the time from milliseconds to a string that is used for the textview
		private String formatTime(long currentTime) {
			final long minute;
			final long second;

			second = (currentTime / MILLI_IN_SECOND) % SECOND_IN_MINUTE;
			minute = currentTime / MILLI_IN_MINUTE;
			return minute + ":" + fmt.format(second);
		}
	}

	/**
	 * A feedback timer class
	 * It takes either a View or an array of Views and sets the duration of the timer accordingly
	 * When the timer is finished, it makes the view invisible
	 */
	private class feedBackTimer {
		private final int duration;
		private final CountDownTimer timer;

		public feedBackTimer(final View view) {
			if (view.getId() == feedback.getId()) {
				duration = 250;
			} else {
				duration = 1000;
			}
			timer = new CountDownTimer(duration, 1000L) {
				@Override
				public void onTick(long l) {
				}

				@Override
				public void onFinish() {
					view.setVisibility(View.INVISIBLE);
				}
			};
		}

		public feedBackTimer(final View[] view) {
			duration = 1000;
			timer = new CountDownTimer(duration, 1000L) {
				@Override
				public void onTick(long l) {
				}

				@Override
				public void onFinish() {
					if (view instanceof ImageView[]) {
						for (ImageView item : (ImageView[]) view)
							item.setImageResource(R.drawable.streakpipoff);
					} else {
						for (View item : view)
							item.setVisibility(View.INVISIBLE);
					}
				}
			};
		}

		public feedBackTimer() {
			final TextView readySetGo = Play.getStartCountDown();
			duration = 3500;
			timer = new CountDownTimer(duration, 1000L) {
				@Override
				public void onTick(long l) {
					readySetGo.setText(String.valueOf(l / 1000));
				}

				@Override
				public void onFinish() {
					readySetGo.setVisibility(View.INVISIBLE);
					playGame(readySetGo);
				}
			};
		}

		public void start() {
			timer.start();
		}

		public void cancel() {
			timer.cancel();
		}
	}

	//This gets called when the back button is pressed, and closes the activity. It also boots you to main menu.
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		finished = true;
		noise.stopMusic();
		noise.menuBGM();
		finish();
	}

	//Defines what happens when the user pauses the app via lock/home button.
	protected void onPause() {
		super.onPause();
		if (!finished) {
			noise.stopMusic();
		}
		finished = true;
	}

	//defines what happens when the user navigates away from activity
	@Override
	protected void onUserLeaveHint() {
		super.onUserLeaveHint();
		if (!finished) {
			noise.stopMusic();
		}
		finished = true;
	}

	//defines what happens when user returns to the activity from else where
	@Override
	protected void onResume() {
		super.onResume();
		if (finished){
			timer.onFinish();
			final Intent intent = new Intent(getApplicationContext(), GameOver.class);
			intent.putExtra("Score", score);
			startActivity(intent);
			finish();
		}
	}
}

