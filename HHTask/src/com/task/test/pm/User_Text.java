package com.task.test.pm;

import com.sun.jna.Structure;

//文本区域
public class User_Text extends Structure {
	// char *chContent; //显示内容
	// User_PartInfo PartInfo; //分区信息
	// COLORREF BkColor; //背景颜色
	// User_FontSet FontInfo; //字体设置
	// User_MoveSet MoveSet; //动作方式设置

	public String chContent; // 显示内容
	public User_PartInfo partInfo;
	public User_FontSet FontInfo;
	public User_MoveSet MoveSet;

	public User_Text(String chContent, User_PartInfo PartInfo) {
		this.chContent = chContent;
		this.partInfo = PartInfo;
	}

	public String getChContent() {
		return chContent;
	}

	public void setChContent(String chContent) {
		this.chContent = chContent;
	}

	public User_FontSet getFontInfo() {
		return FontInfo;
	}

	public void setFontInfo(User_FontSet fontInfo) {
		FontInfo = fontInfo;
	}

	public User_MoveSet getMoveSet() {
		return MoveSet;
	}

	public void setMoveSet(User_MoveSet moveSet) {
		MoveSet = moveSet;
	}

	public User_PartInfo getPartInfo() {
		return partInfo;
	}

	public void setPartInfo(User_PartInfo partInfo) {
		this.partInfo = partInfo;
	}

}
