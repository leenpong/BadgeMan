package com.badgeman;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Timer;
import java.util.TimerTask;

import com.badgeman.R;
import com.renren.api.connect.android.Renren;
import com.sina.weibo.sdk.api.share.BaseResponse;
import com.sina.weibo.sdk.api.share.IWeiboDownloadListener;
import com.sina.weibo.sdk.api.share.IWeiboHandler;
import com.sina.weibo.sdk.api.share.IWeiboShareAPI;
import com.sina.weibo.sdk.api.share.WeiboShareSDK;
import com.sina.weibo.sdk.auth.WeiboAuth;
import com.sina.weibo.sdk.auth.sso.SsoHandler;
import com.sina.weibo.sdk.constant.WBConstants;
import com.tencent.connect.auth.QQAuth;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.Toast;
import android.widget.PopupMenu.OnMenuItemClickListener;



/***
 * 主类
 * 主要实现了界面直接的跳转，
 * 全局变量的定义和初始化*/
@SuppressLint("NewApi")
public class MainActivity extends Activity implements OnMenuItemClickListener, IWeiboHandler.Response{
	
	Uri ResultUri;
	/**微信分享接口*/
	public IWXAPI api;
	private InterfaceOfStartFragment BeginFragment;//启动界面
	private InterfaceOfMainFragment secondfragment;//主界面
	private SharedPreferences.Editor editor;
	private static SharedPreferences sharedPreferences;
	private boolean isFirstRun;//判断是否第一次加载参数
	
	/** 新浪微博分享接口 */
	public IWeiboShareAPI  mWeiboShareAPI = null;
	
	/**QQ接口*/
	public static Tencent mTencent;
	public QQAuth mQQAuth;
	
	/**人人接口*/
	public Renren renren;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		/**实例化微信分享接口*/
		api = WXAPIFactory.createWXAPI(getApplicationContext(), "wx6f834871703de655",true);
		
		
		/***实例化QQ接口*/  
        mQQAuth = QQAuth.createInstance("1101739134", this.getApplicationContext());  
		mTencent = Tencent.createInstance("1101739134",this);
		
		/*****实例化人人网接口*/
		 renren = new Renren("b3aedb3816b3418aa05c5c527bed1af3",
				 			"6092ad167ec849af90df8f5343181587", 
				 				"269875", this);
	        
		/**实例化新浪微博分享接口*/
        mWeiboShareAPI = WeiboShareSDK.createWeiboAPI(this, "4168686268");
        
     /** 如果未安装微博客户端，设置下载微博对应的回调*/
        if (!mWeiboShareAPI.isWeiboAppInstalled()) {
            mWeiboShareAPI.registerWeiboDownloadListener(new IWeiboDownloadListener() {
                @Override
                public void onCancel() {
                	Toast.makeText(MainActivity.this, 
                            R.string.weibosdk_demo_cancel_download_weibo, 
                            Toast.LENGTH_SHORT).show();
                }
            });
        }
        
     /** 当 Activity 被重新初始化时（该 Activity 处于后台时，可能会由于内存不足被杀掉了），
        * 需要调用 {@link IWeiboShareAPI#handleWeiboResponse} 来接收微博客户端返回的数据。
        * 执行成功，返回 true，并调用 {@link IWeiboHandler.Response#onResponse}；
        * 失败返回 false，不调用上述回调*/
        if (savedInstanceState != null) {
            mWeiboShareAPI.handleWeiboResponse(getIntent(), this);
        }
        
      /**用于判断是否第一次登陆代码*/
		sharedPreferences = this.getSharedPreferences("share", MODE_PRIVATE);
		isFirstRun = sharedPreferences.getBoolean("isFirstRun", true);
		sharedPreferences.getBoolean("ifFromAddCorner", false);
		
		/**隐藏APP的actionBar（最顶端操作栏）*/
		ActionBar actionBar = getActionBar();
		actionBar.hide();
		
