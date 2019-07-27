package com.demo.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.alibaba.fastjson.JSONObject;
import com.demo.dao.CourseDao;

/**
 * Servlet implementation class UpdateStudent
 */
@WebServlet("/updateCourse")
public class UpdateCourse extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		response.setHeader("Access-Control-Allow-Origin", "*");
		
		String id = request.getParameter("cid");
		String name = request.getParameter("cname");
		String time = request.getParameter("cdate");
		String address = request.getParameter("caddress");
		String teacher = request.getParameter("cteacher");
		
		CourseDao cd = new CourseDao();
		int code = 0;
		code = cd.updateCourse(Integer.parseInt(id),name,time,address,teacher);
		response.getWriter().write(JSONObject.toJSONString(code));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
