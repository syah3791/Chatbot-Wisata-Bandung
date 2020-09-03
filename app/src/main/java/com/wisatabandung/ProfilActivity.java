package com.wisatabandung;

import android.app.Activity;
import android.app.*;
import android.os.*;
import android.os.Environment;
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
import java.util.*;
import java.text.*;
import android.widget.ScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Button;
import android.content.Intent;
import android.net.Uri;
import android.content.SharedPreferences;
import android.content.ClipData;
import android.view.View;
import android.text.Editable;
import android.text.TextWatcher;
import android.Manifest;
import android.content.pm.PackageManager;
import org.alicebot.ab.Bot;
import org.alicebot.ab.MagicBooleans;

public class ProfilActivity extends Activity {
	
	public final int REQ_CD_IMAGE = 101;
	
	private ScrollView vscroll1;
	private LinearLayout linear1;
	private LinearLayout linear2;
	private LinearLayout linear3;
	private LinearLayout linear4;
	private LinearLayout linear5;
	private LinearLayout linear6;
	private LinearLayout linear7;
	private LinearLayout linear8;
	private LinearLayout linear9;
	private TextView textview1;
	private TextView textview11;
	private TextView textview2;
	private TextView textview3;
	private EditText edittext1;
	private TextView textview4;
	private TextView textview6;
	private EditText edittext2;
	private TextView textview7;
	private TextView textview9;
	private EditText edittext3;
	private Button button1;
	private TextView textview10;
	private Button button2;
	private Button button3;
	private Button button4;
	
