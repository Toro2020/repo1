package com.yc.user.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yc.user.bean.User;
import com.yc.user.biz.BizException;
import com.yc.user.biz.UserBiz;

@WebServlet("/register.s")
public class RegisterServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	private UserBiz ubiz = new UserBiz();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=utf-8");
		User user = new User();
		user.setName(req.getParameter("name"));
		user.setPhone(req.getParameter("phone"));
		user.setEmail(req.getParameter("email"));
		user.setHead(req.getParameter("head"));
		user.setPwd(req.getParameter("pwd"));
		try {
			//调用业务方法
			ubiz.register(user);
			resp.getWriter().append("注册成功");
		} catch (BizException e) {
			e.printStackTrace();
			resp.getWriter().append("注册失败！原因："+e.getMessage());
		}
		
	}

	/**
	 * 测试用
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req,resp);
	}

}
