package com.yc.vue.dyg.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yc.vue.dyg.bean.DygMsg;
import com.yc.vue.dyg.biz.BizException;
import com.yc.vue.dyg.biz.DygBiz;

/**
 * Servlet implementation class AddMsgServlet
 */
@WebServlet("/addmsg.s")
public class AddMsgServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DygBiz biz=new DygBiz();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		
		DygMsg msg = new DygMsg();
		msg.setCreateName(request.getParameter("createName"));
		msg.setEmail(request.getParameter("email"));
		msg.setContent(request.getParameter("content"));
		try {
			biz.addMsg(msg);
			response.getWriter().append("留言成功");
		} catch (BizException e) {
			e.printStackTrace();
			response.getWriter().append(e.getMessage());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
