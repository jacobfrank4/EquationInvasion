package project.equationinvasion;

import android.content.Context;
import android.media.MediaPlayer;

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
     *  until this is called once, to avoid accidental resets between
     *  activities.
     */
    public void toggleMute(){
        if (!muted) {
            muted = true;
        } else {
            muted = false;
        }

    }

    //This method exists for when the home key is pushed.
    public void pauseMusic()
    {

        BGM.pause();
    }

    // this lets the music resume from where it was,
    // if the user leaves the app and comes back without closing
    // it.
    public void resumeMusic()
    {
        if (BGM != null) {
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
        BGM.stop();
        BGM.reset();
        BGM.release();

    }



    /*
        Case definitions:
        0: default button sound
        1: right Answer sound
        2: wrong answer sound

        This method exists to change which sound
        plays when a button is pushed.
        Buttons will need to pass in an integer
        corresponding to one of the cases above.
     */
    public void setSoundState(int i) {

        switch (i)
        {
            case 0:
                    if (SE != null){
                        SE.reset();
                        SE.release();
                    }
                    SE = MediaPlayer.create(context,R.raw.btn1sound);
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
