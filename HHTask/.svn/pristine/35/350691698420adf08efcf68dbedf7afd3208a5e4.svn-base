package com.task.Server;

import com.task.entity.MessagePolling;

public interface MessagePollingService {

    String addMessage(MessagePolling messagePolling);

    String handleMessage();

    /*
        * @author fy
    　　* @date 2018/8/15 16:04
    　　* @Description:删除过期消息
    　　* @param []
    　　* @return java.lang.String
    　　* @throws
    　　*/
    String deleteDueMessage();

    MessagePolling findMessage(Integer id);

    Boolean deleteMessage(Integer id);
}
