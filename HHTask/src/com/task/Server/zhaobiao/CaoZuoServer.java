package com.task.Server.zhaobiao;

import java.util.List;

import com.task.entity.Users;
import com.tast.entity.zhaobiao.Baofei;
import com.tast.entity.zhaobiao.Zh_CaozuoDengji;
import com.tast.entity.zhaobiao.Zh_CaozuoEmp;
import com.tast.entity.zhaobiao.Zh_caozuo;
import com.tast.entity.zhaobiao.Zh_shebei;

public interface CaoZuoServer {

	Object[] listCaoZuo(Zh_shebei zhShebei, Integer parseInt, Integer pageSize);

	List listmachinegongwei();

	List listshebeibianhao(String pageStatus);

	List listshebeibianhao(String pageStatus, String pageName);

	List listshebeigongxu(String pageStatus, String pageName);

	void addshebei(Zh_shebei zhShebei);

	Zh_shebei ByIdZhshebei(Integer id);

	List listDengji(Integer id);

	void addzhCaozuoDengji(Zh_CaozuoDengji zhCaozuoDengji);

	void updateshebei(Zh_shebei zhShebei);

	List listzhCaozuoEmp(Integer id);

	List listEmp(String deptname);

	List listDengjiByshebeiId(Integer id);

	void addzhCaozuoEmp(Zh_CaozuoEmp zhCaozuoEmp);

	void deletezhShebei(Zh_shebei zhShebei);

	void deletezhCaozuoDengji(Zh_CaozuoDengji zhCaozuoDengji);

	void deletezhCaozuoEmp(Zh_CaozuoEmp zhCaozuoEmp);

	Zh_CaozuoDengji byIdEmp(String dengjiId);

	void addzhCao(Zh_caozuo newzhCaozuo);

	Object[] listCaoZuoHuiZong(Zh_caozuo zhCaozuo, Integer parseInt, Integer pageSize);

	void adddengji(String[] dengji, Integer integer);

	Object[] listUsers(Users userss, Integer parseInt, Integer pageSize);

	Users ByIdUsers(Integer id);

	void updateUsers(Users userss);

	Object[] listBaofei(Baofei baofei, Integer parseInt, Integer pageSize);

	void addbaofei(Baofei baofei);

	Baofei ByIdBaofei(Integer id);

	void updatebaofei(Baofei baofei);

	void deleteBaofei(Baofei baofei);

	List listBaofeiname(String yuanyin);

}
