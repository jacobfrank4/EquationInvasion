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
 *
 * Menu Music: Jazz Comedy - Bensound.com
 * Play Screen Music: Jazzy Frenchy - Bensound.com
 * Game Over Music: The Lounge - Bensound.com
 * Transition noise: ooweep-Intermed-594 - Flashkit.com
 * Right answer noise: Plopp-jh-8598 - Flashkit.com
 * Wrong answer noise: Plopp-jh-8598 - Flashkit.com Local edit.
 *
 * All sounds licensed under the Creative Commons Licence Agreement.
 * Full agreement may be read at: http://creativecommons.org/licenses/by-nd/3.0/legalcode
 */

import android.content.Context;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;

// Built to streamline the audio production process.
class Audio {

	// The two media players that will be used for the entirety of the program.
	private static MediaPlayer BGM;
	private MediaPlayer SE;

	//The boolean that keeps track of when the user has muted the audio.
	private static boolean muted;

	// A receiver for the current context that this class is being called in.
	private final Context context;

	// Constructor for this class
	// c is a variable passed in by the host activity to define initial context.
	public Audio(Context c) {
		context = c;
	}

	/**
	 * This method generates the music to be played on the main menu.
	 * It should be called only when a new instance of the main activity
	 * is created, or when it is unmuted. This will not create time displaced copies.
	 */
	public void menuBGM() {
		if (!muted) {
			BGM = MediaPlayer.create(context, R.raw.bgm1);
			BGM.setLooping(true);
			BGM.start();
		}
	}

	// This plays the background music for the play screen.
	public void playBGM() {
		if (!muted) {
			BGM = MediaPlayer.create(context, R.raw.bgm2);
			BGM.setLooping(true);
			BGM.start();
		}
	}

	//This plays the background music for the game over screen
	public void overBGM() {
		if (!muted) {
			BGM = MediaPlayer.create(context, R.raw.bgm3);
			BGM.setLooping(true);
			BGM.start();
		}
	}

	/*
	 *  This is meant to enable the user to turn all sound off.
	 *  It works by setting the muted variable to the opposite
	 *  state of what it is currently. It uses != true instead of
	 *  == false, because the boolean is not actually initialized
	 *  until this is called once,to avoid accidental resets between
	 *  activities.It also calls menu music as that's the only place it can
	 *  be toggled.
	 */
	public void toggleMute() {
		if (!muted) {
			stopMusic();
			muted = true;
		} else {
			muted = false;
			menuBGM();
		}
	}

	//This method exists for when the home or lock keys are pushed.
	public void pauseMusic() {
		if (BGM != null) {
			BGM.pause();
		}
	}

	// This lets the music resume from where it was,
	// if the user leaves the app and comes back without closing it.
	public void resumeMusic() {
		if (BGM != null && !muted && !BGM.isPlaying()) {
			BGM.start();
		}
	}

	// This is meant to enable stopping of music.(used in muting)
	public void stopMusic() {
		if (!muted) {
			BGM.stop();
			BGM.reset();
		}
	}

	// This lets us close the sound effects on another page,
	private final OnCompletionListener done = new OnCompletionListener() {
		@Override
		public void onCompletion(MediaPlayer mp) {
			if (mp == SE) {
				SE.reset();
				SE.release();
			}
		}
	};

	//The sound effect played when the user navigates away from the activity
	public void transitionNoise() {
		if (!muted) {
			SE = MediaPlayer.create(context, R.raw.btn1sound);
			SE.setOnCompletionListener(done);
			SE.start();
		}
	}

	//The sound effect played when the user gets a right answer
	public void rightNoise() {
		if (SE != null) {
			SE.reset();
			SE.release();
		}
		SE = MediaPlayer.create(context, R.raw.right);
		SE.start();
	}

	//The sound effect played when the user gets a wrong answer.
	public void wrongNoise() {
		if (SE != null) {
			SE.reset();
			SE.release();
		}
		SE = MediaPlayer.create(context, R.raw.wrong);
		SE.start();
	}
}


