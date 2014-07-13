package com.badgeMan;

import java.io.File;

import com.badgeMan.R;

import android.app.Dialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class ShareDialog extends Dialog {
	
    Context context;
    private ImageView image;//ͼƬ����ؼ�
   
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
        
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.share_dialog);
        ImageButton weibo_imageButton=(ImageButton)findViewById(R.id.weibo_imageButton);
        ImageButton weixin_imageButton=(ImageButton)findViewById(R.id.weixin_imageButton);
        ImageButton wenxinhome_imageButton=(ImageButton)findViewById(R.id.wenxinhome_imageButton);
        ImageButton QQ_imageButton=(ImageButton)findViewById(R.id.QQ_imageButton);
        ImageButton QQhome_imageButton=(ImageButton)findViewById(R.id.QQhome_imageButton);
        ImageButton renren_imageButton=(ImageButton)findViewById(R.id.renren_imageButton);
        Button close_shareButton=(Button)findViewById(R.id.close_shareButton);
        
        //����΢���İ�ť
        weibo_imageButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				 
				try{
					doShare(StrResources.weiboPackage,StrResources.weiboActivityName,false);
				}catch(Exception e){
					Toast.makeText(context, "�Բ�����δ��װ΢�����,��֧�ָ÷�����",Toast.LENGTH_LONG).show();
				}
			}
		});
        
        close_shareButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				
				try{
					dismiss();
				}catch(Exception e){
					Toast.makeText(context, "�Բ��𣬹ر�ʧ��",Toast.LENGTH_LONG).show();
				}
			}
		});
        
        QQhome_imageButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				
				try{
					doShare(StrResources.QQHomePackage,StrResources.QQHomeActivityName,false);
				}catch(Exception e){
					Toast.makeText(context, "�Բ�����δ��װQZone���,��֧�ָ÷�����",Toast.LENGTH_LONG).show();
				}
			}
		});
        
        weixin_imageButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				
				try{
					doShare(StrResources.weixinPackage,StrResources.weixinHomeActivityName,true);
				}catch(Exception e){
					Toast.makeText(context, "�Բ�����δ��װ΢�����,��֧�ָ÷�����",Toast.LENGTH_LONG).show();
				}
			}
		});
        QQ_imageButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				
				try{
					doShare(StrResources.QQPackage,StrResources.QQActivityName,false);
				}catch(Exception e){
					Toast.makeText(context, "�Բ�����δ��װQQ���,��֧�ָ÷�����",Toast.LENGTH_LONG).show();
				}
			}
		});
        wenxinhome_imageButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				
				try{
					doShare(StrResources.weixinPackage,StrResources.weixinActivityName,false);
				}catch(Exception e){
					Toast.makeText(context, "�Բ�����δ��װ΢�����,��֧�ָ÷�����",Toast.LENGTH_LONG).show();
				}
			}
		});
        renren_imageButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				
				try{
					doShare(StrResources.renrenPackage,StrResources.renrenActivityName,false);
				}catch(Exception e){
					Toast.makeText(context, "�Բ�����δδ��װ������,��֧�ָ÷�����",Toast.LENGTH_LONG).show();
				}
				
			}
		});
        
    }

    
    
    protected void doShare(String packageName,String activityName,boolean ifwenxin){
    	 Intent intent=new Intent(Intent.ACTION_SEND);
	      intent.putExtra(Intent.EXTRA_SUBJECT,"����");
	      DrawCornerMark.SaveBitmap(context,image);
	      File f = new File(DrawCornerMark.imagefile);
	      if (DrawCornerMark.imagefile == null || DrawCornerMark.imagefile.equals("")) {    
	    	   intent.setType("text/plain"); // ���ı�    
	    	  } else {    
	    	       
	    	   if (f != null && f.exists() && f.isFile()) {    
	    	    intent.setType("image/*");  
	    	    Uri u = Uri.fromFile(f);
	    	    intent.putExtra("Kdescription", "#�ձ����������#�ҵ�����ҵĻձ꣬����̬�ȿ����籭�����С�����������ǿ��֢�ǡ��Ķ�����Ǳ꣡����andoirdӦ���г��������գ�hui������ˣ�ios�濪����...");
	    	    if(!ifwenxin){
	    	    	intent.putExtra(Intent.EXTRA_TEXT, "#�ձ����������#�ҵ�����ҵĻձ꣬����̬�ȿ����籭�����С�����������ǿ��֢�ǡ��Ķ�����Ǳ꣡����andoirdӦ���г��������գ�hui������ˣ�ios�濪����...");
	    	    }
	    	    
	    	    intent.putExtra(Intent.EXTRA_STREAM, u);
	    	    
	    	    
	    	    
	    	   }    
	    	  }    
	      ComponentName componetName = new ComponentName(
	    		  packageName, activityName); 
	      intent.setComponent(componetName);
	      context.startActivity(intent);
	     
	      if (context == null){
	    	  
				 return;
			}
    }
}
