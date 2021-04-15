import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;


public class GenSineWave{
    float frequency;
    int bufferSize = 64;
    int minBufferSize = bufferSize;
    short[] buffer = new short[bufferSize];
    float samples[] = new float[bufferSize];
    int sampleRate =  44100;
    CyclicBarrier gateRef;

    AudioTrack track = new AudioTrack(AudioManager.STREAM_MUSIC, sampleRate, AudioFormat.CHANNEL_OUT_STEREO, AudioFormat.ENCODING_PCM_16BIT, minBufferSize, AudioTrack.MODE_STREAM);
    public void setFrequency(float frequency){
        this.frequency = frequency;
    }
    public void adjustPan(float leftPan, float rightPan){
        track.setStereoVolume(leftPan, rightPan);
    }
    // constructor
    GenSineWave(float frequency, float leftPan, float rightPan) {
        this.frequency = frequency;
        track.setStereoVolume(leftPan, rightPan);
    }

    public void run(){
        try {
            gateRef.await();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        track.play();
        while (true) {
            for (int i = 0; i < samples.length; i++) {
                samples[i] = (float) Math.sin( (float)i * ((float)(2*Math.PI) * frequency / sampleRate));    //generates individual samples of the sinewave
                buffer[i] = (short) (samples[i] * Short.MAX_VALUE);
            }
            track.write( buffer, 0, samples.length );  //write to the audio buffer and repeat
        }
    }
}
