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
 * Servlet implementation class AddCourse
 */
@WebServlet("/addCourse")
public class AddCourse extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		response.setHeader("Access-Control-Allow-Origin", "*");
		
		String cname = request.getParameter("cname");
		String ctime = request.getParameter("ctime");
		String cadderss = request.getParameter("caddress");
		String cteacher = request.getParameter("cteacher");
		
		CourseDao cd = new CourseDao();
		
		boolean code = false;
		try {
			if(cname !=null && ctime != null && cadderss !=null && cteacher !=null) {
				code = cd.addCourse(cname,ctime,cadderss,cteacher);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
