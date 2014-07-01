package com.adrian.webchat.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.adrian.webchat.bean.model.MUser;
import com.adrian.webchat.bean.request.RUser;
import com.adrian.webchat.common.utils.BeanUtils;
import com.adrian.webchat.dao.UserDao;

@Repository
public class UserDaoImpl implements UserDao {
	
	private DataSource dataSource;
	
	private JdbcTemplate jdbcTemplate;

	@Override
	public MUser getUser(RUser user) {
		MUser useredb = jdbcTemplate.query("select id,name,username,password,icon from user where username = ?", new ResultSetExtractor<MUser>() {

			@Override
			public MUser extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				MUser user = null;
				if (rs.next()) {
					user = new MUser();
					user.setId(rs.getInt("id"));
					user.setName(rs.getString("name"));
					user.setUsername("username");
					user.setPassword(rs.getString("password"));
					user.setIcon(rs.getString("icon"));
				}
				return null;
			}
		}, user.getUsername());
		return useredb;
	}
	
	@Override
	public MUser addUser(RUser user) {
		jdbcTemplate.update("insert into user (username, password, name, icon) values (?,?,?,?)", user.getUsername(), user.getPassword(), user.getName(), user.getIcon());
		return getUser(user);
	}

	@Resource
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

}
