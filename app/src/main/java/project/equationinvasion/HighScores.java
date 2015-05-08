package project.equationinvasion;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


public class HighScores extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_scores);
    }

    //Called when player clicks the Go To Main button
    public void goToMain(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        btnNoise();
    }
    //the method to be called for sound effects when a button is clicked.
    private void btnNoise()
    {
        MediaPlayer SE = MediaPlayer.create(HighScores.this, R.raw.btn1sound);
        SE.start();
    }
}
