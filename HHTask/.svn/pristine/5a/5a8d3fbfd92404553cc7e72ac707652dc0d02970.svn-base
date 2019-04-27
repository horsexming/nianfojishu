package com.task.ServerImpl.kq;

import java.util.Date;
import java.util.List;

import com.task.Dao.TotalDao;
import com.task.Server.kq.ICardService;
import com.task.entity.Card;



/**
 * @author 曾建森
 * @FileNam CardServiceImpl.java
 * @Date 2012-10-9
 */
public class CardServiceImpl implements ICardService {
	
	private TotalDao totalDao;
	
	public void add(Card ca) {
		totalDao.save(ca);
	}
	public void delById(int id) {
		Card ca = (Card) totalDao.getObjectById(Card.class, id);
		totalDao.delete(ca);
	}
	public void del(Card ca) {
		totalDao.delete(ca);
	}
	public void update(Card ca) {
		totalDao.update(ca);
	}
	@SuppressWarnings("unchecked")
	public List<Card> queryAllCard() {
		return totalDao.query("from Card", null);
	}
	public Card getCardById(int id) {
		List<Card> list = totalDao.query("from Card c where c.id = ?", id);
		 if(list != null && list.size() >0){
			 return list.get(0);
		 }
		 return null;
	}
	public boolean updateBalance(int id,float balance) {
		Card card = getCardById(id);
		card.setBalance(balance);
		card.setUpdateTime(new Date());
		return totalDao.update(card);
	}
	public int getCardBalanceById(int id) {
		int sum = 0;
		List list = totalDao.query("select balance from Card c where c.id = ?", id);
		if(list!=null && list.size()>0){
			float num  =  (Float) list.get(0);
			sum = (int)num;
		}
		return sum;
	}
	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}
	
}
