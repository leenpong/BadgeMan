package com.badgeman;



import com.badgeman.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**关联类
 * 添加徽标可选栏的界面展示由该类实现
 * 它作为关联器，和ViewPager控件关联起来
 * 用在AddCornerMarkFragment.java文件里*/
public class ImageAdapter extends BaseAdapter {  
	
	/**代表实际的Acitivity
	 * 也就是MainActivity*/
    private Context context;
    
    /**徽标在ViewPager这个控件的位置参数*/
    private int position=0;
    /**徽标图片数组*/
    private Integer[] imageInteger; 
    
      
    public ImageAdapter(Context c/**真正的acitivity,这里是MainAcitivity*/,
    					int position/**徽标在ViewPager这个控件的真实位置位置*/) { 
    	//实际绑定的Activity 
        context = c;
        this.position=position;
        imageInteger= StrResources.getIntegers(position);//图片资源实例化
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
	public View getView(int positions,
						View convertView, 
						ViewGroup parent) { 
		if(position==0||position==1){
			ImageView imageView;
			Holder holder;    
	        if(null==convertView)    
	        {    
	            holder=new Holder();    
	            convertView=LayoutInflater.from(context).inflate(R.layout.gridviewitem, null); //mContext指的是调用的Activtty      
	            holder.imageView=(ImageView)convertView.findViewById(R.id.girdViewImageView);
	            holder.textView=(TextView)convertView.findViewById(R.id.girdViewTextView);
	            convertView.setTag(holder);    
	        }    
	        else    
	        {    
	            holder=(Holder)convertView.getTag();    
	        }    
	        Bitmap bm = BitmapFactory.decodeResource(context.getResources(),imageInteger[positions]);//加载角标资源
			holder.imageView.setImageDrawable(new BitmapDrawable(bm));//将画布图像绑定到Image控件
			holder.textView.setText(StrResources.getStringText(position, positions));//将文字资源绑定到textView控件
		}
		else{
			OtherHolder otherHolder = new OtherHolder();
			otherHolder.textView0=(TextView)convertView.findViewById(R.id.gridviewtextView1);
			otherHolder.textView1=(TextView)convertView.findViewById(R.id.gridviewtextView2);
			otherHolder.textView2=(TextView)convertView.findViewById(R.id.gridviewtextView3);
			convertView=LayoutInflater.from(context).inflate(R.layout.gridviewotheritem, null); //mContext指的是调用的Activtty
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
