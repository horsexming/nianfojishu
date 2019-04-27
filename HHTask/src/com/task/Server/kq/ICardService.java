package com.task.Server.kq;


import java.util.List;

import com.task.entity.Card;



/**
 * @author Administrator
 * @FileNam ICardService.java
 * @Date 2012-10-9
 */
public interface ICardService {
	public void add(Card ca);
	public void delById(int id);
	public void del(Card ca);
	public void update(Card ca);
	public boolean updateBalance(int id,float balance);
	public List<Card> queryAllCard();
	public Card getCardById(int id);
	public int getCardBalanceById(int id);
}
