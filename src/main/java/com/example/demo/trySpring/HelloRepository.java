package com.example.demo.trySpring;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

// RepositoryアノテーションをつけることでDIコンテナで管理
//　RepositoryクラスはDBのCRUDを行うクラス
@Repository
public class HelloRepository {
//	Autowiredアノテーションでインスタンスを生成しているイメージ
//	例) private JdbcTemplate jdbcTemplate = new JdbcTemplate();
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public Map<String, Object> findOne(int id) {
		String query = "SELECT" + " employee_id," + " employee_name," + " age " + "FROM employee " + "WHERE employee_id=?";
		
		Map<String, Object> employee = jdbcTemplate.queryForMap(query, id);
		
		return employee;
	}
}