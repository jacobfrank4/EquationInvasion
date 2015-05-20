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
import android.widget.ToggleButton;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.games.Games;
import com.google.android.gms.plus.Plus;
import com.google.example.games.basegameutils.BaseGameUtils;


public class MainActivity extends AppCompatActivity implements
		GoogleApiClient.ConnectionCallbacks,
		GoogleApiClient.OnConnectionFailedListener,
		View.OnClickListener {

	private GoogleApiClient googleApiClient;

	private static final int RC_SIGN_IN = 9001;

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
	private Audio noise;
	private static boolean muted;
	private boolean finished = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		findViewById(R.id.sign_in_button).setOnClickListener(this);
		findViewById(R.id.sign_out_button).setOnClickListener(this);

        /*
			This code relates to instantiating our audio.
			and ensuring proper mute button state.
         */
		noise = new Audio(MainActivity.this);
		noise.menuBGM();
		ToggleButton tb = (ToggleButton) findViewById(R.id.toggleButton);
		if (muted){
			tb.setChecked(true);
		}

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

		// Create the google Api Client with access to the play Game services
		googleApiClient = new GoogleApiClient.Builder(this)
				.addConnectionCallbacks(this)
				.addOnConnectionFailedListener(this)
				.addApi(Plus.API).addScope(Plus.SCOPE_PLUS_LOGIN)
				.addApi(Games.API).addScope(Games.SCOPE_GAMES)
				.build();
	}

	//Called when player clicks the Play button
	public void goToPlay(View view) {
		Intent intent = new Intent(this, Play.class);
		startActivity(intent);
		finished = true;
		noise.stopMusic();

	}


	//Called when player clicks the credits button
	public void goToExtras(View view) {
		Intent intent = new Intent(this, Extras.class);
		startActivity(intent);
		finished = true;
	}

	//mute button toggle method..
	public void muteToggle(View view) {
		noise.toggleMute();
		muted = !muted;
	}

	@Override
	protected void onUserLeaveHint() {
		super.onUserLeaveHint();
		noise.setSoundState(0);
		noise.buttonNoise();
		if (!finished){
			noise.pauseMusic();
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		noise.resumeMusic();
		finished = false;
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
		// show sign-out button, hide the sign-in button
		findViewById(R.id.sign_in_button).setVisibility(View.GONE);
		findViewById(R.id.sign_out_button).setVisibility(View.VISIBLE);
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
					RC_SIGN_IN, getResources().getString(R.string.signin_error))) {
				resolvingConnectionFailure = false;
			}
		}
		findViewById(R.id.sign_in_button).setVisibility(View.VISIBLE);
		findViewById(R.id.sign_out_button).setVisibility(View.GONE);
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

	@Override
	public void onClick(View view) {
		if (view.getId() == R.id.sign_in_button) {
			signInClicked = true;
			googleApiClient.connect();
		} else if (view.getId() == R.id.sign_out_button) {
			signInClicked = false;

			// show sign-in button, hide the sign-out button
			findViewById(R.id.sign_in_button).setVisibility(View.VISIBLE);
			findViewById(R.id.sign_out_button).setVisibility(View.GONE);

			Games.signOut(googleApiClient);
			if (googleApiClient.isConnected()) {
				googleApiClient.disconnect();
			}
		}
	}
}

