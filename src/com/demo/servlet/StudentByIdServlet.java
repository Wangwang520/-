package com.demo.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.demo.dao.StudentDao;
import com.demo.entry.StudentById;

/**
 * Servlet implementation class AdminLoginServlet
 */
@WebServlet("/studentByIdServlet")
public class StudentByIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	StudentDao sd = new StudentDao();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		response.setHeader("Access-Control-Allow-Origin", "*");
		
		String id = request.getParameter("id");
		System.out.println("======"+id);
		List<StudentById> list = null;
		try {
			if(id != null ) {
				list = sd.StudentById(Integer.parseInt(id));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(list.toString());
		response.getWriter().write(JSONObject.toJSONString(list));
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
