package com.pennapps.morningorganizer;

import android.content.Context;
import android.os.Handler;

import com.nuance.nmdp.speechkit.SpeechKit;
import com.nuance.nmdp.speechkit.Vocalizer;

public class Nuance {

	public static final String TTS_KEY = "com.nuance.nmdp.sample.tts";	 //to change later?
    
    private Vocalizer _vocalizer;
    private Object _lastTtsContext = null;
    
	
	private static SpeechKit _speechKit;
    
    // Allow other activities to access the SpeechKit instance.
    static SpeechKit getSpeechKit()
    {
        return _speechKit;
    }
    
    static void initializeSpeechKit(Context appContext)
    {
    	if (_speechKit == null)
    	{
    		  _speechKit = SpeechKit.initialize(appContext, NuanceAppInfo.SpeechKitAppId, NuanceAppInfo.SpeechKitServer, NuanceAppInfo.SpeechKitPort, NuanceAppInfo.SpeechKitSsl, NuanceAppInfo.SpeechKitApplicationKey);
               _speechKit.connect();
    	}
    	
    }
    
    void initializeTheVocalizer(Context appContext, Handler handler)
    {
    	_vocalizer = (Vocalizer) _speechKit.createVocalizerWithLanguage("en_US", (Vocalizer.Listener) appContext, handler);
    }
    
    void speakTheString(String stringToSay, Context appContext)
    {
    	_vocalizer.speakString(stringToSay, appContext);
    }
    
    static void closeSpeechKit()
    {
    	if (_speechKit != null)
    	{
    		_speechKit.cancelCurrent();
    		_speechKit.release();
    	}
    }
	
}
