package com.badgeMan;
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

import com.astuetz.viewpager.extensions.PagerSlidingTabStrip;




@SuppressWarnings("deprecation")
@SuppressLint("NewApi")
public class AddCornerMarkFragment extends Fragment
{
	private PagerSlidingTabStrip tabs;//PagerSlidingTabStrip�ؼ�
	private ViewPager pager;//ViewPager�ؼ�
	private ContactPagerAdapter adapter;//��ViewPager�����Ĺ�����
	private Button savaButton;//���水ť
	private Resources r;
	private ImageView promptImageView;//����ͼƬ������ҿ�һ�£�
	private ImageView image;//ͼƬ����ؼ�
	private Button shareButton;//����ť
	private Button beginButton;//��ҳ��ť
	private Bitmap Returnimage;//�Ӳü����淵����������
	private static int changePicture=0;//��ֵΪż��ʱ����ʾ�û���ͼƬ����С�ͣ���Ϊ����ʱ��ʾȫ����
	
	private SecondFragment secondfragment = new SecondFragment();//������fragment
	
	
	
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
		
		tabs.setShouldExpand(true);//��PagerSlidingTabStrip�����tabstripƽ��
		//tabs.setTextColor(Color.parseColor("#836FFF"));//tabstrip������ɫ
		tabs.setIndicatorColor(Color.parseColor("#36c8b3"));//��tabstrip���û����»��ߵ���ɫ
		//tabs.setUnderlineColor(Color.parseColor("#FFFFFF"));
		//tabs.setDividerColor(Color.parseColor("#FFFFFF"));
		pager = (ViewPager) root.findViewById(R.id.pager);
		adapter = new ContactPagerAdapter();
		r = this.getResources();
		pager.setAdapter(adapter);
		//tabs.setIndicatorColor(currentColor);//tabstrip����ɫ
		image=(ImageView)root.findViewById(R.id.imageView1);
		promptImageView=(ImageView)root.findViewById(R.id.promptImage);
		if (getActivity() instanceof MainActivity) {//�ж��Ƿ��һ������
			
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
				
				Toast.makeText(getActivity(), "����ɹ�"+DrawCornerMark.imagefile, Toast.LENGTH_SHORT).show();
			}
		});
		
		//����ť
		shareButton=(Button)root.findViewById(R.id.shareButton);
		shareButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
		
				 Dialog dialog = new ShareDialog(getActivity(), R.style.MyDialog,image);//������д��dialog
	                
	                dialog.setContentView(R.layout.share_dialog);//��������ContentView
	                Window window = dialog.getWindow();  
	                 window.setWindowAnimations(R.style.MyDialog);  //��Ӷ��� 
	                dialog.show();
	                
			}
		});
		
		
		//���ذ�ť
		beginButton=(Button)root.findViewById(R.id.beginButton);
		beginButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				
				Toast.makeText(getActivity(), "��ӭ�ص���ҳ", Toast.LENGTH_SHORT).show();
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

    	private final String[] TITLES = { "�������籭", "�������","����"};
    	
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
		
			//����ֻ�����Ŀ�
			WindowManager wm = getActivity().getWindowManager();
		     int width = wm.getDefaultDisplay().getWidth();
		     if(position<2){
		    	//�������ȷ�
		    	 GridView gv=new GridView(getActivity());
			        gv.setColumnWidth(width/3);  
			        gv.setNumColumns(GridView.AUTO_FIT);  
			        
			        
			        switch(position){//����Gridview���Ǹ�ҳ�棬ѡ��ͬ��ͼƬ��Դ
			        case 0:gv.setAdapter(new ImageAdapter(getActivity(),0));
			        		break;
			        case 1:gv.setAdapter(new ImageAdapter(getActivity(),1));
		    				break;
			        default:gv.setAdapter(new ImageAdapter(getActivity(),2));
		    				break;
			        }
			        gv.setOnItemClickListener(new Gallery.OnItemClickListener(){
			        	   public void onItemClick(AdapterView<?> parent, View arg1, int Sposition,long arg3) {
			        		  
			        	   
			        		 //ѡ�е�ͼƬ��ɫ
			            	   //arg1.setBackgroundResource(R.drawable.background_tabs_diagonal);
			        	   for(int i=0;i<parent.getCount();i++){
			        	               @SuppressWarnings("unused")
									View v=parent.getChildAt(i);
			        	               if (Sposition == i) {
			        	            	   //���Ǳ�
			        	            	   DrawCornerMark.doCornerMark(Returnimage,r, image, position,Sposition);   
			        	               } 
			        	           }
			        	   
			        	   }});
			        container.addView(gv, 0);  
					return gv;
		     }else{
		    	 
		    	 //gv.setColumnWidth(width); 
		    	 View view = LayoutInflater.from(getActivity()).inflate(R.layout.gridviewotheritem, null); //mContextָ���ǵ��õ�Activtt
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
