package com.badgeMan;

import com.badgeMan.R;

public class StrResources {
	static	public	 String weixinPackage="com.tencent.mm";//΢�Ű���
	static	public   String QQPackage="com.tencent.mobileqq";//QQ����
	static	public   String QQHomePackage="com.qzone";//QQ�ռ����
	static	public   String renrenPackage="com.renren.mobile.android";//����������
	static	public   String weiboPackage="com.sina.weibo";//����΢������
	//�ļ���
	static public    String weixinActivityName="com.tencent.mm.ui.tools.ShareToTimeLineUI";
	static public    String weixinHomeActivityName="com.tencent.mm.ui.tools.ShareImgUI";
	static public    String QQActivityName="com.tencent.mobileqq.activity.JumpActivity";
	static public    String QQHomeActivityName="com.qzone.ui.operation.QZonePublishMoodActivity";
	static public    String renrenActivityName="com.renren.mobile.android.publisher.InputPublisherActivity";
	static public    String weiboActivityName="com.sina.weibo.EditActivity";
	static public Integer getInteger(int position,int i) {
		
		Integer[][] imageInteger = getIntegerss();
        return   imageInteger[position][i];
    }
	
	
	
static public Integer[] getIntegers(int position) {
		
    	Integer[][] imageInteger = getIntegerss();
        return   imageInteger[position];
    }



 private static Integer[][] getIntegerss() {
	//ͼƬ��Դ
	Integer[][] imageInteger = {{	R.drawable.algeria,R.drawable.argentina,  
    								R.drawable.australia,R.drawable.belgium,  
    								R.drawable.bosniaanderzegovina,R.drawable.brazil,
    								R.drawable.cameroon,R.drawable.chile,
    								R.drawable.colombia,R.drawable.costarica,
    								R.drawable.cotedivoire,R.drawable.croatia,
    								R.drawable.ecuador,R.drawable.england,
    								R.drawable.france,R.drawable.germany,
    								R.drawable.ghana,R.drawable.greece,  
    								R.drawable.honduras,R.drawable.iran,  
    								R.drawable.italy,R.drawable.japan,
    								R.drawable.koreasouth,R.drawable.mexico,
    								R.drawable.nigeria,R.drawable.netherlands,
    								R.drawable.portugal,R.drawable.russia,
    								R.drawable.spain,R.drawable.switzerland,
    								R.drawable.unitedstates,R.drawable.uruguay,
    								R.drawable.china,R.drawable.worldcup
    								},
    								{	R.drawable.zero,R.drawable.one,  
        								R.drawable.tow,R.drawable.three,  
        								R.drawable.four,R.drawable.five,
        								R.drawable.six,R.drawable.seven,
        								R.drawable.eight,R.drawable.nine
        								
        								},{
        									
        								}
    }; 
    return   imageInteger;
}
 
 
 
 
 
 	static public String getStringText(int position,int i) {
		
	 	String[][] imageStringTest = getStringTextss();
	 	return   imageStringTest[position][i];
 	}
	
	
	
 	static public String[] getStringTexts(int position) {
		
 		String[][] imageStringTests = getStringTextss();
 		return   imageStringTests[position];
 	}


	private static String[][] getStringTextss() {
		//������Դ
		String[][] imageStringTestss = {{	"����������","����͢","�Ĵ�����","����ʱ","��    ��","��   ��","����¡","��   ��",
										"���ױ���","��˹�����","���ص���","���޵���","��϶��","Ӣ����","��   ��","��   ��",
										"��   ��","ϣ   ��","�鶼��˹","��   ��","�����","��   ��","��   ��","ī����",
										"��������","��   ��","������","����˹","������","��   ʿ","��   ��","������",
										"��   ��","������"
 								},{
 									"0","1","2","3","4","5","6","7","8","9"
 								},{
 								
 								}
	};
	return   imageStringTestss;
}
 	
}
