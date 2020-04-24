package com.example.demo.login.domain.model;

import java.util.Date;
import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;
import lombok.Data;

@Data
public class SignupForm {
	@NotBlank(message = "{require_check}", groups = ValidGroup1.class)
	@Email(message = "{email_check}", groups = ValidGroup2.class)
	private String userId;
	
	@NotBlank(message = "{require_check}", groups = ValidGroup1.class)
	@Length(min = 4, max = 100, message = "{length_check}", groups = ValidGroup2.class)
	@Pattern(regexp = "^[a-zA-Z0-9]*$", message = "{pattern_check}", groups = ValidGroup3.class)
	private String password;
	
	@NotBlank(message = "{require_check}", groups = ValidGroup1.class)
	private String userName;
	
	@NotNull(message = "{require_check}", groups = ValidGroup1.class)
	@DateTimeFormat(pattern="yyyy/MM/dd")
	private Date birthday;
	
	@Min(value = 20, message = "{min_check}", groups = ValidGroup2.class)
	@Max(value = 100, message = "{max_check}", groups = ValidGroup2.class)
	private int age;
	
	@AssertFalse(message = "{false_check}", groups = ValidGroup2.class)
	private boolean marriage;
	
	public String getUserId() {
		return this.userId;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getUserName() {
		return this.userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public Date getBirthday() {
		return this.birthday;
	}
	
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public int getAge() {
		return this.age;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	
	public boolean getMarriage() {
		return this.marriage;
	}
	
	public void setMarriage(boolean marriage) {
		this.marriage = marriage;
	}
}