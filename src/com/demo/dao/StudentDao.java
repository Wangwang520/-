package com.demo.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import com.demo.entry.Student;
import com.demo.entry.StudentById;
import com.demo.utils.JDBCUtils;
import com.demo.utils.MD5Util;

public class StudentDao {
	QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());

	public boolean login(int sid, String password) throws Exception {
		String passwd = MD5Util.MD5EncodeUtf8(password);
		String sqlpasswd = "select * from t_student where sid = '" + sid + "'" + "and spassword = '" + passwd + "'";
		Student s = qr.query(sqlpasswd, new BeanHandler<Student>(Student.class));
		if (s != null) {
			return true;
		} else {
			return false;
		}
	}
	
	public List<StudentById> StudentById(int id){
		String sql ="select c.cid,c.cname,c.ctime,c.caddress,c.cteacher from t_course as c,t_student as s,t_sc as sc where c.cid=sc.cid and s.sid = sc.sid and s.sid =  " + id;
		List<StudentById> list = null;
		try {
			list = qr.query(sql, new BeanListHandler<StudentById>(StudentById.class));
			return list;
		} catch (SQLException e) {
			return null;
		}
	}


	public int addStudent(String sname, String sgender, String sage, String spassword) {
		String sql = "insert into t_student(sname,sgender,sage,spassword) values(?,?,?,?)";
		String passwd = MD5Util.MD5EncodeUtf8(spassword);
		int code = 0;
		try {
			code = qr.update(sql,sname, sgender, sage, passwd);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return code;
	}
	

	public int updateStudent(int id, String passwd, String npd) throws SQLException {
		String password = MD5Util.MD5EncodeUtf8(passwd);
		String sqlp = "SELECT * FROM t_student WHERE sid = '" + id + "'";
		Student s = qr.query(sqlp, new BeanHandler<Student>(Student.class));
		System.out.println("old:" + password);
		if(s.getSpassword().equals(password)) {
			String npassword = MD5Util.MD5EncodeUtf8(npd);
			System.out.println("new:" + npassword);
			String sql = "UPDATE t_student SET  spassword = '"+ npassword +"' WHERE sid = '"+id+"'";
			int c = 0;
			c = qr.update(sql);
			System.out.println(c+"!修改成功");
			return c;
		}
		return 0;
	}

	public int deleteStudent(int id) throws SQLException {
		String sql = "delete from t_student where id = " + id;
		int code = qr.update(sql);
		return code;
	}

	public List<Student> listAll() {
		String sql = "select * from t_student";
		List<Student> studentList;
		try {
			studentList = qr.query(sql, new BeanListHandler<Student>(Student.class));
			return studentList;
		} catch (SQLException e) {
			return null;
		}
	}

	public Student findByIdStudent(String id) throws SQLException {
		String sql = "SELECT * FROM t_student WHERE sid = '" + id + "'";
		Student s = qr.query(sql, new BeanHandler<Student>(Student.class));
		if(s != null) {
			return s;
		}
		return null;
	}
}
