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
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.*;
import com.google.example.games.basegameutils.BaseGameUtils;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.Player;
import com.google.android.gms.plus.Plus;


public class MainActivity extends FragmentActivity
		implements GoogleApiClient.ConnectionCallbacks,
		GoogleApiClient.OnConnectionFailedListener, View.OnClickListener {

	public final static String EXTRA_MESSAGE = "project.equationinvasion.MESSAGE";

	private GoogleApiClient googleApiClient;

	private static int RC_SIGN_IN = 9001;

	// Are we currently resolving a connection failure?
	private boolean resolvingConnectionFailure = false;

	// Has the user clicked the sign-in button?
	private boolean signInClicked = false;

	// Automatically start the sign-in flow when the Activity starts
	private boolean autoStartSignInFlow = true;

	/**
	 * Declarations for audio functionality
	 * -Matt
	 */
	protected Audio noise;

	private Button signInButton;
	private Button signOutButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

        /*
			This code relates to instantiating our audio.
         */
		noise = new Audio(MainActivity.this);
		noise.menuBGM();

		signInButton = (Button)findViewById(R.id.signIn);
		signOutButton = (Button)findViewById(R.id.signOut);


		/**
		 * Setting font style
		 */
		//Font path
		String chalkboardFontPath = "fonts/Chalkboard.ttf";

		//text view label
		TextView txtGameTitle = (TextView) findViewById(R.id.gameTitle);
		TextView txtGameInstructions = (TextView) findViewById(R.id.instructions);

		//Load Font Face
		Typeface chalkboardFont = Typeface.createFromAsset(getAssets(), chalkboardFontPath);

		//Applying font
		txtGameTitle.setTypeface(chalkboardFont);
		txtGameInstructions.setTypeface(chalkboardFont);

		googleApiClient = new GoogleApiClient.Builder(this)
				.addConnectionCallbacks(this)
				.addOnConnectionFailedListener(this)
				.addApi(Plus.API).addScope(Plus.SCOPE_PLUS_LOGIN)
				.addApi(Games.API).addScope(Games.SCOPE_GAMES)
				.build();
	}

	/** Called when the user clicks the Send button */
	/**
	 * public void sendMessage(View view) {
	 * Intent intent = new Intent(this, DisplayMessageActivity.class);
	 * EditText editText = (EditText) findViewById(R.id.edit_message);
	 * String message = editText.getText().toString();
	 * intent.putExtra(EXTRA_MESSAGE, message);
	 * startActivity(intent);
	 * }
	 */

	//Called when player clicks the Play button
	public void goToPlay(View view) {
		Intent intent = new Intent(this, Play.class);
		startActivity(intent);
	}

	//Called when player clicks the High Scores button
	public void goToHighScores(View view) {
		Intent intent = new Intent(this, HighScores.class);
		startActivity(intent);
	}

	//Called when player clicks the credits button
	public void goToCredits(View view) {
		Intent intent = new Intent(this, Credits.class);
		startActivity(intent);
	}

	public void muteToggle() {
		noise.toggleMute();
	}

	@Override
	protected void onUserLeaveHint() {
		super.onUserLeaveHint();
		noise.setSoundState(0);
		noise.buttonNoise();
		noise.pauseMusic();
	}

	@Override
	protected void onResume() {
		super.onResume();
		noise.resumeMusic();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		noise.close();
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

	@Override
	public void onConnected(Bundle bundle) {
		Player player = Games.Players.getCurrentPlayer(googleApiClient);
		String playername;
		if (player == null) {
			playername = "???";
		} else {
			playername = player.getDisplayName();
		}
		signInButton.setText("Hello " + playername);
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
		if (signInClicked || autoStartSignInFlow) {
			autoStartSignInFlow = false;
			signInClicked = false;
			resolvingConnectionFailure = true;
			if (!BaseGameUtils.resolveConnectionFailure(this, googleApiClient, connectionResult,
					//Replace the following string with a generic error message in Strings.xml
					RC_SIGN_IN, "There was an error connecting")) {
				resolvingConnectionFailure = false;
			}
		}
	}

	protected void onActivityResult(int requestCode, int resultCode,
									Intent intent) {
		if (requestCode == RC_SIGN_IN) {
			signInClicked = false;
			resolvingConnectionFailure = false;
			if (resultCode == RESULT_OK) {
				googleApiClient.connect();
			} else {
				// Bring up an error dialog to alert the user that sign-in
				// failed. The R.string.signin_failure should reference an error
				// string in your strings.xml file that tells the user they
				// could not be signed in, such as "Unable to sign in."
				BaseGameUtils.showActivityResultError(this,
						requestCode, resultCode, R.string.hello_world);
			}
		}
	}

//	public void onSignInButtonClicked(View view) {
//		signInClicked = true;
//		googleApiClient.connect();
//	}
//
//	public void onSignOutButtonClicked() {
//		signInClicked = false;
//		Games.signOut(googleApiClient);
//		if (googleApiClient.isConnected()) {
//			googleApiClient.disconnect();
//		}
//
//		mMainMenuFragment.setGreeting(getString(R.string.signed_out_greeting));
//		mMainMenuFragment.setShowSignInButton(true);
//		mWinFragment.setShowSignInButton(true);
//	}

	private boolean isSignedIn() {
		return (googleApiClient != null && googleApiClient.isConnected());
	}

	@Override
	public void onClick(View view) {
		if (view.getId() == signInButton.getId()) {
			signInClicked = true;
			googleApiClient.connect();
			TextView txtGameInstructions = (TextView) findViewById(R.id.instructions);
			txtGameInstructions.setText("Connecting");
		} else if (view.getId() == signOutButton.getId()) {
			signInClicked = false;
			Games.signOut(googleApiClient);
			if (googleApiClient.isConnected()) {
				googleApiClient.disconnect();
			}
//			mMainMenuFragment.setGreeting(getString(R.string.signed_out_greeting));
//			mMainMenuFragment.setShowSignInButton(true);
//			mWinFragment.setShowSignInButton(true);
		}
	}
}

