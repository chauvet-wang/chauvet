package com.chauvet.utils;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * 全局过滤器
 * 一般用来判断是否登录和调整编码<br/>
 * web.xml中设置的过滤url规则和spring转发器规则一样，即过滤url /
 *
 */
public class AllFilter implements Filter {
	private static final Logger log = Logger.getLogger(AllFilter.class);
	
	public void init(FilterConfig arg0) throws ServletException {
		log.info("全局过滤器成功启动！");
	}
	
	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		((HttpServletRequest) request).setCharacterEncoding("utf-8");
		//清除缓存
		HttpServletResponse httpResponse=(HttpServletResponse)response;
		httpResponse.setHeader("Pragma", "No-cache");
		httpResponse.setHeader("Cache-Control", "no-cache");
		httpResponse.setDateHeader("Expires", 0);
		httpResponse.setContentType("text/html;charset=utf-8");
		/*//判断是否登录
		String uri = new String(((HttpServletRequest) request).getRequestURI());
		Object user=((HttpServletRequest)request).getSession().getAttribute("adminUser");

		if (uri.contains("/admin/")) {
			//设置页面不缓存
			((HttpServletResponse)response).setHeader("Pragma", "No-cache");
			((HttpServletResponse)response).setHeader("Cache-Control", "no-cache");
			((HttpServletResponse)response).setDateHeader("Expires", 0);
			
			String ss = uri;
			ss = uri.substring(ss.indexOf("/admin/"), ss.length()).replace("/admin/", "");
			String[] ss2 = ss.split("/");
			String out = new String("");
			for(int i=0;i<ss2.length;i++){
				out+="../";
			}
			//System.out.println(out);
			//如果session不存在 转向登录页面/
			if(((HttpServletRequest) request).getSession().getAttribute("adminUser")==null){
				response.getWriter().print("<script>alert('您还没有登录请先登录！');window.parent.location.href='"+out+"login/gotoFFTLogin';</script>");
				return ;
			}
		}*/
		
		chain.doFilter(request, response);
		 
	}
}
