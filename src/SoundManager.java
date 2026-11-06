import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * This class allows you to load uncompressed sounds (like .wav or .au or .aiff) and play them later, by reference
 * number.
 * Note: this is a "Singleton" class - it is designed so that there is only ever as many as one such SoundManager in
 * existence. Other classes may get this instance via the SoundManager.getSoundManager() command. This way, multiple
 * classes can refer to the same SoundManager and its sounds without having to load them more than once.
 */
// NOTE: This class was written with the assistance of Google Gemini.
public class SoundManager
{
    private final ArrayList<SoundEffect> sounds;
    private final String[] FILE_NAMES = {"tap.wav","trill.wav"};

    public static final int EFFECT_TAP = 0;
    public static final int EFFECT_TRILL = 1;

    private static SoundManager singletonSM = null; // the one SoundManager instance in existence.


    /**
     * This is the only publicly available way of getting a SoundManager instance... if the singleton SoundManager
     * already exists, it will return it; otherwise it will create the (one and only) instance, store it, and return it.
     * @return - the only instance of the SoundManager class.
     */
    public static SoundManager getSoundManager()
    {
        if (singletonSM == null)
        {
            singletonSM = new SoundManager();
        }
        return singletonSM;
    }

    /**
     * loads the hardcoded sounds into this manager. Note: this is a private constructor, so it cannot be called from
     * outside this class!
     */
    private SoundManager()
    {
        sounds = new ArrayList<>();
        for (String filename: FILE_NAMES)
        {
            sounds.add(new SoundEffect(filename));
        }
    }

    /**
     * play the given sound once, from the beginning.
     * @param soundId - the id number referring to the sound desired.
     */
    public void playSound(int soundId)
    {
        sounds.get(soundId).play();
    }

    /**
     * adds an additional sound effect to the list of sounds at runtime and returns the index used to refer to this
     * sound effect.
     * @param filename - the name of the file to load.
     * @return - the id number that can be used in playSound() to make this noise.
     */
    public int addSoundFile(String filename)
    {
        sounds.add(new SoundEffect(filename));
        return sounds.size()-1;
    }

    public String toString()
    {
        StringBuilder result = new StringBuilder("Sound manager with "+sounds.size()+" sounds loaded:\n");
        for (int i=0; i<sounds.size(); i++)
        {
            result.append(i);
            result.append("\t");
            result.append(sounds.get(i).toString());
            result.append("\n");
        }
        return result.toString();
    }

    private class SoundEffect
    {
        private final Clip myClip;   //test
        private final String myFilename;

        public SoundEffect(String filename)
        {
            myFilename = filename;
            File audioFile = new File("sounds/"+filename);
            if (!audioFile.exists())
                throw new RuntimeException("Could not open sound file: " + filename);
            try
            {
                AudioInputStream audioIn = AudioSystem.getAudioInputStream(audioFile);
                myClip = AudioSystem.getClip();
                myClip.open(audioIn);
                audioIn.close();
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e)
            {
                throw new RuntimeException(e);
            }
        }

        public void play()
        {
               myClip.setFramePosition(0);
            myClip.start();
        }

        public String toString()
        {
            return String.format("%s (%3.2f s)", myFilename, myClip.getMicrosecondLength()/1000000.0);
        }
    }
}
