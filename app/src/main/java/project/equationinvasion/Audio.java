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

import android.content.Context;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;

/**
 * Built to streamline the audio production process.
 */
public class Audio {

    /*
        The two media players that will be used for the entirety of the program.
        -Matt
     */
    private  MediaPlayer BGM;
    private  MediaPlayer SE;


    //The boolean that keeps track of when the user has muted the audio.
    //-Matt
    private static boolean muted;

    /*
        A receiver for the current context that this class is being called in.
     */
    private Context context;

    /*
        Constructor for this class
        c is a variable passed in by the host activity to define initial context.
     */
    public Audio(Context c) {
        context = c;
    }

    /**
     * This method generates the music to be played on the main menu.
     * It should be called only when a new instance of the main activity
     * is created. This will not create time displaced copies.
     */
    public void menuBGM() {
        if (!muted){
            BGM = MediaPlayer.create(context, R.raw.bgm1);
            BGM.setLooping(true);
            BGM.start();
        }
    }

    /*
        This should perform the same task as above, but for the play screen.
     */
    public void playBGM() {
        if (!muted) {

        }
    }

    /*
        This method simply makes a noise when a button is clicked.
        The specific noise is defined elsewhere, in the setSoundState
        method. This should reduce the number of instances we need.
     */
    public void buttonNoise() {
        if (!muted) {
            SE.start();
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
    public void toggleMute(){
        if (!muted) {
            muted = true;
            stopMusic();
        } else {
            muted = false;
            menuBGM();
        }

    }

    //This method exists for when the home key is pushed.
    public void pauseMusic()
    {
        if (BGM != null) {
            BGM.pause();
        }
    }

    // this lets the music resume from where it was,
    // if the user leaves the app and comes back without closing
    // it.
    public void resumeMusic()
    {
        if (BGM != null && !muted) {
            BGM.start();
        }
    }

    /*
        This is meant to enable stopping of music.(used in muting)
     */
    public void stopMusic() {
            BGM.stop();
            BGM.reset();
    }


    //This method exists to free up resources when the user navigates away from the screen.
    public void close() {
        if (BGM != null){
            BGM.stop();
            BGM.reset();
            BGM.release();
        }

    }

    /*
        This lets us close the sound effects on another page,
        after we've already closed the BGM
     */
    OnCompletionListener done = new OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            if (mp == SE) {
                SE.reset();
                SE.release();
            }
        }
    };




    /*
        Case definitions:
        0: transition button sound
        1: right Answer sound
        2: wrong answer sound
        3: mute noise

        This method exists to change which sound
        plays when a button is pushed.
        Buttons will need to pass in an integer
        corresponding to one of the cases above.
     */
    public void setSoundState(int i) {

        switch (i)
        {
            case 0:
<<<<<<< HEAD
<<<<<<< HEAD

=======
=======
>>>>>>> parent of b8bcd9d... fixed the transition bug, added audio to Game Over Page
                    if (SE != null){
                        SE.reset();
                        SE.release();
                    }
<<<<<<< HEAD
>>>>>>> parent of b8bcd9d... fixed the transition bug, added audio to Game Over Page
=======
>>>>>>> parent of b8bcd9d... fixed the transition bug, added audio to Game Over Page
                    SE = MediaPlayer.create(context,R.raw.btn1sound);
                    SE.setOnCompletionListener(done);
                    break;

            case 1:
                if (SE != null){
                    SE.reset();
                    SE.release();
                }
                SE = MediaPlayer.create(context,R.raw.right);
                break;

            case 2:
                if (SE != null){
                    SE.reset();
                    SE.release();
                }
                SE = MediaPlayer.create(context,R.raw.wrong);
                break;
        }
    }

}


