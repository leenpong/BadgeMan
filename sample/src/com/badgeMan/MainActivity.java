package com.badgeMan;


import java.io.File;
import java.io.FileNotFoundException;

import com.badgeMan.R;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.PopupMenu.OnMenuItemClickListener;




public class MainActivity extends Activity implements OnMenuItemClickListener{
	private Bitmap ReturnPhoto;//
	Uri ResultUri;
	private FristFragment BeginFragment;//
	private SecondFragment secondfragment;//
	private SharedPreferences.Editor editor;
	private static SharedPreferences sharedPreferences;
	private boolean isFirstRun;//�ж��Ƿ��ǵ�һ�ε�½��һ������
	//private boolean ifFromAddCorner;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		
		
		//�޸Ĵ���
		
		
		
		//����sharedPreferences�洢��ʽ
		sharedPreferences = this.getSharedPreferences("share", MODE_PRIVATE);
		isFirstRun = sharedPreferences.getBoolean("isFirstRun", true);
		sharedPreferences.getBoolean("ifFromAddCorner", false);
		
		//������Ŀ��actionBar����˲�������
		ActionBar actionBar = getActionBar();
		actionBar.hide();
		
		//ʵ��������ҳ��fragment
		BeginFragment = new FristFragment();
		getFragmentManager().beginTransaction().replace(R.id.main_layout, BeginFragment).commit();
		Handler x = new Handler();
		x.postDelayed(new Begin(), 3000); //��ʱ2��
		//isFromAddCorner("false");
		
	}

	class Begin implements Runnable
	{
		public void run() {
			//��ת����
			try{
				secondfragment = new SecondFragment();
				switchConten(secondfragment);
			}catch(Exception e){
				System.exit(0);
			}
			
		}
	}	
	
	public boolean onKeyDown(int keyCode, KeyEvent event)  {
	    if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) { //���µ������BACK��ͬʱû���ظ�
	    	FragmentManager fm = getFragmentManager();
			Fragment fragment =fm.findFragmentById(R.id.main_layout);
			if(fragment instanceof SecondFragment){
				 return super.onKeyDown(keyCode, event);
				
			}
			else{
				secondfragment = new SecondFragment();
				switchConten(secondfragment);
				
			}
	        return true;
	    }

	    return super.onKeyDown(keyCode, event);
	}
	
	
	
	
	public void switchConten(Fragment fragment){
		getFragmentManager()
		 .beginTransaction()
		 .replace(R.id.main_layout, fragment)
		 .commit();
		
	}
	public void setReturnPhoto(Uri imageUri)
	{
		this.ResultUri = imageUri;
	}
	
	public Bitmap getReturnPhoto()
	{
		try
		{
		ReturnPhoto=BitmapFactory.decodeStream((getContentResolver().openInputStream(ResultUri))); 
		
		}catch (FileNotFoundException e){
			e.printStackTrace();
		}
		return ReturnPhoto;
	}
	
	
	public void isFromAddCorner(boolean iftrue){
		editor = sharedPreferences.edit();
		if(iftrue)
			editor.putBoolean("ifFromAddCorner", true);
		else
			editor.putBoolean("ifFromAddCorner", false);
		editor.commit();
	}
	
	public boolean getIfFromAddCorner(){
		boolean ifFromAddCorner=sharedPreferences.getBoolean("ifFromAddCorner", false);
		return ifFromAddCorner;
	}
	
	//�ж��Ƿ��ǵ�һ�ε�½
	public boolean ifFirstRun()
	{
		if (isFirstRun)
		{
			editor = sharedPreferences.edit();
			editor.putBoolean("isFirstRun", false);
			editor.commit();
			return true;
		} else
		{
			return false;
		}
	}
	
	public void OpenOptionsMenu()
	{
		openOptionsMenu();
	}
	
	@Override
	 public boolean onMenuItemClick(MenuItem item) {
	     switch (item.getItemId()) {
	         case R.id.feedback:
	        	 EmailFragment emailfragment01 = new EmailFragment();
	        	 getFragmentManager()
	    		 .beginTransaction()
	    		 .replace(R.id.main_layout, emailfragment01)
	    		 .commit();
	             return true;
	         case R.id.demand:
	        	 EmailFragment mailfragment02 = new EmailFragment();
	        	 mailfragment02.Setpage(1);
	        	 isFromAddCorner(false);
	        	 getFragmentManager()
	    		 .beginTransaction()
	    		 .replace(R.id.main_layout, mailfragment02)
	    		 .commit();
	        	 return true;
	         case R.id.explain:
	        	 ExplainFragment explainfragment = new ExplainFragment();
	        	 getFragmentManager()
	    		 .beginTransaction()
	    		 .replace(R.id.main_layout, explainfragment)
	    		 .commit();
	        	 return true;
	         default:
	             return false;
	     }
	}
	
	public void showPopup(View v) {
		PopupMenu popup = new PopupMenu(this, v);
		 
	    // This activity implements OnMenuItemClickListener
	     popup.setOnMenuItemClickListener(this);
	     popup.inflate(R.menu.main);
	     popup.show();
	 }
	
	public Context getContext()
	{
		return this;
	}

	@SuppressLint("SdCardPath")
	@Override
	public void onDestroy()
	{
		super.onDestroy();
		File file = new File("file:///sdcard/temp.jpg");
		file.delete();
	}
	   
}
