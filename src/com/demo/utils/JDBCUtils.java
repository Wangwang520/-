package com.demo.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * 连接oracle数据库对象，直接复制使用（需要修改，用户的账号和密码）
 */
public class JDBCUtils {

	//使用c3p0连接池
	private static ComboPooledDataSource dataSource = new ComboPooledDataSource();

	private static ThreadLocal<Connection> tl = new ThreadLocal<Connection>(); 
	
	public static DataSource getDataSource() {
		return dataSource;
	}
	
	// 连接数据库
	public static Connection getConnection() throws SQLException {
		Connection con = tl.get();
		if (con == null) {
			con = dataSource.getConnection();
			tl.set(con); 
		}
		
		return con;
	}
 
	// 关闭数据库连接
	public static void conClose(Connection con, PreparedStatement st,ResultSet rs) {
 
		try {
			if (con != null) {
				con.close();
			}
			if (st != null) {
				con.close();
			}
			if (rs != null) {
				con.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
