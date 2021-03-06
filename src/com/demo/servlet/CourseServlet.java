package com.demo.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.demo.dao.CourseDao;
import com.demo.entry.Course;

/**
 * Servlet implementation class AdminLoginServlet
 */
@WebServlet("/courseServlet")
public class CourseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	CourseDao cd = new CourseDao();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		response.setHeader("Access-Control-Allow-Origin", "*");
		
		List<Course> list = null;
		try {
			list = cd.listAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(list);
		response.getWriter().write(JSONObject.toJSONString(list));
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
