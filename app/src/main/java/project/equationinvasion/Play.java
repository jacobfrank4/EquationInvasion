package project.equationinvasion;

import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DecimalFormat;

public class Play extends AppCompatActivity implements View.OnClickListener {

    /**
     * Declaration for the textView that displays the equation.
     */
    private static TextView equation;

    /**
     * Declaration for the textView that displays the possible answer
     */
    private static TextView answer;

    /**
     * Declaration for the display that either shows a check mark or X after answering
     */
    private ImageView feedback;
    /**
     * Declaration for the display that lets the user know 10 seconds have been added to the time
     */
    private ImageView addTime;

    /**
     * Declaration for the generator in EquationGenerator class
     */
    private static EquationGenerator mathGen;

    /**
     * My declarations for the streak counter
     * -John
     */
    private ImageView first, second, third, fourth, fifth;
    private int streak;

    /**
     * Declarations for the countdown Timer
     */
    private final DecimalFormat fmt = new DecimalFormat("00");
    private boolean running = false;
    private long currentMilli = 0;
    private CountDownTimer timer;
    private TextView time;

    /**
     * Declaration for validation text Timer.
     */
    private CountDownTimer invisibleTimer;

    /**
     * Declaration for pip changer Timer
     * When it hits 5 streak, it will briefly show then disappear
     */
    private CountDownTimer pipTimer;

    /**
     * Timer for +10 visual.
     */
    private CountDownTimer addTimeTimer;

    /**
     * Level Changer
     */
    private TextView levelView;
    private static int currentLevel;

    /**
     * Score tracking
     */
    private int score = 0;
    private TextView scoreDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        /**
         * Instantiating everything for streak counter
         * -John
         */
        streak = 0;
        first = (ImageView) findViewById(R.id.imageView);
        second = (ImageView) findViewById(R.id.imageView2);
        third = (ImageView) findViewById(R.id.imageView3);
        fourth = (ImageView) findViewById(R.id.imageView4);
        fifth = (ImageView) findViewById(R.id.imageView5);
        pipChanger();
        /**
         * Timer to display 5 pips for a second before resetting it
         */
        pipTimer = new CountDownTimer(1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {}

            @Override
            public void onFinish() {
                first.setImageResource(R.drawable.streakpipoff);
                second.setImageResource(R.drawable.streakpipoff);
                third.setImageResource(R.drawable.streakpipoff);
                fourth.setImageResource(R.drawable.streakpipoff);
                fifth.setImageResource(R.drawable.streakpipoff);
                pipTimer.cancel();
            }
        };
        /**
         * Instantiating what I need for the timer
         */
        time = (TextView) findViewById(R.id.time);
        timer = new MyTimer(180000);
        timer.start();
        running = true;

