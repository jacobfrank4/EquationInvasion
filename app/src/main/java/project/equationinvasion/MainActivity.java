package project.equationinvasion;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.content.Intent;


public class MainActivity extends AppCompatActivity {


    public final static String EXTRA_MESSAGE = "project.equationinvasion.MESSAGE";



    /**
     * Declarations for audio functionality
     * -Matt
     */
    static private MediaPlayer BGM;
    static private boolean playing = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /**
         * Instantiating the background music.
         * -Matt
         */
        backgroundMusic();
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
        btnNoise();
    }

    //Called when player clicks the High Scores button
    public void goToHighScores(View view) {
        Intent intent = new Intent(this, HighScores.class);
        startActivity(intent);
        btnNoise();
    }

    //Called when player clicks the credits button
    public void goToCredits(View view) {
        Intent intent = new Intent(this, Credits.class);
        startActivity(intent);
        btnNoise();
    }

    //Method that plays button noise.
    private void btnNoise(){
        MediaPlayer SE = MediaPlayer.create(MainActivity.this, R.raw.btn1sound);
        SE.start();
    }

    /*
        This method crates the background music, and limits it to one
        copy so that it doesn't recreate itself
        when switching pages.
     */
    private void backgroundMusic(){
        if (!playing)
        {
            BGM = MediaPlayer.create(MainActivity.this, R.raw.bgm1);
            BGM.setLooping(true);
            BGM.start();
        }
        if (BGM.isPlaying())
        {
            playing = true;
        }

    }
}