		/**实例化启动页的fragment*/
		BeginFragment = new InterfaceOfStartFragment();
		getFragmentManager().beginTransaction().replace(R.id.main_layout, BeginFragment).commit();
		Handler x = new Handler();
		x.postDelayed(new Begin(), 1000); //延时1秒
		
		
	}
	 
	@Override
	protected void onResume() {
		super.onResume();
		renren.init(this);
	}
	 
	 
	class Begin implements Runnable
	{
		public void run() {
			/**从启动页跳转到主界面*/
			try{
				secondfragment = new InterfaceOfMainFragment();
				switchConten(secondfragment);
			}catch(Exception e){
				System.exit(0);
			}
			
		}
	}	
	
	public boolean onKeyDown(int keyCode, KeyEvent event)  {
		
	    if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) { //锟斤拷锟铰碉拷锟斤拷锟斤拷锟紹ACK锟斤拷同时没锟斤拷锟截革拷
	    	FragmentManager fm = getFragmentManager();
			Fragment fragment =fm.findFragmentById(R.id.main_layout);
			if(fragment instanceof InterfaceOfMainFragment){
				 return super.onKeyDown(keyCode, event);
				
			}
			else{
				secondfragment = new InterfaceOfMainFragment();
				switchConten(secondfragment);
				
			}
	        return true;
	    }

	    return super.onKeyDown(keyCode, event);
	}
	
	
	
	/**fragment控件间进行跳转方法*/
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
		Bitmap ReturnPhoto=null;
		try
		{
			ReturnPhoto=BitmapFactory.decodeStream((getContentResolver().openInputStream(ResultUri))); 
		
		}catch (FileNotFoundException e){
			e.printStackTrace();
		}
		return ReturnPhoto;
	}
	
	
	public void isFromAddCorner(boolean iftrue){//
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
	
	/**判断是否是第一次登陆*/
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
	
	/**主界面更多功能方法*/
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
		File file = new File("file:///sdcard/temp.jpg");//拍照的相片没必要保存，在用完合成后销毁掉
		file.delete();
	}




	 /**
     * 接收微客户端博请求的数据。
     * 当微博客户端唤起当前应用并进行分享时，该方法被调用。
     * 
     * @param baseRequest 微博请求数据对象
     * @see {@link IWeiboShareAPI#handleWeiboRequest}
     */
    @Override
    public void onResponse(BaseResponse baseResp) {
        switch (baseResp.errCode) {
        case WBConstants.ErrorCode.ERR_OK:
            Toast.makeText(this, R.string.weibosdk_demo_toast_share_success, Toast.LENGTH_LONG).show();
            break;
        case WBConstants.ErrorCode.ERR_CANCEL:
            Toast.makeText(this, R.string.weibosdk_demo_toast_share_canceled, Toast.LENGTH_LONG).show();
            break;
        case WBConstants.ErrorCode.ERR_FAIL:
            Toast.makeText(this, 
                    getString(R.string.weibosdk_demo_toast_share_failed) + "Error Message: " + baseResp.errMsg, 
                    Toast.LENGTH_LONG).show();
            break;
        }
    }
	
//    /** 
//     * 菜单、返回键响应 
//     */  
//    @Override  
//    public boolean onKeyDown(int keyCode, KeyEvent event) {  
//        // TODO Auto-generated method stub  
//        if(keyCode == KeyEvent.KEYCODE_BACK)  
//           {    
//               exitBy2Click();      //调用双击退出函数  
//           }  
//        return false;  
//    }  
//    
//    /** 
//     * 双击退出函数 
//     */  
//    private static Boolean isExit = false;  
//      
//    private void exitBy2Click() {  
//        Timer tExit = null;  
//        if (isExit == false) {  
//            isExit = true; // 准备退出  
//            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();  
//            tExit = new Timer();  
//            tExit.schedule(new TimerTask() {  
//                @Override  
//                public void run() {  
//                    isExit = false; // 取消退出  
//                }  
//            }, 2000); // 如果2秒钟内没有按下返回键，则启动定时器取消掉刚才执行的任务  
//      
//        } else {  
//            finish();  
//            System.exit(0);  
//        }  
//    }  
//    
   
}
