package com.prgguru.android;

import java.util.Locale;

import android.app.Activity;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TexttoSpeechActivity extends Activity implements OnInitListener {
	/** Called when the activity is first created.
	@author Kush Kapoor
	*/

	private TextToSpeech tts;
	private Button btnSpeak;
	private EditText txtText;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		tts = new TextToSpeech(this, this);
		btnSpeak = (Button) findViewById(R.id.btnSpeak);
		txtText = (EditText) findViewById(R.id.txtText);
		
		btnSpeak.setOnClickListener(new View.OnClickListener() {

			public void onClick(View arg0) {
				speakOut();
			}

		});
	}

	@Override
	public void onDestroy() {
		// Don't forget to shutdown!
		if (tts != null) {
			tts.stop();
			tts.shutdown();
		}
		super.onDestroy();
	}

	public void onInit(int status) {
		// TODO Auto-generated method stub

		if (status == TextToSpeech.SUCCESS) {

			int result = tts.setLanguage(Locale.US);
			
			if (result == TextToSpeech.LANG_MISSING_DATA
					|| result == TextToSpeech.LANG_NOT_SUPPORTED) {
				Toast.makeText(this, "Language not supported", Toast.LENGTH_LONG).show();
				Log.e("TTS", "Language is not supported");
			} else {
				btnSpeak.setEnabled(true);

			}

		} else {
			Log.e("TTS", "Initilization Failed");
		}

	}

	private void speakOut() {

		String text = txtText.getText().toString();
		if (text.length() == 0) {
			tts.speak("Please enter some text in the above placeholder.", TextToSpeech.QUEUE_FLUSH, null);
		} else {
			tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
		}

	}
}
