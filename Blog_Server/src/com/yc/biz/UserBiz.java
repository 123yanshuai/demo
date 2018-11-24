package com.yc.biz;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.yc.bean.User;
import com.yc.utils.DBHelper;
import com.yc.exception.BizException;

public class UserBiz {
	private static final String PASSWORD_REGX = "[0-9a-zA-Z*&^%$#@,.]{6,12}";
	/*private static final String PASSWORD_REGX="[0-9a-zA-Z@!#~$%^&*()+-*
	 * /]{6-12}";*/
	private static final String TEL_REGX = "[1][34578]\\d{9}";
	/*
	 * ��¼����
	 * @param username
	 * @param userpwd
	 * @return ���ص�¼�ɹ����û�����
	 * */
	public User login(User user, String username, String userpwd, String ip) throws BizException{
		
		if(username == null || username.trim().isEmpty()){
			throw new BizException("����д�û�����");
		}
		
		if(userpwd == null || userpwd.trim().isEmpty()){
			throw new BizException("����д���룡");
		}
		//DBHelper db = new DBHelper();
		//List<Object> params = new ArrayList<Object>();
		//params.add(username);
		//params.add(userpwd);
		String sql = "select * from user where account =? and pwd =?";
		user.setCreateDate(new Timestamp(new Date().getTime()));
		DBHelper.update("insert into user_login_record set ,userId =?,time=?,loginIp=?", user.getId(),user.getCreateDate(),ip);
		//Map<String,String> user =db.findMap(sql, params);
		return DBHelper.unique(sql,User.class,username,userpwd);
	}

	public List<User> findAll() {
		return DBHelper.select("select * from user", User.class);
	}

	public void add(User user,String repwd) throws BizException {
		//.trimȥ�ո�
		if(user.getName() == null || user.getName().trim().isEmpty()){
			throw new BizException("����д�˻���!");
		}
		if(user.getAccount()== null || user.getAccount().trim().isEmpty()){
			throw new BizException("����д�û���!");
		}
		if(!user.getTel().matches(TEL_REGX)){
			throw new BizException("��������ȷ�ĵ绰����");
		}
		if(!user.getPwd().matches(PASSWORD_REGX)){
			throw new BizException("��������ȷ������");
		}
		if(!user.getPwd().equals(repwd)){
			throw new BizException("�����������벻һ��");
		}
		user.setCreateDate(new Timestamp(new Date().getTime()));
		
		/*
		 * 
		 * ������û����Ե���֤
		 * */
		String sql = "insert into user (name,account,tel,pwd,createDate) values(?,?,?,?,?)";
		DBHelper.insert(sql, user.getName(),user.getAccount(),user.getTel(),user.getPwd(),user.getCreateDate());
	}

	public Object find(User user) {
		String sql = "select * from user where 1=1";
		ArrayList<Object> params = new ArrayList<Object>();
		//��̬ƴ������
		if(user.getAccount()!= null && user.getAccount().trim().isEmpty() == false){
			sql += " and account like concat('%',?,'%')";//concat ����ƴ���ַ���
			params.add(user.getAccount());
		}
		if(user.getName()!= null && user.getName().trim().isEmpty() == false){
			sql += " and name like ?";
			params.add("%"+user.getName()+"%");
		}
		if(user.getTel()!= null && user.getTel().trim().isEmpty() == false){
			sql += " and tel like ?";
			params.add("%"+user.getTel()+"%");
		}
		return DBHelper.select(sql, params);
	}

	public void delete(String id) {
		String sql = "delete from user where id = ?";
		DBHelper.update(sql, id);
	}

	public User findById(String id) {
		return DBHelper.unique("select * from user where id=?",User.class,id);
	}

	public void save(User user) throws BizException {
		if(user.getName() == null || user.getName().trim().isEmpty()){
			throw new BizException("����д�˻���!");
		}
		if(user.getAccount()== null || user.getAccount().trim().isEmpty()){
			throw new BizException("����д�û���!");
		}
		if(!user.getTel().matches(TEL_REGX)){
			throw new BizException("��������ȷ�ĵ绰����");
		}
		DBHelper.update("update user set name=?,account=?,tel=? where id=?", 
				user.getName(),user.getAccount(),user.getTel(),user.getId());
		/*DBHelper.update("update user set name=?,account=?,tel=? where id=?",
				user.getName(),user.getAccount(),user.getTel(),user.getId());*/
	}
}
