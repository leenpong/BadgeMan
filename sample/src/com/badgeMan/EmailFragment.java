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
	
	SecondFragment secondfragment = new SecondFragment();
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
			advice.setHint("�뾡�����꾡����������ͻ�����Ϣ��");
		}
		if(Page==1)
		{
			TextView title =  (TextView)temp.findViewById(R.id.textView1);
			title.setText("�ձ������ύ");
			TextView forehead =  (TextView)temp.findViewById(R.id.textView5);
			forehead.setText("ֻҪ��Ҫ,ֻҪ���У�");
			TextView advise =  (TextView)temp.findViewById(R.id.textView3);
			advise.setText("��������");
			advice.setHint("���������������ӵ�Ʒ�ƻ���ŵ����ƣ�Ӣ�����������״��������Ӫ�����ʦ�������");
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
				Intent data = new Intent(Intent.ACTION_SENDTO);
				data.setData(Uri.parse("mailto:BadgeGeed@163.com"));
				data.putExtra(Intent.EXTRA_SUBJECT,Name.getText().toString()+WeChat.getText().toString());
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