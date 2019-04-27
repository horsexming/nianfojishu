package com.task.ServerImpl;

import com.task.Dao.TotalDao;
import com.task.Server.MessagePollingService;
import com.task.entity.MessagePolling;
import com.task.entity.Users;
import com.task.util.RtxUtil;
import com.task.util.Util;

import java.util.List;

public class MessagePollingServiceImpl implements MessagePollingService {

    private TotalDao totalDao;


    @Override
    public String addMessage(MessagePolling messagePolling){
        if(messagePolling!=null){
            if(totalDao.save(messagePolling)){
                return "成功";
            }
        }
        return null;
    }


    @Override
    public String handleMessage(){
        deleteDueMessage();
        Integer time=(int) (System.currentTimeMillis() / 1000);
        List<MessagePolling> l=totalDao.query("from MessagePolling where triggerDate-?<600 AND triggerDate-?>0",time,time);
        if(l.size()>0){
            for (MessagePolling messagePolling:l) {
                //发送RTX消息
                RtxUtil.sendNotify(messagePolling.getReceiveUsersCode(),messagePolling.getMessageTitle()+"\n"+messagePolling.getMessage(),"系统消息","0","0");
            }
        }
        return null;
    }




    /*
        * @author fy
    　　* @date 2018/8/15 16:04
    　　* @Description:删除过期消息
    　　* @param []
    　　* @return java.lang.String
    　　* @throws
    　　*/
    @Override
    public String deleteDueMessage(){
        Integer time=(int) (System.currentTimeMillis() / 1000);
        List<MessagePolling> l=totalDao.query("from MessagePolling where triggerDate<?",time);
        if(l.size()>0){
            for (MessagePolling messagePolling:l) {
                totalDao.delete(messagePolling);
            }
        }
        return null;
    }

    @Override
    public MessagePolling findMessage(Integer id){
        return (MessagePolling) totalDao.getObjectById(MessagePolling.class,id);
    }

    @Override
    public Boolean deleteMessage(Integer id){
        MessagePolling m=findMessage(id);
        return  totalDao.delete(m);
    }


    public TotalDao getTotalDao() {
        return totalDao;
    }

    public void setTotalDao(TotalDao totalDao) {
        this.totalDao = totalDao;
    }
}
