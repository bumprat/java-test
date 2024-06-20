package com.aostar.trade.controller;

import com.sun.jna.Library;
import com.sun.jna.Native;

public interface CLibrary extends Library{
	//>---------------init return value --------------------
	//初始化成功
	public static int STATE_SUCCESS = 0;
	//配置文件路径错误
	public static int INIT_CONF_DIR_ERROR = 1;
	//配置文件错误
	public static int INIT_CONF_ERROR = 2;
	//----------------init return value --------------------<
	
	//>---------------send return value --------------------
	//发送成功
	public static int SEND_SUCCESS = 0;
	//发送失败
	public static int SEND_FAIL = 1;
	//发送忙
	public static int SEND_BUSY = 2;
	//内存空间不足
	public static int SEND_NO_MEM = 3;
	//----------------send return value --------------------<
	
	String libPath = (System.getProperty("os.name").contains("inux"))?"/home/xunjian/4lib/SEND4LIB_V1.1.1/lib/libnarisend4.so":System.getProperty("user.dir") + "/send.dll";
	
	CLibrary INSTANCE = (CLibrary) Native.loadLibrary(libPath, CLibrary.class);
	
	int isolate_service_init(String app_isolate_path, int path_len);
	
	int isolate_service_send(int ip, short port, byte[] data, int data_len);
	
}
