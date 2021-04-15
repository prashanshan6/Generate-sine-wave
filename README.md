Generates a sine wave of given frequency as audio stream.


Amplitude of for panning in stereo can be found using these
panValue can range from -1(left) to +1(right)
    normBalance = ( panValue + 1.f ) * 0.5f;
    leftAmp = (float)Math.cos( PI / 2 * normBalance );
    rightAmp = (float)Math.sin( PI / 2 * normBalance );