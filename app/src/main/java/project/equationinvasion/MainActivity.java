package project.equationinvasion;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.EditText;
import android.widget.TextView;
import android.content.Intent;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    public final static String EXTRA_MESSAGE = "project.equationinvasion.MESSAGE";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

    @Override
    public void onClick(View v) {

    }
}
