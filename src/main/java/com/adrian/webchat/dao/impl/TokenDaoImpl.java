package com.adrian.webchat.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.adrian.webchat.bean.model.MToken;
import com.adrian.webchat.bean.model.MUser;
import com.adrian.webchat.dao.TokenDao;

@Repository("tokenDao")
public class TokenDaoImpl implements TokenDao {
	
	private DataSource dataSource;
	
	private JdbcTemplate jdbcTemplate;

	@Resource
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public MToken getTokenByUser(int userId) {
		MToken mToken = jdbcTemplate.query("select userid,token,expired,username,name,icon from token t inner join user u on t.userid = u.id where userid = ?", new ResultSetExtractor<MToken>() {
			@Override
			public MToken extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				MToken mToken = null;
				if (rs.next()) {
					mToken = new MToken();
					mToken.setUserId(rs.getInt("userid"));
					mToken.setToken(rs.getString("token"));
					mToken.setExpired(rs.getDate("expired"));
					
					MUser mUser = new MUser();
					mUser.setId(rs.getInt("userid"));
					mUser.setName(rs.getString("name"));
					mUser.setUsername(rs.getString("username"));
					mUser.setIcon(rs.getString("icon"));
					mToken.setmUser(mUser);
				}
				return mToken;
			}
		}, userId);
		return mToken;
	}


	@Override
	public MToken getTokenByToken(String token) {
		MToken mToken = jdbcTemplate.query("select userid,token,expired,username,name,icon from token t inner join user u on t.userid = u.id where token = ?", new ResultSetExtractor<MToken>() {
			@Override
			public MToken extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				MToken mToken = null;
				if (rs.next()) {
					mToken = new MToken();
					mToken.setUserId(rs.getInt("userid"));
					mToken.setToken(rs.getString("token"));
					mToken.setExpired(rs.getDate("expired"));
					
					MUser mUser = new MUser();
					mUser.setId(rs.getInt("userid"));
					mUser.setName(rs.getString("name"));
					mUser.setUsername(rs.getString("username"));
					mUser.setIcon(rs.getString("icon"));
					mToken.setmUser(mUser);
				}
				return mToken;
			}
		}, token);
		return mToken;
	}

	@Override
	public MToken refresh(MToken mToken) {
		jdbcTemplate.update("replace into token set userid = ?, token = ?, expired = ?", mToken.getUserId(), mToken.getToken(), mToken.getExpired());
		return getTokenByUser(mToken.getUserId());
	}
}
