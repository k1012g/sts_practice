package com.example.demo.login.domain.repository.jdbc;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import com.example.demo.login.domain.model.User;
import com.example.demo.login.domain.repository.UserDao;

// RepositoryアノテーションはDaoクラスにつける
// BeanとしてDIコンテナで管理される
@Repository("UserDaoJdbcImpl")
public class UserDaoJdbcImpl implements UserDao {
	
//	JdbcTemplateはSpringが用意しているため、Bean定義がすでにされている
//	そのためAutowiredアノテーションだけでDIコンテナでBeanとして管理される
	@Autowired
	JdbcTemplate jdbc;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
//	Userテーブルのレコード数をカウントする
	@Override
	public int count() throws DataAccessException {
//		カウントの結果、カラムを一つだけ返す場合はqueryForObjectを使う
//		第一引数にSQL文、第二引数に戻り値のオブジェクトのクラスを指定(この場合はカウントで数値が返ってくるのでIntegerや)
		int count = jdbc.queryForObject("SELECT COUNT(*) FROM m_user", Integer.class);
		return count;
	}
	
	@Override
	public int insertOne(User user) throws DataAccessException {
		String user_password = passwordEncoder.encode(user.getPassword());
		
//		JdbcTemplateを使うときはupdate, delete共にupdateメソッドを使う
//		第一引数にSQL文を入れて、第二引数にSQLでいう?に相当する値を入れている
		int rowNumber = jdbc.update("INSERT INTO m_user(user_id, "
				+ "password, "
				+ "user_name, "
				+ "birthday, "
				+ "age, "
				+ "marriage, "
				+ "role)"
				+ " VALUES(?, ?, ?, ?, ?, ?, ?)"
				, user.getUserId()
				, user_password
				, user.getUserName()
				, user.getBirthday()
				, user.getAge()
				, user.getMarriage()
				, user.getRole());
		
		return rowNumber;
	}
	
	@Override
	public User selectOne(String userId) throws DataAccessException {
		Map<String, Object> map = jdbc.queryForMap("SELECT * FROM m_user" + " WHERE user_id = ?", userId);
		
		User user = new User();
		
		user.setUserId((String)map.get("user_id"));
		user.setPassword((String)map.get("password"));
		user.setUserName((String)map.get("user_name"));
		user.setBirthday((Date)map.get("birthday"));
		user.setAge((Integer)map.get("age"));
		user.setMarriage((Boolean)map.get("marriage"));
		user.setRole((String)map.get("role"));
		
		return user;
		
	}
	
//	Userテーブルのレコードを全て取得
	@Override
	public List<User> selectMany() throws DataAccessException {
//		複数件のレコードを取得するときにはqueryForObjectを使う
		List<Map<String, Object>> getList = jdbc.queryForList("SELECT * FROM m_user");
		
//		List<Map<String、Object>>をList<>に変換するために空のインスタンスを用意
		List<User> userList = new ArrayList<>();
		
		for (Map<String, Object> map:getList) {
			User user = new User();
			
			user.setUserId((String)map.get("user_id"));
			user.setPassword((String)map.get("password"));
			user.setUserName((String)map.get("user_name"));
			user.setBirthday((Date)map.get("birthday"));
			user.setAge((Integer)map.get("age"));
			user.setMarriage((Boolean)map.get("marriage"));
			user.setRole((String)map.get("role"));
			
//			userListに値をセット
			userList.add(user);
		}
		
		return userList;
	}
	
	@Override 
	public int updateOne(User user) throws DataAccessException {
		String user_password = passwordEncoder.encode(user.getPassword());
		
		int rowNumber = jdbc.update("UPDATE m_user"
				+ " SET"
				+ " password = ?,"
				+ " user_name = ?,"
				+ " birthday = ?,"
				+ " age = ?,"
				+ " marriage = ?"
				+ " WHERE user_id = ?"
				, user_password
				, user.getUserName()
				, user.getBirthday()
				, user.getAge()
				, user.getMarriage()
				, user.getUserId());
		
//		if (rowNumber > 0) {
//			throw new DataAccessException("トランザクションテスト") {};
//		}
		
		return rowNumber;
	}
	
	@Override
	public int deleteOne(String userId) throws DataAccessException {
		int rowNumber = jdbc.update("DELETE FROM m_user WHERE user_id = ?", userId);
		return rowNumber;
	}
	
	@Override
	public void userCsvOut() throws DataAccessException {
		String sql = "SELECT * FROM m_user";
		
		UserRowCallbackHandler handler = new UserRowCallbackHandler();
		
		jdbc.query(sql, handler);
	}
}