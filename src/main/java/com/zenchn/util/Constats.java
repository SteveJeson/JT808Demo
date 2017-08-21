package com.zenchn.util;

import java.util.Map;
import java.util.Vector;

public class Constats {
	
	public final static int SET_ACT_LOG = 3; //上传业务数据
	public static final String IS_UPDATE = "1"; //已提交数据
	public final static int UPDATE_BIKEPILE_STATE = 4; //桩位数据
	public static final String SUCCESS = "SUCCESS";
	public static final String ERROR = "ERROR";
	public static final String 	NOCARD = "会员卡不存在";
	public static final String 	NOBIKE = "车辆不存在";
	//需要升级文件类型版本信息键值对
	public static Map<String, byte[]> fileVersionHashtable;
	
	//租还车时限键值对
	public static Map<String, String[]> timeHashtable;
	
	//设备id 站点id限键值对
	public static Map<String, String> equipmentIdWebsiteIdHashtable;
	
	//需分发数据
	public static Vector<String[]> v = new Vector<String[]>();
	public static Vector<String> v1 = new Vector<String>(); //测试用
	
	public final static int HAND_TOP_SIZE = 1000; //Vector元素个数上限 
	
	public static  String PROT; //接收端端口号
	public static  String FTP_URL; //ftp服务器地址
	public static  String FTP_USER; //ftp服务器用户名
	public static  String FTP_PASSWORD; //ftp服务器密码
	public static  String FTP_FILEPATH; //ftp服务器文件目录
	
	
	//测试用
	public static int flag = 0;
	//测试用
	public static int flag1 = 0;
	
	
}
