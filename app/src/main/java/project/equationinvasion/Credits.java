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

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;


public class Credits extends AppCompatActivity {

	//Audio declarations
	private static Audio noise;
	private static boolean finished;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_credits);

		/**
		 * Setting font style
		 */
		//Font path
		final String chalkboardFontPath = "fonts/Chalkboard.ttf";

		//text view label
		final TextView txtCreditsHeader = (TextView) findViewById(R.id.creditsHeader);
		final TextView txtDevList = (TextView) findViewById(R.id.devList);
		final TextView txtContributorHeader = (TextView) findViewById(R.id.contributorHeader);
		final TextView txtContributors = (TextView) findViewById(R.id.contributors);

		//Load Font Face
		final Typeface chalkboardFont = Typeface.createFromAsset(getAssets(), chalkboardFontPath);

		//Applying font
		txtCreditsHeader.setTypeface(chalkboardFont);
		txtDevList.setTypeface(chalkboardFont);
		txtContributorHeader.setTypeface(chalkboardFont);
		txtContributors.setTypeface(chalkboardFont);

		noise = new Audio(Credits.this);
	}

	public void goBack(View view) {
		onBackPressed();
	}

	@Override
	protected void onUserLeaveHint() {
		super.onUserLeaveHint();
		noise.transitionNoise();
		if (!finished) {
			noise.pauseMusic();
		}
	}

	protected void onPause() {
		super.onPause();
		noise.pauseMusic();
	}

	@Override
	protected void onResume() {
		super.onResume();
		noise.resumeMusic();
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		noise.transitionNoise();
		finished = true;
		finish();
	}
}
