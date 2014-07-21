package com.badgeman;


import java.io.File;



import java.net.URL;

import com.badgeman.R;
import com.sina.weibo.sdk.api.ImageObject;
import com.sina.weibo.sdk.api.TextObject;
import com.sina.weibo.sdk.api.WeiboMultiMessage;
import com.sina.weibo.sdk.api.share.IWeiboDownloadListener;
import com.sina.weibo.sdk.api.share.IWeiboShareAPI;
import com.sina.weibo.sdk.api.share.SendMultiMessageToWeiboRequest;
import com.sina.weibo.sdk.api.share.WeiboShareSDK;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.SendMessageToWX;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.tencent.mm.sdk.openapi.WXImageObject;
import com.tencent.mm.sdk.openapi.WXMediaMessage;
import com.tencent.mm.sdk.openapi.WXTextObject;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import android.widget.Toast;
/**主界面类*/
@SuppressLint({ "NewApi", "SdCardPath" })
public class InterfaceOfMainFragment extends Fragment {
	public static final int NONE = 0;
	public static final int PHOTOHRAPH = 1;// 拍照
	public static final int PHOTOZOOM = 2; // 缩放
	public static final int PHOTORESOULT = 3;// 结果
	
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
		

        
		
		/**相册获取图片*/
		albums.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(Intent.ACTION_PICK, null);
				Uri mUri = Uri.parse(GetFilePathThread.buffer.toString());
				intent.setDataAndType(
						MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
						IMAGE_UNSPECIFIED);
				startActivityForResult(intent, PHOTOZOOM);
			}
		});
		
		/**拍照*/
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
		// 拍照
		if (requestCode == PHOTOHRAPH) {
			// 设置文件保存路径这里放在跟目录下
			File picture = new File(Environment.getExternalStorageDirectory()
					+ "/temp.jpg");
			startPhotoZoom(Uri.fromFile(picture));
		}
		if (data == null)
			return;
		// 读取相册缩放图片
		if (requestCode == PHOTOZOOM) {
			startPhotoZoom(data.getData());
		}
		// 处理结果
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
		// aspectX aspectY 是宽高的比例
		intent.putExtra("aspectX", 1);
		intent.putExtra("aspectY", 1);
		// outputX outputY 是裁剪图片宽高
		intent.putExtra("outputX", 300);
		intent.putExtra("outputY", 300);
		intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
		intent.putExtra("return-data", false);
		startActivityForResult(intent, PHOTORESOULT);
	}
	
}