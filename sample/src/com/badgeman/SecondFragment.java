package com.badgeman;


import java.io.File;

import com.badgeman.R;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.PopupMenu;

@SuppressLint({ "NewApi", "SdCardPath" })
public class SecondFragment extends Fragment {
	public static final int NONE = 0;
	public static final int PHOTOHRAPH = 1;// ����
	public static final int PHOTOZOOM = 2; // ����
	public static final int PHOTORESOULT = 3;// ���

	private static final String IMAGE_FILE_LOCATION = "file:///sdcard/temp.jpg";//temp file
	Uri imageUri = Uri.parse(IMAGE_FILE_LOCATION);// The Uri to store the big bitmap

	public static final String IMAGE_UNSPECIFIED = "image/*";
	ImageButton photo;
	ImageButton albums;

	MainActivity ma;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View root = inflater.inflate(R.layout.secondfragment, container, false);
		photo = (ImageButton) root.findViewById(R.id.imageButton2);
		albums = (ImageButton) root.findViewById(R.id.imageButton3);

		if (getActivity() instanceof MainActivity) {
			ma = (MainActivity) getActivity();
		}
		albums.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(Intent.ACTION_PICK, null);
				intent.setDataAndType(
						MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
						IMAGE_UNSPECIFIED);
				startActivityForResult(intent, PHOTOZOOM);
			}
		});
		photo.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(
						Environment.getExternalStorageDirectory(), "temp.jpg")));
				startActivityForResult(intent, PHOTOHRAPH);
			}
		});
		return root;
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
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == NONE)
			return;
		// ����
		if (requestCode == PHOTOHRAPH) {
			// �����ļ�����·��������ڸ�Ŀ¼��
			File picture = new File(Environment.getExternalStorageDirectory()
					+ "/temp.jpg");
			startPhotoZoom(Uri.fromFile(picture));
		}
		if (data == null)
			return;
		// ��ȡ�������ͼƬ
		if (requestCode == PHOTOZOOM) {
			startPhotoZoom(data.getData());
		}
		// ������
		if (requestCode == PHOTORESOULT) {
			Bundle extras = data.getExtras();
			if (extras != null) {
					AddCornerMarkFragment addfragment = new AddCornerMarkFragment();
					ma.setReturnPhoto(imageUri);
					ma.isFromAddCorner(true);
					ma.switchConten(addfragment);
					
			}
		}
	}
	
	public void showPopup(View v) {
	     PopupMenu popup = new PopupMenu(ma.getContext(), v);
	     MenuInflater inflater = popup.getMenuInflater();
	     inflater.inflate(R.menu.main, popup.getMenu());
	     popup.show();
	 }
	
	public void startPhotoZoom(Uri uri) {
		Intent intent = new Intent("com.android.camera.action.CROP");
		intent.setDataAndType(uri, IMAGE_UNSPECIFIED);
		intent.putExtra("crop", "true");
		// aspectX aspectY �ǿ�ߵı���
		intent.putExtra("aspectX", 1);
		intent.putExtra("aspectY", 1);
		// outputX outputY �ǲü�ͼƬ���
		intent.putExtra("outputX", 300);
		intent.putExtra("outputY", 300);
		intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
		intent.putExtra("return-data", false);
		startActivityForResult(intent, PHOTORESOULT);
	}
}