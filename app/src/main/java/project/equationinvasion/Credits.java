package project.equationinvasion;

/**
 * Copyright 2015 Avium Studios
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;


public class Credits extends AppCompatActivity {

    private Audio noise;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credits);

        /**
         * Setting font style
         */
        //Font path
        String chalkboardFontPath = "fonts/Chalkboard.ttf";

        //text view label
        TextView txtCreditsHeader = (TextView) findViewById(R.id.creditsHeader);
        TextView txtDevList = (TextView) findViewById(R.id.devList);
        TextView txtContributorHeader = (TextView) findViewById(R.id.contributorHeader);
        TextView txtContributors = (TextView) findViewById(R.id.contributors);

        //Load Font Face
        Typeface chalkboardFont = Typeface.createFromAsset(getAssets(),chalkboardFontPath);

        //Applying font
        txtCreditsHeader.setTypeface(chalkboardFont);
        txtDevList.setTypeface(chalkboardFont);
        txtContributorHeader.setTypeface(chalkboardFont);
        txtContributors.setTypeface(chalkboardFont);

        noise = new Audio(Credits.this);
    }

    public void goBack(View view) {
        Intent intent = new Intent(this, Extras.class);
        startActivity(intent);
        noise.close();
        noise.setSoundState(0);
        noise.buttonNoise();
        finish();
    }

    //Returns you to the extras screen when back is oushed, and close this activity.
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(), Extras.class);
        startActivity(intent);
        finish();
    }
}
