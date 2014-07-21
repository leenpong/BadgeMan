package com.badgeman;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;

import com.badegman.wxapi.weixinUtil;
import com.badgeman.R;
import com.sina.weibo.sdk.api.ImageObject;
import com.sina.weibo.sdk.api.TextObject;
import com.sina.weibo.sdk.api.WeiboMessage;
import com.sina.weibo.sdk.api.WeiboMultiMessage;
import com.sina.weibo.sdk.api.share.IWeiboDownloadListener;
import com.sina.weibo.sdk.api.share.SendMultiMessageToWeiboRequest;
import com.tencent.connect.share.QQShare;
import com.tencent.mm.sdk.openapi.SendMessageToWX;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.tencent.mm.sdk.openapi.WXImageObject;
import com.tencent.mm.sdk.openapi.WXMediaMessage;

import android.app.Dialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

/**分享类
 * 该类主要实现了弹出的分享窗口的功能*/
public class ShareDialog extends Dialog {
	
    Context context;
    private ImageView image;//图片区域控件
    MainActivity ma;
    public ShareDialog(Context context,ImageView image) {
        super(context);
       
        this.context = context;
        this.image=image;
        
    }
    public ShareDialog(Context context, int theme,ImageView image){
        super(context, theme);
        this.context = context;
        this.image=image;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	if (context instanceof MainActivity) {
			ma = (MainActivity) context;
		}
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.share_dialog);
        ImageButton weibo_imageButton=(ImageButton)findViewById(R.id.weibo_imageButton);
        ImageButton weixin_imageButton=(ImageButton)findViewById(R.id.weixin_imageButton);
        ImageButton wenxinhome_imageButton=(ImageButton)findViewById(R.id.wenxinhome_imageButton);
        ImageButton QQ_imageButton=(ImageButton)findViewById(R.id.QQ_imageButton);
        ImageButton QQhome_imageButton=(ImageButton)findViewById(R.id.QQhome_imageButton);
        ImageButton renren_imageButton=(ImageButton)findViewById(R.id.renren_imageButton);
        Button close_shareButton=(Button)findViewById(R.id.close_shareButton);
        