	private Intent p = new Intent();
	private SharedPreferences s;
	private Intent image = new Intent(Intent.ACTION_GET_CONTENT);
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.profil);
		initialize(_savedInstanceState);
		if (Build.VERSION.SDK_INT >= 23) {
			if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
				requestPermissions(new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, 1000);
			}
			else {
				initializeLogic();
			}
		}
		else {
			initializeLogic();
		}
	}
	@Override
	public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
		if (requestCode == 1000) {
			initializeLogic();
		}
	}
	
	private void initialize(Bundle _savedInstanceState) {
		
		vscroll1 = (ScrollView) findViewById(R.id.vscroll1);
		linear1 = (LinearLayout) findViewById(R.id.linear1);
		linear2 = (LinearLayout) findViewById(R.id.linear2);
		linear3 = (LinearLayout) findViewById(R.id.linear3);
		linear4 = (LinearLayout) findViewById(R.id.linear4);
		linear5 = (LinearLayout) findViewById(R.id.linear5);
		linear6 = (LinearLayout) findViewById(R.id.linear6);
		linear7 = (LinearLayout) findViewById(R.id.linear7);
		linear8 = (LinearLayout) findViewById(R.id.linear8);
		linear9 = (LinearLayout) findViewById(R.id.linear9);
		textview1 = (TextView) findViewById(R.id.textview1);
		textview11 = (TextView) findViewById(R.id.textview11);
		textview2 = (TextView) findViewById(R.id.textview2);
		textview3 = (TextView) findViewById(R.id.textview3);
		edittext1 = (EditText) findViewById(R.id.edittext1);
		textview4 = (TextView) findViewById(R.id.textview4);
		textview6 = (TextView) findViewById(R.id.textview6);
		edittext2 = (EditText) findViewById(R.id.edittext2);
		textview7 = (TextView) findViewById(R.id.textview7);
		textview9 = (TextView) findViewById(R.id.textview9);
		edittext3 = (EditText) findViewById(R.id.edittext3);
		button1 = (Button) findViewById(R.id.button1);
		textview10 = (TextView) findViewById(R.id.textview10);
		button2 = (Button) findViewById(R.id.button2);
		button3 = (Button) findViewById(R.id.button3);
		button4 = (Button) findViewById(R.id.button4);
		s = getSharedPreferences("s", Activity.MODE_PRIVATE);
		image.setType("image/*");
		image.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
		
		edittext1.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				final String _charSeq = _param1.toString();
				if (_charSeq.length() < 8) {
					edittext1.setTextColor(0xFF000000);
				}
				else {
					edittext1.setTextColor(0xFFF44336);
				}
				textview11.setText(_charSeq);
			}
			
			@Override
			public void beforeTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				
			}
			
			@Override
			public void afterTextChanged(Editable _param1) {
				
			}
		});
		
		edittext2.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				final String _charSeq = _param1.toString();
				if (edittext2.getText().toString().contains(" ")) {
					edittext2.setTextColor(0xFFF44336);
				}
				else {
					edittext2.setTextColor(0xFF000000);
				}
			}
			
			@Override
			public void beforeTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				
			}
			
			@Override
			public void afterTextChanged(Editable _param1) {
				
			}
		});
		
		edittext3.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				final String _charSeq = _param1.toString();
				if (_charSeq.contains(" ")) {
					edittext3.setTextColor(0xFFF44336);
				}
				else {
					edittext3.setTextColor(0xFF000000);
				}
			}
			
			@Override
			public void beforeTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				
			}
			
			@Override
			public void afterTextChanged(Editable _param1) {
				
			}
		});
		
		button1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				s.edit().putString("s", edittext1.getText().toString()).commit();
				s.edit().putString("a", edittext2.getText().toString()).commit();
				s.edit().putString("o", edittext3.getText().toString()).commit();
			}
		});
		
		button2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				p.setClass(getApplicationContext(), TiketActivity.class);
				startActivity(p);
			}
		});
		button3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				p.setClass(getApplicationContext(), MessageActivity.class);
				startActivity(p);
			}
		});
		button4.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				final boolean TRACE_MODE = false;
				String resourcesPath = Environment.getExternalStorageDirectory().toString() + "/hari";
				System.out.println(resourcesPath);
				MagicBooleans.trace_mode = TRACE_MODE;
				Bot bot = new Bot("Hari", resourcesPath);

				bot.writeAIMLFiles();
				Log.i(resourcesPath, "MyClass.getView() — get item number ");
			}
		});
	}
	private void initializeLogic() {
		edittext1.setText(s.getString("s", ""));
		edittext2.setText(s.getString("a", ""));
		edittext3.setText(s.getString("o", ""));
	}
	
	@Override
	protected void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
		super.onActivityResult(_requestCode, _resultCode, _data);
		
		switch (_requestCode) {
			case REQ_CD_IMAGE:
			if (_resultCode == Activity.RESULT_OK) {
				ArrayList<String> _filePath = new ArrayList<>();
				if (_data != null) {
					if (_data.getClipData() != null) {
						for (int _index = 0; _index < _data.getClipData().getItemCount(); _index++) {
							ClipData.Item _item = _data.getClipData().getItemAt(_index);
							_filePath.add(FileUtil.convertUriToFilePath(getApplicationContext(), _item.getUri()));
						}
					}
					else {
						_filePath.add(FileUtil.convertUriToFilePath(getApplicationContext(), _data.getData()));
					}
				}
				
			}
			else {
				
			}
			break;
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

	public class AddAiml {

		private static final boolean TRACE_MODE = false;
		private String botName = "super";

		public void main(String[] args) {
			try {

				String resourcesPath = getResourcesPath();
				System.out.println(resourcesPath);
				MagicBooleans.trace_mode = TRACE_MODE;
				Bot bot = new Bot("super", resourcesPath);

				bot.writeAIMLFiles();
				Log.i(resourcesPath, "MyClass.getView() — get item number ");

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		private String getResourcesPath() {
			File currDir = new File(".");
			String path = currDir.getAbsolutePath();
			path = path.substring(0, path.length() - 2);
			System.out.println(path);
			String resourcesPath = Environment.getExternalStorageDirectory().toString() + "/hari";
			return resourcesPath;
		}
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
