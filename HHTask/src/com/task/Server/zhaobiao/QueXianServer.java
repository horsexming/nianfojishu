package com.task.Server.zhaobiao;

import com.task.entity.Users;
import com.tast.entity.zhaobiao.QueXian;

public interface QueXianServer {

	Object[] listQueXian(QueXian queXian, Integer parseInt, Integer pageSize);

	void addqueXian(QueXian queXian);

	void deletequeXian(QueXian queXian);

	QueXian ByIdquexian(Integer id);

	void UpdatequeXian(QueXian queXian);

	Object[] listqueXianZong(QueXian queXian, Integer parseInt, Integer pageSize);

	Users ByIdUser(Integer id);

}
