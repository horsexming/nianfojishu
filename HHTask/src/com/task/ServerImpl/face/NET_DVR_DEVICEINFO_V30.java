package com.task.ServerImpl.face;

import com.sun.jna.Structure;

/**
 * 摄像头设备信息
 * @author wcy
 *
 */
public class NET_DVR_DEVICEINFO_V30 extends Structure
{
	public  byte[] sSerialNumber = new byte[48];  //序列号
	public  byte byAlarmInPortNum;		        //报警输入个数
	public  byte byAlarmOutPortNum;		        //报警输出个数
	public  byte byDiskNum;				    //硬盘个数
	public  byte byDVRType;				    //设备类型, 1:DVR 2:ATM DVR 3:DVS ......
	public  byte byChanNum;				    //模拟通道个数
	public  byte byStartChan;			        //起始通道号,例如DVS-1,DVR - 1
	public  byte byAudioChanNum;                //语音通道数
	public  byte byIPChanNum;					//最大数字通道个数
	public  byte[] byRes1 = new byte[24];					//保留
}
