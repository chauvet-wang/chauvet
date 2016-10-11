package com.chauvet.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.chauvet.dao.IUserDaoInterface;
import com.chauvet.po.User;
import com.chauvet.service.IUserServiceInterface;

@Transactional
@Service
public class UserServiceImpl implements IUserServiceInterface {
	
	@Resource
	private IUserDaoInterface userDaoImpl;

	@Override
	public List<User> getAllUser() {
		return userDaoImpl.getAllUser();
	}

}
