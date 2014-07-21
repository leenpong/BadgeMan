package com.badgeMan;

import com.badgeMan.R;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


@SuppressLint("NewApi")
public class EmailFragment extends Fragment {
	Button Break;
	Button OK;
	EditText Name;
	EditText advice;
	EditText WeChat;
	int Page = 0;
	
	InterfaceOfMainFragment secondfragment = new InterfaceOfMainFragment();
	AddCornerMarkFragment addCornerMarkFragment =new AddCornerMarkFragment();
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View temp = inflater.inflate(R.layout.emailfragment,container,false);
		Break = (Button) temp.findViewById(R.id.button1);
		OK = (Button) temp.findViewById(R.id.button2);
		Name = (EditText) temp.findViewById(R.id.editText1);
		advice = (EditText) temp.findViewById(R.id.editText2);
		WeChat = (EditText) temp.findViewById(R.id.editText3);
		if(Page==0){
			TextView title =  (TextView)temp.findViewById(R.id.textView1);
			advice.setHint("请尽可能详尽地描述问题和机型信息！");
		}
		if(Page==1)
		{
			TextView title =  (TextView)temp.findViewById(R.id.textView1);
			title.setText("徽标需求提交");
			TextView forehead =  (TextView)temp.findViewById(R.id.textView5);
			forehead.setText("只要你要,只要我有");
			TextView advise =  (TextView)temp.findViewById(R.id.textView3);
			advise.setText("需求描述");
			advice.setHint("简单描述你期望增加的品牌或符号的名称，英文名或大致形状，我们运营和设计师会跟进！");
		}
		
		advice.setOnClickListener(new OnClickListener() {            
            public void onClick(View v) {
            	advice.setHint(null);
             }
         });
		
		Break.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {

				if (getActivity() == null){
					 return;
				}
				if (getActivity() instanceof MainActivity) {
					
					 MainActivity ma = (MainActivity) getActivity();
					 if(ma.getIfFromAddCorner()){
						 
						 ma.switchConten(addCornerMarkFragment); 
					 }
					 else{
						 ma.switchConten(secondfragment);
					 }
					 
				}
			}
		});

		OK.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				@SuppressWarnings("unused")
				String typeThem=null;
				if(Page==0){
					typeThem="用户反馈\n";
				}
				else{
					typeThem="徽标需求提交\n";
				}
				Intent data = new Intent(Intent.ACTION_SENDTO);
				data.setData(Uri.parse("mailto:BadgeMan@163.com"));
				data.putExtra(Intent.EXTRA_SUBJECT,typeThem+"姓名："+Name.getText().toString()+"\n"+"微信号："+WeChat.getText().toString());
				data.putExtra(Intent.EXTRA_TEXT, advice.getText().toString());
				startActivity(data);
				if (getActivity() == null){
					 return;
				}
				if (getActivity() instanceof MainActivity) {
					 MainActivity ma = (MainActivity) getActivity();
					 ma.switchConten(secondfragment);
				}
			}
		});
		return temp;
	}

	@Override
	public void onResume() {
		super.onResume();

	}

	@Override
	public void onPause() {
		super.onPause();

	}

	@Override
	public void onStop() {
		super.onStop();
		System.out.println("emailfragment onStop");
	}

	public void Setpage(int page)
	{
		Page = page;
	}
}