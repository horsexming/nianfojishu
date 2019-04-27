package com.task.test.pm;

import java.awt.Color;

import org.w3c.dom.css.RGBColor;

import jxl.format.RGB;

import com.sun.jna.Structure;

/*
 * 锟斤拷锟斤拷锟借定
 */
public class User_PartInfo extends Structure {

	// public static class ByReference extends User_PartInfo implements
	// Structure.ByReference {
	// }
	//
	// public static class ByValue extends User_PartInfo implements
	// Structure.ByValue {
	// }

	// int iX; //窗口的起点X
	// int iY; //窗口的起点Y
	// int iWidth; //窗体的宽度
	// int iHeight; //窗体的高度
	// int iFrameMode; //边框的样式
	// //蓝精灵系列：边框宽度，取值范围：0-3
	// //火凤凰系列：边框样式索引，取值范围：1-99，0无边框
	// // 边框索引值参数“WaterFrame”文件夹，本文件夹与DLL库必// 须在同一级目录
	// COLORREF FrameColor; //边框颜色
	public int iX; // 窗口的起点X
	public int iY; // 窗口的起点Y
	public int iWidth; // 窗体的宽度
	public int iHeight; // 窗体的高度
	public int iFrameMode; // 边框的样式

	// 蓝精灵系列：边框宽度，取值范围：0-3
	// 火凤凰系列：边框样式索引，取值范围：1-99，0无边框
	// 边框索引值参数“WaterFrame”文件夹，本文件夹与DLL库必// 须在同一级目录
	public RGB frameColor; // 边框颜色

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