        /**
         * Instantiating the validation timer.
         */
        invisibleTimer = new CountDownTimer(250, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                feedback.setVisibility(View.INVISIBLE);
            }
        };

        addTimeTimer = new CountDownTimer(1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                addTime.setVisibility(View.INVISIBLE);
            }
        };

        /**
         * Level System
         */
        levelView = (TextView) findViewById(R.id.levelView);
        currentLevel = 1;

        /**
         * Adding score
         */
        scoreDisplay = (TextView) findViewById(R.id.scoreDisplay);

        /**
         * True and False buttons
         */
        final Button TRUE = (Button) findViewById(R.id.trueBtn);
        final Button FALSE = (Button) findViewById(R.id.falseBtn);
        TRUE.setOnClickListener(this);
        FALSE.setOnClickListener(this);
        equation = (TextView) findViewById(R.id.leftEquation);
        answer = (TextView) findViewById(R.id.solution);
        mathGen = new EquationGenerator();
        feedback = (ImageView) findViewById(R.id.feedback);
        addTime = (ImageView) findViewById(R.id.addTime);

        /**
         * Setting font style
         */
        //Font path
        String chalkboardFontPath = "fonts/Chalkboard.ttf";

        //text view label
        TextView txtEquals = (TextView) findViewById(R.id.equals);

        //Load Font Face
        Typeface chalkboardFont = Typeface.createFromAsset(getAssets(),chalkboardFontPath);

        //Applying font
        equation.setTypeface(chalkboardFont);
        time.setTypeface(chalkboardFont);
        scoreDisplay.setTypeface(chalkboardFont);
        answer.setTypeface(chalkboardFont);
        txtEquals.setTypeface(chalkboardFont);

        //generating first equation
        mathGen.generate(currentLevel);
    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.trueBtn || view.getId() == R.id.falseBtn) {
            if (view.getId() == R.id.trueBtn) {
                truthChecker();
            } else {
                falseChecker();
            }
            mathGen.generate(currentLevel);
            btnNoise();
        }
    }


    private class MyTimer extends CountDownTimer {
        public MyTimer(long duration) {
            super(duration, 1000L);
        }

        /**
         * Ticks every second and changes the text of the TextView to represent that change
         *
         * @param millisUntilFinished The amount of time left on the timer. THIS IS THE ONLY WAY IT CAN BE VIEWED OR ACCESSED.
         */
        @Override
        public void onTick(long millisUntilFinished) {
            time.setText(formatTime(millisUntilFinished));
            currentMilli = millisUntilFinished;
        }

        /**
         * Method called when the time reaches zero
         */
        @Override
        public void onFinish() {
            time.setText("Game Over");
        }

        /**
         * My method to format the time from milliseconds to a string that is used for the textview
         */
        private String formatTime(long currentTime) {
            long minute;
            long second;

            second = (currentTime / 1000) % 60;
            minute = currentTime / 60000;
            return minute + ":" + fmt.format(second);
        }
    }

    /**
     * Moved on button click of add time to a method to call
     */
    private void addTime() {
        int secondsToAdd;
        if (running) {
            timer.cancel();
            secondsToAdd = 10;
            timer = new MyTimer(currentMilli + (secondsToAdd * 1000));
            timer.start();
        }
    }

    /**
     * Switch statement utilizes fall-through to keep pips highlighted depending on the value
     * of streak. There's a break to prevent it falling into the default case.
     * Every pip is an ImageView with two images, an on and an off image.
     * I switch them depending on whether or not they should be active for the current streak.
     * Finally there's a simple if statement to keep streak looping from 0-5.
     *
     * -John
     *
     * Moved down to its own method so I can start integrating it into the app.
     *
     * -Chun
     */

    private void pipChanger() {
        switch (streak) {
            case 5:
                addTime.setVisibility(View.VISIBLE);
                addTimeTimer.cancel();
                fifth.setImageResource(R.drawable.streakpipon);
                pipTimer.start();
                addTime.setImageResource(R.drawable.plusten);
                addTime();
                levelChanger();
                streak = 0;
                addTimeTimer.start();
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
        streak++;
        streak %= 6;
    }

    //the method to be called for sound effects when a button is clicked.
    private void btnNoise() {
        MediaPlayer SE = MediaPlayer.create(Play.this, R.raw.btn1sound);
        SE.start();
    }

    /**
     *
     method to change level on method call
     */
    private void levelChanger() {
            if (currentLevel < 6) {
                currentLevel++;
            }
            levelView.setText("Level: " + currentLevel);

    }

    /**
     * Increments the score.
     */
    private void scoreCounter() {
        int scoreIncrement = 100;
        score += (scoreIncrement * currentLevel);
        scoreDisplay.setText("Score: " + score);
    }

    public static TextView getEquationTextView() {
        return equation;
    }

    public static TextView getAnswerTextView() {
        return answer;
    }

    /**
     * Getter for the current level so as to generate correct equation
     * @return currentLevel
     *          The current level of the game
     *
     */
    public static int getCurrentLevel() {
        return currentLevel;
    }

    /**
     * validation for the truth button
     *      Sets the visibility of the picture to visible
     *      deletes any old timer that removes the picture of checkmark/X
     *      checks for correctness
     *      if correct
     *          display checkmark
     *          increase score
     *          change changes
     *      if incorrect
     *          displays X
     *          resets streak
     */
    private void truthChecker() {
        feedback.setVisibility(View.VISIBLE);
        invisibleTimer.cancel();
        if(mathGen.getAnswer() == mathGen.getEquation()) {
            feedback.setImageResource(R.drawable.checkmark);
            scoreCounter();
            pipChanger();
        }else {
            feedback.setImageResource(R.drawable.x);
            streak = 0;
            pipChanger();
        }
        invisibleTimer.start();
    }

    /**
     * validation for the false button
     *      Sets the visibility of the picture to visible
     *      deletes any old timer that removes the picture of checkmark/X
     *      checks for correctness
     *      if correct
     *          display checkmark
     *          increase score
     *          change changes
     *      if incorrect
     *          displays X
     *          resets streak
     *
     */
    private void falseChecker() {
        feedback.setVisibility(View.VISIBLE);
        invisibleTimer.cancel();
        if(mathGen.getAnswer() != mathGen.getEquation()) {
            feedback.setImageResource(R.drawable.checkmark);
            scoreCounter();
            pipChanger();
        }else {
            feedback.setImageResource(R.drawable.x);
            streak = 0;
            pipChanger();
        }
        invisibleTimer.start();
    }
}

