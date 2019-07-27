package com.demo.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.demo.entry.Course;
import com.demo.entry.CourseById;
import com.demo.utils.JDBCUtils;

public class CourseDao {

	QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
	
	public List<Course> listAll(){
		String sql = "select * from t_course";
		List<Course> courseList;
		try {
			courseList = qr.query(sql, new BeanListHandler<Course>(Course.class));
			return courseList;
		} catch (SQLException e) {
			return null;
		}
		
	}
	public List<CourseById> CourseById(int id){
		String sql ="select c.cid,c.cname,s.sid,s.sname from t_course as c,t_student as s,t_sc as sc where c.cid=sc.cid and s.sid = sc.sid and c.cid =  " + id;
		List<CourseById> list = null;
		try {
			list = qr.query(sql, new BeanListHandler<CourseById>(CourseById.class));
			return list;
		} catch (SQLException e) {
			return null;
		}
	}
	public boolean addCourse(String cname, String cdate, String caddress, String cteacher) throws Exception {
		String sql = "insert into t_course(cname,ctime,caddress,cteacher) values(?,?,?,?)";
		int code = qr.update(sql,cname,cdate,caddress,cteacher);
		if(code == 1) {
			return true;
		}
		return false;
	}
	public int updateCourse(int id, String name, String time, String address, String teacher) {
		String sql = "UPDATE t_course SET cname = '"+ name +"', ctime = '"+ time +"', caddress = '"+address+"',cteacher = '"+teacher+"' WHERE id = '"+id+"'";
		int code = 0;
		try {
			code = qr.update(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return code;
	}
	public int deleteCourse(int id) throws SQLException {
		String sql = "delete from t_course where id = " + id;
		int code = qr.update(sql);
		
		return code;
	}
	public List<Course> findCourse(String msg) throws SQLException {
		List<Course> list = new ArrayList<>();
		String sql = "SELECT * FROM t_course WHERE cname LIKE '%" + msg + "%'";
		list = qr.query(sql, new BeanListHandler<Course>(Course.class));
		if (list != null) {
			return list;
		}
		return null;		
	}
	
	public Course findByIdCourse(String id) throws SQLException {
		String sql = "SELECT * FROM t_course WHERE cid = '" + id + "'";
		Course c = qr.query(sql, new BeanHandler<Course>(Course.class));
		if(c != null) {
			return c;
		}
		return null;
	}
}
