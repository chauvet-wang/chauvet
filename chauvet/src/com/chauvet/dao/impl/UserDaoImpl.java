package com.chauvet.dao.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.chauvet.dao.IUserDaoInterface;
import com.chauvet.po.User;

@Repository
public class UserDaoImpl implements IUserDaoInterface {
	
	@Resource
	private SessionFactory sessionFactory;
	public Session getSession(){
//		return sessionFactory.openSession();  // 需要自己关闭session
		/***
		 * getCurrentSession()的两个特性：
		 * 1、在没有session的情况下不会自动创建一个
		 * 2、会自动关闭session
		 */
		return sessionFactory.getCurrentSession();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getAllUser() {
		
		User u= new User();
		u.setName("机构法");
		u.setAge(11);
		u.setCreateTime(new Date());
		u.setRegisterTime(new Date());
		u.setSex(1);
		getSession().save(u);
		
		Query query = getSession().createQuery("from user");
		List<User> userL = query.list();
		System.out.println(userL.size());
		return userL;
	}

}
