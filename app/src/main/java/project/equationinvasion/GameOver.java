package project.equationinvasion;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class GameOver extends ActionBarActivity {

    Audio noise;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);
        noise = new Audio(GameOver.this);
    }


    //Called when player clicks the Play button
    public void goToPlay(View view) {
        Intent intent = new Intent(this, Play.class);
        startActivity(intent);
        noise.setSoundState(0);
        noise.buttonNoise();
    }

    //Called when player clicks the High Scores button
    public void goToHighScores(View view) {
        Intent intent = new Intent(this, HighScores.class);
        startActivity(intent);
        noise.setSoundState(0);
        noise.buttonNoise();
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
}
