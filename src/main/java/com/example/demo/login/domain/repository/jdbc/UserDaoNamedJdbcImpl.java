package com.example.demo.login.domain.repository.jdbc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.demo.login.domain.model.User;
import com.example.demo.login.domain.repository.UserDao;

@Repository("UserDaoNamedJdbcImpl")
public class UserDaoNamedJdbcImpl implements UserDao {
	@Autowired
	private NamedParameterJdbcTemplate jdbc;
	
	@Override
	public int count() {
		String sql = "SELECT COUNT(*) FROM m_user";
		
		SqlParameterSource params = new MapSqlParameterSource();
		
		return jdbc.queryForObject(sql, params, Integer.class);
	}
	
	@Override
	public int insertOne(User user) {
		String sql = "INSERT INTO m_user(user_id,"
				+ " password,"
				+ " user_name,"
				+ " birthday,"
				+ " age,"
				+ " marriage,"
				+ " role)"
				+ " VALUES(:userId,"
				+ " :password,"
				+ " :userName,"
				+ " :birthday,"
				+ " :age,"
				+ " :marriage,"
				+ " :role)";
		
		SqlParameterSource params = new MapSqlParameterSource()
				.addValue("userId", user.getUserId())
				.addValue("password", user.getPassword())
				.addValue("userName", user.getUserName())
				.addValue("birthday", user.getBirthday())
				.addValue("age", user.getAge())
				.addValue("marriage", user.getMarriage())
				.addValue("role", user.getRole());
		
		return jdbc.update(sql, params);
	}	
}