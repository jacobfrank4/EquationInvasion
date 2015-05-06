package project.equationinvasion;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.Random;


public class Play extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
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
        return displayed;
    }
}
