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
            case 3:
                levelThree();
                break;
            case 4:
                levelFour();
                break;
            case 5:
                levelFive();
                break;
            case 6:
                levelSix();
                break;
            default:
                break;
        }
    }

    //comleted
    public void levelOne() {
        final int maxNumber = 10;

        int a = rand.nextInt(maxNumber);
        int b = rand.nextInt(maxNumber);

        int type = rand.nextInt(2);

        switch(type) {
            case 0:
                add(a, b);
                break;
            case 1:
                subtract(a, b);
                break;
            default:
                break;
        }

    }

    //completed
    public void levelTwo() {
        final int maxNumber = 10;

        int a = rand.nextInt(maxNumber);
        int b = rand.nextInt(maxNumber);

        int type = rand.nextInt(2);

        switch(type) {
            case 0:
                multiply(a, b);
                break;
            case 1:
                divide(a, b);
                break;
            default:
                break;
        }

    }

    //completed
    public void levelThree() {
        final int maxNumber = 100;

        int a = rand.nextInt(maxNumber);
        int b = rand.nextInt(maxNumber);

        int type = rand.nextInt(2);

        switch(type) {
            case 0:
                add(a, b);
                break;
            case 1:
                subtract(a, b);
                break;
            default:
                break;
        }

    }

    //completed
    public void levelFour() {
        final int maxNumber = 100;

        int a = rand.nextInt(maxNumber);
        int b = rand.nextInt(maxNumber);
        int c = rand.nextInt(maxNumber);

        int type = rand.nextInt(6);

        switch(type) {
            case 0:
                addAdd(a, b, c);
                break;
            case 1:
                addSubtract(a, b, c);
                break;
            case 2:
                subtractAdd(a, b, c);
                break;
            case 3:
                subtractSubtract(a, b, c);
                break;
            case 4:
                add(a, b);
                break;
            case 5:
                subtract(a, b);
            default:
                break;
        }

    }

    //working on right now
    public void levelFive() {
        final int maxNumber = 100;
        final int maxMultiDivi = 10;

        int a = rand.nextInt(maxNumber);
        int b = rand.nextInt(maxNumber);
        int c = rand.nextInt(maxNumber);

        int d = rand.nextInt(maxMultiDivi);
        int e = rand.nextInt(maxMultiDivi);
        int f = rand.nextInt(maxMultiDivi);

        int type = rand.nextInt(16);

        switch(type) {
            case 0:
                addAdd(a, b, c);
                break;
            case 1:
                addSubtract(a, b, c);
                break;
            case 2:
                addDivide(a, d, e);
                break;
            case 3:
                addMultiply(a, d, e);
                break;
            case 4:
                subtractAdd(a, b, c);
                break;
            case 5:
                subtractSubtract(a, b, c);
                break;
            case 6:
                subtractDivide(a, d, e);
                break;
            case 7:
                subtractMultiply(a, d, e);
                break;
            case 8:
                divideAdd(d, e, a);
                break;
            case 9:
                divideSubtract(d, e, a);
                break;
            case 10:
                divideDivide(d, e, f);
                break;
            case 11:
                divideMultiply(d, e, f);
                break;
            case 12:
                multiplyAdd(d, e, a);
                break;
            case 13:
                multiplySubtract(d, e, a);
                break;
            case 14:
                multiplyDivide(d, e, f);
                break;
            case 15:
                multiplyMultiply(d, e, f);
            default:
                break;
        }
    }

    public void levelSix() {
        final int maxNumber = 100;
        final int maxMultiDivi = 10;

        int a = rand.nextInt(maxNumber);
        int b = rand.nextInt(maxNumber);
        int c = rand.nextInt(maxNumber);
        int d = rand.nextInt(maxNumber);

        int e = rand.nextInt(maxMultiDivi);
        int f = rand.nextInt(maxMultiDivi);
        int g = rand.nextInt(maxMultiDivi);
        int h = rand.nextInt(maxMultiDivi);

        int type = rand.nextInt(64);

        switch(type) {
            case 0:
                addAddAdd(a, b, c, d);
                break;
            case 1:
                addAddSubtract(a, b, c, d);
                break;
            case 3:
                addAddDivide(a, b, e, f);
                break;
            case 4:
                addAddMultiply(a, b, e, f);
                break;
            case 5:
                addSubtractAdd(a, b, c, d);
                break;
            case 6:
                addSubtractSubtract(a, b, c, d);
                break;
            case 7:
                addSubtractDivide(a, b, e, f);
                break;
            case 8:
                addDivideAdd(a, e, f, b);
                break;
            case 9:
                addDivideSubtract(a, e, f, b);
                break;
            case 10:
                addDivideDivide(a, e, f, g);
                break;
            case 11:
                addDivideMultiply(a, e, f, g);
                break;
            case 12:
                addMultiplyAdd(a, e, f, b);
                break;
            case 13:
                addMultiplySubtract(a, e, f, b);
                break;
            case 14:
                addMultiplyDivide(a, e, f, g);
                break;
            case 15:
                addMultiplyMultiply(a, e, f, g);
                break;
            case 16:
                subtractAddAdd(a, b, c, d);
                break;
            case 17:
                subtractAddSubtract(a, b, c, d);
                break;
            case 18:
                subtractAddDivide(a, b , e, f);
                break;
            case 19:
                subtractAddMultiply(a, b, e, f);
                break;
            case 20:
                subtractSubtractAdd(a, b, c, d);
                break;
            case 21:
                subtractSubtractSubtract(a, b, c, d);
                break;
            case 22:
                subtractSubtractDivide(a, b, e, f);
                break;
            case 23:
                subtractSubtractMultiply(a, b, e, f);
                break;
            case 24:
                subtractDivideAdd(a, e, f ,b);
                break;
            case 25:
                subtractDivideSubtract(a, e, f, b);
                break;
            case 26:
                subtractDivideDivide(a, e, f, g);
                break;
            case 27:
                subtractDivideMultiply(a, e, f, g);
                break;
            case 28:
                subtractMultiplyAdd(a, e, f, b);
                break;
            case 29:
                subtractMultiplySubtract(a, e, f, b);
                break;
            case 30:
                subtractMultiplyDivide(a, e, f, g);
                break;
            case 31:
                subtractMultiplyMultiply(a, e, f, g);
                break;
            case 32:
                divideAddAdd(e, f, a, b);
                break;
            case 33:
                divideAddSubtract(e, f, a, b);
                break;
            case 34:
                divideAddDivide(e, f, g, h);
                break;
            case 35:
                divideAddMultiply(e, f, g, h);
                break;
            case 36:
                divideSubtractAdd(e, f, a, b);
                break;
            case 37:
                divideSubtractSubtract(e, f, a, b);
                break;
            case 38:
                divideSubtractDivide(e, f, g, h);
                break;
            case 39:
                divideSubtractMultiply(e, f, g, h);
                break;
            case 40:
                divideDivideAdd(e, f, g, a);
                break;
            case 41:
                divideDivideSubtract(e, f, g, a);
                break;
            case 42:
                divideDivideDivide(e, f, g, h);
                break;
            case 43:
                divideDivideMultiply(e, f, g, h);
                break;
            case 44:
                divideMultiplyAdd(e, f, g, a);
                break;
            case 45:
                divideMultiplySubtract(e, f, g, a);
                break;
            case 46:
                divideMultiplyDivide(e, f, g, h);
                break;
            case 47:
                divideMultiplyMultiply(e, f, g, h);
                break;
            case 48:
                multiplyAddAdd(e, f, a, b);
                break;
            case 49:
                multiplyAddSubtract(e, f, a, b);
                break;
            case 50:
                multiplyAddDivide(e, f, g, h);
                break;
            case 51:
                multiplyAddMultiply(e, f, g, h);
                break;
            case 52:
                multiplySubtractAdd(e, f, a, b);
                break;
            case 53:
                multiplySubtractSubtract(e, f, a, b);
                break;
            case 54:
                multiplySubtractDivide(e, f, g, h);
                break;
            case 55:
                multiplySubtractMultiply(e, f, g, h);
                break;
            case 56:
                multiplyDivideAdd(e, f, g, a);
                break;
            case 57:
                multiplyDivideSubtract(e, f, g, a);
                break;
            case 58:
                multiplyDivideDivide(e, f, g, h);
                break;
            case 59:
                multiplyDivideMultiply(e, f, g, h);
                break;
            case 60:
                multiplyMultiplyAdd(e, f, g, a);
                break;
            case 61:
                multiplyMultiplySubtract(e, f, g, a);
                break;
            case 62:
                multiplyMultiplyDivide(e, f, g, h);
                break;
            case 63:
                multiplyMultiplyMultiply(e, f, g, h);
                break;
            default:
                break;
        }

    }

    /**
     * Methods for addition as first operation
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
        if(c != 0) {
            if (b % c == 0) {
                int expected = a + b / c;
                askQuestion(a + " + " + b + " / " + c);
                answerQuestion("" + answerGen(expected));
            } else {
                int d = rand.nextInt(100);
                int e = rand.nextInt(10);
                int f = rand.nextInt(10);
                addDivide(d, e, f);

            }
        } else {
            int d = rand.nextInt(100);
            int e = rand.nextInt(10);
            int f = rand.nextInt(10);
            addDivide(d, e, f);
        }
    }

    public void addAddAdd(int a, int b, int c, int d) {
        int expected = a + b + c + d;
        askQuestion(a + " + " + b + " + " + c + " + " + d);
        answerQuestion("" + answerGen(expected));
    }

    public void addAddSubtract(int a, int b, int c, int d) {
        int expected = a + b + c - d;
        askQuestion(a + " + " + b + " + " + c + " - " + d);
        answerQuestion("" + answerGen(expected));
    }

    public void addAddDivide(int a, int b, int c, int d) {
        if(c != 0) {
            if (c % d == 0) {
                int expected = a + b + c / d;
                askQuestion(a + " + " + b + " + " + c + " / " + d);
                answerQuestion("" + answerGen(expected));
            } else {
                int e = rand.nextInt(100);
                int f = rand.nextInt(100);
                int g = rand.nextInt(10);
                int h = rand.nextInt(9) + 1;
                addAddDivide(e, f, g, h);
            }
        } else {
            int e = rand.nextInt(100);
            int f = rand.nextInt(100);
            int g = rand.nextInt(10);
            int h = rand.nextInt(9) + 1;
            addAddDivide(e, f, g, h);
        }
    }

    public void addAddMultiply(int a, int b, int c, int d) {
        int expected = a + b + c * d;
        askQuestion(a + " + " + b + " + " + c + " * " + d);
        answerQuestion("" + answerGen(expected));
    }

    public void addSubtractAdd(int a, int b, int c, int d) {
        int expected = a + b - c + d;
        askQuestion(a + " + " + b + " - " + c + " + " + d);
        answerQuestion("" + answerGen(expected));
    }

    public void addSubtractSubtract(int a, int b, int c, int d) {
        int expected = a + b - c - d;
        askQuestion(a + " + " + b + " - " + c + " - " + d);
        answerQuestion("" + answerGen(expected));
    }

    public void addSubtractDivide(int a, int b, int c, int d) {
        if(d != 0) {
            if(c % d == 0) {
                int expected = a + b - c / d;
                askQuestion(a + " + " + b + " - " + c + " / " + d);
                answerQuestion("" + answerGen(expected));
            } else {
                int e = rand.nextInt(100);
                int f = rand.nextInt(100);
                int g = rand.nextInt(10);
                int h = rand.nextInt(9) + 1;
                addAddDivide(e, f, g, h);
            }
        } else {
            int e = rand.nextInt(100);
            int f = rand.nextInt(100);
            int g = rand.nextInt(10);
            int h = rand.nextInt(9) + 1;
            addAddDivide(e, f, g, h);
        }
    }

    public void addSubtractMultiply(int a, int b, int c, int d) {
        int expected = a + b - c * d;
        askQuestion(a + " + " + b + " - " + c + " * " + d);
        answerQuestion("" + answerGen(expected));
    }

    public void addDivideAdd(int a, int b, int c, int d) {
        if(c != 0) {
            if(b % c == 0) {
                int expected = a + b / c + d;
                askQuestion(a + " + " + b + " / " + c + " + " + d);
                answerQuestion("" + answerGen(expected));
            } else {
                int e = rand.nextInt(100);
                int f = rand.nextInt(10);
                int g = rand.nextInt(9) + 1;
                int h = rand.nextInt(100);
                addDivideAdd(e, f, g, h);
            }
        } else {
            int e = rand.nextInt(100);
            int f = rand.nextInt(10);
            int g = rand.nextInt(9) + 1;
            int h = rand.nextInt(100);
            addDivideAdd(e, f, g, h);
        }
    }

    public void addDivideSubtract(int a, int b, int c, int d) {
        if(c != 0) {
            if(b % c == 0) {
                int expected = a + b / c - d;
                askQuestion(a + " + " + b + " / " + c + " - " + d);
                answerQuestion("" + answerGen(expected));
            } else {
                int e = rand.nextInt(100);
                int f = rand.nextInt(10);
                int g = rand.nextInt(9) + 1;
                int h = rand.nextInt(100);
                addDivideSubtract(e, f, g, h);
            }
        } else {
            int e = rand.nextInt(100);
            int f = rand.nextInt(10);
            int g = rand.nextInt(9) + 1;
            int h = rand.nextInt(100);
            addDivideSubtract(e, f, g, h);
        }
    }

    public void addDivideDivide(int a, int b, int c, int d) {
        if(c != 0 && d != 0) {
            if(b % c == 0 && (b / c) % d == 0) {
                int expected = a + b / c / d;
                askQuestion(a + " + " + b + " / " + c + " / " + d);
                answerQuestion("" + answerGen(expected));
            } else {
                int e = rand.nextInt(100);
                int f = rand.nextInt(10);
                int g = rand.nextInt(9) + 1;
                int h = rand.nextInt(9) + 1;
                addDivideAdd(e, f, g, h);
            }
        } else {
            int e = rand.nextInt(100);
            int f = rand.nextInt(10);
            int g = rand.nextInt(9) + 1;
            int h = rand.nextInt(9) + 1;
            addDivideAdd(e, f, g, h);
        }
    }

    public void addDivideMultiply(int a, int b, int c, int d) {
        if(c != 0) {
            if(b % c == 0) {
                int expected = a + b / c * d;
                askQuestion(a + " + " + b + " / " + c + " * " + d);
                answerQuestion("" + answerGen(expected));
            } else {
                int e = rand.nextInt(100);
                int f = rand.nextInt(10);
                int g = rand.nextInt(9) + 1;
                int h = rand.nextInt(10);
                addDivideAdd(e, f, g, h);
            }
        } else {
            int e = rand.nextInt(100);
            int f = rand.nextInt(10);
            int g = rand.nextInt(9) + 1;
            int h = rand.nextInt(10);
            addDivideAdd(e, f, g, h);
        }
    }

    public void addMultiplyAdd(int a, int b, int c, int d) {
        int expected = a + b * c + d;
        askQuestion(a + " + " + b + " * " + c + " + " + d);
        answerQuestion("" + answerGen(expected));
    }

    public void addMultiplySubtract(int a, int b, int c, int d) {
        int expected = a + b * c - d;
        askQuestion(a + " + " + b + " * " + c + " - " + d);
        answerQuestion("" + answerGen(expected));
    }

    public void addMultiplyDivide(int a, int b, int c, int d) {
        if(d != 0) {
            if ((b * c) % d == 0) {
                int expected = a + b * c / d;
                askQuestion(a + " + " + b + " * " + c + " / " + d);
                answerQuestion("" + answerGen(expected));
            } else {
                int e = rand.nextInt(100);
                int f = rand.nextInt(10);
                int g = rand.nextInt(10);
                int h = rand.nextInt(9) + 1;
                addAddDivide(e, f, g, h);
            }
        } else {
            int e = rand.nextInt(100);
            int f = rand.nextInt(10);
            int g = rand.nextInt(10);
            int h = rand.nextInt(9) + 1;
            addAddDivide(e, f, g, h);
        }
    }

    public void addMultiplyMultiply(int a, int b, int c, int d) {
        int expected = a + b * c * d;
        askQuestion(a + " + " + b + " * " + c + " * " + d);
        answerQuestion("" + answerGen(expected));
    }


    /**
     * Methods for subtraction as first operation
     * @param a
     * @param b
     */
    public void subtract(int a, int b) {
        int expected = a - b;
        askQuestion(a + " - " + b );
        answerQuestion("" + answerGen(expected));
    }

    public void subtractAdd(int a, int b, int c) {
        int expected = a - b + c;
        askQuestion(a + " - " + b + " + " + c);
        answerQuestion("" + answerGen(expected));
    }

    public void subtractSubtract(int a, int b, int c) {
        int expected = a - b - c;
        askQuestion(a + " - " + b + " - " + c);
        answerQuestion("" + answerGen(expected));
    }

    public void subtractMultiply(int a, int b, int c) {
        int expected = a - b * c;
        askQuestion(a + " - " + b + " * " + c);
        answerQuestion("" + answerGen(expected));
    }

    public void subtractDivide(int a, int b, int c) {
        if(c != 0) {
            if (b % c == 0) {
                int expected = a - b / c;
                askQuestion(a + " - " + b + " / " + c);
                answerQuestion("" + answerGen(expected));
            } else {
                int d = rand.nextInt(100);
                int e = rand.nextInt(10);
                int f = rand.nextInt(10);
                addDivide(d, e, f);

            }
        } else {
            int d = rand.nextInt(100);
            int e = rand.nextInt(10);
            int f = rand.nextInt(10);
            addDivide(d, e, f);
        }
    }

    public void subtractAddAdd(int a, int b, int c, int d) {
        int expected = a - b + c + d;
        askQuestion(a + " - " + b + " + " + c + " + " + d);
        answerQuestion("" + answerGen(expected));
    }

    public void subtractAddSubtract(int a, int b, int c, int d) {
        int expected = a - b + c - d;
        askQuestion(a + " - " + b + " + " + c + " - " + d);
        answerQuestion("" + answerGen(expected));
    }

    public void subtractAddDivide(int a, int b, int c, int d) {
        if(c != 0) {
            if (c % d == 0) {
                int expected = a - b + c / d;
                askQuestion(a + " - " + b + " + " + c + " / " + d);
                answerQuestion("" + answerGen(expected));
            } else {
                int e = rand.nextInt(100);
                int f = rand.nextInt(100);
                int g = rand.nextInt(9) + 1;
                int h = rand.nextInt(9) + 1;
                subtractAddDivide(e, f, g, h);
            }
        } else {
            int e = rand.nextInt(100);
            int f = rand.nextInt(100);
            int g = rand.nextInt(9) + 1;
            int h = rand.nextInt(9) + 1;
            subtractAddDivide(e, f, g, h);
        }
    }

    public void subtractAddMultiply(int a, int b, int c, int d) {
        int expected = a - b + c * d;
        askQuestion(a + " - " + b + " + " + c + " * " + d);
        answerQuestion("" + answerGen(expected));
    }

    public void subtractSubtractAdd(int a, int b, int c, int d) {
        int expected = a - b - c + d;
        askQuestion(a + " - " + b + " - " + c + " + " + d);
        answerQuestion("" + answerGen(expected));
    }

    public void subtractSubtractSubtract(int a, int b, int c, int d) {
        int expected = a - b - c - d;
        askQuestion(a + " - " + b + " - " + c + " - " + d);
        answerQuestion("" + answerGen(expected));
    }

    public void subtractSubtractDivide(int a, int b, int c, int d) {
        if(d != 0) {
            if(c % d == 0) {
                int expected = a - b - c / d;
                askQuestion(a + " - " + b + " - " + c + " / " + d);
                answerQuestion("" + answerGen(expected));
            } else {
                int e = rand.nextInt(100);
                int f = rand.nextInt(100);
                int g = rand.nextInt(9) + 1;
                int h = rand.nextInt(9) + 1;
                subtractAddDivide(e, f, g, h);
            }
        } else {
            int e = rand.nextInt(100);
            int f = rand.nextInt(100);
            int g = rand.nextInt(9) + 1;
            int h = rand.nextInt(9) + 1;
            subtractAddDivide(e, f, g, h);
        }
    }

    public void subtractSubtractMultiply(int a, int b, int c, int d) {
        int expected = a - b - c * d;
        askQuestion(a + " - " + b + " - " + c + " * " + d);
        answerQuestion("" + answerGen(expected));
    }

    public void subtractDivideAdd(int a, int b, int c, int d) {
        if(c != 0) {
            if(b % c == 0) {
                int expected = a - b / c + d;
                askQuestion(a + " - " + b + " / " + c + " + " + d);
                answerQuestion("" + answerGen(expected));
            } else {
                int e = rand.nextInt(100);
                int f = rand.nextInt(10);
                int g = rand.nextInt(9) + 1;
                int h = rand.nextInt(100);
                subtractDivideAdd(e, f, g, h);
            }
        } else {
            int e = rand.nextInt(100);
            int f = rand.nextInt(10);
            int g = rand.nextInt(9) + 1;
            int h = rand.nextInt(100);
            subtractDivideAdd(e, f, g, h);
        }
    }

    public void subtractDivideSubtract(int a, int b, int c, int d) {
        if(c != 0) {
            if(b % c == 0) {
                int expected = a - b / c - d;
                askQuestion(a + " - " + b + " / " + c + " - " + d);
                answerQuestion("" + answerGen(expected));
            } else {
                int e = rand.nextInt(100);
                int f = rand.nextInt(10);
                int g = rand.nextInt(9) + 1;
                int h = rand.nextInt(100);
                subtractDivideSubtract(e, f, g, h);
            }
        } else {
            int e = rand.nextInt(100);
            int f = rand.nextInt(10);
            int g = rand.nextInt(9) + 1;
            int h = rand.nextInt(100);
            subtractDivideSubtract(e, f, g, h);
        }
    }

    public void subtractDivideDivide(int a, int b, int c, int d) {
        if(c != 0 && d != 0) {
            if(b % c == 0 && (b / c) % d == 0) {
                int expected = a - b / c / d;
                askQuestion(a + " - " + b + " / " + c + " / " + d);
                answerQuestion("" + answerGen(expected));
            } else {
                int e = rand.nextInt(100);
                int f = rand.nextInt(10);
                int g = rand.nextInt(9) + 1;
                int h = rand.nextInt(9) + 1;
                subtractDivideAdd(e, f, g, h);
            }
        } else {
            int e = rand.nextInt(100);
            int f = rand.nextInt(10);
            int g = rand.nextInt(9) + 1;
            int h = rand.nextInt(9) + 1;
            subtractDivideAdd(e, f, g, h);
        }
    }

    public void subtractDivideMultiply(int a, int b, int c, int d) {
        if(c != 0) {
            if(b % c == 0) {
                int expected = a - b / c * d;
                askQuestion(a + " - " + b + " / " + c + " * " + d);
                answerQuestion("" + answerGen(expected));
            } else {
                int e = rand.nextInt(100);
                int f = rand.nextInt(10);
                int g = rand.nextInt(9) + 1;
                int h = rand.nextInt(10);
                subtractDivideAdd(e, f, g, h);
            }
        } else {
            int e = rand.nextInt(100);
            int f = rand.nextInt(10);
            int g = rand.nextInt(9) + 1;
            int h = rand.nextInt(10);
            subtractDivideAdd(e, f, g, h);
        }
    }

    public void subtractMultiplyAdd(int a, int b, int c, int d) {
        int expected = a - b * c + d;
        askQuestion(a + " - " + b + " * " + c + " + " + d);
        answerQuestion("" + answerGen(expected));
    }

    public void subtractMultiplySubtract(int a, int b, int c, int d) {
        int expected = a - b * c - d;
        askQuestion(a + " - " + b + " * " + c + " - " + d);
        answerQuestion("" + answerGen(expected));
    }

    public void subtractMultiplyDivide(int a, int b, int c, int d) {
        if(d != 0) {
            if ((b * c) % d == 0) {
                int expected = a - b * c / d;
                askQuestion(a + " - " + b + " * " + c + " / " + d);
                answerQuestion("" + answerGen(expected));
            } else {
                int e = rand.nextInt(100);
                int f = rand.nextInt(10);
                int g = rand.nextInt(10);
                int h = rand.nextInt(9) + 1;
                subtractAddDivide(e, f, g, h);
            }
        } else {
            int e = rand.nextInt(100);
            int f = rand.nextInt(10);
            int g = rand.nextInt(10);
            int h = rand.nextInt(9) + 1;
            subtractAddDivide(e, f, g, h);
        }
    }

    public void subtractMultiplyMultiply(int a, int b, int c, int d) {
        int expected = a - b * c * d;
        askQuestion(a + " - " + b + " * " + c + " * " + d);
        answerQuestion("" + answerGen(expected));
    }

    /**
     * Methods for division as first operation
     * @param a
     * @param b
     */
    public void divide(int a, int b) {
        if(b != 0) {
            if(a % b == 0) {
                int expected = a / b;
                askQuestion(a + " / " + b);
                answerQuestion("" + answerGen(expected));
            } else {
                int c = rand.nextInt(10);
                int d = rand.nextInt(10);
                divide(c, d);
            }
        } else{
            int c = rand.nextInt(10);
            int d = rand.nextInt(10);
            divide(c, d);
        }
    }

    public void divideAdd(int a, int b, int c) {
        if(b != 0) {
            if(a % b == 0) {
                int expected = a / b + c;
                askQuestion(a + " / " + b + " + " + c);
                answerQuestion("" + answerGen(expected));
            } else {
                int d = rand.nextInt(10);
                int e = rand.nextInt(10);
                int f = rand.nextInt(100);
                divideAdd(d, e, f);
            }
        } else {
            int d = rand.nextInt(9) + 1;
            int e = rand.nextInt(9) + 1;
            int f = rand.nextInt(9) + 1;
            divideDivide(d, e, f);
        }

    }

    public void divideSubtract(int a, int b, int c) {
        if(b != 0) {
            if(a % b == 0) {
                int expected = a / b - c;
                askQuestion(a + " / " + b + " - " + c);
                answerQuestion("" + answerGen(expected));
            } else {
                int d = rand.nextInt(10);
                int e = rand.nextInt(10);
                int f = rand.nextInt(100);
                divideSubtract(d, e, f);
            }
        } else {
            int d = rand.nextInt(9) + 1;
            int e = rand.nextInt(9) + 1;
            int f = rand.nextInt(9) + 1;
            divideDivide(d, e, f);
        }
    }

    public void divideMultiply(int a, int b, int c) {
        if(b != 0) {
            if(a % b == 0) {
                int expected = a / b * c;
                askQuestion(a + " / " + b + " * " + c);
                answerQuestion("" + answerGen(expected));
            } else {
                int d = rand.nextInt(10);
                int e = rand.nextInt(10);
                int f = rand.nextInt(10);
                divideMultiply(d, e, f);
            }
        } else {
            int d = rand.nextInt(9) + 1;
            int e = rand.nextInt(9) + 1;
            int f = rand.nextInt(9) + 1;
            divideDivide(d, e, f);
        }
    }
    public void divideDivide(int a, int b, int c) {
        if(b != 0 && c != 0) {
            if(a % b == 0 && (a / b) % c == 0) {
                int expected = a / b / c;
                askQuestion(a + " / " + b + " / " + c);
                answerQuestion("" + answerGen(expected));
            } else {
                int d = rand.nextInt(9) + 1;
                int e = rand.nextInt(9) + 1;
                int f = rand.nextInt(9) + 1;
                divideDivide(d, e, f);
            }
        } else {
            int d = rand.nextInt(9) + 1;
            int e = rand.nextInt(9) + 1;
            int f = rand.nextInt(9) + 1;
            divideDivide(d, e, f);
        }
    }

    public void divideAddAdd(int a, int b, int c, int d) {
        if(b!= 0) {
            if(a % b == 0) {
                int expected = a / b + c + d;
                askQuestion(a + " / " + b + " + " + c + " + " + d);
                answerQuestion("" + answerGen(expected));
            } else {
                int e = rand.nextInt(10);
                int f = rand.nextInt(9) + 1;
                int g = rand.nextInt(100);
                int h = rand.nextInt(100);
                divideAddAdd(e, f, g, h);
            }
        } else {
            int e = rand.nextInt(10);
            int f = rand.nextInt(9) + 1;
            int g = rand.nextInt(100);
            int h = rand.nextInt(100);
            divideAddAdd(e, f, g, h);
        }
    }

    public void divideAddSubtract(int a, int b, int c, int d) {
        if(b!= 0) {
            if(a % b == 0) {
                int expected = a / b + c - d;
                askQuestion(a + " / " + b + " + " + c + " - " + d);
                answerQuestion("" + answerGen(expected));
            } else {
                int e = rand.nextInt(10);
                int f = rand.nextInt(9) + 1;
                int g = rand.nextInt(100);
                int h = rand.nextInt(100);
                divideAddSubtract(e, f, g, h);
            }
        } else {
            int e = rand.nextInt(10);
            int f = rand.nextInt(9) + 1;
            int g = rand.nextInt(100);
            int h = rand.nextInt(100);
            divideAddSubtract(e, f, g, h);
        }
    }

    public void divideAddDivide(int a, int b, int c, int d) {
        if(b!= 0 && d != 0) {
            if(a % b == 0 && c % d == 0) {
                int expected = a / b + c / d;
                askQuestion(a + " / " + b + " + " + c + " / " + d);
                answerQuestion("" + answerGen(expected));
            } else {
                int e = rand.nextInt(10);
                int f = rand.nextInt(9) + 1;
                int g = rand.nextInt(10);
                int h = rand.nextInt(9) + 1;
                divideAddDivide(e, f, g, h);
            }
        } else {
            int e = rand.nextInt(10);
            int f = rand.nextInt(9) + 1;
            int g = rand.nextInt(10);
            int h = rand.nextInt(9) + 1;
            divideAddDivide(e, f, g, h);
        }
    }

    public void divideAddMultiply(int a, int b, int c, int d) {
        if(b!= 0) {
            if(a % b == 0) {
                int expected = a / b + c * d;
                askQuestion(a + " / " + b + " + " + c + " * " + d);
                answerQuestion("" + answerGen(expected));
            } else {
                int e = rand.nextInt(10);
                int f = rand.nextInt(9) + 1;
                int g = rand.nextInt(10);
                int h = rand.nextInt(10);
                divideAddMultiply(e, f, g, h);
            }
        } else {
            int e = rand.nextInt(10);
            int f = rand.nextInt(9) + 1;
            int g = rand.nextInt(10);
            int h = rand.nextInt(10);
            divideAddMultiply(e, f, g, h);
        }
    }

    public void divideSubtractAdd(int a, int b, int c, int d) {
        if(b!= 0) {
            if(a % b == 0) {
                int expected = a / b - c + d;
                askQuestion(a + " / " + b + " - " + c + " + " + d);
                answerQuestion("" + answerGen(expected));
            } else {
                int e = rand.nextInt(10);
                int f = rand.nextInt(9) + 1;
                int g = rand.nextInt(100);
                int h = rand.nextInt(100);
                divideSubtractAdd(e, f, g, h);
            }
        } else {
            int e = rand.nextInt(10);
            int f = rand.nextInt(9) + 1;
            int g = rand.nextInt(100);
            int h = rand.nextInt(100);
            divideSubtractAdd(e, f, g, h);
        }
    }

    public void divideSubtractSubtract(int a, int b, int c, int d) {
        if(b!= 0) {
            if(a % b == 0) {
                int expected = a / b - c - d;
                askQuestion(a + " / " + b + " - " + c + " - " + d);
                answerQuestion("" + answerGen(expected));
            } else {
                int e = rand.nextInt(10);
                int f = rand.nextInt(9) + 1;
                int g = rand.nextInt(100);
                int h = rand.nextInt(100);
                divideSubtractSubtract(e, f, g, h);
            }
        } else {
            int e = rand.nextInt(10);
            int f = rand.nextInt(9) + 1;
            int g = rand.nextInt(100);
            int h = rand.nextInt(100);
            divideSubtractSubtract(e, f, g, h);
        }
    }

    public void divideSubtractDivide(int a, int b, int c, int d) {
        if(b!= 0 && d != 0) {
            if(a % b == 0 && c % d == 0) {
                int expected = a / b - c / d;
                askQuestion(a + " / " + b + " - " + c + " / " + d);
                answerQuestion("" + answerGen(expected));
            } else {
                int e = rand.nextInt(10);
                int f = rand.nextInt(9) + 1;
                int g = rand.nextInt(10);
                int h = rand.nextInt(9) + 1;
                divideSubtractDivide(e, f, g, h);
            }
        } else {
            int e = rand.nextInt(10);
            int f = rand.nextInt(9) + 1;
            int g = rand.nextInt(10);
            int h = rand.nextInt(9) + 1;
            divideSubtractDivide(e, f, g, h);
        }
    }

    public void divideSubtractMultiply(int a, int b, int c, int d) {
        if(b!= 0) {
            if(a % b == 0) {
                int expected = a / b - c * d;
                askQuestion(a + " / " + b + " - " + c + " * " + d);
                answerQuestion("" + answerGen(expected));
            } else {
                int e = rand.nextInt(10);
                int f = rand.nextInt(9) + 1;
                int g = rand.nextInt(10);
                int h = rand.nextInt(10);
                divideSubtractMultiply(e, f, g, h);
            }
        } else {
            int e = rand.nextInt(10);
            int f = rand.nextInt(9) + 1;
            int g = rand.nextInt(10);
            int h = rand.nextInt(10);
            divideSubtractMultiply(e, f, g, h);
        }
    }

    public void divideDivideAdd(int a, int b, int c, int d) {
        if(b != 0 && c != 0) {
            if(a % b == 0 && (a / b) % c == 0) {
                int expected = a / b / c + d;
                askQuestion(a + " / " + b + " / " + c + " + " + d);
                answerQuestion("" + answerGen(expected));
            } else {
                int e = rand.nextInt(10);
                int f = rand.nextInt(9) - 1;
                int g = rand.nextInt(9) - 1;
                int h = rand.nextInt(100);
                divideDivideAdd(e, f, g, h);
            }
        } else {
            int e = rand.nextInt(10);
            int f = rand.nextInt(9) - 1;
            int g = rand.nextInt(9) - 1;
            int h = rand.nextInt(100);
            divideDivideAdd(e, f, g, h);
        }
    }

    public void divideDivideSubtract(int a, int b, int c, int d) {
        if(b != 0 && c != 0) {
            if(a % b == 0 && (a / b) % c == 0) {
                int expected = a / b / c - d;
                askQuestion(a + " / " + b + " / " + c + " - " + d);
                answerQuestion("" + answerGen(expected));
            } else {
                int e = rand.nextInt(10);
                int f = rand.nextInt(9) - 1;
                int g = rand.nextInt(9) - 1;
                int h = rand.nextInt(100);
                divideDivideSubtract(e, f, g, h);
            }
        } else {
            int e = rand.nextInt(10);
            int f = rand.nextInt(9) - 1;
            int g = rand.nextInt(9) - 1;
            int h = rand.nextInt(100);
            divideDivideSubtract(e, f, g, h);
        }
    }

    public void divideDivideDivide(int a, int b, int c, int d) {
        if(b != 0 && c != 0 && d != 0) {
            if(a % b == 0 && (a / b) % c == 0 && (a / b / c) % d == 0) {
                int expected = a / b / c / d;
                askQuestion(a + " / " + b + " / " + c + " / " + d);
                answerQuestion("" + answerGen(expected));
            } else {
                int e = rand.nextInt(10);
                int f = rand.nextInt(9) - 1;
                int g = rand.nextInt(9) - 1;
                int h = rand.nextInt(9) - 1;
                divideDivideDivide(e, f, g, h);
            }
        } else {
            int e = rand.nextInt(10);
            int f = rand.nextInt(9) - 1;
            int g = rand.nextInt(9) - 1;
            int h = rand.nextInt(9) - 1;
            divideDivideDivide(e, f, g, h);
        }
    }

    public void divideDivideMultiply(int a, int b, int c, int d) {
        if(b != 0 && c != 0) {
            if(a % b == 0 && (a / b) % c == 0) {
                int expected = a / b / c * d;
                askQuestion(a + " / " + b + " / " + c + " * " + d);
                answerQuestion("" + answerGen(expected));
            } else {
                int e = rand.nextInt(10);
                int f = rand.nextInt(9) - 1;
                int g = rand.nextInt(9) - 1;
                int h = rand.nextInt(10);
                divideDivideMultiply(e, f, g, h);
            }
        } else {
            int e = rand.nextInt(10);
            int f = rand.nextInt(9) - 1;
            int g = rand.nextInt(9) - 1;
            int h = rand.nextInt(10);
            divideDivideMultiply(e, f, g, h);
        }
    }

    public void divideMultiplyAdd(int a, int b, int c, int d) {
        if (b != 0) {
            if(a % b == 0) {
                int expected = a / b * c + d;
                askQuestion(a + " / " + b + " * " + c + " + " + d);
                answerQuestion("" + answerGen(expected));
            } else {
                int e = rand.nextInt(10);
                int f = rand.nextInt(9) + 1;
                int g = rand.nextInt(10);
                int h = rand.nextInt(100);
                divideMultiplyAdd(e, f, g, h);
            }
        } else {
            int e = rand.nextInt(10);
            int f = rand.nextInt(9) + 1;
            int g = rand.nextInt(10);
            int h = rand.nextInt(100);
            divideMultiplyAdd(e, f, g, h);
        }
    }

    public void divideMultiplySubtract(int a, int b, int c, int d) {
        if (b != 0) {
            if(a % b == 0) {
                int expected = a / b * c - d;
                askQuestion(a + " / " + b + " * " + c + " - " + d);
                answerQuestion("" + answerGen(expected));
            } else {
                int e = rand.nextInt(10);
                int f = rand.nextInt(9) + 1;
                int g = rand.nextInt(10);
                int h = rand.nextInt(100);
                divideMultiplySubtract(e, f, g, h);
            }
        } else {
            int e = rand.nextInt(10);
            int f = rand.nextInt(9) + 1;
            int g = rand.nextInt(10);
            int h = rand.nextInt(100);
            divideMultiplySubtract(e, f, g, h);
        }
    }

    public void divideMultiplyDivide(int a, int b, int c, int d) {
        if (b != 0 && d != 0) {
            if(a % b == 0 && (a / b * c) % d == 0) {
                int expected = a / b * c / d;
                askQuestion(a + " / " + b + " * " + c + " / " + d);
                answerQuestion("" + answerGen(expected));
            } else {
                int e = rand.nextInt(10);
                int f = rand.nextInt(9) + 1;
                int g = rand.nextInt(10);
                int h = rand.nextInt(9) + 1;
                divideMultiplySubtract(e, f, g, h);
            }
        } else {
            int e = rand.nextInt(10);
            int f = rand.nextInt(9) + 1;
            int g = rand.nextInt(10);
            int h = rand.nextInt(9) + 1;
            divideMultiplySubtract(e, f, g, h);
        }
    }

    public void divideMultiplyMultiply(int a, int b, int c, int d) {
        if (b != 0) {
            if(a % b == 0) {
                int expected = a / b * c * d;
                askQuestion(a + " / " + b + " * " + c + " * " + d);
                answerQuestion("" + answerGen(expected));
            } else {
                int e = rand.nextInt(10);
                int f = rand.nextInt(9) + 1;
                int g = rand.nextInt(10);
                int h = rand.nextInt(10);
                divideMultiplyMultiply(e, f, g, h);
            }
        } else {
            int e = rand.nextInt(10);
            int f = rand.nextInt(9) + 1;
            int g = rand.nextInt(10);
            int h = rand.nextInt(10);
            divideMultiplyMultiply(e, f, g, h);
        }
    }

    /**)
     * Methods for multiplication as first operation
     * @param a
     * @param b
     */
    public void multiply( int a, int b) {
        int expected = a * b;
        askQuestion(a + " * " + b);
        answerQuestion("" + answerGen(expected));
    }

    public void multiplyAdd( int a, int b, int c) {
        int expected = a * b + c;
        askQuestion(a + " * " + b + " + " + c);
        answerQuestion("" + answerGen(expected));
    }

    public void multiplySubtract(int a, int b, int c) {
        int expected = a * b - c;
        askQuestion(a + " * " + b + " - " + c);
        answerQuestion("" + answerGen(expected));
    }


    public void multiplyDivide(int a, int b, int c) {
        if(c != 0) {
            if ((a * b) % c == 0) {

                int expected = a * b / c;
                askQuestion(a + " * " + b + " / " + c);
                answerQuestion("" + answerGen(expected));
            } else {
                int d = rand.nextInt(10);
                int e = rand.nextInt(10);
                int f = rand.nextInt(9) + 1;
                multiplyDivide(d, e, f);
            }
        }else {
            int d = rand.nextInt(10);
            int e = rand.nextInt(10);
            int f = rand.nextInt(9) + 1;
            multiplyDivide(d, e, f);
        }
    }

    public void multiplyMultiply(int a, int b, int c) {
        int expected = a * b * c;
        askQuestion(a + " * " + b + " * " + c);
        answerQuestion("" + answerGen(expected));
    }

    public void multiplyAddAdd(int a, int b, int c, int d) {
        int expected = a * b + c + d;
        askQuestion(a + " * " + b + " + " + c + " + " + d);
        answerQuestion("" + answerGen(expected));
    }

    public void multiplyAddSubtract(int a, int b, int c, int d) {
        int expected = a * b + c - d;
        askQuestion(a + " * " + b + " + " + c + " - " + d);
        answerQuestion("" + answerGen(expected));
    }

    public void multiplyAddDivide(int a, int b, int c, int d) {
        if(d != 0) {
            if (c % d == 0) {
                int expected = a * b + c / d;
                askQuestion(a + " * " + b + " + " + c + " / " + d);
                answerQuestion("" + answerGen(expected));
            } else {
                int e = rand.nextInt(10);
                int f = rand.nextInt(10);
                int g = rand.nextInt(10);
                int h = rand.nextInt(9) + 1;
                multiplyAddDivide(e, f, g, h);
            }
        } else {
            int e = rand.nextInt(10);
            int f = rand.nextInt(10);
            int g = rand.nextInt(10);
            int h = rand.nextInt(9) + 1;
            multiplyAddDivide(e, f, g, h);
        }
    }

    public void multiplyAddMultiply(int a, int b, int c, int d) {
        int expected = a * b + c * d;
        askQuestion(a + " * " + b + " + " + c + " * " + d);
        answerQuestion("" + answerGen(expected));
    }

    public void multiplySubtractAdd(int a, int b, int c, int d) {
        int expected = a * b - c + d;
        askQuestion(a + " * " + b + " - " + c + " + " + d);
        answerQuestion("" + answerGen(expected));
    }

    public void multiplySubtractSubtract(int a, int b, int c, int d) {
        int expected = a * b - c - d;
        askQuestion(a + " * " + b + " - " + c + " - " + d);
        answerQuestion("" + answerGen(expected));
    }

    public void multiplySubtractDivide(int a, int b, int c, int d) {
        if(d != 0) {
            if (c % d == 0) {
                int expected = a * b - c / d;
                askQuestion(a + " * " + b + " - " + c + " / " + d);
                answerQuestion("" + answerGen(expected));
            } else {
                int e = rand.nextInt(10);
                int f = rand.nextInt(10);
                int g = rand.nextInt(10);
                int h = rand.nextInt(9) + 1;
                multiplySubtractDivide(e, f, g, h);
            }
        } else {
            int e = rand.nextInt(10);
            int f = rand.nextInt(10);
            int g = rand.nextInt(10);
            int h = rand.nextInt(9) + 1;
            multiplySubtractDivide(e, f, g, h);
        }
    }

    public void multiplySubtractMultiply(int a, int b, int c, int d) {
        int expected = a * b + c * d;
        askQuestion(a + " * " + b + " + " + c + " * " + d);
        answerQuestion("" + answerGen(expected));
    }

    public void multiplyDivideAdd(int a, int b, int c, int d) {
        if(c != 0) {
            if((a * b) % c == 0) {
                int expected = a * b / c + d;
                askQuestion(a + " * " + b + " / " + c + " + " + d);
                answerQuestion("" + answerGen(expected));
            } else {
                int e = rand.nextInt(10);
                int f = rand.nextInt(10);
                int g = rand.nextInt(9) + 1;
                int h = rand.nextInt(100);
                multiplyDivideAdd(e, f, g, h);
            }
        } else {
            int e = rand.nextInt(10);
            int f = rand.nextInt(10);
            int g = rand.nextInt(9) + 1;
            int h = rand.nextInt(100);
            multiplyDivideAdd(e, f, g, h);
        }
    }

    public void multiplyDivideSubtract(int a, int b, int c, int d) {
        if(c != 0) {
            if((a * b) % c == 0) {
                int expected = a * b / c - d;
                askQuestion(a + " * " + b + " / " + c + " - " + d);
                answerQuestion("" + answerGen(expected));
            } else {
                int e = rand.nextInt(10);
                int f = rand.nextInt(10);
                int g = rand.nextInt(9) + 1;
                int h = rand.nextInt(100);
                multiplyDivideSubtract(e, f, g, h);
            }
        } else {
            int e = rand.nextInt(10);
            int f = rand.nextInt(10);
            int g = rand.nextInt(9) + 1;
            int h = rand.nextInt(100);
            multiplyDivideSubtract(e, f, g, h);
        }
    }

    public void multiplyDivideDivide(int a, int b, int c, int d) {
        if(c != 0 && d != 0) {
            if((a * b) % c == 0 && (a * b / c) % d == 0) {
                int expected = a * b / c / d;
                askQuestion(a + " * " + b + " / " + c + " / " + d);
                answerQuestion("" + answerGen(expected));
            } else {
                int e = rand.nextInt(10);
                int f = rand.nextInt(10);
                int g = rand.nextInt(9) + 1;
                int h = rand.nextInt(9) + 1;
                multiplyDivideDivide(e, f, g, h);
            }
        } else {
            int e = rand.nextInt(10);
            int f = rand.nextInt(10);
            int g = rand.nextInt(9) + 1;
            int h = rand.nextInt(9) + 1;
            multiplyDivideDivide(e, f, g, h);
        }
    }

    public void multiplyDivideMultiply(int a, int b, int c, int d) {
        if(c != 0) {
            if((a * b) % c == 0) {
                int expected = a * b / c * d;
                askQuestion(a + " * " + b + " / " + c + " * " + d);
                answerQuestion("" + answerGen(expected));
            } else {
                int e = rand.nextInt(10);
                int f = rand.nextInt(10);
                int g = rand.nextInt(9) + 1;
                int h = rand.nextInt(10);
                multiplyDivideMultiply(e, f, g, h);
            }
        } else {
            int e = rand.nextInt(10);
            int f = rand.nextInt(10);
            int g = rand.nextInt(9) + 1;
            int h = rand.nextInt(10);
            multiplyDivideMultiply(e, f, g, h);
        }
    }

    public void multiplyMultiplyAdd(int a, int b, int c, int d) {
        int expected = a * b * c + d;
        askQuestion(a + " * " + b + " * " + c + " + " + d);
        answerQuestion("" + answerGen(expected));
    }

    public void multiplyMultiplySubtract(int a, int b, int c, int d) {
        int expected = a * b * c - d;
        askQuestion(a + " * " + b + " * " + c + " - " + d);
        answerQuestion("" + answerGen(expected));
    }

    public void multiplyMultiplyDivide(int a, int b, int c, int d) {
        if(d != 0) {
            if ((a * b * c) % d == 0) {
                int expected = a * b * c / d;
                askQuestion(a + " * " + b + " * " + c + " / " + d);
                answerQuestion("" + answerGen(expected));
            } else {
                int e = rand.nextInt(10);
                int f = rand.nextInt(10);
                int g = rand.nextInt(10);
                int h = rand.nextInt(9) + 1;
                multiplyMultiplyDivide(e, f, g, h);
            }
        } else {
            int e = rand.nextInt(10);
            int f = rand.nextInt(10);
            int g = rand.nextInt(10);
            int h = rand.nextInt(9) + 1;
            multiplyMultiplyDivide(e, f, g, h);
        }
    }

    public void multiplyMultiplyMultiply(int a, int b, int c, int d) {
        int expected = a * b * c * d;
        askQuestion(a + " * " + b + " * " + c + " * " + d);
        answerQuestion("" + answerGen(expected));
    }


    /**
     * Generates the equation as a text on the canvas
     * Basically renders the question onto the screen
     * @param question
     */
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

        if(currentLevel < 6) {
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
