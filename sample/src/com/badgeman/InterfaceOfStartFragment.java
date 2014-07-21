package com.badgeman;


import com.badgeman.R;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
/**
 * 启动界面类*/
@SuppressLint("NewApi")
public class InterfaceOfStartFragment extends Fragment
{
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View root = inflater.inflate(R.layout.fristfragment, container, false);
				
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
}