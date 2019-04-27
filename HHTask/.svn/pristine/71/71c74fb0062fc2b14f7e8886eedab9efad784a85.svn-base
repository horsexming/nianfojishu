package com.task.ServerImpl.pmi;

import com.task.Dao.TotalDao;
import com.task.Server.pmi.PmiManagementServer;
import com.task.Server.sys.CircuitRunServer;
import com.task.entity.Machine;
import com.task.entity.pmi.PmiManagement;
import com.task.entity.sop.Procard;
import com.task.entity.sop.ProcessInfor;
import com.task.util.Util;

import java.util.List;

public class PmiManagementServerImpl implements PmiManagementServer {
    public CircuitRunServer getCircuitRunServer() {
        return circuitRunServer;
    }

    public void setCircuitRunServer(CircuitRunServer circuitRunServer) {
        this.circuitRunServer = circuitRunServer;
    }

    private TotalDao totalDao;
    private CircuitRunServer circuitRunServer;

    public TotalDao getTotalDao() {
        return totalDao;
    }

    public void setTotalDao(TotalDao totalDao) {
        this.totalDao = totalDao;
    }

    /*
     * 查询所有的pmi(non-Javadoc)
     * @see com.task.Server.pmi.PmiManagementServer#findPmi(com.task.entity.pmi.PmiManagement, int, int)
     */
    @Override
    public Object[] findPmi(PmiManagement pmiManagement, int parseInt,
                            int pageSize, Machine machine) {
        if (pmiManagement == null) {
            pmiManagement = new PmiManagement();

        }
        String hql = totalDao.criteriaQueries(pmiManagement, null);
        if (pmiManagement != null) {
            if (pmiManagement.getPmi_name() != null && !"".equals(pmiManagement.getPmi_name())) {
                hql += " and pmi_name like '%" + pmiManagement.getPmi_name() + "%'";
            }
            if (pmiManagement.getPmi_ip() != null && !"".equals(pmiManagement.getPmi_ip())) {
                hql += " and pmi_ip like '%" + pmiManagement.getPmi_ip() + "%'";
            }
        }
        if (machine != null) {
            String sonHql = "";
            if (machine.getWorkPosition() != null && !machine.getWorkPosition().equals("")) {
                sonHql += " and workPosition like'%" + machine.getWorkPosition() + "%'";
            }
            if (machine.getNo() != null && !machine.getNo().equals("")) {
                sonHql += " and no like'%" + machine.getNo() + "%'";
            }
            if (!sonHql.equals("")) {
                hql += " and id in (select pmiManagements.id from Machine where 1=1 " + sonHql + ")";
            }
        }
        hql += " order by status desc,pmi_name asc";
        List list = totalDao.findAllByPage(hql, parseInt, pageSize);
        int count = totalDao.getCount(hql);
        Object[] o = {list, count};
        return o;
    }

    /*
     * 添加pmi(non-Javadoc)
     * @see com.task.Server.pmi.PmiManagementServer#addPmi(com.task.entity.pmi.PmiManagement)
     */
    @Override
    public boolean addPmi(PmiManagement pmiManagement) {
        String createdate = Util.getDateTime("yyyy-MM-dd ");
        pmiManagement.setPmi_date(createdate);
        pmiManagement.setStatus("空闲");
        boolean b = this.totalDao.save(pmiManagement);
        return b;
    }

    /*
     * 根据id查询PMI(non-Javadoc)
     * @see com.task.Server.pmi.PmiManagementServer#salPmiByid(com.task.entity.pmi.PmiManagement)
     */
    @Override
    public PmiManagement salPmiByid(PmiManagement pmiManagement) {
        // TODO Auto-generated method stub
        PmiManagement pmiManagement2 = (PmiManagement) this.totalDao.getObjectById(PmiManagement.class, pmiManagement.getId());
        return pmiManagement2;
    }

    /*
     * 更新PMI(non-Javadoc)
     * @see com.task.Server.pmi.PmiManagementServer#updatePmi(com.task.entity.pmi.PmiManagement)
     */
    @Override
    public boolean updatePmi(PmiManagement pmiManagement) {
        // TODO Auto-generated method stub
        String createdate = Util.getDateTime("yyyy-MM-dd");
        PmiManagement pmiManagement2 = (PmiManagement) this.totalDao.getObjectById(PmiManagement.class, pmiManagement.getId());
        pmiManagement2.setPmi_name(pmiManagement.getPmi_name());
        pmiManagement2.setPmi_ip(pmiManagement.getPmi_ip());
        pmiManagement2.setPmi_serverIp(pmiManagement.getPmi_serverIp());
        pmiManagement2.setMin_num(pmiManagement.getMin_num());
        pmiManagement2.setPmi_port(pmiManagement.getPmi_port());
        pmiManagement2.setPmi_date(createdate);
        pmiManagement2.setPmi_type(pmiManagement.getPmi_type());
        pmiManagement2.setStatus(pmiManagement.getStatus());
        pmiManagement2.setAlert_Percentage(pmiManagement.getAlert_Percentage());
        pmiManagement2.setRated_Current(pmiManagement.getRated_Current());
        boolean b = this.totalDao.update(pmiManagement2);
        return b;
    }

    /*
     * 删除PMI(non-Javadoc)
     * @see com.task.Server.pmi.PmiManagementServer#delPmi(com.task.entity.pmi.PmiManagement)
     */
    @Override
    public boolean delPmi(PmiManagement pmiManagement) {
        boolean b = false;
        PmiManagement pmiManagement2 = (PmiManagement) this.totalDao.getObjectById(PmiManagement.class, pmiManagement.getId());
        if (pmiManagement2 != null) {
            String hql = "from Machine where pmiManagements.id=?";
            List list = this.totalDao.query(hql, pmiManagement2.getId());
            if (list.size() > 0) {
                for (int i = 0; i < list.size(); i++) {
                    Machine machine = (Machine) list.get(i);
                    machine.setPmiManagements(null);
                    this.totalDao.update(machine);
                }
            }
            b = this.totalDao.delete(pmiManagement2);
        }
        return b;
    }

