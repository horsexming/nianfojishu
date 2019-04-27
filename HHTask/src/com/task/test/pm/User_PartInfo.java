package com.task.test.pm;

import java.awt.Color;

import org.w3c.dom.css.RGBColor;

import jxl.format.RGB;

import com.sun.jna.Structure;

/*
 * �����趨
 */
public class User_PartInfo extends Structure {

	// public static class ByReference extends User_PartInfo implements
	// Structure.ByReference {
	// }
	//
	// public static class ByValue extends User_PartInfo implements
	// Structure.ByValue {
	// }

	// int iX; //���ڵ����X
	// int iY; //���ڵ����Y
	// int iWidth; //����Ŀ��
	// int iHeight; //����ĸ߶�
	// int iFrameMode; //�߿����ʽ
	// //������ϵ�У��߿��ȣ�ȡֵ��Χ��0-3
	// //����ϵ�У��߿���ʽ������ȡֵ��Χ��1-99��0�ޱ߿�
	// // �߿�����ֵ������WaterFrame���ļ��У����ļ�����DLL���// ����ͬһ��Ŀ¼
	// COLORREF FrameColor; //�߿���ɫ
	public int iX; // ���ڵ����X
	public int iY; // ���ڵ����Y
	public int iWidth; // ����Ŀ��
	public int iHeight; // ����ĸ߶�
	public int iFrameMode; // �߿����ʽ

	// ������ϵ�У��߿��ȣ�ȡֵ��Χ��0-3
	// ����ϵ�У��߿���ʽ������ȡֵ��Χ��1-99��0�ޱ߿�
	// �߿�����ֵ������WaterFrame���ļ��У����ļ�����DLL���// ����ͬһ��Ŀ¼
	public RGB frameColor; // �߿���ɫ

	public User_PartInfo(int iX, int iY, int iWidth, int iHeight, int iFrameMode) {
		this.iX = iX;
		this.iY = iY;
		this.iWidth = iWidth;
		this.iHeight = iHeight;
		this.iFrameMode = iFrameMode;
		// this.frameColor = frameColor;
	}

	public int getiX() {
		return iX;
	}

	public void setiX(int iX) {
		this.iX = iX;
	}

	public int getiY() {
		return iY;
	}

	public void setiY(int iY) {
		this.iY = iY;
	}

	public int getiWidth() {
		return iWidth;
	}

	public void setiWidth(int iWidth) {
		this.iWidth = iWidth;
	}

	public int getiHeight() {
		return iHeight;
	}

	public void setiHeight(int iHeight) {
		this.iHeight = iHeight;
	}

	public int getiFrameMode() {
		return iFrameMode;
	}

	public void setiFrameMode(int iFrameMode) {
		this.iFrameMode = iFrameMode;
	}

}