      //分享到微博的按钮
        weibo_imageButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				 
				try{
					//调用第三方应用实现微博分享
					//doShare(StrResources.weiboPackage,StrResources.weiboActivityName,false);
					
					//新浪微博注册
					ma.mWeiboShareAPI.registerApp();
					// 1. 初始化微博的分享消息
			        DrawCornerMark.SaveBitmap(context,image);
			        WeiboMultiMessage weiboMessage = new WeiboMultiMessage();
			        ImageObject imageObject = new ImageObject();
			        BitmapDrawable bitmapDrawable = (BitmapDrawable) DrawCornerMark.Image.getDrawable();
			        imageObject.setImageObject(bitmapDrawable.getBitmap());
			        weiboMessage.mediaObject=imageObject;
			        
			     // 2. 初始化从第三方到微博的消息请求
			        SendMultiMessageToWeiboRequest request = new SendMultiMessageToWeiboRequest();
			     // 用transaction唯一标识一个请求
			        request.transaction = String.valueOf(System.currentTimeMillis());
			        request.multiMessage = weiboMessage;
			        
			     // 3. 发送请求消息到微博，唤起微博分享界面
			        ma.mWeiboShareAPI.sendRequest(request);
			        
			        
				}catch(Exception e){
					Toast.makeText(context, "对不起，您没有安装新浪微博相应的软件，不支持该分享功能",Toast.LENGTH_LONG).show();
				}
			}
		});
        
        //关闭分享窗口按钮
        close_shareButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				
				try{
					dismiss();
				}catch(Exception e){
					Toast.makeText(context, "",Toast.LENGTH_LONG).show();
				}
			}
		});
        
        //分享到QQ空间按钮
        QQhome_imageButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				
				try{
					//调用第三方应用实现QQ空间分享
					//doShare(StrResources.QQHomePackage,StrResources.QQHomeActivityName,false);
					
					//1初始化分享消息
					DrawCornerMark.SaveBitmap(context,image);
					Bundle params = new Bundle();
					params.putString(QQShare.SHARE_TO_QQ_IMAGE_LOCAL_URL,DrawCornerMark.imagefile);
					params.putString(QQShare.SHARE_TO_QQ_APP_NAME, "yingyong");
					params.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, QQShare.SHARE_TO_QQ_TYPE_IMAGE);
					params.putInt(QQShare.SHARE_TO_QQ_EXT_INT, QQShare.SHARE_TO_QQ_FLAG_QZONE_AUTO_OPEN);
					//调用分享接口
					ma.mTencent.shareToQQ(ma, params, new BaseUIListener(context)); 
				}catch(Exception e){
					Toast.makeText(context, "对不起，您没有安装QZone相应的软件，不支持该分享功能",Toast.LENGTH_LONG).show();
				}
			}
		});
        
        //分享到微信按钮
        weixin_imageButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				ShareFromWeixin(SendMessageToWX.Req.WXSceneSession);
				
			}
		});
        
        //分享到QQ按钮
        QQ_imageButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				
				try{
					DrawCornerMark.SaveBitmap(context,image);
					//doShare(StrResources.QQPackage,StrResources.QQActivityName,false);
					Bundle params = new Bundle();
					params.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, QQShare.SHARE_TO_QQ_TYPE_DEFAULT);
				    params.putString(QQShare.SHARE_TO_QQ_IMAGE_LOCAL_URL,DrawCornerMark.imagefile);
				    params.putString(QQShare.SHARE_TO_QQ_APP_NAME, "yingyong");
				    params.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, QQShare.SHARE_TO_QQ_TYPE_IMAGE);
				   // params.putInt(QQShare.SHARE_TO_QQ_EXT_INT, QQShare.SHARE_TO);	
				    ma.mTencent.shareToQQ(ma, params, new BaseUIListener(context)); 
					
				}catch(Exception e){
					Toast.makeText(context, "对不起，您没有安装QQ相应的软件，不支持该分享功能",Toast.LENGTH_LONG).show();
				}
			}
		});
        
        //分享到朋友圈按钮
        wenxinhome_imageButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				ShareFromWeixin(SendMessageToWX.Req.WXSceneTimeline);
				
			}
		});
        
        //分享到人人网按钮
        renren_imageButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				
				try{
					//doShare(StrResources.renrenPackage,StrResources.renrenActivityName,false);
					
					// 读取assets文件夹下的图片，保存在手机中
					String fileName = "renren.png";
					// 获取文件后缀，构造本地文件名
					int index = fileName.lastIndexOf('.');
					// 文件保存在/sdcard目录下，以renren_前缀加系统毫秒数构造文件名
					final String realName = "renren_" + System.currentTimeMillis()
							+ fileName.substring(index, fileName.length());
					try {
						InputStream is = ma.getResources().getAssets().open(fileName);
						BufferedOutputStream bos = new BufferedOutputStream(
						ma.openFileOutput(realName, Context.MODE_PRIVATE));
						int length = 0;
						byte[] buffer = new byte[1024];
						while ((length = is.read(buffer)) != -1) {
							bos.write(buffer, 0, length);
						}
						is.close();
						bos.close();
					} catch (MalformedURLException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
					String filePath = ma.getFilesDir().getAbsolutePath() + "/"
							+ realName;
					
					// 以上准备好了File参数
					// 下面调用SDK接口
					ma.renren.publishPhoto(ma, new File(filePath), "传入的默认参数");
					
				}catch(Exception e){
					Toast.makeText(context, "对不起，您没有安装人人网相应的软件，不支持该分享功能",Toast.LENGTH_LONG).show();
				}
				
			}
		});
        
    }

    //微信分享实现方法
    protected void ShareFromWeixin(int scene){
    	
		ma.api.registerApp("wx6f834871703de655");
    	try{
			DrawCornerMark.SaveBitmap(context,image);
			String path = DrawCornerMark.imagefile;
			File file = new File(path);
			if (!file.exists()) {
				String tip = context.getString(R.string.send_img_file_not_exist);
				Toast.makeText(context, tip + " path = " + path, Toast.LENGTH_LONG).show();
				
			}
			WXImageObject imgObj = new WXImageObject();
			imgObj.setImagePath(path);
			WXMediaMessage msg = new WXMediaMessage();
			msg.mediaObject = imgObj;
			Bitmap bmp = BitmapFactory.decodeFile(path);
			Bitmap thumbBmp = Bitmap.createScaledBitmap(bmp, 150, 150, true);
			bmp.recycle();
			msg.thumbData = weixinUtil.bmpToByteArray(thumbBmp, true);
			SendMessageToWX.Req req = new SendMessageToWX.Req();
			req.transaction =  "img" + System.currentTimeMillis();
			req.message = msg;
			req.scene =scene;
			ma.api.sendReq(req);
			}catch(Exception e){
				Toast.makeText(context, "分享失败",Toast.LENGTH_LONG).show();
			}	
    }
    
    
//    //调用第三方应用的分享
//    protected void doShare(String packageName,String activityName,boolean ifwenxin){
//    	 Intent intent=new Intent(Intent.ACTION_SEND);
//	      intent.putExtra(Intent.EXTRA_SUBJECT,"分享");
//	      DrawCornerMark.SaveBitmap(context,image);
//	      File f = new File(DrawCornerMark.imagefile);
//	      if (DrawCornerMark.imagefile == null || DrawCornerMark.imagefile.equals("")) {    
//	    	   intent.setType("text/plain"); // 纯文本     
//	    	  } else {    
//	    	       
//	    	   if (f != null && f.exists() && f.isFile()) {    
//	    	    intent.setType("image/*");  
//	    	    Uri u = Uri.fromFile(f);
//	    	    intent.putExtra("Kdescription", "#徽标达人上线啦#我的球队我的徽标，带着态度看世界杯！更有“难受死各种强迫症们”的恶作剧角标！各大andoird应用市场搜索：徽（hui）标达人，ios版开发中...");
//	    	    if(!ifwenxin){
//	    	    	intent.putExtra(Intent.EXTRA_TEXT, "#徽标达人上线啦#我的球队我的徽标，带着态度看世界杯！更有“难受死各种强迫症们”的恶作剧角标！各大andoird应用市场搜索：徽（hui）标达人，ios版开发中...");
//	    	    }
//	    	    
//	    	    intent.putExtra(Intent.EXTRA_STREAM, u);
//	    	    
//	    	    
//	    	    
//	    	   }    
//	    	  }    
//	      ComponentName componetName = new ComponentName(
//	    		  packageName, activityName); 
//	      intent.setComponent(componetName);
//	      context.startActivity(intent);
//	     
//	      if (context == null){
//	    	  
//				 return;
//			}
//    }
    
    
//    private String Transaction(final String type) {
//		return (type == null) ? String.valueOf(System.currentTimeMillis()) : type + System.currentTimeMillis();
//	}
    
    
    
}
