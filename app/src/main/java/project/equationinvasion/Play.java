package project.equationinvasion;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;
import java.text.DecimalFormat;

public class Play extends AppCompatActivity implements View.OnClickListener {

    private TextView equation;
    private TextView answer;
    Random rand = new Random();
    private MediaPlayer SE;

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
     * Level Changer
     */
    private Button levelChange;
    private TextView levelView;
    public int currentLevel = 1;

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
        addTime = (Button)findViewById(R.id.timeAdd);
        addTime.setOnClickListener(this);
        time = (TextView) findViewById(R.id.time);
        timer = new MyTimer(180000);
        timer.start();
        running = true;


        /**
         * Instantiating variables for equation generator
         * ~Chun
         */
        final Button newEquation = (Button) findViewById(R.id.button);
        newEquation.setOnClickListener(this);
        equation = (TextView) findViewById(R.id.leftEquation);
        answer = (TextView) findViewById(R.id.solution);

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

    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.button) {
            levelChecker();
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
            if (streak < 5) {
                streak++;
            } else {
                streak = 0;
            }
        } else if (view.getId() == addTime.getId()) {
            long carryOver = currentMilli;
            int secondsToRun;
            if (running) {
                timer.cancel();
                secondsToRun = 10;
                timer = new MyTimer(carryOver + (secondsToRun * 1000));
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
            super(duration, (long) 1000);
        }

        /**
         * Ticks every second and changes the text of the TextView to represent that change
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
         * My method to format the tme from milliseconds to a string that is used for the textview
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
    public void btnNoise()
    {
        SE = MediaPlayer.create(Play.this,R.raw.btn1sound);
        SE.start();
    }

    /**
     * Sets the equation level to be current level
     */
    public void levelChecker() {
        switch(currentLevel) {
            case 1:
                levelOne();
                break;
            case 2:
                levelTwo();
                break;
        }
    }

    public void levelOne() {
        final int maxNumber = 10;

        int a = rand.nextInt(maxNumber);
        int b = rand.nextInt(maxNumber);

        int type = rand.nextInt(4);

        switch(type) {
            case 0:
                add(a, b);
                break;
            case 1:
                subtract(a, b);
                break;
            case 2:
                divide(a, b);
                break;
            case 3:
                multiply(a, b);
                break;
            default:
                break;
        }

    }

    public void levelTwo() {
        final int maxNumber = 20;

        int a = rand.nextInt(maxNumber);
        int b = rand.nextInt(maxNumber);
        int c = rand.nextInt(maxNumber);

        int type = rand.nextInt(4);

        switch(type) {
            case 0:
                addAdd(a, b, c);
                break;
            case 1:
                addSubtract(a, b, c);
                break;
            case 2:
                addDivide(a, b, c);
                break;
            case 3:
                addMultiply(a, b, c);
                break;
            default:
                break;
        }
    }

    /**
     * Method for 2 variable addition
     * @param a
     * @param b
     */
    public void add(int a, int b) {
        int expected = a + b;
        askQuestion(a + " + " + b );
        answerQuestion("" + answerGen(expected));
    }

    public void addAdd(int a, int b, int c) {
        int expected = a + b + c;
        askQuestion(a + " + " + b + " + " + c);
        answerQuestion("" + answerGen(expected));
    }

    public void addSubtract(int a, int b, int c) {
        int expected = a + b - c;
        askQuestion(a + " + " + b + " - " + c);
        answerQuestion("" + answerGen(expected));
    }

    public void addMultiply(int a, int b, int c) {
        int expected = a + b * c;
        askQuestion(a + " + " + b + " * " + c);
        answerQuestion("" + answerGen(expected));
    }

    public void addDivide(int a, int b, int c) {
        if (c != 0) {
            int expected = a + b / c;
            askQuestion(a + " + " + b + " / " + c);
            answerQuestion("" + answerGen(expected));
        } else {
            levelTwo();
        }
    }

    /**
     * Method for 2 variable subtraction
     * @param a
     * @param b
     */
    public void subtract(int a, int b) {
        int expected = a - b;
        askQuestion(a + " - " + b );
        answerQuestion("" + answerGen(expected));
    }

    /**
     * Method for 2 variable division
     * @param a
     * @param b
     */
    public void divide(int a, int b) {
        if(b != 0) {
            if (a % b == 0) {
                int expected = a / b;
                askQuestion(a + " / " + b);
                answerQuestion("" + answerGen(expected));
            } else {
                int c = rand.nextInt(10) + 1;
                int d = rand.nextInt(10) + 1;
                divide(c, d);
            }
        } else{
            int c = rand.nextInt(10) + 1;
            int d = rand.nextInt(10) + 1;
            divide(c, d);
        }
    }

    /**
     * Method for 2 variable multiplication
     * @param a
     * @param b
     */
    public void multiply(int a, int b) {
        int expected = a * b;
        askQuestion(a + " * " + b);
        answerQuestion("" + answerGen(expected));
    }

    private void askQuestion(final String question) {
        equation.setText(question);
    }

    /**
     * Random Answer Generator.
     *
     * @param answer from equation
     * @return displayed
     */
    public int answerGen(int answer) {
        Random gen = new Random();

        int wrong;
        int displayed;
        int choice = gen.nextInt(2) + 1;  // True or False Decision

        if (answer >= -10 && answer <= 10) {
            if (choice == 2) {
                int variable = gen.nextInt(2) + 1;
                int a = gen.nextInt(2) + 1;
                if (a == 1) {
                    wrong = answer + variable;
                } else {
                    wrong = answer - variable;
                }
                displayed = wrong;
            } else {
                displayed = answer;
            }
        } else if (answer >= -20 && answer <= 20) {
            if (choice == 2) {
                int variable = gen.nextInt(3) + 1;
                int a = gen.nextInt(2) + 1;
                if (a == 1) {
                    wrong = answer + variable;
                } else {
                    wrong = answer - variable;
                }
                displayed = wrong;
            } else {
                displayed = answer;
            }
        } else if (answer >= -40 && answer <= 40) {
            if (choice == 2) {
                int variable = gen.nextInt(4) + 1;
                int a = gen.nextInt(2) + 1;
                if (a == 1) {
                    wrong = answer + variable;
                } else {
                    wrong = answer - variable;
                }
                displayed = wrong;
            } else {
                displayed = answer;
            }
        } else if (answer >= -60 && answer <= 60) {
            if (choice == 2) {
                int variable = gen.nextInt(5) + 1;
                int a = gen.nextInt(2) + 1;
                if (a == 1) {
                    wrong = answer + variable;
                } else {
                    wrong = answer - variable;
                }
                displayed = wrong;
            } else {
                displayed = answer;
            }
        } else {
            if (choice == 2) {
                int variable = gen.nextInt(6) + 1;
                int a = gen.nextInt(2) + 1;
                if (a == 1) {
                    wrong = answer + variable;
                } else {
                    wrong = answer - variable;
                }
                displayed = wrong;
            } else {
                displayed = answer;
            }
        }
        return displayed;
    }

    public void answerQuestion(final String answered) {
        answer.setText(answered);
    }

    public int levelChanger() {

        if(currentLevel < 2) {
            currentLevel++;
        } else {
            currentLevel = 1;
        }
        levelViewer("Level: " + currentLevel);
        return currentLevel;
    }

    /**
     * Increments the score.
     */
    public void scoreCounter() {

        score += scoreIncrement;
        scoreDisplay.setText("Score: " + score);
    }

    public void levelViewer(final String level) {
        levelView.setText(level);
    }
}
