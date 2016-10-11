package com.chauvet.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.chauvet.po.User;
import com.chauvet.service.IUserServiceInterface;


@Controller
@RequestMapping("/login")
public class LoginController extends BaseController {
	
	@Resource
	private IUserServiceInterface userServiceImpl;
	
	/**
	 *	跳转到登录页面 
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/gotoLogin", method = RequestMethod.GET)
	public ModelAndView gotoLogin(HttpServletRequest request,HttpServletResponse response){
		
		List<User> userList = userServiceImpl.getAllUser();
		System.out.println(userList);
		return new ModelAndView("login/gotoLogin");
	}

}
