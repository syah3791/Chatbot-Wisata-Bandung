package com.wisatabandung;

import android.app.Activity;
import android.app.*;
import android.content.res.AssetManager;
import android.os.*;
import android.support.design.widget.FloatingActionButton;
import android.view.*;
import android.view.View.*;
import android.widget.*;
import android.content.*;
import android.graphics.*;
import android.media.*;
import android.net.*;
import android.text.*;
import android.util.*;
import android.webkit.*;
import android.animation.*;
import android.view.animation.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.*;
import java.text.*;
import android.widget.LinearLayout;
import android.content.Intent;
import android.net.Uri;
import java.util.Timer;
import java.util.TimerTask;
import android.media.SoundPool;

import com.wisatabandung.Adapter.ChatMessageAdapter;

import android.content.res.AssetManager;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import org.alicebot.ab.AIMLProcessor;
import org.alicebot.ab.Bot;
import org.alicebot.ab.Chat;
import org.alicebot.ab.Graphmaster;
import org.alicebot.ab.MagicBooleans;
import org.alicebot.ab.MagicStrings;
import org.alicebot.ab.PCAIMLProcessorExtension;
import com.wisatabandung.Adapter.ChatMessageAdapter;
import com.wisatabandung.Pojo.ChatMessage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class MainActivity extends Activity {
	
	private Timer _timer = new Timer();
	
	private LinearLayout linear1;
	
	private Intent i = new Intent();
	private TimerTask t;
	private SoundPool s;
	private ListView mListView;
	private FloatingActionButton mButtonSend;
	private EditText mEditTextMessage;
	private ImageView mImageView;
	public Bot bot;
	public static Chat chat;
	private ChatMessageAdapter mAdapter;
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.main);
		initialize(_savedInstanceState);
		//checking SD card availablility
		boolean a = isSDCARDAvailable();
		//receiving the assets from the app directory
		AssetManager assets = getResources().getAssets();
		File jayDir = new File(Environment.getExternalStorageDirectory().toString() + "/hari/bots/Hari");
		boolean b = jayDir.mkdirs();
		if (jayDir.exists()) {
			//Reading the file
			try {
				for (String dir : assets.list("Hari")) {
					File subdir = new File(jayDir.getPath() + "/" + dir);
					boolean subdir_check = subdir.mkdirs();
					for (String file : assets.list("Hari/" + dir)) {
						File f = new File(jayDir.getPath() + "/" + dir + "/" + file);
						if (f.exists()) {
							continue;
						}
						InputStream in = null;
						OutputStream out = null;
						in = assets.open("Hari/" + dir + "/" + file);
						out = new FileOutputStream(jayDir.getPath() + "/" + dir + "/" + file);
						//copy file from assets to the mobile's SD card or any secondary memory
						copyFile(in, out);
						in.close();
						in = null;
						out.flush();
						out.close();
						out = null;
					}
				}
				final boolean TRACE_MODE = false;
				String resourcesPath = Environment.getExternalStorageDirectory().toString() + "/hari";
				System.out.println(resourcesPath);
				MagicBooleans.trace_mode = TRACE_MODE;
				Bot bot = new Bot("Hari", resourcesPath);

				bot.writeAIMLFiles();
				Log.i(resourcesPath, "MyClass.getView() — get item number ");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		initializeLogic();
	}

	public static boolean isSDCARDAvailable(){
		return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)? true :false;
	}

	private void copyFile(InputStream in, OutputStream out) throws IOException {
		byte[] buffer = new byte[1024];
		int read;
		while((read = in.read(buffer)) != -1){
			out.write(buffer, 0, read);
		}
	}
	
	private void initialize(Bundle _savedInstanceState) {
		
		linear1 = (LinearLayout) findViewById(R.id.linear1);
	}
	private void initializeLogic() {
		t = new TimerTask() {
			@Override
			public void run() {
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						i.setClass(getApplicationContext(), MenuActivity.class);
						startActivity(i);
						finish();
					}
				});
			}
		};
		_timer.schedule(t, (int)(1000));
	}
	
	@Override
	protected void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
		super.onActivityResult(_requestCode, _resultCode, _data);
		
		switch (_requestCode) {
			
			default:
			break;
		}
	}
	
	@Deprecated
	public void showMessage(String _s) {
		Toast.makeText(getApplicationContext(), _s, Toast.LENGTH_SHORT).show();
	}
	
	@Deprecated
	public int getLocationX(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[0];
	}
	
	@Deprecated
	public int getLocationY(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[1];
	}
	
	@Deprecated
	public int getRandom(int _min, int _max) {
		Random random = new Random();
		return random.nextInt(_max - _min + 1) + _min;
	}
	
	@Deprecated
	public ArrayList<Double> getCheckedItemPositionsToArray(ListView _list) {
		ArrayList<Double> _result = new ArrayList<Double>();
		SparseBooleanArray _arr = _list.getCheckedItemPositions();
		for (int _iIdx = 0; _iIdx < _arr.size(); _iIdx++) {
			if (_arr.valueAt(_iIdx))
			_result.add((double)_arr.keyAt(_iIdx));
		}
		return _result;
	}
	
	@Deprecated
	public float getDip(int _input){
		return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, _input, getResources().getDisplayMetrics());
	}
	
	@Deprecated
	public int getDisplayWidthPixels(){
		return getResources().getDisplayMetrics().widthPixels;
	}
	
	@Deprecated
	public int getDisplayHeightPixels(){
		return getResources().getDisplayMetrics().heightPixels;
	}
	
}
