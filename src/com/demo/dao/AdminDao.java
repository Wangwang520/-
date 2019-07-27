package com.demo.dao;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import com.demo.entry.Admin;
import com.demo.utils.JDBCUtils;

public class AdminDao {

	QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());

	public boolean login(String username, String password) throws Exception {
		String sqlpasswd = "select * from t_admin where username = '" + username + "'" + "and password = '" + password
				+ "'";
		Admin admin = qr.query(sqlpasswd, new BeanHandler<Admin>(Admin.class));
		if (admin != null) {
			System.out.println(username + "," + password);
			return true;
		} else {
			System.out.println(username + "," + password);
			return false;
		}
	}

}
