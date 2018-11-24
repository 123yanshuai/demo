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
		String path = httpRequest.getServletPath();//���ط��ص���Դ·��
		//�ж���Դ���Ƿ���Ҫ������
		if(path.endsWith("login.jsp") || path.endsWith("user.s")){
			//ֱ�ӷ���
			chain.doFilter(request, response);
			
			return;
		}
		
		if(httpRequest.getSession().getAttribute("loginedUser") != null){
			//�Ѿ���¼
			//����ҵ�����ִ�� ����������doFilter
			chain.doFilter(request, response);
		} else{
			//δ��¼
			request.setAttribute("msg", "���ȵ�¼ϵͳ��");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
