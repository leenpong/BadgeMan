package com.badgeMan;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;

import com.badgeMan.R;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Bitmap.Config;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.widget.ImageView;

/**画角标类*/
public class DrawCornerMark {
	
	static String imagefile=null;
	static int TabPosition;//标题导航栏位置
	static int CornerMarkPosition;//角标位置
	static int PictureWidth;//头像大小
	static int PictureX;//头像边距
	static int cornerMarkWidth=0;//角标大小
	static Bitmap bit;
	static ImageView Image;
	static File uri;
	
	//向图片加角标
	static ImageView doCornerMark(Bitmap picture, Resources r,ImageView image,int tabPosition,int cornerMarkPosition){
		 Bitmap bitmap = Bitmap.createBitmap(675, 675, Config.ARGB_8888);    
	     Canvas canvas = new Canvas(bitmap);
	     TabPosition=tabPosition;
	     CornerMarkPosition=cornerMarkPosition;
	     Bitmap cornerMark=null;//角标位图
	     switch(tabPosition){//确定角标的大小
	     	case 0:cornerMarkWidth=240;break;
	     	case 1:cornerMarkWidth=255;break;
	     	case 2:cornerMarkWidth=200;break;
	     	default:cornerMarkWidth=240;break;
	     }
	     if(cornerMarkPosition>=0){
	    	 cornerMark=BitmapFactory.decodeResource(r,StrResources.getInteger(tabPosition,cornerMarkPosition));//打开资源图片
	    	 
	     }
	    	 
        canvas.drawBitmap(BitmapFactory.decodeResource(r,R.drawable.tabs_pattern_diagonal), 0, 0, null);
        canvas.drawBitmap(Bitmap.createScaledBitmap(picture,540+PictureWidth,540+PictureWidth,true), 67-PictureX, 67-PictureX, null);
        canvas.drawBitmap(BitmapFactory.decodeResource(r,R.drawable.circle), 0, 0, null);
        if(cornerMarkPosition>=0){
        	canvas.drawBitmap(Bitmap.createScaledBitmap(cornerMark,cornerMarkWidth,cornerMarkWidth,true), 675-cornerMarkWidth, 0, null);
        }
        
        canvas.save(Canvas.ALL_SAVE_FLAG);//保存画布资源  
        
        canvas.restore();//画布结束
        @SuppressWarnings("deprecation")
		Drawable drawable = new BitmapDrawable(bitmap);//对画布资源进行格式转换
        image.setImageDrawable(drawable); //将画布资源和Image控件绑定
        bit=bitmap;
        Image=image;
        return image;
	}
	
	
	
	
	static ImageView changePicture(Bitmap picture, Resources r,ImageView image,int pictureWidth,int pictureX){
		 Bitmap bitmap = Bitmap.createBitmap(675, 675, Config.ARGB_8888);    
	     Canvas canvas = new Canvas(bitmap);
	     PictureWidth=pictureWidth;
	     PictureX=pictureX;
	     Bitmap cornerMark=null;
	     if(CornerMarkPosition>=0){
	    	 cornerMark=BitmapFactory.decodeResource(r,StrResources.getInteger(TabPosition,CornerMarkPosition));//打开资源图片 
	     }
	    	 
       canvas.drawBitmap(BitmapFactory.decodeResource(r,R.drawable.tabs_pattern_diagonal), 0, 0, null);
       canvas.drawBitmap(Bitmap.createScaledBitmap(picture,540+pictureWidth,540+pictureWidth,true), 67-pictureX, 67-pictureX, null);
       canvas.drawBitmap(BitmapFactory.decodeResource(r,R.drawable.circle), 0, 0, null);
       if(CornerMarkPosition>=0){
       	canvas.drawBitmap(Bitmap.createScaledBitmap(cornerMark,cornerMarkWidth,cornerMarkWidth,true), 675-cornerMarkWidth, 0, null);
       }
       canvas.save(Canvas.ALL_SAVE_FLAG);
       
       canvas.restore();
       @SuppressWarnings("deprecation")
	Drawable drawable = new BitmapDrawable(bitmap);
       image.setImageDrawable(drawable); 
       return image;
	}
	
	
	//保存合成好的图片
	@SuppressLint("SdCardPath")
	static void SaveBitmap(Context context,ImageView image )  
	    {  
			//获取当前日期和时间
	        SimpleDateFormat sDateFormat=new SimpleDateFormat("yyyyMMddhhmmss");       
	        String date= sDateFormat.format(new java.util.Date());
	        //存储路径 
	        File file = new File("/sdcard/徽标达人/");  
	        if(!file.exists())  
	            file.mkdirs();  
	            try { 
	            	//文件名
	            	imagefile=file.getPath() + "/"+date+"BadgeMan.png";
	            	
	                FileOutputStream fileOutputStream = new FileOutputStream(imagefile);
	                ((BitmapDrawable)image.getDrawable()).getBitmap().compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);  
	                fileOutputStream.close();
	              //解决在部分机器缓存更新不及时问题
	                context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_MOUNTED, Uri.parse("file://"+ Environment.getExternalStorageDirectory())));
	                //ContentResolver resolver = context.getContentResolver();
                    //MediaStore.Images.Media.insertImage(resolver, ((BitmapDrawable)image.getDrawable()).getBitmap(), imagefile, imagefile);
	            } catch (Exception e) {  
	                        e.printStackTrace();  
	        }  
	    }  
}