    /*
     * 查询对应设备(non-Javadoc)
     * @see com.task.Server.pmi.PmiManagementServer#findMachineByid(com.task.entity.Machine, int, int, java.lang.Integer)
     */
    @Override
    public Object[] findMachineByid(Machine machine, int parseInt,
                                    int pageSize, Integer pmiId) {
        // TODO Auto-generated method stub
        PmiManagement pmiManagement = (PmiManagement) this.totalDao.getObjectById(PmiManagement.class, pmiId);
        String hql = "from Machine where pmiManagements.id=? order by id desc";
        List list = this.totalDao.query(hql, pmiManagement.getId());
        int count = totalDao.getCount(hql, pmiManagement.getId());
        Object[] o = {list, count};
        return o;
    }

    /*
     * 查询所有设备的工位(non-Javadoc)
     * @see com.task.Server.pmi.PmiManagementServer#findMachineworkPosition()
     */
    @Override
    public List findMachineworkPosition() {
        // TODO Auto-generated method stub
        String hql = "from Machine where pmiManagements.id is null order by workPosition";
        List list = this.totalDao.query(hql);
        return list;
    }

    /*
     * 添加对应设备信息(non-Javadoc)
     * @see com.task.Server.pmi.PmiManagementServer#addMachine(com.task.entity.Machine, java.lang.Integer)
     */
    @Override
    public boolean addMachine(Machine machine, Integer pmiId) {
        // TODO Auto-generated method stub
        String hql = "from Machine where workPosition=? and  no=?";
        Machine machine2 = (Machine) this.totalDao.getObjectByCondition(hql, machine.getWorkPosition(), machine.getNo());
        PmiManagement pmiManagement = (PmiManagement) this.totalDao.getObjectById(PmiManagement.class, pmiId);
        machine2.setPmiManagements(pmiManagement);
        boolean bool = this.totalDao.update(machine2);
        return bool;
    }

    /*
     * 根据工位查找设备(non-Javadoc)
     * @see com.task.Server.pmi.PmiManagementServer#findMachineByworkPosition(java.lang.Integer)
     */
    @Override
    public List findMachineByworkPosition(String workPosition) {
        String hql = "from Machine where workPosition=? and  pmiManagements.id is null";
        List list = this.totalDao.query(hql, workPosition);
        return list;
    }

    /*
     * 删除相应的设备(non-Javadoc)
     * @see com.task.Server.pmi.PmiManagementServer#delMachine(com.task.entity.Machine)
     */
    @Override
    public boolean delMachine(Machine machine) {
        // TODO Auto-generated method stub
        boolean bool = false;
        Machine machine2 = (Machine) this.totalDao.getObjectById(Machine.class, machine.getId());
        machine2.setPmiManagements(null);
        bool = this.totalDao.update(machine2);
        return bool;

    }


    @Override
    public Object[] findProcessInforByid(ProcessInfor processInfor,
                                         int parseInt, int pageSize, Integer pmiId) {
        PmiManagement pmiManagement2 = (PmiManagement) this.totalDao.getObjectById(PmiManagement.class, pmiId);
//        String hql = totalDao.criteriaQueries(processInfor, null, "totalCount", "applyCount", "submmitCount", "breakCount");

//        SELECT pd.Markid,pd.selfCard,pc.* from ta_sop_w_ProcessInfor pc INNER JOIN ta_sop_w_procard pd WHERE pc.pmiId =2  and pc.fk_procardId=pd.id and pd.selfCard like "%%"
        String hql = "select pc from ProcessInfor pc INNER JOIN pc.procard pd WHERE ";
        if (pmiId != null && pmiId > 0) {
            hql += " pc.pmiId = '" + pmiId + "'";
        }
        if (processInfor != null) {
            if (!processInfor.getProcessName().equals("")) {
                hql += " and pc.processName like \'%" + processInfor.getProcessName() + "%\'";
            }
            if (!processInfor.getUsernames().equals("")) {
                hql += " and pc.usernames like \'%" + processInfor.getUsernames() + "%\'";
            }
            if (!processInfor.getGongwei().equals("")) {
                hql += " and pc.gongwei like \'%" + processInfor.getGongwei() + "%\'";
            }
            if (!processInfor.getMarkId().equals("")) {
                hql += " and pd.markId like \'%" + processInfor.getMarkId() + "%\'";
            }
            if (!processInfor.getSelfCard().equals("")) {
                hql += " and pd.selfCard like \'%" + processInfor.getSelfCard() + "%\'";
            }
        }
        hql += " order by pc.id desc";
        List<ProcessInfor> list = totalDao.findAllByPage(hql, parseInt, pageSize);
        int count = totalDao.getCount(hql);
        for (ProcessInfor processinfo : list
                ) {
            Procard pd = processinfo.getProcard();
            processinfo.setMarkId(pd.getMarkId());
            processinfo.setSelfCard(pd.getSelfCard());
        }
        Object[] o = {list, count, pmiManagement2};
        return o;
    }

    @Override
    public Integer findRoodId(Integer id) {
        // TODO Auto-generated method stub
        String hql_getProId = "select procard.rootId from ProcessInfor where id=?";
        return (Integer) totalDao.getObjectByCondition(hql_getProId, id);
    }


}
