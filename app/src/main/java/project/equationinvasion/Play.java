package project.equationinvasion;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.graphics.Typeface;

import java.util.Random;
import java.text.DecimalFormat;

public class Play extends AppCompatActivity implements View.OnClickListener {

    private static TextView equation;
    private static TextView answer;
    private ImageView feedback;
    private static final Random rand = new Random();
    private static EquationGenerator mathGen;

    /**
     * My declarations for the streak counter
     * -John
     */
    private ImageView first, second, third, fourth, fifth;
    private Button pipChange;
    private int streak;

    /**
     * Declarations for the countdown Timer
     */
    private final DecimalFormat fmt = new DecimalFormat("00");
    private boolean running = false;
    private long currentMilli = 0;
    private CountDownTimer timer;
    private TextView time;
    private Button addTime;

    /**
     * Declaration for validation text Timer.
     */
    private CountDownTimer invisibleTimer;
    /**
     * Level Changer
     */
    private Button levelChange;
    private TextView levelView;
    private static int currentLevel = 1;

    /**
     * Score tracking
     */
    private int score = 0;
    private final int scoreIncrement = 100;
    private Button scoreAdder;
    private TextView scoreDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        /**
         * Instantiating everything for streak counter
         * -John
         */
        first = (ImageView) findViewById(R.id.imageView);
        second = (ImageView) findViewById(R.id.imageView2);
        third = (ImageView) findViewById(R.id.imageView3);
        fourth = (ImageView) findViewById(R.id.imageView4);
        fifth = (ImageView) findViewById(R.id.imageView5);
        pipChange = (Button) findViewById(R.id.pipSwitch);
        pipChange.setOnClickListener(this);
        /**
         * Instantiating what I need for the timer
         */
        addTime = (Button) findViewById(R.id.timeAdd);
        addTime.setOnClickListener(this);
        time = (TextView) findViewById(R.id.time);
        timer = new MyTimer(180000);
        timer.start();
        running = true;

        /**
         * Instantiating the validation timer.
         */
        invisibleTimer = new CountDownTimer(2000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                feedback.setVisibility(View.INVISIBLE);
            }
        };

        /**
         * Level System
         */
        levelChange = (Button) findViewById(R.id.levelChange);
        levelChange.setOnClickListener(this);
        levelView = (TextView) findViewById(R.id.levelView);

        /**
         * Adding score with button
         */
        scoreAdder = (Button) findViewById(R.id.scoreAdder);
        scoreAdder.setOnClickListener(this);
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

        /**
         * Setting font style
         */
        //Font path
        String chalkboardFontPath = "fonts/Chalkboard.ttf";

        //text view label
        TextView txtEquation = (TextView) findViewById(R.id.leftEquation);
        TextView txtTimer = (TextView) findViewById(R.id.time);
        TextView txtScore = (TextView) findViewById(R.id.scoreDisplay);
        TextView txtAnswer = (TextView) findViewById(R.id.solution);
        TextView txtEquals = (TextView) findViewById(R.id.equals);

        //Load Font Face
        Typeface chalkboardFont = Typeface.createFromAsset(getAssets(),chalkboardFontPath);

        //Applying font
        txtEquation.setTypeface(chalkboardFont);
        txtTimer.setTypeface(chalkboardFont);
        txtScore.setTypeface(chalkboardFont);
        txtAnswer.setTypeface(chalkboardFont);
        txtEquals.setTypeface(chalkboardFont);

        //generating first equation
        mathGen.generate(currentLevel);
    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.trueBtn || view.getId() == R.id.falseBtn) {
            if (view.getId() == R.id.trueBtn) {
                truthChecker();
            } else if (view.getId() == R.id.falseBtn) {
                falseChecker();
            }
            mathGen.generate(currentLevel);
            btnNoise();
        } else if (view.getId() == pipChange.getId()) {
            /**
             * Switch statement utilizes fall-through to keep pips highlighted depending on the value
             * of streak. There's a break to prevent it falling into the default case.
             * Every pip is an ImageView with two images, an on and an off image.
             * I switch them depending on whether or not they should be active for the current streak.
             * Finally there's a simple if statement to keep streak looping from 0-5.
             *
             * -John
             */
            switch (streak) {
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
            streak++;
            streak %= 6;
        } else if (view.getId() == addTime.getId()) {
            int secondsToAdd;
            if (running) {
                timer.cancel();
                secondsToAdd = 10;
                timer = new MyTimer(currentMilli + (secondsToAdd * 1000));
                timer.start();
            }
        } else if (view.getId() == levelChange.getId()) {
            levelChanger();
        } else if (view.getId() == scoreAdder.getId()) {
            scoreCounter();
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
            time.setText("Ding fries are done!");
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

    //the method to be called for sound effects when a button is clicked.
    private void btnNoise() {
        MediaPlayer SE = MediaPlayer.create(Play.this, R.raw.btn1sound);
        SE.start();
    }

    //method to change level on method call
    private void levelChanger() {
        if (currentLevel < 6) {
            currentLevel++;
        } else {
            currentLevel = 1;
        }
        levelView.setText("Level: " + currentLevel);
    }

    /**
     * Increments the score.
     */
    private void scoreCounter() {
        int scoreIncrement = 100;
        score += scoreIncrement;
        scoreDisplay.setText("Score: " + score);
    }

    public static TextView getEquationTextView() {
        return equation;
    }

    public static TextView getAnswerTextView() {
        return answer;
    }

    public static int getCurrentLevel() {
        return currentLevel;
    }

    /**
     * validation for the truth button
     */
    private void truthChecker() {
        feedback.setVisibility(View.VISIBLE);
        invisibleTimer.cancel();
        if(mathGen.getAnswer() == mathGen.getEquation()) {
            feedback.setImageResource(R.drawable.checkmark);
            scoreCounter();
        }else {
            feedback.setImageResource(R.drawable.x);
        }
        invisibleTimer.start();
    }

    /**
     * validation for the false button
     */
    private void falseChecker() {
        feedback.setVisibility(View.VISIBLE);
        invisibleTimer.cancel();
        if(mathGen.getAnswer() != mathGen.getEquation()) {
            feedback.setImageResource(R.drawable.checkmark);
            scoreCounter();
        }else {
            feedback.setImageResource(R.drawable.x);
        }
        invisibleTimer.start();
    }
}

