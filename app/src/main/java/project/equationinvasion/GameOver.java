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
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.games.Games;
import com.google.android.gms.plus.Plus;
import com.google.example.games.basegameutils.BaseGameUtils;

public class GameOver extends AppCompatActivity implements
		GoogleApiClient.OnConnectionFailedListener,
		GoogleApiClient.ConnectionCallbacks {

	private static GoogleApiClient googleApiClient;

	private static final int RC_HIGH_SCORE = 1337;

	private static final int RC_SIGN_IN = 9001;

	// Are we currently resolving a connection failure?
	private static boolean resolvingConnectionFailure = false;

	// Has the user clicked the sign-in button?
	private static boolean signInClicked = false;

	// Automatically start the sign-in flow when the Activity starts
	private static boolean autoStartSignInFlow = true;

	// The score the user got while playing
	private static int score;

	//Declarations for audio functionality
	private static Audio noise;
	private boolean finished;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game_over);
		googleApiClient = new GoogleApiClient.Builder(this)
				.addConnectionCallbacks(this)
				.addOnConnectionFailedListener(this)
				.addApi(Plus.API).addScope(Plus.SCOPE_PLUS_LOGIN)
				.addApi(Games.API).addScope(Games.SCOPE_GAMES)
				.build();

		//Standard audio instantiation
		noise = new Audio(GameOver.this);
		noise.overBGM();

		final TextView scoreDisplay = (TextView) findViewById(R.id.finalScore);

		//Font path
		final String chalkboardFontPath = "fonts/Chalkboard.ttf";

		//Load Font Face
		final Typeface chalkboardFont = Typeface.createFromAsset(getAssets(), chalkboardFontPath);

		// Retrieving the score
		score = getIntent().getIntExtra("Score", 0);

		//Applying font
		scoreDisplay.setTypeface(chalkboardFont);

		scoreDisplay.setText("Great Job!\n you scored\n\n\n" +
				String.valueOf(score) + " points");
	}

	//Called when player clicks the Play button
	public void goToPlay(View view) {
		final Intent intent = new Intent(this, Play.class);
		noise.stopMusic();
		startActivity(intent);
		finished = true;
		finish();
	}

	//Called when player clicks the High Scores button
	public void goToHighScores(View view) {
		if (isSignedIn()) {
			startActivityForResult(Games.Leaderboards.getLeaderboardIntent(googleApiClient,
					"CgkIsIanxbIGEAIQBg"), RC_HIGH_SCORE);
		}
	}

	public void goToMain(View view) {
		onBackPressed();
	}


	//defines what happens when the user navigates away from activity
	@Override
	protected void onUserLeaveHint() {
		super.onUserLeaveHint();
		noise.transitionNoise();
	}

	//Defines what happens when the phone's back button is pushed
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		noise.stopMusic();
		noise.transitionNoise();
		finished = true;
		noise.menuBGM();
		finish();
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

	//What occurs when the player is signed in and connected to Google Play services
	@Override
	public void onConnected(Bundle bundle) {
		if (isSignedIn()) {
			Games.Leaderboards.submitScore(googleApiClient,
					"CgkIsIanxbIGEAIQBg", score);
		}
	}

	//Attempt to reconnect
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

	private boolean isSignedIn() {
		return (googleApiClient != null && googleApiClient.isConnected());
	}
}
