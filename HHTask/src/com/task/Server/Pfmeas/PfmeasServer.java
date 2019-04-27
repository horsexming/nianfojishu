package com.task.Server.Pfmeas;



import com.task.entity.Pfmeas.Pfmeas;





public interface PfmeasServer {
	 
	Object[] findPfmeas(Pfmeas pfmeas, int parseNo, int pageSize,Integer test);

	void savePfmeas(Pfmeas pfmeas);

	void delPfmeas(Integer delId);

	Pfmeas findPfmeasById(Integer salId);

	void updatePfmeas(Pfmeas Pfmeas);
    /**
     * 通过id获取原文件的文件名
     * @param id
     * @return
     */
	String getFileName(Integer id);
}
