package project.equationinvasion;

import android.content.Intent;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;


public class Credits extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credits);

        /**
         * Setting font style
         */
        //Font path
        String chalkboardFontPath = "fonts/Chalkboard.ttf";

        //text view label
        TextView txtCreditsHeader = (TextView) findViewById(R.id.creditsHeader);
        TextView txtDevList = (TextView) findViewById(R.id.devList);
        TextView txtContributorHeader = (TextView) findViewById(R.id.contributorHeader);
        TextView txtContributors = (TextView) findViewById(R.id.contributors);

        //Load Font Face
        Typeface chalkboardFont = Typeface.createFromAsset(getAssets(),chalkboardFontPath);

        //Applying font
        txtCreditsHeader.setTypeface(chalkboardFont);
        txtDevList.setTypeface(chalkboardFont);
        txtContributorHeader.setTypeface(chalkboardFont);
        txtContributors.setTypeface(chalkboardFont);

    }

    public void goToMain(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        btnNoise();
    }
    //the method to be called for sound effects when a button is clicked.
    private void btnNoise()
    {
        MediaPlayer SE = MediaPlayer.create(Credits.this, R.raw.btn1sound);
        SE.start();
    }

}
