package project.equationinvasion;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.Random;


public class Play extends ActionBarActivity implements View.OnClickListener {
    private TextView equation;
    private TextView answer;
    Random rand = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        final Button newEquation = (Button) findViewById(R.id.button);
        newEquation.setOnClickListener(this);
        equation = (TextView) findViewById(R.id.textView);
        answer = (TextView) findViewById(R.id.textView2);

    }

    public void onClick(View v) {
        if(v.getId() == R.id.button) {
            getNew();
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_play, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        return id == R.id.action_settings || super.onOptionsItemSelected(item);
    }

    public void getNew() {
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

    public void add(int a, int b) {
        int correct = a + b;
        askQuestion(a + " + " + b + " = ");
        askAnswer("" + answerGen(correct));

    }

    public void subtract(int a, int b) {
        int correct = a - b;
        askQuestion(a + " - " + b + " = ");
        askAnswer("" + answerGen(correct));
    }

    public void divide(int a, int b) {
        int correct = a / b;
        askQuestion(a + " / " + b + " = ");
        askAnswer("" + answerGen(correct));
    }

    public void multiply( int a, int b) {
        int correct = a * b;
        askQuestion(a + " * " + b + " = ");
        askAnswer("" + answerGen(correct));
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

        double wrong;
        int displayed;
        int choice = gen.nextInt(2) + 1;  // True or False Decision

        if (choice == 2) {
            double variable = 1/10;
            int a = gen.nextInt(2) + 1;
                if (a == 1) {
                    wrong = answer + variable;
                } else {
                    wrong = answer - variable;
                }
            displayed = (int) wrong;
        } else {
            displayed = answer;
        }
        return displayed;
    }

    private void askAnswer(final String answered) {
        answer.setText(answered);
    }
}
