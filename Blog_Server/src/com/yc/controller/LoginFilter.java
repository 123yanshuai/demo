package com.yc.controller;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

/**
 * Servlet Filter implementation class LoginFilter
 */
@WebFilter(urlPatterns={"*.jsp","*.s"})
public class LoginFilter implements Filter {

	public void destroy() {
		
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		String path = httpRequest.getServletPath();//返回返回的资源路径
		//判断资源名是否需要被拦截
		if(path.endsWith("login.jsp") || path.endsWith("user.s")){
			//直接放行
			chain.doFilter(request, response);
			
			return;
		}
		
		if(httpRequest.getSession().getAttribute("loginedUser") != null){
			//已经登录
			//正常业务必须执行 过滤器链的doFilter
			chain.doFilter(request, response);
		} else{
			//未登录
			request.setAttribute("msg", "请先登录系统！");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
