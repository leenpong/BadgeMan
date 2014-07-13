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


public class DrawCornerMark {
	
	
	
	static String imagefile=null;
	static int TabPosition;//���⵼����λ��
	static int CornerMarkPosition;//�Ǳ�λ��
	static int PictureWidth;//ͷ���С
	static int PictureX;//ͷ��߾�
	static int cornerMarkWidth=0;//�Ǳ��С
	//��ͼƬ�ӽǱ�
	static ImageView doCornerMark(Bitmap picture, Resources r,ImageView image,int tabPosition,int cornerMarkPosition){
		 Bitmap bitmap = Bitmap.createBitmap(675, 675, Config.ARGB_8888);    
	     Canvas canvas = new Canvas(bitmap);
	     TabPosition=tabPosition;
	     CornerMarkPosition=cornerMarkPosition;
	     Bitmap cornerMark=null;//�Ǳ�λͼ
	     switch(tabPosition){//ȷ���Ǳ�Ĵ�С
	     	case 0:cornerMarkWidth=240;break;
	     	case 1:cornerMarkWidth=255;break;
	     	case 2:cornerMarkWidth=200;break;
	     	default:cornerMarkWidth=240;break;
	     }
	     if(cornerMarkPosition>=0){
	    	 cornerMark=BitmapFactory.decodeResource(r,StrResources.getInteger(tabPosition,cornerMarkPosition));//����ԴͼƬ
	    	 
	     }
	    	 
        canvas.drawBitmap(BitmapFactory.decodeResource(r,R.drawable.tabs_pattern_diagonal), 0, 0, null);
        canvas.drawBitmap(Bitmap.createScaledBitmap(picture,540+PictureWidth,540+PictureWidth,true), 67-PictureX, 67-PictureX, null);
        if(cornerMarkPosition>=0){
        	canvas.drawBitmap(Bitmap.createScaledBitmap(cornerMark,cornerMarkWidth,cornerMarkWidth,true), 675-cornerMarkWidth, 0, null);
        }
        
        canvas.save(Canvas.ALL_SAVE_FLAG);//���滭����Դ  
        
        canvas.restore();//��������
        @SuppressWarnings("deprecation")
		Drawable drawable = new BitmapDrawable(bitmap);//�Ի�����Դ���и�ʽת��
        image.setImageDrawable(drawable); //��������Դ��Image�ؼ���
        return image;
	}
	
	
	
	
	static ImageView changePicture(Bitmap picture, Resources r,ImageView image,int pictureWidth,int pictureX){
		 Bitmap bitmap = Bitmap.createBitmap(675, 675, Config.ARGB_8888);    
	     Canvas canvas = new Canvas(bitmap);
	     PictureWidth=pictureWidth;
	     PictureX=pictureX;
	     Bitmap cornerMark=null;
	     if(CornerMarkPosition>=0){
	    	 cornerMark=BitmapFactory.decodeResource(r,StrResources.getInteger(TabPosition,CornerMarkPosition));//����ԴͼƬ
	    	 
	     }
	    	 
       canvas.drawBitmap(BitmapFactory.decodeResource(r,R.drawable.tabs_pattern_diagonal), 0, 0, null);
       canvas.drawBitmap(Bitmap.createScaledBitmap(picture,540+pictureWidth,540+pictureWidth,true), 67-pictureX, 67-pictureX, null);
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
	
	
	
	@SuppressLint("SdCardPath")
	static void SaveBitmap(Context context,ImageView image )  
	    {  
			//��ȡ��ǰ���ں�ʱ��
	        SimpleDateFormat sDateFormat=new SimpleDateFormat("yyyyMMddhhmmss");       
	        String date= sDateFormat.format(new java.util.Date());
	        //�洢·��  
	        File file = new File("/sdcard/�ձ����/");  
	        if(!file.exists())  
	            file.mkdirs();  
	            try { 
	            	//�ļ���
	            	imagefile=file.getPath() + "/"+date+"BadgeMan.png";
	                FileOutputStream fileOutputStream = new FileOutputStream(imagefile);
	                ((BitmapDrawable)image.getDrawable()).getBitmap().compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);  
	                fileOutputStream.close();
	              //����ڲ��ֻ���������²���ʱ����
	                context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_MOUNTED, Uri.parse("file://"+ Environment.getExternalStorageDirectory())));
	                 
	            } catch (Exception e) {  
	                        e.printStackTrace();  
	        }  
	    }  
}
