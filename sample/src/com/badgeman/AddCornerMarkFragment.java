package com.badgeman;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.Fragment;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.badgeman.widget.PagerSlidingTabStrip;


/**添加角标界面类*/
@SuppressWarnings("deprecation")
@SuppressLint("NewApi")
public class AddCornerMarkFragment extends Fragment
{
	private PagerSlidingTabStrip tabs;//PagerSlidingTabStrip控件
	private ViewPager pager;//ViewPager控件
	private ContactPagerAdapter adapter;//和ViewPager关联的管理器
	private Button savaButton;//保存按钮
	private Resources r;
	private ImageView promptImageView;//引导图片
	private ImageView image;//图片区域控件
	private Button shareButton;//分享按钮
	private Button beginButton;//首页按钮
	private Bitmap Returnimage;//从裁剪界面返回来的数据
	private static int changePicture=0;//当值为偶数时，表示用户的图片是缩小型，当为奇数时表示全部型
	private InterfaceOfMainFragment secondfragment = new InterfaceOfMainFragment();//主界面fragment
	
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }
     
    @SuppressLint("CutPasteId")
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
		View root = inflater.inflate(R.layout.addcornermarkfragment, container, false);
		if (getActivity() instanceof MainActivity) {
			MainActivity ma = (MainActivity) getActivity();
			Returnimage = ma.getReturnPhoto();	
		}
		tabs = (PagerSlidingTabStrip) root.findViewById(R.id.tabs);
		tabs.setShouldExpand(true);//让PagerSlidingTabStrip上面的tabstrip平分
		//tabs.setTextColor(Color.parseColor("#836FFF"));//tabstrip字体颜色
		tabs.setIndicatorColor(Color.parseColor("#36c8b3"));//给tabstrip设置滑动下划线的颜色
		//tabs.setUnderlineColor(Color.parseColor("#FFFFFF"));
		//tabs.setDividerColor(Color.parseColor("#FFFFFF"));
		pager = (ViewPager) root.findViewById(R.id.pager);
		adapter = new ContactPagerAdapter();
		r = this.getResources();
		pager.setAdapter(adapter);
		//tabs.setIndicatorColor(currentColor);//tabstrip背景色
		image=(ImageView)root.findViewById(R.id.imageView1);
		promptImageView=(ImageView)root.findViewById(R.id.promptImage);
		if (getActivity() instanceof MainActivity) {//判断是否第一次启动
			
			MainActivity ma = (MainActivity) getActivity();
			if(ma.ifFirstRun()){
				promptImageView.setVisibility(View.VISIBLE);
			}
		}  
		tabs.setViewPager(pager);
		savaButton=(Button)root.findViewById(R.id.saveButton);
		savaButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				DrawCornerMark.SaveBitmap(getActivity(),image);
			Toast.makeText(getActivity(), "保存成功,保存在："+"图库/徽标达人", Toast.LENGTH_SHORT).show();
			}
		});
		
		//分享按钮
		shareButton=(Button)root.findViewById(R.id.shareButton);
		shareButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
		
				 Dialog dialog = new ShareDialog(getActivity(), R.style.MyDialog,image);//调用重写的dialog
	                
	                dialog.setContentView(R.layout.share_dialog);//设置它的ContentView
	                Window window = dialog.getWindow();  
	                 window.setWindowAnimations(R.style.MyDialog);  //添加动画 
	                dialog.show();
	                
			}
		});
		
		
		//返回按钮
		beginButton=(Button)root.findViewById(R.id.beginButton);
		beginButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				
				Toast.makeText(getActivity(), "欢迎回到首页", Toast.LENGTH_SHORT).show();
				if (getActivity() == null){
					 return;
				}
				if (getActivity() instanceof MainActivity) {
					changePicture=0;
					 MainActivity ma = (MainActivity) getActivity();
					 ma.switchConten(secondfragment);
				}
				
			}
		});
		
		
		image.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				
				promptImageView.setVisibility(View.GONE);
				changePicture++;
				if(changePicture%2!=0){
					DrawCornerMark.changePicture(Returnimage,r, image, 135,67);
				}
				else{
					DrawCornerMark.changePicture(Returnimage,r, image, 0,0);
				}
				
			}
		});
		
		 DrawCornerMark.doCornerMark(Returnimage,r, image, 0,-1); 
		return root;
       
    }

    @Override
    public void onResume()
    {
        super.onResume();
       
    }
    
    @Override
    public void onPause()
    {
        super.onPause();
        
    }
    
    
    @Override
    public void onStop()
    {
        super.onStop();
       
    }
    
    public class ContactPagerAdapter extends PagerAdapter {

    	private final String[] TITLES = { "激情世界杯", "网络个性","更多"};
    	
		public ContactPagerAdapter() {
			super();
		}

		@Override
		public int getCount() {
			return TITLES.length;
		}

		public CharSequence getPageTitle(int position) {
			return TITLES[position];
		}

		@Override
		public Object instantiateItem(ViewGroup container,  final int position) {
			// looks a little bit messy here
		
			
			WindowManager wm = getActivity().getWindowManager();//获得手机界面的宽
		     int width = wm.getDefaultDisplay().getWidth();
		     if(position<2){
		    	//将它三等份
		    	 GridView gv=new GridView(getActivity());
			        gv.setColumnWidth(width/3);  
			        gv.setNumColumns(GridView.AUTO_FIT);  
			        
			        switch(position){//根据Gridview是那个页面，选择不同的图片资源
			        case 0:gv.setAdapter(new ImageAdapter(getActivity(),0));
			        		break;
			        case 1:gv.setAdapter(new ImageAdapter(getActivity(),1));
		    				break;
			        default:gv.setAdapter(new ImageAdapter(getActivity(),2));
		    				break;
			        }
			        gv.setOnItemClickListener(new Gallery.OnItemClickListener(){
			        	   public void onItemClick(AdapterView<?> parent, View arg1, int Sposition,long arg3) {
			        		
			        	   //arg1.setBackgroundResource(R.drawable.background_tabs_diagonal);//选中的图片变色
			        		   
			        	   for(int i=0;i<parent.getCount();i++){
			        	               @SuppressWarnings("unused")
			        	               View v=parent.getChildAt(i);
			        	               if (Sposition == i) {
			        	            	   //画角标
			        	            	   DrawCornerMark.doCornerMark(Returnimage,r, image, position,Sposition);   
			        	               } 
			        	           }
			        	   
			        	   }});
			        container.addView(gv, 0);  
					return gv;
		     }else{
		    	 
		    	 	//gv.setColumnWidth(width); 
		    	 	View view = LayoutInflater.from(getActivity()).inflate(R.layout.gridviewotheritem, null); //mContext指锟斤拷锟角碉拷锟矫碉拷Activtt
		    	 	TextView gridviewtextView3=(TextView)view.findViewById(R.id.gridviewtextView3);
		    	 	gridviewtextView3.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
		    	 	container.addView(view, 0); 
		    	 	gridviewtextView3.setOnClickListener(new View.OnClickListener() {
		    	 		@Override
		    	 		public void onClick(View v) {
		    	 			EmailFragment mailfragment02 = new EmailFragment();
		    	 			mailfragment02.Setpage(1);
		    	 			getFragmentManager()
		    	 			.beginTransaction()
		    	 			.replace(R.id.main_layout, mailfragment02)
		    	 			.commit();
			        	 
		    	 		}
		    	 	});
		    	 return view;
		     }
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object view) {
			container.removeView((View) view);
		}

		@Override
		public boolean isViewFromObject(View v, Object o) {
			return v == ((View) o);
		}
	} 
}
