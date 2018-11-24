package com.yc.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.yc.bean.User;
import com.yc.biz.UserBiz;
import com.yc.exception.BizException;
import com.yc.utils.BeanUtils;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/user.s")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserBiz ubiz = new UserBiz();
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String op = request.getParameter("op");
		if("login".equals(op)){
			login(request,response);
		}else if("query".equals(op)){
			query(request,response);
		}else if("add".equals(op)){
			add(request,response);
		}else if("find".equals(op)){
			find(request,response);
		}else if("delete".equals(op)){
			delete(request,response);
		}else if("save".equals(op)){
			save(request,response);
		}
	}

	private void save(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		response.setCharacterEncoding("utf-8");
		User user = BeanUtils.asBean(request, User.class);
		System.out.println(user);
		String msg;
		try {
			ubiz.save(user);
			msg ="用户信息修改成功";
		} catch (BizException e) {
			e.printStackTrace();
			msg = e.getMessage();
		}
		response.getWriter().append(msg);
	}

	private void find(HttpServletRequest request, HttpServletResponse response) 
			throws IOException {
		response.setCharacterEncoding("utf-8");
		String id = request.getParameter("findId");
		User user = ubiz.findById(id);
		String userString = JSON.toJSONString(user);
		response.getWriter().append(userString);
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("deleteId");
		ubiz.delete(id);
	}

	private void add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException  {
		request.setCharacterEncoding("utf-8");
		System.out.println("--------------"+request.getParameter("name"));
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		String repwd = request.getParameter("repwd");
		UserBiz ubiz = new UserBiz();
		//接受页面传回的参数
		//将参数加载到user对象中
		User user = BeanUtils.asBean(request, User.class);
		try {
			ubiz.add(user,repwd);
		} catch (BizException e) {
			// TODO Auto-generated catch block
			request.setAttribute("msg", e.getMessage());
			e.printStackTrace();
		}finally{
			//方式一 request.getRequestDispatcher("user.s?op=query").forward(request, response);
			//方式二
			query(request,response);
		}
		//调用userBiz.add方法，将用户添加到数据库中
		//如果成功则跳转到用户查询界面
		//如果失败？
	}

	private void query(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		User user = BeanUtils.asBean(request, User.class);
		request.setAttribute("userList", ubiz.find(user));
		request.getRequestDispatcher("manage-user.jsp").forward(request, response);
	}

	private void login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException  {
		//接受参数
				String username = request.getParameter("username");
				String userpwd = request.getParameter("userpwd");
				
				
			    User user = null;
				String ip =request.getRemoteAddr();
				/*System.out.println(ip);*/
				try {
					user = ubiz.login(user,username,userpwd,ip);
				} catch (BizException e) {
					e.printStackTrace();
					request.setAttribute("msg", e.getMessage());
					//失败
					request.getRequestDispatcher("login.jsp").forward(request, response);
					return;
				}
				
				if(user == null){
					request.setAttribute("msg", /*"<script>alert('用户名或者密码错误!')</script>"*/"用户名或者密码错误");
					//失败
					request.getRequestDispatcher("login.jsp").forward(request, response);
				}else{
					//将用户信息保存到会话中
					request.getSession().setAttribute("loginedUser", user);
					//成功
					response.sendRedirect("index.jsp");
				}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doGet(request, response);
		//Timestamp time = new Timestamp(new Date().getTime());
	}
	
}
