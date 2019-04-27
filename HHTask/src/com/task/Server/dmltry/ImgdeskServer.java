package com.task.Server.dmltry;

import java.util.List;

import com.task.entity.dmltry.Imgdesk;

public interface ImgdeskServer {

	//添加
	public String addimg(Imgdesk im);
	//查询
	public List<Imgdesk>  img_show();
	//删除
	public String imgdelte(int id);
	
}
