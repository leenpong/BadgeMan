package com.badgeMan;

import java.util.zip.Inflater;

import com.badgeMan.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Bitmap.Config;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;


public class ImageAdapter extends BaseAdapter {  
	  
    private Context context;
    
    //ͼƬԴ����   
    private int position=0;
    private Integer[] imageInteger; 
    
      
    public ImageAdapter(Context c,int position) {  
        context = c;//ʵ�ʰ󶨵�Activity 
        this.position=position;
        imageInteger= StrResources.getIntegers(position);//ͼƬ��Դʵ����
    }  

    public int getCount() {  
        return imageInteger.length;  
    }  

    public Object getItem(int position) {  
        return position;  
    }  

    public long getItemId(int position) {  
        return position;  
    }  

    
	
	@SuppressWarnings("unused")
	public View getView(int positions, View convertView, ViewGroup parent) { 
		if(position==0||position==1){
			ImageView imageView;
			Holder holder;    
	        if(null==convertView)    
	        {    
	            holder=new Holder();    
	            convertView=LayoutInflater.from(context).inflate(R.layout.gridviewitem, null); //mContextָ���ǵ��õ�Activtty    
	            holder.imageView=(ImageView)convertView.findViewById(R.id.girdViewImageView);
	            holder.textView=(TextView)convertView.findViewById(R.id.girdViewTextView);
	            convertView.setTag(holder);    
	        }    
	        else    
	        {    
	            holder=(Holder)convertView.getTag();    
	        }    
	        Bitmap bm = BitmapFactory.decodeResource(context.getResources(),imageInteger[positions]);//���ؽǱ���Դ
			holder.imageView.setImageDrawable(new BitmapDrawable(bm));//������ͼ��󶨵�Image�ؼ�
			holder.textView.setText(StrResources.getStringText(position, positions));//��������Դ�󶨵�textView�ؼ�
		}
		else{
			OtherHolder otherHolder = new OtherHolder();
			otherHolder.textView0=(TextView)convertView.findViewById(R.id.gridviewtextView1);
			otherHolder.textView1=(TextView)convertView.findViewById(R.id.gridviewtextView2);
			otherHolder.textView2=(TextView)convertView.findViewById(R.id.gridviewtextView3);
			convertView=LayoutInflater.from(context).inflate(R.layout.gridviewotheritem, null); //mContextָ���ǵ��õ�Activtty
			convertView.setTag(otherHolder); 
		}
		
            return convertView;             
       

    } 
	
	class Holder    
    {    
        public ImageView imageView;
        public TextView textView;
    
    }
	class OtherHolder    
    {    
        public TextView textView0;
        public TextView textView1;
        public TextView textView2;
    
    }
}  
