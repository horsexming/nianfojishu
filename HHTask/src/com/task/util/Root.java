package com.task.util;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class Root extends HibernateDaoSupport {


	// 通过id获得对象
	public Object getId(Object o, int id) {
		return this.getHibernateTemplate().get((Class) o, id);
	}

	// 删除
	public boolean delete(Object obj) {
		this.getHibernateTemplate().delete(obj);
		return true;
	}

	// 查询
	public List<Object> query(String hql, Object... agr) {
		List<Object> list = this.getHibernateTemplate().find(hql, agr);
		return list;
	}

	// 保存
	public boolean save(Object obj) {
		this.getHibernateTemplate().save(obj);
		return true;
	}

	// 修改
	public boolean update(Object obj) {
		this.getHibernateTemplate().update(obj);
		return true;
	}

	//创建query对象
	public Query createQuery(String sql){
		SessionFactory sf=this.getHibernateTemplate().getSessionFactory();
		Session session=sf.openSession();
		return session.createQuery(sql);
	}
	
	// 创建sql语句查询
	public List createQuerySelect(String sql, Object... agr) {
		Query query=this.createQuery(sql);
		List list = query.list();
		return list;
	}
	
	
	// 创建sql语句修改
	public int createQueryUpdate(String sql){
		Query query=this.createQuery(sql);
		return query.executeUpdate();
		
	}

	// 分页方法
	public List<Object> findAllByPage(String hql, int pageNo, int pageSize,
			Object... agr) {
		if (pageNo < 1) {
			pageNo=1;
		}
		int offset = (pageNo - 1) * pageSize;
		return findByPage(hql, offset, pageSize);
	}

	// 获得总数量
	public int getCount(String hql, Object... agr) {
		String select=hql.substring(0,hql.indexOf("from"));
		String from=hql.substring(hql.indexOf("from"));
		if("".equals(select)){
			select="select";
		}
		hql=select+" count(*) "+from;
		List list = this.getHibernateTemplate().find(hql, agr);
		
		if(list!=null){
			return Integer.parseInt(list.get(0).toString());
		}
		return 0;
	}

	// 分页
	public List findByPage(final String hql, final int offset,
			final int pageSize) {
		List list = this.getHibernateTemplate().executeFind(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						List result = session.createQuery(hql).setFirstResult(
								offset).setMaxResults(pageSize).list();
						return result;
					}
				});
		return list;
	}

	// 多条件查询
	public String criteriaQueries(Object obj, String other, String[] otherNames) {
		String hql = null;
		if (obj != null) {
			String className = obj.getClass().getSimpleName();// 类名称
			hql = "from " + className;
			String propertyName = null;// 属性名称
			int status = 0;// 状态区分是否存在where 条件
			try {
				// 处理对象
				Field[] fields = obj.getClass().getDeclaredFields();
				String hql2 = null;

				out: for (Field field : fields) {
					Class c = field.getType();// 获得属性的类型
					PropertyDescriptor pd = new PropertyDescriptor(field
							.getName(), obj.getClass());
					Method method = pd.getReadMethod();// 获得get方法
					Object propertyObj = method.invoke(obj);// 获得get方法的值
					if (c.getName().equals("int") && (Integer) propertyObj == 0) {
						continue;
					}
					if (propertyObj != null && !"".equals(propertyObj)) {
						propertyName = pd.getName();// 获得属性名称
						if (otherNames != null) {
							for (String otherName : otherNames) {
								if (propertyName.equals(otherName)) {
									continue out;
								}
							}
						}
						status += 1;
						if (status == 1) {
							hql2 = " where " + propertyName + " like '%"
									+ propertyObj.toString().trim() + "%'";
						} else {
							hql2 += " and " + propertyName + " like '%"
									+ propertyObj + "%'";
						}
					}
				}
				// 处理hql语句
				if (hql2 != null) {
					hql += hql2;
					if (other != null) {
						hql += " and " + other;
					}
				} else if (hql2 == null) {
					if (other != null) {
						hql += " where " + other;
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return hql;
	}

	// 多条件查询 两个日期之间
	public String criteriaQueries(Object obj, String betweenName,
			String[] between, String other) {
		String hql = null;
		if (obj != null) {
			String className = obj.getClass().getSimpleName();// 类名称
			hql = "from " + className;
			String propertyName = null;// 属性名称
			int status = 0;// 状态区分是否存在where 条件
			try {
				// 处理对象
				Field[] fields = obj.getClass().getDeclaredFields();
				String hql2 = null;
				for (Field field : fields) {
					Class c = field.getType();// 获得属性的类型
					PropertyDescriptor pd = new PropertyDescriptor(field
							.getName(), obj.getClass());
					Method method = pd.getReadMethod();// 获得get方法
					Object propertyObj = method.invoke(obj);// 获得get方法的值
					if (c.getName().equals("int") && (Integer) propertyObj == 0) {
						continue;
					}
					if (propertyObj != null && !"".equals(propertyObj)) {
						propertyName = pd.getName();// 获得属性名称

						if (propertyName.equals(betweenName)) {

						}
						status += 1;
						if (status == 1) {
							hql2 += " where " + propertyName + "='"
									+ propertyObj + "'";
						} else {
							hql2 += " and " + propertyName + "='" + propertyObj
									+ "'";
						}
					}
				}
				// 处理比较数据
				/*
				 * for(int i=0;i<between.length;i++){
				 * if((Object)between[0]>(Object)between[1]){ } }
				 */

				// 处理hql语句
				if (hql2.length() > 0) {
					hql += hql2;
					if (other.length() > 0) {
						hql += " and " + other;
					}
				} else {
					if (other.length() > 0) {
						hql += " where " + other;
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return hql;
	}

}
