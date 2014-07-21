package com.badgeMan;

import com.badgeMan.R;

public class StrResources {
	static	public	 String weixinPackage="com.tencent.mm";//微信包名
	static	public   String QQPackage="com.tencent.mobileqq";//QQ包名
	static	public   String QQHomePackage="com.qzone";//QQ空间包名
	static	public   String renrenPackage="com.renren.mobile.android";//人人网包名
	static	public   String weiboPackage="com.sina.weibo";//新浪微博包名
	//文件名
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
	//图片资源
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
		//文字资源
		String[][] imageStringTestss = {{	"阿尔及利亚","阿根廷","澳大利亚","比利时","波    黑","巴   西","喀麦隆","智   利",
			"哥伦比亚","哥斯达黎加","科特迪瓦","克罗地亚","厄瓜多尔","英格兰","法   国","德   国",
			"加   纳","希   腊","洪都拉斯","伊   朗","意大利","日   本","韩   国","墨西哥",
			"尼日利亚","荷   兰","葡萄牙","俄罗斯","西班牙","瑞   士","美   国","乌拉圭",
			"中   国","大力神杯"
		},{
			"0","1","2","3","4","5","6","7","8","9"
		},{
		
		}
	};
	return   imageStringTestss;
}
 	
}
