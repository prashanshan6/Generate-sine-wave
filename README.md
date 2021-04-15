Generates a sine wave of given frequency as audio stream.


Amplitude of for panning in stereo can be found using these <br />
panValue can range from -1(left) to +1(right) <br /><br />
    
<ul>
    <li>normBalance = ( panValue + 1.f ) * 0.5f; </li> <br />
    <li>leftAmp = (float)Math.cos( PI / 2 * normBalance ); </li> <br />
    <li>rightAmp = (float)Math.sin( PI / 2 * normBalance ); </li> <br />
</ul>