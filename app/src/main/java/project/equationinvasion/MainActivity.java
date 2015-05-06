package project.equationinvasion;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Intent;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    public final static String EXTRA_MESSAGE = "project.equationinvasion.MESSAGE";

    /**
     * My declarations for the streak counter
     * -John
     */
    private ImageView first, second, third, fourth, fifth;
    private Button button;
    private int streak;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        /**
         * Instantiating everything for streak counter
         * -John
         */
        first = (ImageView)findViewById(R.id.imageView);
        second = (ImageView)findViewById(R.id.imageView2);
        third = (ImageView)findViewById(R.id.imageView3);
        fourth = (ImageView)findViewById(R.id.imageView4);
        fifth = (ImageView)findViewById(R.id.imageView5);
        button = (Button)findViewById(R.id.button);
        button.setOnClickListener(this);
    }

    /** Called when the user clicks the Send button */
    /**
    public void sendMessage(View view) {
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editText = (EditText) findViewById(R.id.edit_message);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }
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
    @Override
    public void onClick(View v) {
        if (v.getId() == button.getId()) {
            /**
             * Switch statement utilizes fall-through to keep pips highlighted depending on the value
             * of streak. There's a break to prevent it falling into the default case.
             * Every pip is an ImageView with two images, an on and an off image.
             * I switch them depending on whether or not they should be active for the current streak.
             * Finally there's a simple if statement to keep streak looping from 0-5.
             *
             * -John
             */
            switch(streak) {
                case 5:
                    fifth.setImageResource(R.drawable.streakpipon);
                case 4:
                    fourth.setImageResource(R.drawable.streakpipon);
                case 3:
                    third.setImageResource(R.drawable.streakpipon);
                case 2:
                    second.setImageResource(R.drawable.streakpipon);
                case 1:
                    first.setImageResource(R.drawable.streakpipon);
                    break;
                default:
                    first.setImageResource(R.drawable.streakpipoff);
                    second.setImageResource(R.drawable.streakpipoff);
                    third.setImageResource(R.drawable.streakpipoff);
                    fourth.setImageResource(R.drawable.streakpipoff);
                    fifth.setImageResource(R.drawable.streakpipoff);
            }
            if (streak < 5) {
                streak++;
            } else {
                streak = 0;
            }
        }
    }


}
