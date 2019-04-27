package com.task.util;


import org.eclipse.paho.client.mqttv3.MqttException;

import cn.usr.UsrCloudMqttClientAdapter;
import cn.usr.Interface.UsrCloudMqttCallback;



/**
 * Created by shizhiyuan on 2017/7/21.
 */

public class UsrCloudClient extends UsrCloudMqttClientAdapter{

	/**
	 * 初始客户端,建立连接
	 */
    @Override
    public void Connect(String userName, String passWord) throws MqttException {
        super.Connect(userName, passWord);
    }


    /**
	 * 结束连接
	 */
    @Override
    public boolean DisConnectUnCheck() throws MqttException {
        return super.DisConnectUnCheck();
    }
    /**
	 * 开始监听次编号的设备
	 */
    @Override
    public void SubscribeForDevId(String devId) throws MqttException {
        super.SubscribeForDevId(devId);
        System.out.println("开始监听此编号数据SubscribeForDevId:"+devId);
    }

    @Override
    public void SubscribeForUsername() throws MqttException {
        super.SubscribeForUsername();
    }

    /**
	 * 结束监听次编号的设备
	 */
    @Override
    public void DisSubscribeforDevId(String devId) throws MqttException {
        super.DisSubscribeforDevId(devId);
    }

    @Override
    public void DisSubscribeforuName() throws MqttException {
        super.DisSubscribeforuName();
    }

    @Override
    public void setUsrCloudMqttCallback(UsrCloudMqttCallback CloudMqttCallback) {
        super.setUsrCloudMqttCallback(CloudMqttCallback);
    }

    @Override
    public void publishForDevId(String devId, byte[] data) throws MqttException {
        super.publishForDevId(devId, data);
        System.out.println("发送设备：" +devId + "发送数据：" + Util.bytes2hex01(data));
    }

    @Override
    public void publishForuName(byte[] data) throws MqttException {
        super.publishForuName(data);
        System.out.println("发送给全部设备：" + Util.bytes2hex01(data));
    }
}
