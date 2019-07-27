package com.demo.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.alibaba.fastjson.JSONObject;
import com.demo.dao.StudentDao;

/**
 * Servlet implementation class AdminLoginServlet
 */
@WebServlet("/studentLoginServlet")
public class StudentLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	StudentDao sd = new StudentDao();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		response.setHeader("Access-Control-Allow-Origin", "*");
		
		String sid = request.getParameter("username");
		String password = request.getParameter("password");
//		Cookie cookie = new Cookie("sid", sid);
		boolean flag = false;
		try {
			flag = sd.login(Integer.parseInt(sid), password);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(flag);
//		System.out.println(cookie);
//		response.addCookie(cookie);
		response.getWriter().write(JSONObject.toJSONString(flag));
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
