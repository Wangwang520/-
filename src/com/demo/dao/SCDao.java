package com.demo.dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;

import com.demo.utils.JDBCUtils;

public class SCDao {

	QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());

	public int add(int sid, int cid) throws SQLException {
		String sql = "INSERT into t_sc VALUES(?,?)";
		int code = qr.update(sql, sid,cid);
		return code;
	}

	public int delete(int sid, int cid) throws SQLException {
		String sql = "delete from t_sc where sid="+sid+" and cid="+cid;
		int code = qr.update(sql);
		return code;
	}

}
