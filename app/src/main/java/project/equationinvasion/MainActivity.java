package project.equationinvasion;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
