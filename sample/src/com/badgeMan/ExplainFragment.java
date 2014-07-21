package com.badgeMan;

import com.badgeMan.R;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

@SuppressLint("NewApi")
public class ExplainFragment extends Fragment {
	Button Break;
	TextView team01;
	TextView team02;
	TextView team03;
	TextView team04;
	TextView team05;
	TextView team06;
	InterfaceOfMainFragment secondfragment = new InterfaceOfMainFragment();
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View temp = inflater.inflate(R.layout.explainfragment,container,false);
		team01=  (TextView) temp.findViewById(R.id.textViewteam01);
		team02=  (TextView) temp.findViewById(R.id.textViewteam02);
		team03=  (TextView) temp.findViewById(R.id.textViewteam03);
		team04=  (TextView) temp.findViewById(R.id.textViewteam04);
		team05=  (TextView) temp.findViewById(R.id.textViewteam05);
		team06=  (TextView) temp.findViewById(R.id.textViewteam06);
		Break = (Button) temp.findViewById(R.id.button1);
		team01.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
		team02.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
		team03.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
		team04.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
		team05.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
		team06.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
		
		Break.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {

				if (getActivity() == null){
					 return;
				}
				if (getActivity() instanceof MainActivity) {
					 MainActivity ma = (MainActivity) getActivity();
					 ma.switchConten(secondfragment);
				}
			}
		});
		team01.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				String uri = "http://weibo.com/919880911";
				Intent i = new Intent(Intent.ACTION_VIEW);
				i.setData(Uri.parse(uri));
				startActivity(i);
			}
		});
		team02.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				String uri = "http://weibo.com/sukikilam";
				Intent i = new Intent(Intent.ACTION_VIEW);
				i.setData(Uri.parse(uri));
				startActivity(i);
			}
		});

		team03.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				String uri = "http://weibo.com/aztamic";
				Intent i = new Intent(Intent.ACTION_VIEW);
				i.setData(Uri.parse(uri));
				startActivity(i);
			}
		});

		team04.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				String uri = "http://weibo.com/u/1872065320";
				Intent i = new Intent(Intent.ACTION_VIEW);
				i.setData(Uri.parse(uri));
				startActivity(i);
			}
		});

		team05.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				String uri = "http://t.cn/Rvmns7F";
				Intent i = new Intent(Intent.ACTION_VIEW);
				i.setData(Uri.parse(uri));
				startActivity(i);
			}
		});
		
		team06.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				String uri = "http://m.weibo.cn/u/2265578680?vt=4&rl=2&luicode=20000060";
				Intent i = new Intent(Intent.ACTION_VIEW);
				i.setData(Uri.parse(uri));
				startActivity(i);
			}
		});

		
		return temp;
	}
}