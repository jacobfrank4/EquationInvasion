package project.equationinvasion;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.*;
import com.google.example.games.basegameutils.BaseGameUtils;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.Player;
import com.google.android.gms.plus.Plus;


public class GameOver extends AppCompatActivity implements
        GoogleApiClient.OnConnectionFailedListener,
        GoogleApiClient.ConnectionCallbacks {

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

        googleApiClient.connect();
        // Create the google Api Client with access to the play Game services
        //updateLeaderboard();

        //Standard audio instantiation
        noise = new Audio(GameOver.this);
        noise.overBGM();
    }


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

    public void goToMain(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_game_over, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
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
        Log.d("fuck", "CgkI-_7R9foMEAIQAA");
        if (isSignedIn()) {
            Log.d("fuck", "If Statement runs");
            Games.Leaderboards.submitScore(googleApiClient,
                    "CgkI-_7R9foMEAIQAA",
                    getIntent().getIntExtra("Score", 0));

            startActivityForResult(Games.Leaderboards.getLeaderboardIntent(googleApiClient,
                    "CgkI-_7R9foMEAIQAA"), 1337);
        }
    }

    //Attempt to reconnect
    @Override
    public void onConnectionSuspended(int i) {
        googleApiClient.connect();
    }

    //Simplifying leave sound efects
    @Override
    protected void onUserLeaveHint() {
        super.onUserLeaveHint();
        noise.pauseMusic();
        noise.setSoundState(0);
        noise.buttonNoise();
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

    private void updateLeaderboard() {
        Log.d("fuck", String.valueOf(googleApiClient.isConnected()));
        if (isSignedIn()) {
            Log.d("fuck", "If Statement runs");
            Games.Leaderboards.submitScore(googleApiClient,
                    String.valueOf(R.string.leaderboard_id),
                    getIntent().getIntExtra("Score", 0));
        }
    }
}
