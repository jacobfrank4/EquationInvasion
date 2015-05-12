package project.equationinvasion;

import android.content.Intent;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    public final static String EXTRA_MESSAGE = "project.equationinvasion.MESSAGE";

    /**
     * Declarations for audio functionality
     * -Matt
     */
    protected Audio noise;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
            This code relates to instantiating our audio.
         */
        noise = new Audio(MainActivity.this);
        noise.menuBGM();

        /**
         * Setting font style
         */
        //Font path
        String chalkboardFontPath = "fonts/Chalkboard.ttf";

        //text view label
        TextView txtGameTitle = (TextView) findViewById(R.id.gameTitle);
        TextView txtGameInstructions = (TextView) findViewById(R.id.instructions);

        //Load Font Face
        Typeface chalkboardFont = Typeface.createFromAsset(getAssets(),chalkboardFontPath);

        //Applying font
        txtGameTitle.setTypeface(chalkboardFont);
        txtGameInstructions.setTypeface(chalkboardFont);
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
}

